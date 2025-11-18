package Questao4;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;


 
public class CadeiaValidacao {
    private final List<Validador> ordem = new ArrayList<>();
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    // Mapa de dependências/condicionais: se X falhar, pular Y.
    private final ConcurrentMap<String, Set<String>> puloCondicional = new ConcurrentHashMap<>();

    // Pilha de validadores que efetuaram mudanças e precisam de rollback se houver falha posterior
    private final List<Validador> trilhaRollback = new ArrayList<>();

    public CadeiaValidacao adicionar(Validador validador) {
        ordem.add(validador);
        return this;
    }

    public CadeiaValidacao pularSeFalhar(String nomeFalhou, Set<String> nomesASeremPulados) {
        puloCondicional.put(nomeFalhou, nomesASeremPulados);
        return this;
    }

    public List<ResultadoValidacao> executar(DocumentoNFe nfe, ContextoValidacao ctx) {
        List<ResultadoValidacao> resultados = new ArrayList<>();
        int falhasConsecutivas = 0;
        boolean anteriorFalhou = false;
        String ultimoFalhou = null;

        try {
            for (Validador v : ordem) {
                // Condicional explícita: se o anterior falhou e há regra para pular este, pule.
                if (anteriorFalhou && ultimoFalhou != null) {
                    Set<String> pulos = puloCondicional.get(ultimoFalhou);
                    if (pulos != null && pulos.contains(v.getNome())) {
                        resultados.add(ResultadoValidacao.ok("Pulado por falha anterior em " + ultimoFalhou + ": " + v.getNome()));
                        continue;
                    }
                }

                // Regra global: validadores 3 (Fiscais) e 5 (SEFAZ) só executam se anteriores passarem.
                if (!v.deveExecutar(nfe, ctx)) {
                    resultados.add(ResultadoValidacao.ok("Pulado por regra de dependência: " + v.getNome()));
                    continue;
                }

                Duration timeout = ctx.obterTimeout(v.getNome());
                Future<ResultadoValidacao> future = executor.submit(() -> v.validar(nfe, ctx));

                ResultadoValidacao res;
                try {
                    res = future.get(timeout.toMillis(), TimeUnit.MILLISECONDS);
            } catch (TimeoutException te) {
                    future.cancel(true);
                    res = ResultadoValidacao.falha("Timeout em " + v.getNome() + " após " + timeout.toMillis() + " ms");
            } catch (ExecutionException ee) {
                    res = ResultadoValidacao.falha("Exceção em " + v.getNome() + ": " + ee.getCause().getMessage());
            } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt(); // boa prática: reinterromper a thread
                    res = ResultadoValidacao.falha("Execução interrompida em " + v.getNome());
            }

                resultados.add(res);

                if (res.isSucesso()) {
                    anteriorFalhou = false;
                    ultimoFalhou = null;
                    // Se este validador modificou estado, registrá-lo para possível rollback
                    trilhaRollback.add(v);
                } else {
                    anteriorFalhou = true;
                    ultimoFalhou = v.getNome();
                    falhasConsecutivas++;

                    // Circuit Breaker: interrompe após limite de falhas
                    if (falhasConsecutivas >= ctx.getLimiteFalhasCircuitBreaker()) {
                        resultados.add(ResultadoValidacao.falha("Circuit breaker acionado após " + falhasConsecutivas + " falhas."));
                        // Executa rollback de todos que alteraram estado desde o último sucesso
                        executarRollback(nfe, ctx);
                        break;
                    }
                    // Em caso de qualquer falha, dispara rollback dos modificadores subsequentes (garantindo consistência)
                    executarRollback(nfe, ctx);
                }
            }
        } finally {
            trilhaRollback.clear();
            // Mantém executor reutilizável; em app real, teria ciclo de vida controlado
        }

        return resultados;
    }

    private void executarRollback(DocumentoNFe nfe, ContextoValidacao ctx) {
        // Executa rollback na ordem inversa de aplicação
        for (int i = trilhaRollback.size() - 1; i >= 0; i--) {
            try {
                trilhaRollback.get(i).rollback(nfe, ctx);
            } catch (Exception e) {
                // log real omitido; aqui mantemos robustez
            }
        }
        trilhaRollback.clear();
    }
}