package Questao4;


import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class ContextoValidacao {
    private final Map<String, Duration> timeoutsPorValidador = new HashMap<>();
    private int limiteFalhasCircuitBreaker = 3;

    public void definirTimeout(String nomeValidador, Duration timeout) {
        timeoutsPorValidador.put(nomeValidador, timeout);
    }
    public Duration obterTimeout(String nomeValidador) {
        return timeoutsPorValidador.getOrDefault(nomeValidador, Duration.ofSeconds(5));
    }

    public int getLimiteFalhasCircuitBreaker() { return limiteFalhasCircuitBreaker; }
    public void setLimiteFalhasCircuitBreaker(int limiteFalhasCircuitBreaker) {
        this.limiteFalhasCircuitBreaker = limiteFalhasCircuitBreaker;
    }
}