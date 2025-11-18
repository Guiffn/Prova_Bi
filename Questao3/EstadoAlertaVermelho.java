package Questao3;


public class EstadoAlertaVermelho implements EstadoUsina {
    @Override
    public void validarTransicao(ContextoUsina contexto) {
        if (!contexto.isSistemaResfriamentoOk()) {
            contexto.setEstado(new EstadoEmergencia());
        } else if (contexto.getTemperatura() <= 300) {
            contexto.setEstado(new EstadoOperacaoNormal());
        }
    }
    @Override
    public String getNome() { return "ALERTA_VERMELHO"; }
}