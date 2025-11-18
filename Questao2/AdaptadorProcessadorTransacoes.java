package Questao2;

import java.util.HashMap;

class AdaptadorProcessadorTransacoes implements ProcessadorTransacoes {
    private SistemaBancarioLegado legado;

    public AdaptadorProcessadorTransacoes(SistemaBancarioLegado legado) {
        this.legado = legado;
    }

    @Override
    public RespostaTransacao autorizar(String cartao, double valor, String moeda) {
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("cartao", cartao);
        parametros.put("valor", valor);
        parametros.put("moeda", converterMoeda(moeda));

        // Campo obrigatório do legado que não existe na interface moderna
        parametros.put("canalOrigem", "INTERNET"); 

        HashMap<String, Object> respostaLegado = legado.processarTransacao(parametros);

        // Conversão da resposta legado para formato moderno
        String status = (String) respostaLegado.get("status");
        boolean autorizado = "APROVADO".equals(status);
        String mensagem = "Código: " + respostaLegado.get("codigo") + " - " + status;

        return new RespostaTransacao(autorizado, mensagem);
    }
     private int converterMoeda(String moeda) {
        switch (moeda) {
            case "USD": return 1;
            case "EUR": return 2;
            case "BRL": return 3;
            default: throw new IllegalArgumentException("Moeda não suportada: " + moeda);
        }
    }
}

