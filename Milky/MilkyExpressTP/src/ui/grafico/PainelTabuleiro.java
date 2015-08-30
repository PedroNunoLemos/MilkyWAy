package ui.grafico;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.Serializable;

import javax.swing.*;

import logicajogo.Jogo;
import logicajogo.Posicao;
import logicajogo.cartas.Carta;
import logicajogo.cartas.galaxia.*;
import logicajogo.cartas.galaxia.planetas.*;

public class PainelTabuleiro extends JPanel implements MouseMotionListener, Serializable {

	private static final long serialVersionUID = 1L;


	public PainelTabuleiro() {

		setOpaque(false);
		setVisible(true);

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

		JOptionPane.showMessageDialog(null, "My Goodness, this is so concise");

	}

	private Posicao[] geraMapaSeq(Jogo j) {

		Posicao[] poslst = new Posicao[63];

		for (int y = 0; y < 7; y++) {
			for (int x = 0; x < 9; x++) {

				Posicao pos = j.devolveMapa().consultaPosicao(x, y);
				poslst[y * 9 + x] = pos;
			}
		}

		return poslst;

	}

	public void atualizaMapa(Jogo j) {

		this.removeAll();

		GridLayout grelha = new GridLayout(7, 9, 0, 0);

		setLayout(grelha);

		Posicao[] posics = this.geraMapaSeq(j);

		for (int i = 0; i < 63; i++) {

			// desenha mapa

			Posicao pos = posics[i];
			Carta card = pos.obterCarta();

			int x = 0, y = 0;

			x = pos.obterX();
			y = pos.obterY();

			int aix = j.obterIAJogo().obterNave().posicaoAtual()[0];
			int aiy = j.obterIAJogo().obterNave().posicaoAtual()[1];

			int jx = j.consultaJogador().obterNave().posicaoAtual()[0];
			int jy = j.consultaJogador().obterNave().posicaoAtual()[1];

			if (card == null) {

				GuiCarta guiCarta = new GuiCarta(card, 0);

				this.add(guiCarta);

			}

			if (!pos.foiExplorada() && card != null) {

				GuiCarta guiCarta = new GuiCarta(card, 1);

				if (jx == x && jy == y)
					guiCarta.naveNaArea(true, false);

				if (aix == x && aiy == y && pos.foiExplorada())
					guiCarta.naveNaArea(true, true);

				this.add(guiCarta);
			}

			if (pos.foiExplorada() && card != null) {

				GuiCarta guiCarta;

				if (card instanceof BuracoNegro) {

					guiCarta = new GuiCarta(card, 3);

				} else if (card instanceof PlanetaPirata) {
					guiCarta = new GuiCarta(new Striterax(), 3);

				} else if (card instanceof Planeta) {

					guiCarta = new GuiCarta(card, 3);

				} else {

					guiCarta = new GuiCarta(card, 3);

				}

				if (jx == x && jy == y)
					guiCarta.naveNaArea(true, false);

				if (aix == x && aiy == y && pos.foiExplorada())
					guiCarta.naveNaArea(true, true);

				this.add(guiCarta);

			}

			repaint();
			revalidate();
		} // fim for

	}

}