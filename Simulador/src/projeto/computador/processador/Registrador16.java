package projeto.computador.processador;

public class Registrador16 extends Registrador<Short> {
	private Short palavra;
	
	Registrador16(String alias) {
		super(alias);
	}

	@Override
	void setPalavra(Short palavra) {
		this.palavra = palavra;
	}
	
	void setPalavra(Integer palavra) {
		this.palavra = palavra.shortValue();
	}

	@Override
	Short getPalavra() {
		return palavra;
	}
	
	
}
