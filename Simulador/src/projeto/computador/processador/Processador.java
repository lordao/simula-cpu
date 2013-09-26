package projeto.computador.processador;

import java.util.HashMap;
import java.util.Map;

import projeto.computador.Barramento;

public class Processador {
	private Ula ula;
	private UnidadeControle uControle;
	private Map<Byte,Registrador> rs;
	
	private Registrador16 pc;
	private Registrador16 ir;
	private Registrador16 mar;
	private Registrador16 mbr;

	private Registrador16 ax;
	private Registrador16 bx;
	private Registrador16 cx;
	private Registrador32 eax;
	private Registrador32 ebx;

	private static Processador instance;

	public static Processador getInstance() {
		if (instance == null) {
			instance = new Processador();
		}
		return instance;
	}

	private Processador() {
		rs = new HashMap<>();
		
		ula = new Ula();
		uControle = new UnidadeControle();

		rs.put((byte) 0x00, new Registrador16("PC"));
		rs.put((byte) 0x01, new Registrador16("AX"));
		rs.put((byte) 0x02, new Registrador16("BX"));
		rs.put((byte) 0x03, new Registrador16("CX"));
		rs.put((byte) 0x12, new Registrador32("EAX"));
		rs.put((byte) 0x12, new Registrador32("EBX"));
		
//		pc = new Registrador16("PC");
//		ax = new Registrador16("AX");
//		bx = new Registrador16("BX");
//		cx = new Registrador16("CX");
//		eax = new Registrador32("EAX");
//		ebx = new Registrador32("EBX");
	}

	public void buscarInstrucao() {
		Barramento bEnd = Barramento.getBarramentoEndereco();
		Barramento bControle = Barramento.getBarramentoControle();

		boolean ack = (bControle.ler() >> 1) == 1;

		if (ack) {
			Barramento bDados = Barramento.getBarramentoDados();
			ir.setPalavra(bDados.ler());
			bControle.escrever(0);
		} else {
			bEnd.escrever(pc.getPalavra());
			bControle.escrever(0);
		}
	}

	public void escrever(short endereco, short dado) {
		Barramento bEnd = Barramento.getBarramentoEndereco();
		Barramento bControle = Barramento.getBarramentoControle();
		Barramento bDados = Barramento.getBarramentoDados();

		bEnd.escrever(endereco);
		bDados.escrever(dado);
		bControle.escrever(1);
	}

	public int getRegistrador(byte endereco) {
		return rs.get(endereco).getPalavra().intValue();
	}
	
	public void setRegistrador(byte endereco, int palavra) {
		rs.get(endereco).setPalavra(palavra);
	}
}
