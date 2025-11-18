package Questao4.Validadores;


import Questao4.*; 


public class ValidadorRegrasFiscais extends ValidadorBase {
    public ValidadorRegrasFiscais() { super("ValidadorRegrasFiscais"); }

    @Override
    public boolean deveExecutar(DocumentoNFe nfe, ContextoValidacao ctx) {
        // Executa somente se schema, certificado e banco passaram
        return nfe.isSchemaValido() && nfe.isCertificadoValido();
    }

    @Override
    public ResultadoValidacao validar(DocumentoNFe nfe, ContextoValidacao ctx) throws Exception {
        // Dummy: calculou impostos com sucesso
        nfe.setRegrasCalculadas(true);
        return ResultadoValidacao.ok("Regras fiscais calculadas.");
    }
}
