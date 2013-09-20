package projeto.computador;

import projeto.computador.processador.Processador;

public class Main {
	
	public static void main(String[] args) {
		Memoria mem = Memoria.getInstance();
		Processador cpu = Processador.getInstance();
		
		Estado estadoAtual = Estado.BUSCA_INSTRUCAO;
		mainLoop:
		while (true) {
			switch(estadoAtual) {
			case BUSCA_INSTRUCAO:
				break;
			case DECODIFICACAO:
				break;
			case BUSCA_OPERANDO:
				break;
			case EXECUCAO:
				break;
			case HALT:
				break mainLoop;
			}
		}
	}

	private enum Estado {
		BUSCA_INSTRUCAO,
		DECODIFICACAO,
		BUSCA_OPERANDO,
		EXECUCAO,
		HALT
	}
}
