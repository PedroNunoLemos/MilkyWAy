
package logicajogo.cartas.galaxia.planetas;

import  logicajogo.cubos.*;
import java.util.HashMap;
import java.util.Map;


public class Lamarckia extends Planeta{
    
    public Lamarckia(){
        nome = "Lamarckia";
        precario.put(new Agua(), 1);
        precario.put(new Comida(), 2);
        precario.put(new Medicamento(), 3);
        precario.put(new Ilegal(), 3);
    }
}
