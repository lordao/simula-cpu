package projeto.computador.processador;

import projeto.computador.Memoria;

class Registrador {
	private String alias;
	private int palavra;
	
	Registrador(String alias) {
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
		return "Registrador " + alias +": " + palavra;
	}
}
