package Questao4.Validadores;


import Questao4.*; 

public class ValidadorSchemaXml extends ValidadorBase {
    public ValidadorSchemaXml() { super("ValidadorSchemaXml"); }

    @Override
    public ResultadoValidacao validar(DocumentoNFe nfe, ContextoValidacao ctx) throws Exception {
        // Dummy: checa se XML contém uma tag esperada
        boolean valido = nfe.getXml() != null && nfe.getXml().contains("<NFe>");
        nfe.setSchemaValido(valido);
        if (valido) return ResultadoValidacao.ok("Schema XML válido.");
        return ResultadoValidacao.falha("Schema XML inválido.");
    }
}