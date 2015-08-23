
package logicajogo.cartas.galaxia.planetas;

import java.io.Serializable;

import  logicajogo.cubos.*;


public class Lamarckia extends Planeta implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Lamarckia(){
        nome = "Lamarckia";
        precario.put(new Agua(), 1);
        precario.put(new Comida(), 2);
        precario.put(new Medicamento(), 3);
        precario.put(new Ilegal(), 3);
    }
}
