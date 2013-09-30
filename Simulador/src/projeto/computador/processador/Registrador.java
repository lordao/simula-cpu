package projeto.computador.processador;

import projeto.computador.Representacao;

abstract class Registrador<T extends Number> {
	private String alias;
	private Representacao representacao;

	Registrador(String alias, Representacao representacao) {
		this.alias = alias;
		this.representacao = representacao;
	}

	abstract void setPalavra(T palavra);

	abstract T getPalavra();

	@Override
	public String toString() {
		switch (representacao) {
		case Binaria:
			return toBinaryString();
		case Hexadecimal:
			return toHexString();
		default:
			return toDecimalString();
		}
	}

	public String toDecimalString() {
		return alias + ": " + getPalavra();
	}

	public String toBinaryString() {
		return alias + ": " + String.format("(%04d)_16", getPalavra());
	}

	public String toHexString() {
		return alias + ": " + String.format("(%04x)_16", getPalavra());
	}
}
