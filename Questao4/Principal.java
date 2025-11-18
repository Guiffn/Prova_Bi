package Questao4;
import Questao4.Validadores.*;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Set;

/**
 * Documentação de design:
 * - Chain of Responsibility para composição da pipeline de validadores.
 * - Strategy para cada validador especializado.
 * - Circuit Breaker (limite de 3 falhas) no orquestrador.
 * - Timeout individual por validador via ContextoValidacao.
 * - Rollback implementado nos validadores que alteram estado (BD).
 * SOLID: SRP (classes focadas), OCP (adicionar novos validadores sem mudar os existentes),
 * LSP (todos implementam Validador), ISP (interface mínima), DIP (cadeia depende de Validador).
 */
public class Principal {
    public static void main(String[] args) {
        DocumentoNFe nfe = new DocumentoNFe(
                "<NFe><infNFe>...</infNFe></NFe>",
                "35191111111111111111550010000000011000000011",
                "000000001",
                Instant.now().minusSeconds(60));

        ContextoValidacao ctx = new ContextoValidacao();
        // Timeouts específicos
        ctx.definirTimeout("ValidadorSchemaXml", Duration.ofSeconds(2));
        ctx.definirTimeout("ValidadorCertificadoDigital", Duration.ofSeconds(2));
        ctx.definirTimeout("ValidadorBancoDados", Duration.ofSeconds(1));
        ctx.definirTimeout("ValidadorRegrasFiscais", Duration.ofSeconds(3));
        ctx.definirTimeout("ValidadorServicoSefaz", Duration.ofSeconds(5));
        ctx.setLimiteFalhasCircuitBreaker(3);

        CadeiaValidacao cadeia = new CadeiaValidacao()
                .adicionar(new ValidadorSchemaXml())
                .adicionar(new ValidadorCertificadoDigital())
                .adicionar(new ValidadorBancoDados())     // 4 antes de 3 para permitir rollback se 3/5 falharem
                .adicionar(new ValidadorRegrasFiscais())  // 3 só executa se anteriores passarem
                .adicionar(new ValidadorServicoSefaz());  // 5 só executa se anteriores passarem

        // Regras condicionais: se Schema falhar, pular Certificado e subsequentes.
        cadeia.pularSeFalhar("ValidadorSchemaXml",
                Set.of("ValidadorCertificadoDigital", "ValidadorBancoDados", "ValidadorRegrasFiscais", "ValidadorServicoSefaz"));
        // Se Certificado falhar, pular Banco, Fiscais e SEFAZ
        cadeia.pularSeFalhar("ValidadorCertificadoDigital",
                Set.of("ValidadorBancoDados", "ValidadorRegrasFiscais", "ValidadorServicoSefaz"));

        List<ResultadoValidacao> resultados = cadeia.executar(nfe, ctx);

        System.out.println("Resultados da validação:");
        for (ResultadoValidacao r : resultados) {
            System.out.println(" - " + (r.isSucesso() ? "OK" : "FALHA") + ": " + r.getMensagem());
        }
    }
}