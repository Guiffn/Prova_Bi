package Questao3;

public class Principal {
    public static void main(String[] args) {
        ContextoUsina usina = new ContextoUsina(new EstadoOperacaoNormal());

        usina.atualizarCondicoes(350, 100, 5, true, 10);
        usina.validarTransicao(); // Vai para ALERTA_AMARELO

        usina.atualizarCondicoes(410, 120, 6, true, 40);
        usina.validarTransicao(); // Vai para ALERTA_VERMELHO

        usina.atualizarCondicoes(410, 120, 6, false, 50);
        usina.validarTransicao(); // Vai para EMERGENCIA

        usina.setEstado(new EstadoManutencao());
        usina.validarTransicao(); // Suspende transições
    }
}