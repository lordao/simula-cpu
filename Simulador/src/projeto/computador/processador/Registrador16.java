package projeto.computador.processador;

import projeto.computador.Representacao;


public class Registrador16 extends Registrador<Short> {
	private Short palavra;
	
	Registrador16(String alias, Representacao representacao) {
		super(alias, representacao);
		palavra = 0;
	}

	@Override
	void setPalavra(Short palavra) {
		this.palavra = palavra;
	}

	@Override
	Short getPalavra() {
		return palavra;
	}
	
	
}
