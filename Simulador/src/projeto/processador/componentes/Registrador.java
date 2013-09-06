package projeto.processador.componentes;

public class Registrador {
	private String alias;
	private int palavra;
	
	public Registrador(String alias) {
		this.alias = alias;
		palavra = 0;
	}
	
	public void setPalavra(int palavra) {
		this.palavra = palavra % Memoria.TAMANHO_PALAVRA;
	}
	
	public int getPalavra() {
		return palavra;
	}
	
	@Override
	public String toString() {
		return new StringBuilder().append("Registrador ").append(alias).append(": ").append(palavra).toString();
	}
}
