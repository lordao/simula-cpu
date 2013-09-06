package projeto.processador.componentes;

public class Memoria {
	private static final Memoria instance = new Memoria();
	public static final int TAMANHO_PALAVRA = (int) Math.pow(2, 16);
	public static final int TAMANHO_MEMORIA = (int) Math.pow(2, 12);
	
	private int[] mem;
	
	private Memoria() {
		mem = new int[TAMANHO_MEMORIA];
	}
	
	public static Memoria getInstance() {
		return instance;
	}
}