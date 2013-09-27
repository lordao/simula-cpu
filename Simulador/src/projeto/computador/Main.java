package projeto.computador;

import java.util.Scanner;

import projeto.computador.processador.Processador;

public class Main {

	public static void main(String[] args) {
//		String programaPath = args[0];
		
		Memoria mem = Memoria.getInstance();
		Processador cpu = Processador.getInstance();
		Scanner sc = new Scanner(System.in);
		
		mainLoop: 
		while (true) {
			switch (Processador.estadoAtual) {
			case SOLICITAR_INSTRUCAO:
				cpu.solicitarInstrucao();
				break;
			case BUSCA_INSTRUCAO:
				cpu.buscarInstrucao();
				break;
			case DECODIFICACAO:
				cpu.decodificar();
				break;
			case BUSCA_OPERANDO:
				cpu.buscarOperando();
				break;
			case EXECUCAO:
				cpu.executar();
				break;
			case HALT:
				break mainLoop;
			}
			mem.ciclo();
			Processador.ciclos++;
			
			System.out.printf("Ciclo %d\n", Processador.ciclos);
			cpu.show();
			sc.nextLine();
		}
		sc.close();
	}
}