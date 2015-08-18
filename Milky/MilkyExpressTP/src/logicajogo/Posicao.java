package logicajogo;

import logicajogo.cartas.Carta;

public class Posicao {

	private int x;
	private int y;

	private Carta carta;
	private boolean explorada;

	public Posicao(int _x, int _y, Carta card) {

		this.x = _x;
		this.y = _y;

		this.carta = card;
		this.explorada = false;

	}

	public int obterX() {
		return this.x;
	}

	public int obterY() {
		return this.y;
	}

	public Carta obterCarta() {
		return this.carta;
	}

	public void atualizarCarta(Carta card) {

		this.carta = card;

	}

	public void defExplorada(boolean exp) {

		this.explorada = exp;

	}

	public boolean foiExplorada() {
		return this.explorada;
	}

}
