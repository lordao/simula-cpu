package projeto.computador.processador;

class Ula {
	private final static Ula instance = new Ula();
	
	private short oprd1;
	private short oprd2;
	private Registrador regUla;
	
	private short opcode;
	
	private Ula() {
		regUla = new Registrador("Registrador de Estado (ULA)");
	}
	
	static Ula getInstance() {
		return instance;
	}
	
	void setOprd1(short oprd) {
		this.oprd1 = oprd;
	}
	
	void setOprd2(short oprd) {
		this.oprd2 = oprd;
	}
	
	short executar() {
		int result = 0;
		switch (opcode) {
		
		}
		
		short zero     = (short) (1 & result),
			  negativo = (short) (result >>> 31),
			  overflow;
		
		return (short) result;
	}
}
