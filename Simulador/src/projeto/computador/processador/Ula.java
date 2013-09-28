package projeto.computador.processador;

class Ula {	
	private int oprd1;
	private int oprd2;
	private RegistradorEstado regUla;
	
	private int opcode;
	
	Ula() {
		regUla = new RegistradorEstado("Registrador de Estado (ULA)");
	}
	
	void setOprd1(int oprd) {
		this.oprd1 = oprd;
	}
	
	void setOprd2(int oprd) {
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
		
		int zero     = 1 & result,
			igual    = oprd1 == oprd2 ? 1 : 0;
		//IZ
		int palavra = igual;
		palavra = palavra << 1;
		palavra = palavra | zero;
		
		regUla.setPalavra((byte) palavra);
		
		return result;
	}
}
