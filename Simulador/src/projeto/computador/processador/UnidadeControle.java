package projeto.computador.processador;

import projeto.computador.Barramento;
import projeto.computador.Estado;
import projeto.computador.Main;

class UnidadeControle {
	private Decodificador decoder;
	
	public UnidadeControle() {
	}
	
	public void obterInstrucao(short instrucao) {
		decoder.parse(instrucao);
		boolean logicoAritmetica = decoder.isLogicoAritmetica();
		boolean usaRegistrador   = decoder.usaRegistrador();
		byte opcode = decoder.getOpcode();
		if (!usaRegistrador) {
			Barramento.getBarramentoEndereco().escrever(decoder.getEndereco());
			Barramento.getBarramentoControle().escrever(Barramento.SINAL_LEITURA);
			Main.estadoAtual = Estado.BUSCA_OPERANDO;
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
