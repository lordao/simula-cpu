package projeto.computador;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Programa {
	private List<Integer> instrucoes;
	
	
	public Programa() {
		instrucoes = new LinkedList<Integer>();
	}
	
	public void addInstrucao(int instrucao) {
		instrucoes.add(instrucao);
	}
	
	public Iterator<Integer> iterador() {
		return instrucoes.iterator();
	}
}
