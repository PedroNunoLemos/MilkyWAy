
package logicajogo.cartas.galaxia.planetas;


import logicajogo.cubos.*;

import java.io.Serializable;

public class Arrakis extends Planeta implements Serializable{
    
    
        /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		public Arrakis(){
        	
        	
        nome = "Arrakis";
        precario.put(new Agua(), 2);
        precario.put(new Comida(), 1);
        precario.put(new Medicamento(), 3);
        precario.put(new Ilegal(), 3);
        
        
    }
}
