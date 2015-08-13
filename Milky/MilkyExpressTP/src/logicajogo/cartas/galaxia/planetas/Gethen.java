
package logicajogo.cartas.galaxia.planetas;


import logicajogo.cubos.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Gethen extends Planeta{    
    
        public Gethen(){
        	
        nome = "Gethen";
        precario.put(new Agua(), 1);
        precario.put(new Comida(), 3);
        precario.put(new Medicamento(), 2);
        precario.put(new Ilegal(), 3);                
        
    }


}
