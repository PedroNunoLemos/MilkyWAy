package logicajogo.cartas.galaxia.planetas;

import logicajogo.cubos.*;
import logicajogo.cartas.Carta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public abstract class Planeta extends Carta {

	Map<Cubo, Integer> precario = new HashMap<>();

	private Cubo[] mercado = new Cubo[2];

	public Planeta() {
	}

	public void atualizaMercado(Cubo cubo) {

		for (int i = 0; i < 2; i++) {

			if (mercado[i] == null) {

				mercado[i] = cubo;

			}
		}

	}

	public boolean retiraCuboMercado(Cubo cubo) {

		for (int i = 0; i < 2; i++) {

			if (mercado[i] != null) {
				if (cubo.obtemNome() == mercado[i].obtemNome()) {
					mercado[i] = null;
					return true;
				}
			}
		}

		return false;

	}

	public int obtemPreco(Cubo cubo) {

		return precario.get(cubo).intValue();

	}

	public int obtemPreco(String cubo) {

		for (Entry<Cubo, Integer> entrada : precario.entrySet()) {
		    Cubo cubom = entrada.getKey();
		    Integer preco = entrada.getValue();
		    
		    if (cubom.obtemNome()==cubo)
		    	return preco;
		}
		
		return 0;
	}

}