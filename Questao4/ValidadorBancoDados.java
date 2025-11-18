package Questao4;


import java.util.HashSet;
import java.util.Set;


public class ValidadorBancoDados extends ValidadorBase {
    // Simulação de banco: conjunto de números já usados
    private static final Set<String> numerosUsados = new HashSet<>();

    public ValidadorBancoDados() { super("ValidadorBancoDados"); }

    @Override
    public boolean deveExecutar(DocumentoNFe nfe, ContextoValidacao ctx) {
        // Executa somente se 1 e 2 passaram
        return nfe.isSchemaValido() && nfe.isCertificadoValido();
    }

    @Override
    public ResultadoValidacao validar(DocumentoNFe nfe, ContextoValidacao ctx) throws Exception {
        String numero = nfe.getNumero();
        if (numerosUsados.contains(numero)) {
            return ResultadoValidacao.falha("Número NF-e duplicado no banco.");
        }
        // Reserva a numeração (modifica estado do sistema)
        numerosUsados.add(numero);
        nfe.getEstado().put("reservaInserida", true);
        return ResultadoValidacao.ok("Número NF-e reservado no banco.");
    }

    @Override
    public void rollback(DocumentoNFe nfe, ContextoValidacao ctx) {
        // Se reserva foi inserida, remover (rollback)
        Object reserva = nfe.getEstado().get("reservaInserida");
        if (Boolean.TRUE.equals(reserva)) {
            numerosUsados.remove(nfe.getNumero());
            nfe.getEstado().put("reservaInserida", false);
        }
    }
}