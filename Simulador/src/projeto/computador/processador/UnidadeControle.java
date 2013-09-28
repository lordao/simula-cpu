package projeto.computador.processador;

import projeto.computador.Barramento;
import projeto.computador.Estado;
import projeto.computador.Main;

class UnidadeControle {
	private Decodificador decoder;

	UnidadeControle() {
		decoder = null;
	}

	byte decodificarInstrucao(short instrucao) {
		decoder = new Decodificador(instrucao);
		return decoder.getOpcode();
	}

	boolean buscaOperandos(short oprd) {
		if (decoder.isLogicoAritmetica()) {
			return true;
		}

		int[] flags = decoder.getFlags();
		if (decoder.isMov()) {
			//Origem dos dados. Registradores já são encontrados durante a decodificação.
			switch (flags[0]) {
			//Registrador 
			case 0:
				if (flags[1] == 1 && decoder.getEnd1() == null) {
					decoder.setEnd1(oprd);
				} 
				return true;
			//Memória
			case 1:
				if (decoder.getEnd1() == null) {
					decoder.setEnd1(oprd);
					return false;
				} else if (decoder.getEnd1Val() == null) {
					decoder.setEnd1Val(oprd);
				}
				return true;
			case 2:
				if (decoder.getDado() == null) {
					decoder.setDado(oprd);
					return false;
				}
				if (flags[1] == 1 && decoder.getEnd1() == null) {
					decoder.setEnd1(oprd);
					return false;
				}
				return true;
			}
		} else if (decoder.getOpcode() == 1 && decoder.getEnd1() == null) {
			decoder.setEnd1(oprd);
		}

		return true;
	}

	void executar() {
		Processador p = Processador.getInstance();
		if (decoder.isMov()) {
			byte r1 = decoder.getReg1(),
				 r2 = decoder.getReg2();
			short val1 = p.getRegistrador(r1),
				  val2 = p.getRegistrador(r2),
				  end  = decoder.getEnd1Val(),
				  dado = decoder.getDado();
			
			
			switch (decoder.getOpcode()) {
			//MOV %<reg>, $<end>
			case 0:
				p.setRegistrador(r1, end);
				break;
			
			//MOV $<end>, %<reg>
			case 1:
				p.escrita(end, val1);
				break;

			//MOV %<reg>, %<reg>
			case 2:
				p.setRegistrador(r1, val2);
				break;

			//MOV $<end>, #<const>
			case 3:
				p.escrita(end, dado);
				break;
				
			//MOV %<reg>, #<const> 
			case 4:
				p.setRegistrador(r1, dado);
				break;
				
			//NOP
			case 5:
			case 6:
			case 7:
				break;
			}
			return;
		}
		switch (decoder.getOpcode()) {
		// NOP
		case 0:
			break;
		// JMP
		case 1:
			p.setRegistrador(Processador.PC_END, decoder.getEnd1());
			break;
		// JZ
		case 2:
			if ((p.getEstadoUla() & 1) == 1) {
				p.incrementarPc();
			}
			break;
		// JNZ
		case 3:
			if ((p.getEstadoUla() & 1) == 0) {
				p.incrementarPc();
			}
			break;
		// JE
		case 4:
			if (((p.getEstadoUla() >> 2) & 1) == 1) {
				p.incrementarPc();
			}
			break;
		// JNE
		case 5:
			if (((p.getEstadoUla() >> 2) & 1) == 0) {
				p.incrementarPc();
			}
			break;
		// JNG
		case 6:
			if (((p.getEstadoUla() >> 1) & 1) == 1) {
				p.incrementarPc();
			}
			break;
		// HLT
		case 7:
			Processador.estadoAtual = Estado.HALT;
			break;
		}
	}

	Decodificador getDecoder() {
		return decoder;
	}
}
