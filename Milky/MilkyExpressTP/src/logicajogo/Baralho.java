package logicajogo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import logicajogo.cartas.Carta;
import logicajogo.cartas.galaxia.*;
import logicajogo.cartas.galaxia.planetas.*;

public class Baralho implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public class CartaBaralho implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private Carta carta;
		private boolean retirada;

		public CartaBaralho(Carta carta) {

			this.carta = carta;
			retirada = false;

		};
	}

	private ArrayList<CartaBaralho> baralho;

	public Baralho() {

		baralho = new ArrayList<CartaBaralho>();

		for (int i = 0; i < 4; i++)
			baralho.add(new CartaBaralho(new BuracoNegro()));

		for (int i = 0; i < 12; i++)
			baralho.add(new CartaBaralho(new Vazio()));

		// Nao Pirata
		baralho.add(new CartaBaralho(new Gethen()));
		baralho.add(new CartaBaralho(new Kiber()));
		baralho.add(new CartaBaralho(new Reverie()));
		baralho.add(new CartaBaralho(new Tiamat()));
		baralho.add(new CartaBaralho(new Lamarckia()));
		baralho.add(new CartaBaralho(new Arrakis()));

		// Pirata
		baralho.add(new CartaBaralho(new Whirl()));
		baralho.add(new CartaBaralho(new Striterax()));
		baralho.add(new CartaBaralho(new Asperta()));

	}

	public Carta retiraCartaTipo(String tipo) {

		for (CartaBaralho cartabar : baralho) {

			if (!cartabar.retirada && cartabar.carta.validaNome(tipo)) {
				cartabar.retirada = true;
				return cartabar.carta;
			}
		}

		return null;
	}

	public Carta retiraCartaAleatoria() {

		Random rand = new Random();
		int min = 1;
		int max = 25;
		int cr = 0;
		int cnt = 0;

		Carta card = null;

		while (card == null) {

			cr = rand.nextInt((max - min) + 1) + min;

			cnt = 0;

			for (CartaBaralho cartabar : baralho) {

				if (!cartabar.retirada && cnt == cr) {
					cartabar.retirada = true;
					card = cartabar.carta;
				}

				cnt++;
			}
		}

		return card;
	}

}
