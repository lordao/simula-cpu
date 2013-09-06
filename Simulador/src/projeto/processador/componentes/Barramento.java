package projeto.processador.componentes;

public class Barramento {
	private static final Barramento dados = new Barramento(TipoBarramento.Dados);
	private static final Barramento endereco = new Barramento(TipoBarramento.Endereco);
	private static final Barramento controle = new Barramento(TipoBarramento.Controle);
	
	private TipoBarramento tipo;
	private int sinal;
	
	private Barramento(TipoBarramento tipo) {
		this.tipo = tipo;
	}
	
	public static Barramento getBarramentoControle() {
		return controle;
	}
	
	public static Barramento getBarramentoDados() {
		return dados;
	}
	
	public static Barramento getBarramentoEndereco() {
		return endereco;
	}
	
	public void escrever(int palavra) {
		switch (tipo) {
		case Controle:
			this.sinal = palavra & 1;
			break;
		default:
			this.sinal = palavra % Memoria.TAMANHO_PALAVRA;
			break;
		}
	}
	
	public int ler() {
		return sinal;
	}
	
	private enum TipoBarramento {
		Dados, Endereco, Controle
	}
}
