package logicajogo.cartas.galaxia;

import java.io.Serializable;

import logicajogo.cartas.Carta;

public class BuracoNegro extends Carta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int entradaSaida;

	public BuracoNegro() {

		nome = "BuracoNegro";
	}

	public int obterEntradaSaida() {
		return entradaSaida;
	}

	public void definirEntradaSaida(int entradaSaida) {
		this.entradaSaida = entradaSaida;
	}

}
