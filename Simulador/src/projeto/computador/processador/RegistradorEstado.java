package projeto.computador.processador;

public class RegistradorEstado extends Registrador<Byte> {
	private Byte palavra;

	RegistradorEstado(String alias) {
		super(alias);
	}

	@Override
	void setPalavra(Byte palavra) {
		this.palavra = palavra;
	}

	@Override
	Byte getPalavra() {
		return palavra;
	}

}
