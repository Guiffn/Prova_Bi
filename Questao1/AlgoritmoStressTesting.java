

// Algoritmo Stress Testing
public class AlgoritmoStressTesting implements AlgoritmoRisco {
    @Override
    public String calcular(ContextoRisco contexto) {
        return String.format(
            "[Stress] Carteira=%s, Fator=%s -> Resultado: Stress Testing Dummy.",
            contexto.obterIdCarteira(),
            contexto.obterFatorEstresse()
        );
    }
}