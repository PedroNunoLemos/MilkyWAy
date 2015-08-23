package logicajogo;

import logicajogo.cartas.naves.*;

public class Jogador {

	private final Nave nave;
	private int moedas;
	private boolean ai;
	private boolean temsuborno;

	public Jogador(int _moedas) {

		nave = new Nave();
		moedas = _moedas;
		temsuborno = false;

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

	public boolean ativouSuborno() {
		return this.temsuborno;
	}

	public void ativaSuborno() {
		this.temsuborno = true;
	}

	public void atualizaMoedas(int moedasadic) {

		if (this.moedas + moedasadic <= 0)
			this.moedas = 0;

		if (this.moedas + moedasadic > 0)
			this.moedas = (this.moedas + moedasadic);

	}

	public Nave obterNave() {
		return nave;
	}

}
