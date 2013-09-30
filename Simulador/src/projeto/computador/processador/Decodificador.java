package projeto.computador.processador;

class Decodificador {
	private boolean logicoAritmetica;
	private boolean isMov;
	private byte opcode;

	private Short end1 = null;
	private Short end2 = null;
	
	private Short end1Val = null;
	private Short end2Val = null;
	
	private Short dado = null;

	private Byte reg1 = null;
	private Byte reg2 = null;

	private int[] flags;
	private boolean precisaBusca = false;

	Decodificador(int instrucao) {
		parse(instrucao);
	}

	private void parse(int instrucao) {
		logicoAritmetica = instrucao >> 14 == 1;
		if (logicoAritmetica) {
			opcode = (byte) ((instrucao >> 9) & 0b111);
			reg1 = (byte) ((instrucao >> 2) & 0b11);
			reg2 = (byte) (instrucao & 0b11);
		} else {
			isMov = ((instrucao >> 13) & 0b11) == 1;
			if (isMov) {
				precisaBusca = true;
				opcode = (byte) ((instrucao >> 10) & 0b111);
				switch (opcode) {
				//0: Registrador
				//1: Memória
				//2: Constante
				//Forma: {_ -> _}, ou MOV {}[1], {}[0]
				case 0:
					reg1 = (byte) (instrucao & 0b11);
					flags = new int[] { 0, 1 };
					break;
				case 1:
					reg1 = (byte) (instrucao & 0b11);
					flags = new int[] { 1, 0 };
					break;
				case 2:
					reg1 = (byte) ((instrucao >> 2) & 0b11);
					reg2 = (byte) (instrucao & 0b11);
					flags = new int[] { 0, 0 };
					break;
				case 3:
					flags = new int[] { 2, 1 };
					break;
				case 4:
					reg1 = (byte) (instrucao & 0b11);
					flags = new int[] { 2, 0 };
					break;
				}
			} else {
				opcode = (byte) ((instrucao >> 10) & 0b111);
				if (opcode == 1) {
					precisaBusca = true;
					flags = new int[] { 0 };
				}
			}
		}
	}

	boolean isLogicoAritmetica() {
		return logicoAritmetica;
	}
	
	boolean precisaBusca() {
		return precisaBusca;
	}

	int[] getFlags() {
		return flags;
	}

	byte getOpcode() {
		return opcode;
	}

	Byte getReg1() {
		return reg1;
	}
	
	Byte getReg2() {
		return reg2;
	}

	Short getEnd1() {
		return end1;
	}

	Short getEnd2() {
		return end2;
	}
	
	Short getEnd1Val() {
		return end1Val;
	}

	Short getEnd2Val() {
		return end2Val;
	}
	
	boolean isMov() {
		return isMov;
	}

	Short getDado() {
		return dado;
	}
	
	void setEnd1(Short end1) {
		this.end1 = end1;
	}
	
	void setEnd2(Short end2) {
		this.end2 = end2;
	}
	
	void setEnd1Val(Short end1Val) {
		this.end1Val = end1Val;
	}
	
	void setEnd2Val(Short end2Val) {
		this.end2Val = end2Val;
	}
	
	void setDado(Short dado) {
		this.dado = dado;
	}
}