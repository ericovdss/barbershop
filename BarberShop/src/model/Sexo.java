package model;

public enum Sexo {
	
	VAZIO(""), MASCULINO("Masculino"), FEMININO("Feminino");
	
	private final String text;
	
    /**
     * @param text
     */
    Sexo(final String text) {
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
