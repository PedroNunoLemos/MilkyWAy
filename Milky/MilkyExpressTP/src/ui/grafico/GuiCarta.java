package ui.grafico;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import logicajogo.Jogo;
import logicajogo.Posicao;
import logicajogo.cartas.Carta;
import logicajogo.cartas.galaxia.BuracoNegro;
import logicajogo.cartas.galaxia.Vazio;
import logicajogo.cartas.galaxia.planetas.Arrakis;
import logicajogo.cartas.galaxia.planetas.Planeta;
import logicajogo.cartas.galaxia.planetas.PlanetaBase;
import logicajogo.cartas.galaxia.planetas.PlanetaPirata;
import logicajogo.cubos.Agua;
import logicajogo.cubos.Comida;
import logicajogo.cubos.Cubo;
import logicajogo.cubos.Ilegal;
import logicajogo.cubos.Medicamento;
import logicajogo.estados.Movimentar;
import resources.ResourceLoader;

public class GuiCarta extends JPanel implements Observer, MouseMotionListener, MouseListener, Serializable {

	private static final long serialVersionUID = 1L;
	private Image image, imagesel;

	private int sx = 75, sy = 65;
	private Posicao pos;
	private Carta carta;
	private Jogo jogo;

	public Posicao devolvePosicao() {
		return pos;
	}

	public GuiCarta(Jogo j, Posicao ps, int inx) {

		this.jogo = j;
		this.pos = ps;
		this.carta = ps.obterCarta();

		j.addObserver(this);

		registaListeners();
		try {

			imagesel = ResourceLoader.loadImage("select.png");

			if (inx == 0) {

				image = ResourceLoader.loadImage("outer.png");
			}

			else if (inx == 1) {

				image = ResourceLoader.loadImage("vazioinex.png");

			} else {

				final String nomecrt = carta.getNome().toLowerCase();

				if (carta != null) {
					
					image = ResourceLoader.loadImage(nomecrt + ".png");
					
								
				}
			}
		} catch (Exception e) {
			/* handled in paintComponent() */
			JOptionPane.showMessageDialog(null, e.getMessage());

		}

		setPreferredSize(new Dimension(sx, sy));
		setMaximumSize(new Dimension(sx, sy));
		setOpaque(false);
		setVisible(true);

	}

	private void registaListeners() {
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	public void naveNaArea(boolean nave, boolean ai) {

		if (nave) {

			GuiNaveOverlay naveov = new GuiNaveOverlay(ai);
			add(naveov);
		}

	}

	private Image devolveCuboImg(Cubo cubo) {

		Image amarelo;
		Image vermelho;
		Image azul;
		Image preto;
		Image cinzento;

		try {

			amarelo = ResourceLoader.loadImage("icuboam.png");

			vermelho = ResourceLoader.loadImage("icubovm.png");

			azul = ResourceLoader.loadImage("icuboaz.png");

			preto = ResourceLoader.loadImage("icubopt.png");

			cinzento = ResourceLoader.loadImage("icubogr.png");

			if (cubo == null)
				return cinzento;

			if (cubo instanceof Comida)
				return amarelo;

			if (cubo instanceof Medicamento)
				return vermelho;

			if (cubo instanceof Ilegal)
				return preto;

			if (cubo instanceof Agua)
				return azul;

		} catch (Exception e) {
			/* handled in paintComponent() */
			JOptionPane.showMessageDialog(null, e.getMessage());

		}

		return null;

	}

	private Image devolveStockImagem(int idx) {

		PlanetaBase pl = (PlanetaBase) carta;

		if (carta != null && (carta instanceof Planeta || carta instanceof PlanetaPirata)) {

			Cubo[] stock = pl.obterStock();

			if (stock[idx] != null)
				return devolveCuboImg(stock[idx]);

		}

		return null;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (image != null)
			g.drawImage(image, 0, 0, sx, sy, this);

		Image imgcb;

		if (this.pos.foiExplorada()) {

			if (this.carta instanceof Planeta) {

				imgcb = devolveStockImagem(0);

				if (imgcb != null)
					g.drawImage(imgcb, 25, 48, 9, 9, this);

				imgcb = devolveStockImagem(1);

				if (imgcb != null)
					g.drawImage(imgcb, 39, 48, 9, 9, this);
			}

			if (this.carta instanceof PlanetaPirata) {

				imgcb = devolveStockImagem(0);

				if (imgcb != null)
					g.drawImage(imgcb, 30, 46, 10, 10, this);

			}

		}

		if (pos.estaSelecionado() && imagesel != null)
			g.drawImage(imagesel, 0, 0, sx, sy, this);

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
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

		if (jogo.devolveEstado() instanceof Movimentar) {

			if (carta != null && jogo.obterTipomov() > 0)
				jogo.definePosicaoSelecionada(pos);
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

		this.jogo = (Jogo) arg0;

		int x = pos.obterX();
		int y = pos.obterY();

		this.pos = this.jogo.devolveMapa().consultaPosicao(x, y);

		this.carta = pos.obterCarta();

		repaint();
		validate();

	}

}
