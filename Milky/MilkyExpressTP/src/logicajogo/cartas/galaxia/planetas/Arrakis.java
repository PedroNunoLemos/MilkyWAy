
package logicajogo.cartas.galaxia.planetas;


import logicajogo.cubos.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Arrakis extends Planeta{
    
    
        public Arrakis(){
        	
        	
        nome = "Arrakis";
        precario.put(new Agua(), 2);
        precario.put(new Comida(), 1);
        precario.put(new Medicamento(), 3);
        precario.put(new Ilegal(), 3);
        
        
    }
}
