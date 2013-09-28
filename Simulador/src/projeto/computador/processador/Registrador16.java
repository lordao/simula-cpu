package projeto.computador.processador;


public class Registrador16 extends Registrador<Short> {
	private Short palavra;
	
	Registrador16(String alias) {
		super(alias);
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
