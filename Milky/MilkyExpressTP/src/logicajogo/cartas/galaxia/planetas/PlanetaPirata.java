
package logicajogo.cartas.galaxia.planetas;

import java.util.HashMap;
import java.util.Map;

import logicajogo.cartas.Carta;
import logicajogo.cubos.*;


public abstract class PlanetaPirata extends Carta{
	
	  String nome;
	    
	    Map<Cubo,Integer> precario =
	    		new HashMap<>();
	    
	    Cubo [] mercado = new Cubo[3];
	    
	    Cubo verificaCubos(){
	        return null;
	    }
	    

}
