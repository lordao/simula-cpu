package projeto.computador.processador;

import projeto.computador.Representacao;

public class RegistradorEstado extends Registrador<Byte> {
	private Byte palavra = 0;

	RegistradorEstado(String alias, Representacao representacao) {
		super(alias, representacao);
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
