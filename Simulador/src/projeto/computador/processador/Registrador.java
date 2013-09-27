package projeto.computador.processador;

abstract class Registrador<T extends Number> {
	private String alias;
	
	Registrador(String alias) {
		this.alias = alias;
	}
	
	abstract void setPalavra(T palavra);
	
	abstract T getPalavra();
	
	@Override
	public String toString() {
		return alias +": " + getPalavra();
	}
}
