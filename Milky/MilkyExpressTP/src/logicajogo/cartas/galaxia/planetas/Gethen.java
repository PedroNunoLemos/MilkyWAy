
package logicajogo.cartas.galaxia.planetas;


import logicajogo.cubos.*;

import java.io.Serializable;

public class Gethen extends Planeta implements Serializable{    
    
        /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		public Gethen(){
        	
        nome = "Gethen";
        precario.put(new Agua(), 1);
        precario.put(new Comida(), 3);
        precario.put(new Medicamento(), 2);
        precario.put(new Ilegal(), 3);                
        
    }


}
