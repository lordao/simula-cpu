package projeto.computador;

public class Barramento {
	public static final short SINAL_ESCRITA = 1;
	public static final short SINAL_LEITURA = 0;
    private static final Barramento dados    = new Barramento(TipoBarramento.Dados);
    private static final Barramento endereco = new Barramento(TipoBarramento.Endereco);
    private static final Barramento controle = new Barramento(TipoBarramento.Controle);
	

    private TipoBarramento tipo;
    private short sinal;

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

    public void escrever(short palavra) {
        switch (tipo) {
        case Controle:
            this.sinal = (short) (palavra & 1);
            break;
        default:
        	short tmp = (short) (palavra & Memoria.TAMANHO_PALAVRA);
            this.sinal = tmp;
        }
    }

    public short ler() {
        return sinal;
    }

    private enum TipoBarramento {
        Dados, Endereco, Controle
    }
}