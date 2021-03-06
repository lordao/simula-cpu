package projeto.computador.processador;

import projeto.computador.Representacao;

class Ula {	
	private short oprd1;
	private short oprd2;
	private RegistradorEstado regUla;
	
	private int opcode;
	
	Ula(Representacao representacao) {
		regUla = new RegistradorEstado("Registrador de Estado (ULA)", representacao);
	}
	
	void setOprd1(short oprd) {
		this.oprd1 = oprd;
	}
	
	void setOprd2(short oprd) {
		this.oprd2 = oprd;
	}
	
	void setOperacao(int opcode) {
		this.opcode = opcode;
	}
	
	byte getEstado() {
		return regUla.getPalavra();
	}
	
	int executar() {
		int result = 0;
		switch (opcode) {
		case 00:
			result = oprd1 + oprd2;
			break;
		case 01:
			result = oprd1 - oprd2;
			break;
		case 02:
			result = oprd1 * oprd2;
			break;
		case 03:
			result = oprd1 / oprd2;
			break;
		case 04:
			result = ~oprd1;
			break;
		case 05:
			result = oprd1 & oprd2;
			break;
		case 06:
			result = oprd1 | oprd2;
			break;
		case 07:
			result = oprd1 ^ oprd2;
			break;
		}
		
		int zero     = result == 0 ? 1 : 0,
			negativo = result >>> 31,
			igual    = oprd1 == oprd2 ? 1 : 0;
		//INZ
		int palavra = igual;
		palavra = palavra << 1;
		palavra = palavra | negativo;
		palavra = palavra << 1;
		palavra = palavra | zero;
		
		regUla.setPalavra((byte) palavra);
		
		return result;
	}
}
