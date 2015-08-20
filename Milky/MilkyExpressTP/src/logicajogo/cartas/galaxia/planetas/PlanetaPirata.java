
package logicajogo.cartas.galaxia.planetas;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import logicajogo.cartas.Carta;
import logicajogo.cubos.*;

public abstract class PlanetaPirata extends Carta {

	Map<Cubo, Integer> precario = new HashMap<>();

	private Cubo[] mercado = new Cubo[1];

	public PlanetaPirata() {
	}

	public int validaMercadoAberto() {

		int cnt =0;
		for (int i = 0; i < 1; i++) {

			if (mercado[i] == null) {

				 cnt++;

			}
		}

		return cnt;
	}

	public void atualizaMercado(Cubo cubo) {

		for (int i = 0; i < 1; i++) {

			if (mercado[i] == null) {

				mercado[i] = cubo;

			}
		}

	}

	public boolean retiraCuboMercado(Cubo cubo) {

		for (int i = 0; i < 1; i++) {

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

			if (cubom.obtemNome() == cubo)
				return preco;
		}

		return 0;
	}

}
