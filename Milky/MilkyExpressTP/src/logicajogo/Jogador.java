package logicajogo;

import logicajogo.cartas.naves.*;

public class Jogador {

	private final Nave nave;
	private int moedas;
	private int moedasbase;
	private boolean ai;

	public Jogador(int _moedas) {

		nave = new Nave();
		moedas = _moedas;
		moedasbase = _moedas;

	}

	public int devolveMoedas() {
		return moedas;
	}

	public boolean computador() {
		return ai;
	}

	public void modocomputador() {
		this.ai = true;
	}

	public void defineMoedas(int moedasadic) {

		if (this.moedasbase + moedasadic <= 0)
			this.moedas = 0;

		if (this.moedasbase + moedasadic > 0)
			this.moedas = (this.moedasbase + moedasadic);

	}

	public Nave getNave() {
		return nave;
	}

}
