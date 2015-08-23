package logicajogo.cartas;

import java.io.Serializable;

public abstract class Carta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected String nome;
	//private boolean facevisivel;

	public String getNome() {
		return nome;
	}

	public boolean validaNome(String nome) {

		int res = 0;

		res = nome.trim().compareToIgnoreCase(this.nome.trim());

		return (res == 0);
	}

	
}
