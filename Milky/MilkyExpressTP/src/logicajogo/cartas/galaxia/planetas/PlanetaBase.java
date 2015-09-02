package logicajogo.cartas.galaxia.planetas;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import logicajogo.cartas.Carta;
import logicajogo.cubos.Cubo;

public abstract class PlanetaBase extends Carta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int qtdespacosmercados;
	Map<Cubo, Integer> precario = new HashMap<>();

	private Cubo[] mercado;
	private boolean comprador;

	public PlanetaBase(int mercadoespaco) {
		// TODO Auto-generated constructor stub
		this.qtdespacosmercados = mercadoespaco;
		mercado = new Cubo[mercadoespaco];
		comprador = false;
	}

	public char veMercado(int idx) {

		if (mercado[idx] != null)
			return mercado[idx].obtemNome().charAt(0);

		return ' ';
	}

	public int mercadoEspacosDisponiveis() {

		int cnt = 0;
		for (int i = 0; i < qtdespacosmercados; i++) {

			if (mercado[i] == null) {

				cnt++;

			}
		}

		return cnt;
	}

	public void mercadoReabastecer(Cubo cubo) {

		for (int i = 0; i < qtdespacosmercados; i++) {

			if (mercado[i] == null) {

				mercado[i] = cubo;
				break;

			}
		}

	}

	public boolean mercadoComprador() {
		return this.comprador;
	}

	public boolean mercadoRetirarCubo(Cubo cubo) {

		for (int i = 0; i < qtdespacosmercados; i++) {

			if (mercado[i] != null) {
				if (cubo.obtemNome() == mercado[i].obtemNome()) {
					mercado[i] = null;
					this.comprador = true;
					return true;
				}
			}
		}

		return false;

	}

	public void RetiraComprador() {

		this.comprador = false;
	}

	public Cubo[] obterStock() {

		Cubo[] cubos = new Cubo[qtdespacosmercados];

		cubos = mercado;

		return cubos;

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
