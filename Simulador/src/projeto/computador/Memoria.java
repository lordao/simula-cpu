package projeto.computador;

public class Memoria {
    private static final Memoria instance = new Memoria();
    public static final int TAMANHO_PALAVRA = (int) Math.pow(2, 16);
    public static final int TAMANHO_MEMORIA = (int) Math.pow(2, 11);

    private short[] mem;

    private Memoria() {
        mem = new short[TAMANHO_MEMORIA];
    }

    public static Memoria getInstance() {
        return instance;
    }
    
    public void ciclo() {
        short controle = Barramento.getBarramentoControle().ler();
        
        short endereco = Barramento.getBarramentoEndereco().ler();
        if (controle == Barramento.SINAL_ESCRITA) {
        	short dado = Barramento.getBarramentoDados().ler();
        	mem[endereco] = dado;
        } else if (controle == Barramento.SINAL_LEITURA) {
        	Barramento.getBarramentoDados().escrever(mem[endereco]);
        }
    }
}
