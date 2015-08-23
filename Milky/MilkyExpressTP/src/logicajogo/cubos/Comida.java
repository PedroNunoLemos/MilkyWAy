package logicajogo.cubos;

import java.awt.Color;
import java.io.Serializable;

public class Comida extends Cubo  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Comida() {
		super(Color.yellow);
		this.defineNome("Comida");
	}
	
}
