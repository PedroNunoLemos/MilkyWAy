
package logicajogo.cartas.galaxia.planetas;

import logicajogo.cubos.*;
import java.util.HashMap;
import java.util.Map;

public class Kiber extends Planeta{
	
    public Kiber(){
    
    	nome = "Kiber";
        precario.put(new Agua(), 3);
        precario.put(new Comida(), 1);
        precario.put(new Medicamento(), 2);
        precario.put(new Ilegal(), 3);
        
        
    }
}
