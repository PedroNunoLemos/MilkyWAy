
package logicajogo.cartas.galaxia.planetas;

import logicajogo.cubos.*;
import java.util.HashMap;
import java.util.Map;

public class Reverie extends Planeta{
	
    public Reverie(){
    	
        nome = "Reverie";
        precario.put(new Agua(), 1);
        precario.put(new Comida(), 2);
        precario.put(new Medicamento(), 3);
        precario.put(new Ilegal(), 3);
        
    }
    
    
}
