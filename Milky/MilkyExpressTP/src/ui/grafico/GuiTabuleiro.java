package ui.grafico;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.Serializable;

import javax.imageio.ImageIO;
import javax.swing.*;

import logicajogo.Jogo;
import logicajogo.Posicao;
import logicajogo.cartas.Carta;
import logicajogo.cartas.galaxia.*;
import logicajogo.cartas.galaxia.planetas.*;

public class GuiTabuleiro extends JPanel implements MouseMotionListener, Serializable {

	private static final long serialVersionUID = 1L;
	private BufferedImage image;

	public GuiTabuleiro() {

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

	public void geraTabuleiro(Jogo j) {

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
				
				if (jx == pos.obterX() && jy == pos.obterY())
					guiCarta.naveNaArea(true);
				
				this.add(guiCarta);
			}

			if (pos.foiExplorada() && card != null) {

				GuiCarta guiCarta = new GuiCarta(card, 3);

				if (card instanceof BuracoNegro) {

				} else if (card instanceof PlanetaPirata) {

				} else if (card instanceof Planeta) {

				} else {

				}

				if (jx == pos.obterX() && jy == pos.obterY())
					guiCarta.naveNaArea(true);

				this.add(guiCarta);

			}

			repaint();
			revalidate();
		} // fim for

	}

}