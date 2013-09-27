package projeto.computador.processador;

import java.util.HashMap;
import java.util.Map;

import projeto.computador.Barramento;

public class Processador {
	private Ula ula;
	private UnidadeControle uControle;
	private Map<Byte, Registrador16> regs16;
	private Map<Byte, Registrador32> regs32;
	
	private static final byte PC_END = 0xE;
	private static final byte IR_END = 0xF;
	
	private static final byte AX_END = 0x0;
	private static final byte BX_END = 0x1;
	private static final byte CX_END = 0x2;
	private static final byte DX_END = 0x3;
	
	private static final byte EAX_END = 0x4;
	private static final byte EBX_END = 0x5;

	private static Processador instance;

	public static Processador getInstance() {
		if (instance == null) {
			instance = new Processador();
		}
		return instance;
	}

	private Processador() {
		regs16 = new HashMap<>();
		regs32 = new HashMap<>();

		ula = new Ula();
		uControle = new UnidadeControle();

		regs16.put(PC_END, new Registrador16("PC"));
		regs16.put(IR_END, new Registrador16("IR"));
		
		regs16.put(AX_END, new Registrador16("AX"));
		regs16.put(BX_END, new Registrador16("BX"));
		regs16.put(CX_END, new Registrador16("CX"));
		regs16.put(DX_END, new Registrador16("DX"));
		
		regs32.put(EAX_END, new Registrador32("EAX"));
		regs32.put(EBX_END, new Registrador32("EBX"));
	}

	public void solicitarInstrucao() {
		Barramento bEnd = Barramento.getBarramentoEndereco();
		Barramento bControle = Barramento.getBarramentoControle();

		bEnd.escrever(getRegistrador(PC_END));
		bControle.escrever(0);
	}

	public void buscarInstrucao() {
		Barramento bDados = Barramento.getBarramentoDados();
		setRegistrador(IR_END, bDados.ler());
	}

	public void decodificar() {
		uControle.decodificarInstrucao(getRegistrador16(IR_END));
	}
	
	public void buscarOperando() {
		
	}

	public int getRegistrador(byte endereco) {
		if (endereco > 0x0F) {
			return getRegistrador32(endereco);
		} else {
			return getRegistrador16(endereco);
		}
	}

	public int getRegistrador32(byte endereco) {
		return regs32.get(endereco).getPalavra();
	}

	public short getRegistrador16(byte endereco) {
		return regs16.get(endereco).getPalavra();
	}

	public void setRegistrador(byte endereco, int palavra) {
		try {
			if (endereco > 0x0F) {
				regs32.get(endereco).setPalavra(palavra);
			} else {
				regs16.get(endereco).setPalavra(palavra);
			}
		} catch (NullPointerException n) {
			System.err.printf("Endereço 0x%x inexistente!\n", endereco);
		}
	}
}
