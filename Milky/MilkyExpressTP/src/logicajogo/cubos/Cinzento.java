package logicajogo.cubos;

import java.awt.Color;
import java.io.Serializable;

public class Cinzento extends Cubo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Cinzento() {
		super(Color.gray);
		
		this.defineNome("Cinzento");
		
	}

}
