package Questao3;
public class EstadoAlertaAmarelo implements EstadoUsina {
    @Override
    public void validarTransicao(ContextoUsina contexto) {
        if (contexto.getTemperatura() > 400 && contexto.getTempoEmEstado() > 30) {
            contexto.setEstado(new EstadoAlertaVermelho());
        } else if (contexto.getTemperatura() <= 300) {
            contexto.setEstado(new EstadoOperacaoNormal());
        }
    }
    @Override
    public String getNome() { return "ALERTA_AMARELO"; }
}