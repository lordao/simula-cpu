package projeto.computador.processador;

public class Processador {
	private static Processador instance;
	
	public static Processador getInstance() {
		if (instance == null) {
			instance = new Processador();
		}
		return instance;
	}
	
	private Processador() {
	}
}
