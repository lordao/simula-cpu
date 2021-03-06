package projeto.computador;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import projeto.computador.processador.Processador;
import projeto.montador.Montador;

public class Main {

	public static void main(String[] args) {
  		if (args.length < 1) {
  			System.err.println("Nenhum parâmetro passado!");
  			System.out.println("Uso: java -jar simulador.jar <representacao> nomeDoArquivo.jas"
  					+ "\nRepresentações suportadas: Binária (bin), Decimal* (dec) e Hexadecimal (hex)"
  					+ "\n*: Comportamento padrão.");
  			System.exit(1);
  		}
  		
		String programaPath;
		Representacao r;
		if (args.length == 1) {
			programaPath = args[0];
			r = Representacao.Decimal;
		} else {
			switch (args[0]) {
			case "bin":
				r = Representacao.Binaria;
				break;
			case "hex":
				r = Representacao.Hexadecimal;
				break;
			default:
				r = Representacao.Decimal;
			}
			programaPath = args[1];
		}
		Montador m = Montador.getInstance();
		
		Programa p = null;
		try {
			p = m.parseFromFile(programaPath);
			writePreProcessedCode(programaPath, p);
		} catch (IOException io) {
			System.err.println(io.getMessage());
			io.printStackTrace();
			System.exit(1);
		}
		Memoria.gerarMemoria(p);
		
		Memoria mem = Memoria.getInstance();
		Processador cpu = Processador.criarProcessador(r);
		Scanner sc = new Scanner(System.in);
		
		mainLoop: 
		while (true) {
			System.out.printf("Ciclo %d - %s\n", cpu.getCiclos(), cpu.getEstadoAtual().toString());
			cpu.lerBarramentos();
			switch (cpu.getEstadoAtual()) {
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
			cpu.encerrarCiclo();

			sc.nextLine();
		}
		sc.close();
	}

	private static void writePreProcessedCode(String path, Programa p) throws IOException {
		StringBuilder b = new StringBuilder(path);
		b.replace(b.lastIndexOf("."), b.length(), ".jo");
		
		FileWriter w = new FileWriter(b.toString());
		BufferedWriter writer = new BufferedWriter(w);
		writer.write(p.getPreProcessedCode());
		
		writer.close();
		w.close();
	}
}