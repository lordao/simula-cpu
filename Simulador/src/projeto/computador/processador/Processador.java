package projeto.computador.processador;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import projeto.computador.Barramento;
import projeto.computador.Estado;

public class Processador {
	private Ula ula;
	private UnidadeControle uControle;
	private Map<Byte, Registrador16> regs;
	private short enderecoAtual;
	public static Estado estadoAtual = Estado.BUSCA_INSTRUCAO;
	public static int ciclos = 0;

	public static final byte AX_END = 0b00;
	public static final byte BX_END = 0b01;
	public static final byte CX_END = 0b10;
	public static final byte DX_END = 0b11;

	public static final byte MAR_END = 0b100;
	public static final byte MBR_END = 0b101;
	public static final byte PC_END = 0b110;
	public static final byte IR_END = 0b111;

	private static Processador instance;

	public static Processador getInstance() {
		if (instance == null) {
			instance = new Processador();
		}
		return instance;
	}

	private Processador() {
		regs = new HashMap<Byte, Registrador16>();

		ula = new Ula();
		uControle = new UnidadeControle();

		regs.put(MAR_END, new Registrador16("MAR"));
		regs.put(MBR_END, new Registrador16("MBR"));
		regs.put(PC_END, new Registrador16("PC"));
		regs.put(IR_END, new Registrador16("IR"));

		regs.put(AX_END, new Registrador16("AX"));
		regs.put(BX_END, new Registrador16("BX"));
		regs.put(CX_END, new Registrador16("CX"));
		regs.put(DX_END, new Registrador16("DX"));
	}

	public void lerBarramentos() {
		setRegistrador(MAR_END, Barramento.getBarramentoEndereco().ler());
		setRegistrador(MBR_END, Barramento.getBarramentoDados().ler());
	}

	public void escreverBarramentos() {
		Barramento.getBarramentoEndereco().escrever(getRegistrador(MAR_END));
		Barramento.getBarramentoDados().escrever(getRegistrador(MBR_END));
	}
	
	void escrita(short endereco, short dado) {
		setRegistrador(MAR_END, endereco);
		setRegistrador(MBR_END, dado);
		Barramento.getBarramentoControle().escrever(Barramento.SINAL_ESCRITA);
	}
	
	void leitura(short endereco) {
		setRegistrador(MAR_END, endereco);
		Barramento.getBarramentoControle().escrever(Barramento.SINAL_LEITURA);
	}

	public void solicitarInstrucao() {
		leitura(getRegistrador(PC_END));
	}

	public void buscarInstrucao() {
		setRegistrador(IR_END, getRegistrador(MBR_END));
		estadoAtual = Estado.DECODIFICACAO;
	}

	public void decodificar() {
		byte opcode = uControle.decodificarInstrucao(getRegistrador(IR_END));
		if (uControle.getDecoder().isLogicoAritmetica()) {
			ula.setOperacao(opcode);
		}
		if (uControle.getDecoder().precisaBusca()) {
			estadoAtual = Estado.BUSCA_OPERANDO;
			enderecoAtual = getRegistrador(PC_END);
			leitura(++enderecoAtual);
		}
	}

	public void buscarOperando() {
		if (uControle.getDecoder().isLogicoAritmetica()) {
			ula.setOprd1((short) uControle.getDecoder().getReg1());
			ula.setOprd2((short) uControle.getDecoder().getReg2());
		} else {
			short oprd = getRegistrador(MBR_END);
			if (uControle.buscaOperandos(oprd)) {
				estadoAtual = Estado.EXECUCAO;
			} else {
				leitura(++enderecoAtual);
			}
		}
	}

	public void executar() {
		if (uControle.getDecoder().isLogicoAritmetica()) {
			int result = ula.executar();
			guardarResultado(result, uControle.getDecoder().getOpcode());
		} else {
			uControle.executar();
		}
		setPc(++enderecoAtual);
	}

	private void guardarResultado(int result, byte opcode) {
		switch (opcode) {
		case 0:
		case 1:
		case 5:
		case 6:
		case 7:
			setRegistrador(uControle.getDecoder().getReg2(), result);
			break;
		case 2:
		case 3:
			short cx = (short) (result >>> 16),
			dx = (short) result;
			setRegistrador(CX_END, cx);
			setRegistrador(DX_END, dx);
			break;
		case 4:
			setRegistrador(uControle.getDecoder().getReg1(), result);
			break;
		}
	}

	short getRegistrador(byte endereco) {
		return regs.get(endereco).getPalavra();
	}

	void setRegistrador(byte endereco, int palavra) {
		try {
			regs.get(endereco).setPalavra(palavra);
		} catch (NullPointerException n) {
			System.err.printf("Endereço 0b%x inexistente!\n", endereco);
		}
	}

	byte getEstadoUla() {
		return ula.getEstado();
	}

	public void show() {
		Iterator iter = regs.values().iterator();
		StringBuilder sb = new StringBuilder();
		while (iter.hasNext()) {
			sb.append(iter.next()).append('\n');
		}

		sb.append(ula);
		System.out.println(sb.toString());
	}

	void setPc(short enderecoAtual) {
		setRegistrador(PC_END, enderecoAtual);
	}
	
	void incrementarPc() {
		short s = getRegistrador(PC_END);
		setRegistrador(PC_END, ++s);
	}
}
