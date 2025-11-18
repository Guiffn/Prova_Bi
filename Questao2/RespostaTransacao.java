package Questao2;

class RespostaTransacao {
    private boolean autorizado;
    private String mensagem;

    public RespostaTransacao(boolean autorizado, String mensagem) {
        this.autorizado = autorizado;
        this.mensagem = mensagem;
    }

    public boolean isAutorizado() { return autorizado; }
    public String getMensagem() { return mensagem; }
}

