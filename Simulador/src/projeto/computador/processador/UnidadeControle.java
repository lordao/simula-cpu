package projeto.computador.processador;

import projeto.computador.Barramento;
import projeto.computador.Estado;
import projeto.computador.Main;

class UnidadeControle {
	private Decodificador decoder;
	private boolean faltaOperando;
	private boolean usaRegistrador;
	private boolean isMov;
	private short oprd;
	private short end;
	private byte reg1;
	private byte reg2;
	
	UnidadeControle() {
		decoder = new Decodificador();
		faltaOperando = false;
	}
	
	void decodificarInstrucao(short instrucao) {
		decoder.parse(instrucao);
		boolean logicoAritmetica = decoder.isLogicoAritmetica();
		usaRegistrador = decoder.usaRegistrador();
		isMov = decoder.isMov();
		byte opcode = decoder.getOpcode();
		if (usaRegistrador) {
			Main.estadoAtual = Estado.EXECUCAO;
		} else {
			faltaOperando = true;
			Barramento.getBarramentoEndereco().escrever(decoder.getEndereco());
			Barramento.getBarramentoControle().escrever(Barramento.SINAL_LEITURA);
			Main.estadoAtual = Estado.BUSCA_OPERANDO;
		}
	}
	

	void escrever(short endereco, short dado) {
		Barramento bEnd = Barramento.getBarramentoEndereco();
		Barramento bControle = Barramento.getBarramentoControle();
		Barramento bDados = Barramento.getBarramentoDados();

		bEnd.escrever(endereco);
		bDados.escrever(dado);
		bControle.escrever(1);
	}

	
	private void obterOperando(byte opcode) {
		if (faltaOperando) {
			
		}
		
		switch(opcode) {
		
		}
	}
	
	private void executar(byte opcode) {
		switch(opcode) {
		case 01:
			break;
		case 02:
			break;
		}
	}
}
