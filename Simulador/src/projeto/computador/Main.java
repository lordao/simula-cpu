package projeto.computador;

import java.util.Scanner;

import projeto.computador.processador.Processador;

public class Main {
	public static Estado estadoAtual = Estado.SOLICITAR_INSTRUCAO;
	public static int ciclos = 0;
	
	public static void main(String[] args) {
		Memoria mem = Memoria.getInstance();
		Processador cpu = Processador.getInstance();
		Scanner sc = new Scanner(System.in);
		mainLoop:
		while (true) {
			switch(estadoAtual) {
			case SOLICITAR_INSTRUCAO:
				cpu.solicitarInstrucao();
				mem.ciclo();
				estadoAtual = Estado.BUSCA_INSTRUCAO;
				break;
			case BUSCA_INSTRUCAO:
				cpu.buscarInstrucao();
				estadoAtual = Estado.DECODIFICACAO;
				break;
			case DECODIFICACAO:
				cpu.decodificar();
				break;
			case BUSCA_OPERANDO:
				
				mem.ciclo();
				break;
			case EXECUCAO:
				
				break;
			case HALT:
				break mainLoop;
			}
			ciclos++;
			
			sc.nextLine();
		}
		sc.close();
	}
}