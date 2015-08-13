
package logicajogo.cartas.galaxia.planetas;

import logicajogo.cubos.*;
import logicajogo.cartas.*;
import java.util.HashMap;
import java.util.Map;


public abstract class PlanetaPirata extends Carta{
	
    String nome;
    
    Map<Cubo,Integer> precario = new HashMap<>();
    
    Cubo [] mercado = new Cubo[1];

}
