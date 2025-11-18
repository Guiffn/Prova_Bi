package Questao2;
import java.util.HashMap;


class SistemaBancarioLegado {
    public HashMap<String, Object> processarTransacao(HashMap<String, Object> parametros) {
        HashMap<String, Object> resposta = new HashMap<>();
        // Simulação de regra simples
        if ((double) parametros.get("valor") > 1000) {
            resposta.put("status", "NEGADO");
            resposta.put("codigo", 401);
        } else {
            resposta.put("status", "APROVADO");
            resposta.put("codigo", 200);
        }
        return resposta;
    }
}
