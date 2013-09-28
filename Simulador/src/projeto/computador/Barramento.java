package projeto.computador;

public class Barramento {
	public static final int SINAL_ESCRITA = 1;
	public static final int SINAL_LEITURA = 0;
    private static final Barramento dados    = new Barramento(TipoBarramento.Dados);
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
        	int tmp = palavra & (Memoria.TAMANHO_PALAVRA-1);
        	String s = Integer.toBinaryString(tmp),
        			s0 = Integer.toBinaryString(Memoria.TAMANHO_PALAVRA-1);
            this.sinal = tmp;
        }
    }

    public int ler() {
        return sinal;
    }

    private enum TipoBarramento {
        Dados, Endereco, Controle
    }
}