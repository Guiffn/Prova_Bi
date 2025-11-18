package Questao4;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;


public class DocumentoNFe {
    private String xml;
    private String chaveAcesso;
    private String numero;
    private Instant dataEmissao;
    private boolean certificadoValido;
    private boolean schemaValido;
    private boolean regrasCalculadas;
    private boolean consultaSefazOk;

    private final Map<String, Object> estado = new HashMap<>();

    public DocumentoNFe(String xml, String chaveAcesso, String numero, Instant dataEmissao) {
        this.xml = xml;
        this.chaveAcesso = chaveAcesso;
        this.numero = numero;
        this.dataEmissao = dataEmissao;
    }

    public String getXml() { return xml; }
    public String getChaveAcesso() { return chaveAcesso; }
    public String getNumero() { return numero; }
    public Instant getDataEmissao() { return dataEmissao; }

    public boolean isCertificadoValido() { return certificadoValido; }
    public void setCertificadoValido(boolean certificadoValido) { this.certificadoValido = certificadoValido; }

    public boolean isSchemaValido() { return schemaValido; }
    public void setSchemaValido(boolean schemaValido) { this.schemaValido = schemaValido; }

    public boolean isRegrasCalculadas() { return regrasCalculadas; }
    public void setRegrasCalculadas(boolean regrasCalculadas) { this.regrasCalculadas = regrasCalculadas; }

    public boolean isConsultaSefazOk() { return consultaSefazOk; }
    public void setConsultaSefazOk(boolean consultaSefazOk) { this.consultaSefazOk = consultaSefazOk; }

    public Map<String, Object> getEstado() { return estado; }
}