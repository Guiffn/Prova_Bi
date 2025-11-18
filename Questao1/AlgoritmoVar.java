

// Algoritmo VaR
public class AlgoritmoVar implements AlgoritmoRisco {
    @Override
    public String calcular(ContextoRisco contexto) {
        return String.format(
            "[VaR] Carteira=%s, Confiança=%s, Horizonte=%d dias, Moeda=%s -> Resultado: VaR Dummy.",
            contexto.obterIdCarteira(),
            contexto.obterNivelConfianca(),
            contexto.obterHorizonteDias(),
            contexto.obterMoeda()
        );
    }
}