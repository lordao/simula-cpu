package projeto.computador;

import java.util.Scanner;

import projeto.computador.processador.Processador;

public class Main {
	public static Estado estadoAtual = Estado.BUSCA_INSTRUCAO;
	public static int ciclos = 0;
	
	public static void main(String[] args) {
		Memoria mem = Memoria.getInstance();
		Processador cpu = Processador.getInstance();
		Scanner sc = new Scanner(System.in);
		mainLoop:
		while (true) {
			switch(estadoAtual) {
			case BUSCA_INSTRUCAO:
				cpu.buscarInstrucao();
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
			mem.ciclo();
			ciclos++;
			
			
			sc.nextLine();
		}
	}
}
