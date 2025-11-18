

// Algoritmo Expected Shortfall
public class AlgoritmoExpectedShortfall implements AlgoritmoRisco {
    @Override
    public String calcular(ContextoRisco contexto) {
        return String.format(
            "[ES] Carteira=%s, Confiança=%s, Data=%s -> Resultado: Expected Shortfall Dummy.",
            contexto.obterIdCarteira(),
            contexto.obterNivelConfianca(),
            contexto.obterDataReferencia()
        );
    }
}