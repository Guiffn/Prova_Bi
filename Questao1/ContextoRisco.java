

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

// ============================================================================
// Contexto compartilhado entre algoritmos (Builder Pattern)
// ============================================================================
public class ContextoRisco {
    private final List<BigDecimal> seriePnL;
    private final BigDecimal nivelConfianca;
    private final int horizonteDias;
    private final LocalDate dataReferencia;
    private final BigDecimal fatorEstresse;
    private final String idCarteira;
    private final String moeda;

    private ContextoRisco(Construtor construtor) {
        this.seriePnL = construtor.seriePnL;
        this.nivelConfianca = construtor.nivelConfianca;
        this.horizonteDias = construtor.horizonteDias;
        this.dataReferencia = construtor.dataReferencia;
        this.fatorEstresse = construtor.fatorEstresse;
        this.idCarteira = construtor.idCarteira;
        this.moeda = construtor.moeda;
    }

    public List<BigDecimal> obterSeriePnL() { return seriePnL; }
    public BigDecimal obterNivelConfianca() { return nivelConfianca; }
    public int obterHorizonteDias() { return horizonteDias; }
    public LocalDate obterDataReferencia() { return dataReferencia; }
    public BigDecimal obterFatorEstresse() { return fatorEstresse; }
    public String obterIdCarteira() { return idCarteira; }
    public String obterMoeda() { return moeda; }

    // Builder em português
    public static class Construtor {
        private List<BigDecimal> seriePnL;
        private BigDecimal nivelConfianca = BigDecimal.valueOf(0.95);
        private int horizonteDias = 10;
        private LocalDate dataReferencia = LocalDate.now();
        private BigDecimal fatorEstresse = BigDecimal.ONE;
        private String idCarteira = "PADRAO";
        private String moeda = "BRL";

        public Construtor seriePnL(List<BigDecimal> seriePnL) {
            this.seriePnL = seriePnL;
            return this;
        }

        public Construtor nivelConfianca(BigDecimal nivelConfianca) {
            this.nivelConfianca = nivelConfianca;
            return this;
        }

        public Construtor horizonteDias(int horizonteDias) {
            this.horizonteDias = horizonteDias;
            return this;
        }

        public Construtor dataReferencia(LocalDate dataReferencia) {
            this.dataReferencia = dataReferencia;
            return this;
        }

        public Construtor fatorEstresse(BigDecimal fatorEstresse) {
            this.fatorEstresse = fatorEstresse;
            return this;
        }

        public Construtor idCarteira(String idCarteira) {
            this.idCarteira = idCarteira;
            return this;
        }

        public Construtor moeda(String moeda) {
            this.moeda = moeda;
            return this;
        }

        public ContextoRisco construir() {
            if (seriePnL == null || seriePnL.isEmpty()) {
                throw new IllegalArgumentException("A série de PnL é obrigatória.");
            }
            return new ContextoRisco(this);
        }
    }
}