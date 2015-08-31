package ui.grafico;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import logicajogo.Jogo;
import logicajogo.cartas.Carta;
import logicajogo.cartas.galaxia.planetas.Planeta;
import logicajogo.cartas.galaxia.planetas.PlanetaBase;
import logicajogo.cartas.galaxia.planetas.PlanetaPirata;
import logicajogo.cartas.naves.Nave;
import logicajogo.cubos.Cubo;
import logicajogo.estados.Negociar;

public class PainelOpcoesJogador extends JPanel implements Observer, MouseMotionListener, Serializable {

	private static final long serialVersionUID = 1L;

	private int sx = 100, sy = 100;

	private Jogo jogo;

	private void adiconaComprar() {

		int x = this.jogo.consultaJogador().obterNave().posicaoAtual()[0];
		int y = this.jogo.consultaJogador().obterNave().posicaoAtual()[1];

		Carta carta = this.jogo.devolveMapa().obtemCarta(x, y);

		if (carta != null && (carta instanceof Planeta || carta instanceof PlanetaPirata)) {

			PlanetaBase pl = (PlanetaBase) carta;

			Cubo[] stock = pl.obterStock();

			if (stock[0] != null) {

				JButton comprar1 = new JButton("Comprar " + stock[0].obtemNome());

				comprar1.setAlignmentX(BOTTOM_ALIGNMENT);
				comprar1.setAlignmentY(CENTER_ALIGNMENT);

				comprar1.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						if (carta != null && (carta instanceof Planeta || carta instanceof PlanetaPirata)) {

							Cubo[] stock = pl.obterStock();

							if (stock[0] != null) {
								jogo.comprarBens(stock[0]);
							}
						}
					}
				});

				add(comprar1);

			}

			if (stock.length > 1)
				if (stock[1] != null) {

					JButton comprar2 = new JButton("Comprar " + stock[1].obtemNome());

					comprar2.setAlignmentX(BOTTOM_ALIGNMENT);
					comprar2.setAlignmentY(CENTER_ALIGNMENT);

					comprar2.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {
							// TODO Auto-generated method stub
							if (carta != null && (carta instanceof Planeta || carta instanceof PlanetaPirata)) {

								Cubo[] stock = pl.obterStock();

								if (stock[1] != null) {
									jogo.comprarBens(stock[1]);
								}
							}
						}
					});

					add(comprar2);
				}

		} // fim ver pl

	}

	private void adicionarVender() {

		int x = this.jogo.consultaJogador().obterNave().posicaoAtual()[0];
		int y = this.jogo.consultaJogador().obterNave().posicaoAtual()[1];

		Nave nave = this.jogo.consultaJogador().obterNave();

		Carta carta = this.jogo.devolveMapa().obtemCarta(x, y);

		if (carta != null && (carta instanceof Planeta || carta instanceof PlanetaPirata)) {

			Cubo[] carga = nave.obterCarga();

			if (nave.obterTotalCargaOcupada() > 0) {

				for (int i = 0; i < nave.obterTotalCargaOcupada(); i++) {

					final int idx = i;

					JButton vender = new JButton("Vender " + carga[i].obtemNome());

					vender.setAlignmentX(BOTTOM_ALIGNMENT);
					vender.setAlignmentY(CENTER_ALIGNMENT);

					vender.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {
							// TODO Auto-generated method stub
							if (carta != null && (carta instanceof Planeta || carta instanceof PlanetaPirata)) {

								if (carga[idx] != null) {
									jogo.venderBens(carga[idx]);
								}
							}
						}
					});

					add(vender);

				}
			}
		}
	}

	public PainelOpcoesJogador(Jogo j) {

		jogo = j;

		j.addObserver(this);

		setPreferredSize(new Dimension(sx, sy));
		setMaximumSize(new Dimension(sx, sy));
		setOpaque(false);
		setVisible(true);

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

		this.jogo = (Jogo) arg0;

		if (this.jogo.devolveEstado() instanceof Negociar) {

			this.adiconaComprar();
			this.adicionarVender();

		}

		repaint();
		revalidate();

	}

}
