package projeto.computador;

import java.util.Iterator;

public class Memoria {
    private static Memoria instance;
    public static final int TAMANHO_PALAVRA = Short.MAX_VALUE;
    public static final int TAMANHO_MEMORIA = Short.MAX_VALUE;

    private short[] mem;

    private Memoria() {
        mem = new short[TAMANHO_MEMORIA];
    }
    
    private Memoria(Programa p) {
    	this();
    	int i = -1;
    	Iterator<Short> iter = p.iterador();
    	while (iter.hasNext()) {
    		mem[++i] = iter.next();
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
        short controle = Barramento.getBarramentoControle().ler();
        
        short endereco = Barramento.getBarramentoEndereco().ler();
        if (endereco < 0) {
        	endereco *= -1;
        }
        if (controle == Barramento.SINAL_ESCRITA) {
        	short dado = Barramento.getBarramentoDados().ler();
        	mem[endereco] = dado;
        } else if (controle == Barramento.SINAL_LEITURA) {
        	Barramento.getBarramentoDados().escrever(mem[endereco]);
        }
    }
}
