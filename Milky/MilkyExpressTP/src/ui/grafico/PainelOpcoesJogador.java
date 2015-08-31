package ui.grafico;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import logicajogo.Jogo;
import logicajogo.Posicao;
import logicajogo.cartas.Carta;
import logicajogo.cartas.galaxia.BuracoNegro;
import logicajogo.cartas.galaxia.planetas.Planeta;
import logicajogo.cartas.galaxia.planetas.PlanetaBase;
import logicajogo.cartas.galaxia.planetas.PlanetaPirata;
import logicajogo.cartas.naves.Nave;
import logicajogo.cubos.Cubo;
import logicajogo.estados.Movimentar;
import logicajogo.estados.Negociar;

public class PainelOpcoesJogador extends JPanel implements Observer, MouseMotionListener, Serializable {

	private static final long serialVersionUID = 1L;

	private int sx = 100, sy = 100;

	private Jogo jogo;

	private JPanel adicionarMovimentar() {

		int x = this.jogo.consultaJogador().obterNave().posicaoAtual()[0];
		int y = this.jogo.consultaJogador().obterNave().posicaoAtual()[1];

		Nave nave = this.jogo.consultaJogador().obterNave();

		Carta carta = this.jogo.devolveMapa().obtemCarta(x, y);

		if (carta != null) {

			JPanel movimentar = new JPanel();
			movimentar.setBorder(BorderFactory.createTitledBorder("Movimentar"));

			if ((carta instanceof BuracoNegro && !nave.viajandoBuracoNegro()) || !(carta instanceof BuracoNegro)) {
				// mover
				JButton movimentar1 = new JButton("Mover Nave ");

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

				JButton movimentar2 = new JButton("Mover Buraco Negro");

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

				JButton movimentar3 = new JButton("Mover Modo Warp");

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
			comprar.setBorder(BorderFactory.createTitledBorder("Comprar"));

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
			vender.setBorder(BorderFactory.createTitledBorder("Comprar"));

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
			guardar.setBorder(BorderFactory.createTitledBorder("Subornar"));

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

									if (idx >= 1 && idx <= 3)
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
			atualizar.setBorder(BorderFactory.createTitledBorder("Atualizar Nave"));

			if (!nave.maxForca()) {

				String pr = String.valueOf(nave.obterProximoCustoUpgradeForca());

				JButton atualizar1 = new JButton("Atualizar For�a (" + pr + ") Moedas");

				atualizar1.setAlignmentX(BOTTOM_ALIGNMENT);
				atualizar1.setAlignmentY(CENTER_ALIGNMENT);

				atualizar1.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						if (carta != null && (carta instanceof Planeta || carta instanceof PlanetaPirata)) {

							jogo.atualizarNave(0);

						}
					}
				});

				atualizar.add(atualizar1);

			} // fim atualizar forca

			if (!nave.naveCargaMaxima()) {

				String pr = String.valueOf(nave.obterProximoCustoUpgradeForca());

				JButton atualizar2 = new JButton("Atualizar Carga (" + pr + ") Moedas");

				atualizar2.setAlignmentX(BOTTOM_ALIGNMENT);
				atualizar2.setAlignmentY(CENTER_ALIGNMENT);

				atualizar2.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						if (carta != null && (carta instanceof Planeta || carta instanceof PlanetaPirata)) {

							jogo.atualizarNave(1);

						}
					}
				});

				atualizar.add(atualizar2);

			} // fim atualizar carga

			return atualizar;

		} // fim vld pl

		return null;

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
				g.setFont(new Font("Arial", Font.BOLD, 14));
				g.drawString("S� pode comprar vender subornar ", 90, 65);
				g.drawString(" e atualizar nave num planeta ", 90, 75);
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

		if (this.jogo.devolveEstado() instanceof Movimentar) {

			this.removeAll();

			JPanel movimentar = adicionarMovimentar();

			if (movimentar != null)
				this.add(movimentar);

		}

		if (this.jogo.devolveEstado() instanceof Negociar) {

			this.removeAll();

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

		repaint();
		revalidate();

	}

}
