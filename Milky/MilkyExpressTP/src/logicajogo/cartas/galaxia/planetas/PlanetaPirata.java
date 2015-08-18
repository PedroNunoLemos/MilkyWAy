
package logicajogo.cartas.galaxia.planetas;

import java.util.HashMap;
import java.util.Map;

import logicajogo.cartas.Carta;
import logicajogo.cubos.*;


public abstract class PlanetaPirata extends Carta{


	Map<Cubo,Integer> precario =
	    		new HashMap<>();
	    
	    Cubo [] mercado = new Cubo[3];
	    
	    Cubo verificaCubos(){
	        return null;
	    }
	    

}
