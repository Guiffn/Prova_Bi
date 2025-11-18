package Questao3;
// Contexto que mantém o estado atual e condições da usina
public class ContextoUsina {
    private EstadoUsina estadoAtual;
    private double temperatura;
    private double pressao;
    private double radiacao;
    private boolean sistemaResfriamentoOk;
    private long tempoEmEstado; // em segundos

    public ContextoUsina(EstadoUsina estadoInicial) {
        this.estadoAtual = estadoInicial;
        this.tempoEmEstado = 0;
    }

    public void atualizarCondicoes(double temperatura, double pressao, double radiacao, boolean sistemaResfriamentoOk, long tempoEmEstado) {
        this.temperatura = temperatura;
        this.pressao = pressao;
        this.radiacao = radiacao;
        this.sistemaResfriamentoOk = sistemaResfriamentoOk;
        this.tempoEmEstado = tempoEmEstado;
    }

    public void setEstado(EstadoUsina novoEstado) {
        this.estadoAtual = novoEstado;
        this.tempoEmEstado = 0;
        System.out.println("Transição para estado: " + novoEstado.getNome());
    }

    public EstadoUsina getEstadoAtual() { return estadoAtual; }
    public double getTemperatura() { return temperatura; }
    public double getPressao() { return pressao; }
    public double getRadiacao() { return radiacao; }
    public boolean isSistemaResfriamentoOk() { return sistemaResfriamentoOk; }
    public long getTempoEmEstado() { return tempoEmEstado; }

    public void validarTransicao() {
        estadoAtual.validarTransicao(this);
    }
}