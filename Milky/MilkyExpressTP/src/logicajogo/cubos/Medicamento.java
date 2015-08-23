
package logicajogo.cubos;

import java.awt.Color;
import java.io.Serializable;

public class Medicamento extends Cubo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Medicamento() {

		super(Color.red);
		this.defineNome("Medicamento");
	}
    
}
