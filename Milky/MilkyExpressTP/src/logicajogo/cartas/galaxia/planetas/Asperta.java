
package logicajogo.cartas.galaxia.planetas;

import java.io.Serializable;

import logicajogo.cubos.*;

public class Asperta extends PlanetaPirata implements Serializable{    
    
        /**
	 * 
	 */
	private static final long serialVersionUID = -7944742211804793124L;

		public Asperta(){
        	
        nome = "Asperta";
        precario.put(new Agua(), 3);
        
        
    }
}
