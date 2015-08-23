
package logicajogo.cartas.galaxia.planetas;

import java.io.Serializable;

import logicajogo.cubos.Medicamento;


public class Striterax extends PlanetaPirata  implements Serializable{    
    
        /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		public Striterax(){
        	
        nome = "Striterax";
        precario.put(new Medicamento(), 3);
        
    }
}
