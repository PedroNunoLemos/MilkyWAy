
package logicajogo.cartas.galaxia.planetas;

import logicajogo.cubos.*;

import java.io.Serializable;

public class Reverie extends Planeta implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Reverie(){
    	
        nome = "Reverie";
        precario.put(new Agua(), 1);
        precario.put(new Comida(), 2);
        precario.put(new Medicamento(), 3);
        precario.put(new Ilegal(), 3);
        
    }
    
    
}
