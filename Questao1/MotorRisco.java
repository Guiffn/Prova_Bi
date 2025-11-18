
// Engine em português
public class MotorRisco {
    private AlgoritmoRisco algoritmo;
    private ContextoRisco contexto;

    public MotorRisco(AlgoritmoRisco algoritmo, ContextoRisco contexto) {
        this.algoritmo = algoritmo;
        this.contexto = contexto;
    }

    public void definirAlgoritmo(AlgoritmoRisco algoritmo) {
        if (algoritmo == null) throw new IllegalArgumentException("Algoritmo não pode ser nulo.");
        this.algoritmo = algoritmo;
    }

    public void definirContexto(ContextoRisco contexto) {
        if (contexto == null) throw new IllegalArgumentException("Contexto não pode ser nulo.");
        this.contexto = contexto;
    }

    public String calcularRisco() {
        return algoritmo.calcular(contexto);
    }
}