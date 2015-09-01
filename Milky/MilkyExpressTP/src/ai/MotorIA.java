package ai;

import java.io.Serializable;

import logicajogo.Jogador;
import logicajogo.Jogo;
import logicajogo.Posicao;
import logicajogo.cartas.naves.Nave;

public class MotorIA implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Jogador jogdr;

	public MotorIA(Jogo jog) {
		this.jogdr = new Jogador(9999, true);
		Posicao pos = jog.devolveMapa().escolheAdjacentAleatorio(4, 4);

		this.jogdr.obterNave().mover(pos.obterX(), pos.obterY());

	}

	public void MoverNave(Jogo jog) {

		Nave nave = this.jogdr.obterNave();

		int x = nave.posicaoAtual()[0];
		int y = nave.posicaoAtual()[1];

		Posicao pos = new Posicao(-1, -1, null);

		do {
			pos = jog.devolveMapa().escolheAdjacentAleatorio(x, y);
		} while (pos.obterCarta() == null);

		this.jogdr.obterNave().mover(pos.obterX(), pos.obterY());

	}

	public Jogador consultaJogadorAI() {
		return this.jogdr;
	}

	public boolean noRadar(Jogo jog, int fx, int fy) {

		Nave nave = this.jogdr.obterNave();

		int x = nave.posicaoAtual()[0];
		int y = nave.posicaoAtual()[1];

		return jog.devolveMapa().validaMovimentoAdjacentes(x, y, fx, fy);

	}

	public Nave obterNave() {

		return this.jogdr.obterNave();
	}

}
