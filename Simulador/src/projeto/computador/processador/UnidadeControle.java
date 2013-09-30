package projeto.computador.processador;

import projeto.computador.Estado;

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
				if (flags[1] == 1 && decoder.getEnd1() == null) {
					decoder.setEnd1(oprd);
					return false;
				}
				if (decoder.getDado() == null) {
					decoder.setDado(oprd);
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
			byte r1 = 0, r2 = 0;
			short val1 = 0, val2 = 0, end = 0, dado = 0;
			
			if (decoder.getReg1() != null) {
				r1 = decoder.getReg1();
				val1 = p.getRegistrador(r1);
			}
			if (decoder.getReg2() != null) {
				r2 = decoder.getReg2();
				val2 = p.getRegistrador(r2);
			}
			if (decoder.getEnd1() != null) {
				end  = decoder.getEnd1Val();
			}
			if (decoder.getDado() != null) {
				dado = decoder.getDado();
			}
			
			
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
			p.setPc(decoder.getEnd1());
			break;
		// JZ
		case 2:
			if ((p.getEstadoUla() & 1) == 1) {
				p.pularPc();
			}
			break;
		// JNZ
		case 3:
			if ((p.getEstadoUla() & 1) == 0) {
				p.pularPc();
			}
			break;
		// JE
		case 4:
			if (((p.getEstadoUla() >> 2) & 1) == 1) {
				p.pularPc();
			}
			break;
		// JNE
		case 5:
			if (((p.getEstadoUla() >> 2) & 1) == 0) {
				p.pularPc();
			}
			break;
		// JNG
		case 6:
			if (((p.getEstadoUla() >> 1) & 1) == 1) {
				p.pularPc();
			}
			break;
		case 7:
			Processador.getInstance().estadoAtual = Estado.HALT;
			break;
		}
	}

	Decodificador getDecoder() {
		return decoder;
	}
}
