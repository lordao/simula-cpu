package projeto.computador;

import java.util.Iterator;

public class Memoria {
    private static Memoria instance;
    public static final int TAMANHO_PALAVRA = (int) Math.pow(2, 16);
    public static final int TAMANHO_MEMORIA = (int) Math.pow(2, 16);

    private int[] mem;

    private Memoria() {
        mem = new int[TAMANHO_MEMORIA];
    }
    
    private Memoria(Programa p) {
    	this();
    	int i = 0;
    	Iterator<Integer> iter = p.iterador();
    	while (iter.hasNext()) {
    		mem[i++] = iter.next();
    	}
    }
    
    public static void gerarMemoria(Programa p) {
    	if (instance == null) {
    		instance = new Memoria(p);
    	}
    }

    public static Memoria getInstance() {
        if (instance == null) {
        	instance = new Memoria();
        }
    	return instance;
    }
    
    public void ciclo() {
        int controle = Barramento.getBarramentoControle().ler();
        
        int endereco = Barramento.getBarramentoEndereco().ler();
        if (controle == Barramento.SINAL_ESCRITA) {
        	int dado = Barramento.getBarramentoDados().ler();
        	mem[endereco] = dado;
        } else if (controle == Barramento.SINAL_LEITURA) {
        	Barramento.getBarramentoDados().escrever(mem[endereco]);
        }
    }
}
