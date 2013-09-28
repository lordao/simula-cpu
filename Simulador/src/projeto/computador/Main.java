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
		try {
			Programa p = m.parseFromFile(programaPath);
			Iterator<Short> iter = p.iterador();
			while (iter.hasNext()) {
				System.out.println(Integer.toBinaryString(iter.next()));
			}
			System.exit(0);
		} catch (IOException io) {
			System.err.println(io.getMessage());
			io.printStackTrace();
			System.exit(1);
		}
  		
		Memoria mem = Memoria.getInstance();
		Processador cpu = Processador.getInstance();
		Scanner sc = new Scanner(System.in);
		
		mainLoop: 
		while (true) {
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
			
			System.out.printf("Ciclo %d\n", Processador.ciclos);
			cpu.show();
			sc.nextLine();
		}
		sc.close();
	}
}