
// Factory em português
public class FabricaAlgoritmoRisco {
    public static AlgoritmoRisco criar(TipoAlgoritmoRisco tipo) {
        switch (tipo) {
            case VAR:
                return new AlgoritmoVar();
            case EXPECTED_SHORTFALL:
                return new AlgoritmoExpectedShortfall();
            case STRESS_TESTING:
                return new AlgoritmoStressTesting();
            default:
                throw new IllegalArgumentException("Tipo não suportado: " + tipo);
        }
    }
}