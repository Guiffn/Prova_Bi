package Questao4;

public abstract class ValidadorBase implements Validador {
    protected final String nome;

    protected ValidadorBase(String nome) { this.nome = nome; }

    @Override public String getNome() { return nome; }

    @Override
    public boolean deveExecutar(DocumentoNFe nfe, ContextoValidacao ctx) {
        // Por padrão, executa sempre. Validadores específicos podem sobreescrever.
        return true;
    }

    @Override
    public void rollback(DocumentoNFe nfe, ContextoValidacao ctx) {
        // Por padrão, nenhum efeito; validadores que alteram estado devem sobreescrever.
    }
}
