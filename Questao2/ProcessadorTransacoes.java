package Questao2;

interface ProcessadorTransacoes {
    RespostaTransacao autorizar(String cartao, double valor, String moeda);
}

