package Questao4;

public class ResultadoValidacao {
    private final boolean sucesso;
    private final String mensagem;

    public static ResultadoValidacao ok(String msg) { return new ResultadoValidacao(true, msg); }
    public static ResultadoValidacao falha(String msg) { return new ResultadoValidacao(false, msg); }

    private ResultadoValidacao(boolean sucesso, String mensagem) {
        this.sucesso = sucesso;
        this.mensagem = mensagem;
    }

    public boolean isSucesso() { return sucesso; }
    public String getMensagem() { return mensagem; }
}