package logicajogo.cubos;

import java.awt.Color;
import java.io.Serializable;

public class Agua extends Cubo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Agua() {
		super(Color.blue);
		this.defineNome("Agua");
		
	}
    
}
