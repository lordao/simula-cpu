package projeto.computador.processador;

import projeto.computador.Memoria;

public class Registrador16 extends Registrador<Integer> {
	private Integer palavra;
	
	Registrador16(String alias) {
		super(alias);
		palavra = 0;
	}

	@Override
	void setPalavra(Integer palavra) {
		this.palavra = palavra & (Memoria.TAMANHO_PALAVRA - 1);
	}

	@Override
	Integer getPalavra() {
		return palavra;
	}
	
	
}
