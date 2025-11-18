package Questao3;
public class EstadoDesligada implements EstadoUsina {
    @Override
    public void validarTransicao(ContextoUsina contexto) {
        if (contexto.getTemperatura() < 100) {
            contexto.setEstado(new EstadoOperacaoNormal());
        }
    }
    @Override
    public String getNome() { return "DESLIGADA"; }
}