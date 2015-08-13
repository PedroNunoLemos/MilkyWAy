package logicajogo;

import logicajogo.estados.Estado;

public class Jogo {
	
	private Estado estado;
	private Estado estadopirata;

	public Jogo(Estado estado) {
		this.estado = estado;
	}

	public Estado GetEstado(){
		
		return this.estado;
		
	}
	
	public void mudarEstado() {
		System.out.println("Estado anterior: " + this.estado);
		this.estado = this.estado.proximoEstado(this);
		System.out.println("Estado atual: " + this.estado);
	}

	public Estado getEstadopirata() {
		return estadopirata;
	}

	public void setEstadopirata(Estado estadopirata) {
		this.estadopirata = estadopirata;
	}

}
