package model;

public enum NivelAcesso {
	
	VAZIO(""), ADMINISTRADOR("Administrador"), FUNCIONARIO("Funcionário");
	
	private final String text;
	
    /**
     * @param text
     */
    NivelAcesso(final String text) {
        this.text = text;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return text;
    }
}
