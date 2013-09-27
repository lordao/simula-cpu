package projeto.computador.processador;

class Decodificador {
	private boolean logicoAritmetica;
	private byte opcode;
	private short endereco;
	private byte reg1;
	private byte reg2;
	private boolean usaRegistrador;

	Decodificador() {
	}

	void parse(short instrucao) {
		logicoAritmetica = instrucao >>> 15 == 0;
		usaRegistrador = (instrucao & 0x0800) != 0;
		opcode = (byte) (instrucao >>> 11);
		if (!usaRegistrador) {
			this.endereco = (short) (instrucao & 0x0FFF);
		} else {
			this.reg1 = (byte) ((instrucao & 0x00F0) >> 3);
			this.reg2 = (byte) (instrucao & 0x000F);
		}
	}

	boolean isLogicoAritmetica() {
		return logicoAritmetica;
	}

	boolean usaRegistrador() {
		return usaRegistrador;
	}

	byte getOpcode() {
		return opcode;
	}

	short getEndereco() {
		return endereco;
	}

	byte getRegistrador1() {
		return reg1;
	}

	byte getRegistrador2() {
		return reg2;
	}

	public boolean isMov() {
		return false;
	}
}