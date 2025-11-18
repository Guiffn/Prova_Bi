package Questao3;

public class EstadoManutencao implements EstadoUsina {
    @Override
    public void validarTransicao(ContextoUsina contexto) {
        System.out.println("Usina em MANUTENÇÃO: transições normais suspensas.");
    }
    @Override
    public String getNome() { return "MANUTENCAO"; }
}