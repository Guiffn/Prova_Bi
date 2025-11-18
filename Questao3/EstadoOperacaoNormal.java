package Questao3;

public class EstadoOperacaoNormal implements EstadoUsina {
    @Override
    public void validarTransicao(ContextoUsina contexto) {
        if (contexto.getTemperatura() > 300) {
            contexto.setEstado(new EstadoAlertaAmarelo());
        }
    }
    @Override
    public String getNome() { return "OPERACAO_NORMAL"; }
}