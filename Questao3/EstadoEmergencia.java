package Questao3;

public class EstadoEmergencia implements EstadoUsina {
    @Override
    public void validarTransicao(ContextoUsina contexto) {
        System.out.println("Estado EMERGENCIA: não é possível sair sem intervenção manual!");
    }
    @Override
    public String getNome() { return "EMERGENCIA"; }
}
