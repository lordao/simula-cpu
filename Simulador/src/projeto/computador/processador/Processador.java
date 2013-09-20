package projeto.computador.processador;

import projeto.computador.Barramento;

public class Processador {
	private Ula ula;
	private Registrador pc;
	private Registrador ir;
	private Registrador mar;
	private Registrador mbr;

	private Registrador ax;
	private Registrador bx;
	private Registrador cx;
	private Registrador eax;
	private Registrador ebx;

	private static Processador instance;

	public static Processador getInstance() {
		if (instance == null) {
			instance = new Processador();
		}
		return instance;
	}

	private Processador() {
		ula = new Ula();

		ax = new Registrador("AX");
		bx = new Registrador("BX");
		cx = new Registrador("CX");
		eax = new Registrador("EAX");
		ebx = new Registrador("EBX");
	}

	public void buscarInstrucao() {
		Barramento bEnd      = Barramento.getBarramentoEndereco();
		Barramento bControle = Barramento.getBarramentoControle();
		
		bEnd.escrever(pc.getPalavra());
		bControle.escrever(1);
	}
	
	public void lerInstrucao() {
		Barramento bDados = Barramento.getBarramentoDados();
		
		ir.setPalavra(bDados.ler());
	}

	public int getAx() {
		return ax.getPalavra();
	}

	public int getBx() {
		return bx.getPalavra();
	}

	public int getCx() {
		return cx.getPalavra();
	}

	public int getEax() {
		return eax.getPalavra();
	}

	public int getEbx() {
		return ebx.getPalavra();
	}
}
