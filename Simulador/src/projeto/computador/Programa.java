package projeto.computador;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Programa {
	private List<Short> instrucoes;
	
	
	public Programa() {
		instrucoes = new LinkedList<Short>();
	}
	
	public void addInstrucao(short instrucao) {
		instrucoes.add(instrucao);
	}
	
	public Iterator<Short> iterador() {
		return instrucoes.iterator();
	}

	public void addInstrucao(int instrucao) {
		addInstrucao((short) instrucao); 
	}
}
