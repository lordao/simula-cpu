package projeto.computador;

public class Montador {
	private static Montador instance = null;

	private Montador() {
	}

	public static Montador getInstance() {
		if (instance == null) {
			instance = new Montador();
		}
		return instance;
	}

	public Programa parseFromFile(String filepath) {
		return null;
	}

}
