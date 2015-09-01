package logicajogo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import logicajogo.cartas.Carta;
import logicajogo.cartas.galaxia.planetas.Planeta;
import logicajogo.cartas.galaxia.planetas.PlanetaPirata;

public class Tabuleiro implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Baralho baralhojogo;
	private ArrayList<Posicao> galaxia;
	private Posicao posicalselecionada;

	public Tabuleiro() {

		posicalselecionada = null;

		galaxia = new ArrayList<Posicao>();

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				galaxia.add(new Posicao(i, j, null));
			}
		}
	}

	public Posicao consultaPosicaoSelecionada() {

		return this.posicalselecionada;

	}

	public void definePosicaoSelecionada(Posicao pos) {

		if (this.posicalselecionada != null)
			atualizaPosicao(this.posicalselecionada.obterX(), this.posicalselecionada.obterY(), false);

		atualizaPosicao(pos.obterX(), pos.obterY(), true);

		this.posicalselecionada = pos;

	}

	public Posicao consultaPosicao(int x, int y) {

		for (Posicao tmpg : galaxia) {

			if (tmpg.obterX() == x && tmpg.obterY() == y)
				return tmpg;

		}

		return null;
	}

	public ArrayList<Posicao> listaPlanetasPiratas(boolean explorado) {

		ArrayList<Posicao> tmp = new ArrayList<Posicao>();

		for (Posicao tmpg : galaxia) {

			if (tmpg.foiExplorada() == explorado && tmpg.obterCarta() instanceof PlanetaPirata)
				tmp.add(tmpg);

		}

		return tmp;
	}

	public ArrayList<Posicao> listaPlanetas(boolean explorado, boolean reverse) {

		ArrayList<Posicao> tmp = new ArrayList<Posicao>();

		if (!reverse)
			for (Posicao tmpg : galaxia) {

				if (tmpg.foiExplorada() == explorado && tmpg.obterCarta() instanceof Planeta)
					tmp.add(tmpg);

			}

		if (reverse) {

			for (int x = 8; x >= 0; x--)
				for (int y = 0; y < 7; y++) {

					Posicao tmpg = consultaPosicao(x, y);

					if (tmpg.foiExplorada() == explorado && tmpg.obterCarta() instanceof Planeta)
						tmp.add(tmpg);

				}

		}

		return tmp;
	}

	private void atualizaPosicao(int x, int y, boolean exp, Carta card) {

		for (Posicao tmpg : galaxia) {

			if (tmpg.obterX() == x && tmpg.obterY() == y) {
				tmpg.defExplorada(exp);
				tmpg.atualizarCarta(card);
			}
		}
	}

	public void geraGalaxia() {

		baralhojogo = new Baralho();

		Carta wrm1 = baralhojogo.retiraCartaTipo("buraconegro");
		Carta wrm2 = baralhojogo.retiraCartaTipo("buraconegro");

		atualizaPosicao(0, 6, true, wrm1);
		atualizaPosicao(8, 0, true, wrm2);

		atualizaPosicao(0, 4, false, baralhojogo.retiraCartaAleatoria());
		atualizaPosicao(0, 5, false, baralhojogo.retiraCartaAleatoria());
		atualizaPosicao(1, 3, false, baralhojogo.retiraCartaAleatoria());
		atualizaPosicao(1, 4, false, baralhojogo.retiraCartaAleatoria());
		atualizaPosicao(2, 2, false, baralhojogo.retiraCartaAleatoria());
		atualizaPosicao(2, 3, false, baralhojogo.retiraCartaAleatoria());
		atualizaPosicao(2, 4, false, baralhojogo.retiraCartaAleatoria());
		atualizaPosicao(3, 1, false, baralhojogo.retiraCartaAleatoria());
		atualizaPosicao(3, 2, false, baralhojogo.retiraCartaAleatoria());
		atualizaPosicao(3, 3, false, baralhojogo.retiraCartaAleatoria());
		atualizaPosicao(4, 2, false, baralhojogo.retiraCartaAleatoria());
		atualizaPosicao(4, 3, false, baralhojogo.retiraCartaAleatoria());
		atualizaPosicao(4, 4, false, baralhojogo.retiraCartaAleatoria());
		atualizaPosicao(5, 3, false, baralhojogo.retiraCartaAleatoria());
		atualizaPosicao(5, 4, false, baralhojogo.retiraCartaAleatoria());
		atualizaPosicao(5, 5, false, baralhojogo.retiraCartaAleatoria());
		atualizaPosicao(6, 2, false, baralhojogo.retiraCartaAleatoria());
		atualizaPosicao(6, 3, false, baralhojogo.retiraCartaAleatoria());
		atualizaPosicao(6, 4, false, baralhojogo.retiraCartaAleatoria());
		atualizaPosicao(7, 2, false, baralhojogo.retiraCartaAleatoria());
		atualizaPosicao(7, 3, false, baralhojogo.retiraCartaAleatoria());
		atualizaPosicao(8, 2, false, baralhojogo.retiraCartaAleatoria());
		atualizaPosicao(8, 1, false, baralhojogo.retiraCartaAleatoria());

	}

	public Carta obtemCarta(int x, int y) {

		return consultaPosicao(x, y).obterCarta();
	}

	public void viraCartasAdjacentes(int x, int y) {

		Posicao atual = consultaPosicao(x, y);

		int nx = atual.obterX() + 1;
		int ny = atual.obterY() + 1;
		int px = atual.obterX() - 1;
		int py = atual.obterY() - 1;

		Posicao nxp = consultaPosicao(nx, y);
		Posicao nyp = consultaPosicao(x, ny);
		Posicao pxp = consultaPosicao(px, y);
		Posicao pyp = consultaPosicao(x, py);

		Posicao pnxpy = consultaPosicao(nx, py);
		Posicao pnxny = consultaPosicao(nx, ny);
		Posicao ppxpy = consultaPosicao(px, py);
		Posicao ppxny = consultaPosicao(px, py);

		if (nxp != null && nxp.obterCarta() != null && !nxp.foiExplorada())
			nxp.defExplorada(true);
		if (nyp != null && nyp.obterCarta() != null && !nyp.foiExplorada())
			nyp.defExplorada(true);
		if (pxp != null && pxp.obterCarta() != null && !pxp.foiExplorada())
			pxp.defExplorada(true);
		if (pyp != null && pyp.obterCarta() != null && !pyp.foiExplorada())
			pyp.defExplorada(true);

		if (pnxpy != null && pnxpy.obterCarta() != null && !pnxpy.foiExplorada())
			pnxpy.defExplorada(true);
		if (pnxny != null && pnxny.obterCarta() != null && !pnxny.foiExplorada())
			pnxny.defExplorada(true);
		if (ppxpy != null && ppxpy.obterCarta() != null && !ppxpy.foiExplorada())
			ppxpy.defExplorada(true);
		if (ppxny != null && ppxny.obterCarta() != null && !ppxny.foiExplorada())
			ppxny.defExplorada(true);

	}

	public Posicao escolheAdjacentAleatorio(int x, int y) {

		ArrayList<Posicao> posicoes = new ArrayList<Posicao>();

		Posicao atual = consultaPosicao(x, y);

		int nx = atual.obterX() + 1;
		int ny = atual.obterY() + 1;
		int px = atual.obterX() - 1;
		int py = atual.obterY() - 1;

		posicoes.add(consultaPosicao(nx, y));
		posicoes.add(consultaPosicao(x, ny));
		posicoes.add(consultaPosicao(px, y));
		posicoes.add(consultaPosicao(x, py));

		posicoes.add(consultaPosicao(nx, py));
		posicoes.add(consultaPosicao(nx, ny));
		posicoes.add(consultaPosicao(px, py));
		posicoes.add(consultaPosicao(px, py));

		Collections.shuffle(posicoes);

		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());

		int ranint = rand.nextInt(8 - 1) + 1;

		return posicoes.get(ranint);

	}

	private void atualizaPosicao(int x, int y, boolean sel) {

		for (Posicao tmpg : galaxia) {

			if (tmpg.obterX() == x && tmpg.obterY() == y) {
				tmpg.defineSelecionado(sel);
			}
		}
	}

	public boolean todasViradas() {

		int cnt = 0;

		for (Posicao tmpg : galaxia) {

			if (tmpg.foiExplorada())
				cnt++;

		}

		return (cnt == 25);
	}

	public boolean validaMovimentoAdjacentes(int x, int y, int fx, int fy) {

		Posicao atual = consultaPosicao(x, y);

		int nx = atual.obterX() + 1;
		int ny = atual.obterY() + 1;
		int px = atual.obterX() - 1;
		int py = atual.obterY() - 1;

		for (int rowNum = px; rowNum <= nx; rowNum++) {
			for (int colNum = py; colNum <= ny; colNum++) {
				if (fx == rowNum && fy == colNum)
					return true;
			}
		}

		return false;

	}

}
