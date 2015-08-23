
package logicajogo.cubos;

import java.awt.Color;
import java.io.Serializable;

public class Ilegal extends Cubo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Ilegal() {

		super(Color.black);
		this.defineNome("Ilegal");

	}

}
