package logicajogo;

import java.util.ArrayList;
import java.util.HashMap;

import logicajogo.cartas.Carta;

public class Tabuleiro {

	private Baralho baralhojogo;
	private ArrayList<Posicao> galaxia;

	public Tabuleiro() {

		galaxia = new ArrayList<Posicao>();

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				galaxia.add(new Posicao(i, j, null));
			}
		}
	}

	public Posicao consultaPosicao(int x, int y) {

		for (Posicao tmpg : galaxia) {

			if (tmpg.obterX() == x && tmpg.obterY() == y)
				return tmpg;

		}

		return null;
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
