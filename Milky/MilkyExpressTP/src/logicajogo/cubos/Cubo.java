
package logicajogo.cubos;

import java.awt.Color;
import java.io.Serializable;

public abstract class Cubo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private Color cor; 
	
    Cubo(Color cor){this.cor=cor;}

	public String obtemNome() {
		return nome;
	}

	public void defineNome(String nome) {
		this.nome = nome;
	}

	public Color obtemCor() {
		return cor;
	}
    


}
