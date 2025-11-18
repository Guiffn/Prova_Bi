package Questao4;

public interface Validador {
    String getNome();
    boolean deveExecutar(DocumentoNFe nfe, ContextoValidacao ctx);
    ResultadoValidacao validar(DocumentoNFe nfe, ContextoValidacao ctx) throws Exception;
    void rollback(DocumentoNFe nfe, ContextoValidacao ctx);
}
