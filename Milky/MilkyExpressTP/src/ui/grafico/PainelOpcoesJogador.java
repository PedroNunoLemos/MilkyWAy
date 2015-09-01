package ui.grafico;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import logicajogo.Jogo;
import logicajogo.cartas.Carta;
import logicajogo.cartas.galaxia.BuracoNegro;
import logicajogo.cartas.galaxia.planetas.Planeta;
import logicajogo.cartas.galaxia.planetas.PlanetaBase;
import logicajogo.cartas.galaxia.planetas.PlanetaPirata;
import logicajogo.cartas.naves.Nave;
import logicajogo.cubos.Cubo;
import logicajogo.estados.AtaquePirata;
import logicajogo.estados.Movimentar;
import logicajogo.estados.Negociar;

public class PainelOpcoesJogador extends JPanel implements Observer, MouseMotionListener, Serializable {

	private static final long serialVersionUID = 1L;

	private int sx = 400, sy = 100;

	private Jogo jogo;

	private JPanel adicionarMovimentar() {

		int x = this.jogo.consultaJogador().obterNave().posicaoAtual()[0];
		int y = this.jogo.consultaJogador().obterNave().posicaoAtual()[1];

		Carta carta = this.jogo.devolveMapa().obtemCarta(x, y);

		if (carta != null) {

			JPanel movimentar = new JPanel();
			movimentar.setOpaque(false);

			if ((carta instanceof BuracoNegro && !this.jogo.consultaJogador().obterNave().viajandoBuracoNegro()
					|| !(carta instanceof BuracoNegro))) {

				// mover
				JButton movimentar1 = new JButton("Mover 1 Posicao ");

				movimentar1.setAlignmentX(BOTTOM_ALIGNMENT);
				movimentar1.setAlignmentY(CENTER_ALIGNMENT);

				movimentar1.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub

						JOptionPane.showMessageDialog(null, "Clique na celula de destino e depois em continuar");

						jogo.defineTipomov(1);
					}
				});

				movimentar.add(movimentar1);

			}

			if (carta != null && carta instanceof BuracoNegro) {
				// buraco negro

				JButton movimentar2 = new JButton("Viajar Buraco Negro");

				movimentar2.setAlignmentX(BOTTOM_ALIGNMENT);
				movimentar2.setAlignmentY(CENTER_ALIGNMENT);

				movimentar2.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub

						JOptionPane.showMessageDialog(null, "Clique na celula de destino e depois em continuar");

						jogo.defineTipomov(2);

					}
				});

				movimentar.add(movimentar2);

			}

			if (!(carta instanceof BuracoNegro)) {
				// warp

				JButton movimentar3 = new JButton("Viajar Modo Warp");

				movimentar3.setAlignmentX(BOTTOM_ALIGNMENT);
				movimentar3.setAlignmentY(CENTER_ALIGNMENT);

				movimentar3.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub

						JOptionPane.showMessageDialog(null, "Clique na celula de destino e depois em continuar");

						jogo.defineTipomov(3);

					}
				});

				movimentar.add(movimentar3);

			}

			return movimentar;

		} // fim

		return null;

	}

	private JPanel adicionarComprar() {

		int x = this.jogo.consultaJogador().obterNave().posicaoAtual()[0];
		int y = this.jogo.consultaJogador().obterNave().posicaoAtual()[1];

		Carta carta = this.jogo.devolveMapa().obtemCarta(x, y);

		if (carta != null && (carta instanceof Planeta || carta instanceof PlanetaPirata)) {

			PlanetaBase pl = (PlanetaBase) carta;

			Cubo[] stock = pl.obterStock();

			JPanel comprar = new JPanel();
			comprar.setOpaque(false);

			TitledBorder bordacentro = BorderFactory.createTitledBorder("Comprar");
			bordacentro.setTitleJustification(TitledBorder.CENTER);
			bordacentro.setTitleColor(Color.white);

			comprar.setBorder(bordacentro);

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

				comprar.add(comprar1);

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

					comprar.add(comprar2);
				}

			return comprar;

		} // fim ver pl

		return null;
	}

	private JPanel adicionarVender() {

		int x = this.jogo.consultaJogador().obterNave().posicaoAtual()[0];
		int y = this.jogo.consultaJogador().obterNave().posicaoAtual()[1];

		Nave nave = this.jogo.consultaJogador().obterNave();

		Carta carta = this.jogo.devolveMapa().obtemCarta(x, y);

		if (carta != null && (carta instanceof Planeta || carta instanceof PlanetaPirata)) {

			JPanel vender = new JPanel();
			vender.setOpaque(false);

			TitledBorder bordacentro = BorderFactory.createTitledBorder("Vender");
			bordacentro.setTitleJustification(TitledBorder.CENTER);
			bordacentro.setTitleColor(Color.white);

			vender.setBorder(bordacentro);

			Cubo[] carga = nave.obterCarga();

			if (nave.obterTotalCargaOcupada() > 0) {

				for (int i = 0; i < nave.obterTotalCargaOcupada(); i++) {

					final int idx = i;

					if (carga[i] != null) {

						JButton vender1 = new JButton("Vender " + carga[i].obtemNome());

						vender1.setAlignmentX(BOTTOM_ALIGNMENT);
						vender1.setAlignmentY(CENTER_ALIGNMENT);

						vender1.addActionListener(new ActionListener() {

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

						vender.add(vender1);
					}
				}
			}

			return vender;
		} // fim vld pl

		return null;

	}

	private JPanel adicionarSuborno() {

		int x = this.jogo.consultaJogador().obterNave().posicaoAtual()[0];
		int y = this.jogo.consultaJogador().obterNave().posicaoAtual()[1];

		Nave nave = this.jogo.consultaJogador().obterNave();

		Carta carta = this.jogo.devolveMapa().obtemCarta(x, y);

		if (carta != null && (carta instanceof Planeta || carta instanceof PlanetaPirata)) {

			JPanel guardar = new JPanel();
			guardar.setOpaque(false);

			TitledBorder bordacentro = BorderFactory.createTitledBorder("Subornar");
			bordacentro.setTitleJustification(TitledBorder.CENTER);
			bordacentro.setTitleColor(Color.white);

			guardar.setBorder(bordacentro);

			Cubo[] carga = nave.obterCarga();

			if (nave.obterTotalCargaOcupada() > 0 && !this.jogo.consultaJogador().ativouSuborno()) {

				for (int i = 0; i < nave.obterTotalCargaOcupada(); i++) {

					final int idx = i;

					if (carga[i] != null) {

						JButton guardar1 = new JButton("Guardar " + carga[i].obtemNome());

						guardar1.setAlignmentX(BOTTOM_ALIGNMENT);
						guardar1.setAlignmentY(CENTER_ALIGNMENT);

						guardar1.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent arg0) {
								// TODO Auto-generated method stub
								if (carta != null && (carta instanceof Planeta || carta instanceof PlanetaPirata)) {

									if (carga[idx] != null) {

										jogo.ativarSuborno((carga[idx]));

									}
								}
							}
						});

						guardar.add(guardar1);
					}

				}

			} // fim vl carga

			return guardar;

		} // fim vld pl

		return null;

	}

	private JPanel adicionarUpgradeNave() {

		int x = this.jogo.consultaJogador().obterNave().posicaoAtual()[0];
		int y = this.jogo.consultaJogador().obterNave().posicaoAtual()[1];

		Nave nave = this.jogo.consultaJogador().obterNave();

		Carta carta = this.jogo.devolveMapa().obtemCarta(x, y);

		if (carta != null && (carta instanceof Planeta || carta instanceof PlanetaPirata)) {

			JPanel atualizar = new JPanel();
			atualizar.setOpaque(false);

			TitledBorder bordacentro = BorderFactory.createTitledBorder("Atualizar Nave");
			bordacentro.setTitleJustification(TitledBorder.CENTER);
			bordacentro.setTitleColor(Color.white);

			atualizar.setBorder(bordacentro);

			if (!nave.maxForca()) {

				String pr = String.valueOf(nave.obterProximoCustoUpgradeForca());

				JButton atualizar1 = new JButton("Força (" + pr + ") Moedas");

				atualizar1.setAlignmentX(BOTTOM_ALIGNMENT);
				atualizar1.setAlignmentY(CENTER_ALIGNMENT);

				atualizar1.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						if (carta != null && (carta instanceof Planeta || carta instanceof PlanetaPirata)) {

							jogo.atualizarNave(1);

						}
					}
				});

				atualizar.add(atualizar1);

			} // fim atualizar forca

			if (!nave.naveCargaMaxima()) {

				String pr = String.valueOf(nave.obterProximoCustoUpgradeForca());

				JButton atualizar2 = new JButton("Carga (" + pr + ") Moedas");

				atualizar2.setAlignmentX(BOTTOM_ALIGNMENT);
				atualizar2.setAlignmentY(CENTER_ALIGNMENT);

				atualizar2.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						if (carta != null && (carta instanceof Planeta || carta instanceof PlanetaPirata)) {

							jogo.atualizarNave(2);

						}
					}

				});

				atualizar.add(atualizar2);

			} // fim atualizar carga

			return atualizar;

		} // fim vld pl

		return null;

	}

	private JPanel adicionarAtaquePirata() {

		JPanel ataque = new JPanel();
		ataque.setOpaque(false);

		TitledBorder bordacentro = BorderFactory.createTitledBorder("Ataque Pirata");
		bordacentro.setTitleJustification(TitledBorder.CENTER);
		bordacentro.setTitleColor(Color.white);

		ataque.setBorder(bordacentro);

		JButton ataque1 = new JButton("Combater");

		ataque1.setAlignmentX(BOTTOM_ALIGNMENT);
		ataque1.setAlignmentY(CENTER_ALIGNMENT);

		ataque1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				jogo.combaterPiratas();

				removeAll();
				repaint();
				validate();

			}
		});

		ataque.add(ataque1);

		return ataque;

	}

	public PainelOpcoesJogador(Jogo j) {

		jogo = j;

		j.addObserver(this);

		this.setLayout(new GridLayout(1, 4));

		setPreferredSize(new Dimension(sx, sy));
		setMaximumSize(new Dimension(sx, sy));

		setOpaque(false);
		setVisible(true);

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setFont(new Font("Arial", Font.BOLD, 15));

		g.setColor(Color.getHSBColor(170, 15, 100));

		int x = this.jogo.consultaJogador().obterNave().posicaoAtual()[0];
		int y = this.jogo.consultaJogador().obterNave().posicaoAtual()[1];

		Carta carta = this.jogo.devolveMapa().obtemCarta(x, y);

		if (this.jogo.devolveEstado() instanceof Negociar) {

			if (carta != null && !(carta instanceof Planeta || carta instanceof PlanetaPirata)

			) {
				String estado = "Não esta posicionado num planeta";
				g.setFont(new Font("Arial", Font.BOLD, 14));

				Graphics2D g2d = (Graphics2D) g;
				FontMetrics fm = g2d.getFontMetrics();
				Rectangle2D r = fm.getStringBounds(estado, g2d);

				int sx = (this.getWidth() - (int) r.getWidth()) / 2;
				int sy = (this.getHeight() - (int) r.getHeight()) / 2 + fm.getAscent();

				g.drawString(estado, sx, sy);

			}
		}

		g.setFont(new Font("Arial", Font.BOLD, 15));
		g.drawString("", 90, 65);
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

		this.removeAll();

		if (this.jogo.devolveEstado() instanceof Movimentar) {

			this.removeAll();

			JPanel movimentar = adicionarMovimentar();

			if (movimentar != null)
				this.add(movimentar);

		}

		if (this.jogo.devolveEstado() instanceof Negociar) {

			JPanel comprar = adicionarComprar();
			JPanel vender = adicionarVender();
			JPanel subornar = adicionarSuborno();
			JPanel atualizar = adicionarUpgradeNave();

			if (comprar != null)
				this.add(comprar);

			if (vender != null)
				this.add(vender);

			if (subornar != null)
				this.add(subornar);

			if (atualizar != null)
				this.add(atualizar);

		}

		if (this.jogo.devolveEstado() instanceof AtaquePirata) {

			this.removeAll();

			JPanel ataque = adicionarAtaquePirata();

			this.add(ataque);

		}

		repaint();
		revalidate();

	}

}
