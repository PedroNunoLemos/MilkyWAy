package logicajogo.cartas.galaxia.planetas;

import logicajogo.cubos.*;
import logicajogo.cartas.Carta;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class Planeta extends Carta{
     
    Map<Cubo,Integer> precario =
    		new HashMap<>();
    
    Cubo [] mercado = new Cubo[3];
    
    Cubo verificaCubos(){
        return null;
    }

    
}