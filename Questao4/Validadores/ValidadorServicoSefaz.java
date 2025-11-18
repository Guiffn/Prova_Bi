package Questao4.Validadores;

import Questao4.*; 


public class ValidadorServicoSefaz extends ValidadorBase {
    public ValidadorServicoSefaz() { super("ValidadorServicoSefaz"); }

    @Override
    public boolean deveExecutar(DocumentoNFe nfe, ContextoValidacao ctx) {
        // Executa somente se schema, certificado e (opcionalmente) regras fiscais passaram
        return nfe.isSchemaValido() && nfe.isCertificadoValido() && nfe.isRegrasCalculadas();
    }

    @Override
    public ResultadoValidacao validar(DocumentoNFe nfe, ContextoValidacao ctx) throws Exception {
        // Dummy: consulta ok
        nfe.setConsultaSefazOk(true);
        return ResultadoValidacao.ok("Consulta SEFAZ aprovada.");
    }
}