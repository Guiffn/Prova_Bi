package Questao4.Validadores;
import Questao4.*;

import java.time.Instant;

/**
 * Validador 2: Certificado Digital (expiração/revogação).
 */
public class ValidadorCertificadoDigital extends ValidadorBase {
    public ValidadorCertificadoDigital() { super("ValidadorCertificadoDigital"); }

    @Override
    public ResultadoValidacao validar(DocumentoNFe nfe, ContextoValidacao ctx) throws Exception {
        // Dummy: válido se data de emissão não for no futuro
        boolean valido = nfe.getDataEmissao().isBefore(Instant.now());
        nfe.setCertificadoValido(valido);
        if (valido) return ResultadoValidacao.ok("Certificado válido.");
        return ResultadoValidacao.falha("Certificado expirado/revogado.");
    }
}