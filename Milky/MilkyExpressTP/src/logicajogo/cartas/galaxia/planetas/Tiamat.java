
package logicajogo.cartas.galaxia.planetas;

import logicajogo.cubos.*;

import java.io.Serializable;

public class Tiamat extends Planeta implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Tiamat(){
    
		nome = "Tiamat";
        precario.put(new Agua(), 2);
        precario.put(new Comida(), 3);
        precario.put(new Medicamento(), 1);
        precario.put(new Ilegal(), 3);
    
	}

}
