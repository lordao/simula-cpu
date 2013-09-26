package projeto.computador.processador;

public class Registrador32 extends Registrador<Integer> {
	private Integer palavra;
	
	Registrador32(String alias) {
		super(alias);
	}

	@Override
	void setPalavra(Integer palavra) {
		this.palavra = palavra;
	}

	@Override
	Integer getPalavra() {
		return palavra;
	}

}
