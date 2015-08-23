
package logicajogo.cartas.galaxia.planetas;

import logicajogo.cubos.*;

import java.io.Serializable;

public class Kiber extends Planeta implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Kiber(){
    
    	nome = "Kiber";
        precario.put(new Agua(), 3);
        precario.put(new Comida(), 1);
        precario.put(new Medicamento(), 2);
        precario.put(new Ilegal(), 3);
        
        
    }
}
