

import java.math.BigDecimal;
import java.util.Arrays;

// Cliente
public class Principal {
    public static void main(String[] args) {
        ContextoRisco contexto = new ContextoRisco.Construtor()
            .seriePnL(Arrays.asList(
                BigDecimal.valueOf(-1000),
                BigDecimal.valueOf(500),
                BigDecimal.valueOf(-200),
                BigDecimal.valueOf(800)))
            .nivelConfianca(BigDecimal.valueOf(0.99))
            .horizonteDias(10)
            .fatorEstresse(BigDecimal.valueOf(1.5))
            .idCarteira("PF-123")
            .moeda("BRL")
            .construir();

        MotorRisco motor = new MotorRisco(FabricaAlgoritmoRisco.criar(TipoAlgoritmoRisco.VAR), contexto);
        System.out.println(motor.calcularRisco());

        motor.definirAlgoritmo(FabricaAlgoritmoRisco.criar(TipoAlgoritmoRisco.EXPECTED_SHORTFALL));
        System.out.println(motor.calcularRisco());

        motor.definirAlgoritmo(FabricaAlgoritmoRisco.criar(TipoAlgoritmoRisco.STRESS_TESTING));
        System.out.println(motor.calcularRisco());
    }
}