package projeto.computador;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Programa {
	private List<Short> instrucoes;
	private String preProcessedCode;
	
	public Programa() {
		instrucoes = new LinkedList<Short>();
		preProcessedCode = null;
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
	
	public String getPreProcessedCode() {
		return preProcessedCode;
	}

	public void setPreProcessedCode(String preProcessedCode) {
		this.preProcessedCode = preProcessedCode;
	}
}
