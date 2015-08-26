package ui.grafico;

import java.awt.BorderLayout;
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

		try {
			image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Imagens/stars.png"));

		} catch (Exception e) {
			/* handled in paintComponent() */
			JOptionPane.showMessageDialog(null, e.getMessage());

		}

		setOpaque(false);
		setVisible(true);

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (image != null)
			g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
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

	public void geraTabuleiro(Jogo j) {

		boolean ncrt = true;

		GuiCarta guiCarta = new GuiCarta(new Vazio());
		this.add(guiCarta);

		setLayout(new GridLayout(8, 11));

		for (int y = 0; y < 7; y++) {

			for (int x = 0; x < 9; x++) {

				// desenha mapa
				if (x >= 0 && x < 9 && y >= 0 && y < 7) {

					ncrt = false;

					Carta card = j.devolveMapa().obtemCarta(x, y);
					Posicao pos = j.devolveMapa().consultaPosicao(x, y);

					int aix = j.obterIAJogo().obterNave().posicaoAtual()[0];
					int aiy = j.obterIAJogo().obterNave().posicaoAtual()[1];

					int jx = j.consultaJogador().obterNave().posicaoAtual()[0];
					int jy = j.consultaJogador().obterNave().posicaoAtual()[1];

					if (aix == x && aiy == y) {
						// ncrt = true;
						// nave inimiga
					}

					if (jx == x && jy == y) {
						// nave jog
						// ncrt = true;
					}

					if (jx == aix && aiy == jy && jx == x && jy == y) {
						// as duas
						// ncrt = true;
					}

					if (!ncrt) {

						if (card == null) {
							JButton jb = new JButton("espaço");
							jb.setBackground(Color.black);

							this.add(jb);
						}

						if (pos.foiExplorada() && card != null) {
							JButton jb = new JButton(card.getNome());
							
							
							if (card instanceof BuracoNegro)
								jb.setBackground(Color.lightGray);
							else if (card instanceof PlanetaPirata)
								jb.setBackground(Color.orange);
							else if (card instanceof Planeta)
								jb.setBackground(Color.cyan);
							else
								jb.setBackground(Color.magenta);
							
							this.add(jb);
							
							
						}

						if (!pos.foiExplorada() && card != null) {

							JButton jb = new JButton(card.getNome());
				
							
							if (card instanceof BuracoNegro)
								jb.setBackground(Color.lightGray);
							else if (card instanceof PlanetaPirata)
								jb.setBackground(Color.orange);
							else if (card instanceof Planeta)
								jb.setBackground(Color.cyan);
							else
								jb.setBackground(Color.magenta);
						}

					}

				} else {

					// desenha coordenadas
					if (x < 9) {

						// if (x >= 0 || y >= 0)
						// System.out.print("|");
						//
						// if (y == -1 && x >= 0)
						// System.out.print(x);
						// if (x == -1 && y >= 0)
						// System.out.print(y);
						//
						// if (x == -1 && y == -1)
						// System.out.print(" ");
						//
						// if (x >= 0 || y >= 0)
						// System.out.print("|");
					}

				}

			}
		}

	}

}