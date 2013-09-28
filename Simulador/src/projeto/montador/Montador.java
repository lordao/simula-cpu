package projeto.montador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import projeto.computador.Programa;
import projeto.computador.processador.Processador;

public class Montador {
	private static Montador instance = null;

	private Montador() {
	}

	public static Montador getInstance() {
		if (instance == null) {
			instance = new Montador();
		}
		return instance;
	}

	public Programa parseFromFile(String filepath) throws IOException {
		FileReader r = new FileReader(filepath);
		BufferedReader leitor = new BufferedReader(r);
		String linha = leitor.readLine();;
		Programa p = new Programa();
		loop:
		while (linha != null) {	
			System.out.println(linha);
			if (linha.isEmpty() || linha.charAt(0) == ';') {
				linha = leitor.readLine();
				continue loop;
			}
			byte opcode, reg1, reg2;
			short end, cnst;
			
			String[] tokens = linha.split(" ");
			switch (tokens[0]) {
			case "ADD":
			case "SUB":
			case "MUL":
			case "DIV":
			case "NOT":
			case "AND":
			case "OR":
			case "XOR":
				opcode = opcodeLA(tokens[0]);
				if (tokens.length < 3) {
					throw new IllegalArgumentException("Padrão não reconhecido!");
				}
				reg1 = parseReg(tokens[1]);
				reg2 = parseReg(tokens[2]);
				p.addInstrucao((short) (((((opcode << 2) | reg1) << 2) | reg2) << 6));
				if (tokens.length > 3) {
					System.out.println("Ignorando resto da linha...");
				}
				break;
			
			case "MOV":
				switch(tokens[1].charAt(0)) {
				case '%':
					reg1 = parseReg(tokens[1]);
					
					break;
				case '$':
					break;
				case '#':
					throw new IllegalArgumentException("Constante não pode ser destino em MOV!");
				
				default:
					throw new IllegalArgumentException("Padrão não reconhecido!");
				}
				break;
				
			
			case "JMP":
				opcode = 0b00001;
				if (tokens.length < 2) {
					throw new IllegalArgumentException("Operação JMP requer endereço!");
				}
				if (tokens[1].charAt(0) == '$') {
					end = Short.parseShort(tokens[1].substring(1));
				} else {
					throw new IllegalArgumentException("Operação JMP requer endereço!");
				}
				if (tokens.length > 2) {
					System.out.println("Ignorando resto da linha...");
				}
				break;
			case "NOP":
			case "JZ":
			case "JNZ":
			case "JE":
			case "JNE":
			case "JNG":
			case "HLT":
				opcode = opcodeFluxo(tokens[0]);
				p.addInstrucao((short) (opcode << 11));
				break;
			default:
				throw new IllegalArgumentException("Padrão não reconhecido!");
			}
			linha = leitor.readLine();
		}
		return p;
	}

	private byte parseReg(String string) {
		switch(string.substring(1)) {
		case "AX":
			return Processador.AX_END;
		case "BX":
			return Processador.BX_END;
		case "CX":
			return Processador.CX_END;
		case "DX":
			return Processador.DX_END;
		default:
			throw new IllegalArgumentException("Padrão não reconhecido!");
		}
	}
	
	private byte opcodeLA(String op) {
		switch (op) {
		case "ADD":
			return 0b100000;
		case "SUB":
			return 0b100001;
		case "MUL":
			return 0b100010;
		case "DIV":
			return 0b100011;
		case "NOT":
			return 0b100100;
		case "AND":
			return 0b100101;
		case "OR":
			return 0b100110;
		case "XOR":
			return 0b100111;
		default:
			throw new IllegalArgumentException("Padrão não reconhecido!");
		}
	}
	
	private byte opcodeFluxo(String op) {
		switch (op) {
		case "NOP":
			return 0b00000;
		case "JZ":
			return 0b00010;
		case "JNZ":
			return 0b00011;
		case "JE":
			return 0b00100;
		case "JNE":
			return 0b00101;
		case "JNG":
			return 0b00110;
		case "HLT":
			return 0b00111;
		default:
			throw new IllegalArgumentException("Padrão não reconhecido!");
		}
	}
}
