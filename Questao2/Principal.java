package Questao2;

public class Principal {
    public static void main(String[] args) {
        SistemaBancarioLegado legado = new SistemaBancarioLegado();
        ProcessadorTransacoes processador = new AdaptadorProcessadorTransacoes(legado);

        RespostaTransacao resposta1 = processador.autorizar("1234-5678", 500.0, "USD");
        System.out.println("Autorizado: " + resposta1.isAutorizado() + " | Mensagem: " + resposta1.getMensagem());

        RespostaTransacao resposta2 = processador.autorizar("9876-5432", 2000.0, "BRL");
        System.out.println("Autorizado: " + resposta2.isAutorizado() + " | Mensagem: " + resposta2.getMensagem());
    }
}
