
package logicajogo.cartas.galaxia.planetas;

import logicajogo.cubos.*;
import java.util.HashMap;
import java.util.Map;

public class Tiamat extends Planeta{

	public Tiamat(){
    
		nome = "Tiamat";
        precario.put(new Agua(), 2);
        precario.put(new Comida(), 3);
        precario.put(new Medicamento(), 1);
        precario.put(new Ilegal(), 3);
    
	}

}
