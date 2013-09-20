package projeto.computador.processador;

class Ula {	
	private int oprd1;
	private int oprd2;
	private Registrador regUla;
	
	private int opcode;
	
	Ula() {
		regUla = new Registrador("Registrador de Estado (ULA)");
	}
	
	void setOprd1(int oprd) {
		this.oprd1 = oprd;
	}
	
	void setOprd2(int oprd) {
		this.oprd2 = oprd;
	}
	
	int executar() {
		int result = 0;
		switch (opcode) {
		
		}
		
		int zero     = 1 & result,
			negativo = result >>> 31,
			overflow = result >>> 15 != 0 ? 1 : 0;
		
		int palavra = overflow;
		palavra = palavra << 1;
		palavra = palavra | negativo;
		palavra = palavra << 1;
		palavra = palavra | zero;
		
		regUla.setPalavra(palavra);
		
		return result;
	}
}
