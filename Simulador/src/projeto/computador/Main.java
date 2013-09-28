package projeto.computador;

import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

import projeto.computador.processador.Processador;
import projeto.montador.Montador;

public class Main {

	public static void main(String[] args) {
//  		String programaPath = args[0];
		String programaPath = "/tmp/teste";
		Montador m = Montador.getInstance();
		
		Programa p = null;
		try {
			p = m.parseFromFile(programaPath);
		} catch (IOException io) {
			System.err.println(io.getMessage());
			io.printStackTrace();
			System.exit(1);
		}
//		p.addInstrucao(0b0000100000000000);
//		p.addInstrucao(3);
//		p.addInstrucao(16);
//		p.addInstrucao(0b0011100000000000);
		Memoria.gerarMemoria(p);
		
		Memoria mem = Memoria.getInstance();
		Processador cpu = Processador.getInstance();
		Scanner sc = new Scanner(System.in);
		
		mainLoop: 
		while (true) {
			System.out.printf("Ciclo %d - %s\n", Processador.ciclos, Processador.estadoAtual.toString());
			cpu.lerBarramentos();
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
			cpu.escreverBarramentos();
			mem.ciclo();
			Processador.ciclos++;
			
			cpu.show();
//			sc.nextLine();
		}
		sc.close();
	}
}