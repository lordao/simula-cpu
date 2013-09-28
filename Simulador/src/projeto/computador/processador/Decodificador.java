package projeto.computador.processador;

class Decodificador {
	private boolean logicoAritmetica;
	private boolean isMov;
	private byte opcode;

	private Integer end1 = null;
	private Integer end2 = null;
	
	private Integer end1Val = null;
	private Integer end2Val = null;
	
	private Integer dado = null;

	private Byte reg1 = null;
	private Byte reg2 = null;

	private int[] flags;
	private boolean precisaBusca = false;

	Decodificador(int instrucao) {
		parse(instrucao);
	}

	private void parse(int instrucao) {
		logicoAritmetica = instrucao >>> 15 == 1;
		if (logicoAritmetica) {
			opcode = (byte) ((instrucao >>> 10) & 0b111);
			reg1 = (byte) ((instrucao >> 2) & 0b11);
			reg2 = (byte) (instrucao & 0b11);
		} else {
			isMov = ((instrucao >>> 14) & 0b11) == 1;
			if (isMov) {
				precisaBusca = true;
				opcode = (byte) ((instrucao >>> 11) & 0b111);
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
				opcode = (byte) ((instrucao >>> 11) & 0b111);
				if (opcode == 1) {
					precisaBusca = true;
					end1 = (instrucao & 0b11111111111);
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

	Integer getEnd1() {
		return end1;
	}

	Integer getEnd2() {
		return end2;
	}
	
	Integer getEnd1Val() {
		return end1Val;
	}

	Integer getEnd2Val() {
		return end2Val;
	}
	
	boolean isMov() {
		return isMov;
	}

	Integer getDado() {
		return dado;
	}
	
	void setEnd1(Integer end1) {
		this.end1 = end1;
	}
	
	void setEnd2(Integer end2) {
		this.end2 = end2;
	}
	
	void setEnd1Val(Integer end1Val) {
		this.end1Val = end1Val;
	}
	
	void setEnd2Val(Integer end2Val) {
		this.end2Val = end2Val;
	}
	
	void setDado(Integer dado) {
		this.dado = dado;
	}
}