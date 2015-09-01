package ui.grafico;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import logicajogo.Jogo;
import logicajogo.Posicao;
import logicajogo.cartas.Carta;
import logicajogo.cartas.galaxia.planetas.Planeta;
import logicajogo.cartas.galaxia.planetas.PlanetaBase;
import logicajogo.cartas.galaxia.planetas.PlanetaPirata;
import logicajogo.cubos.Cubo;
import logicajogo.estados.Movimentar;

public class GuiCarta extends JPanel implements Observer, MouseMotionListener, MouseListener, Serializable {

	private static final long serialVersionUID = 1L;
	private BufferedImage image, imagesel;

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

			imagesel = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Imagens/select.png"));

			if (inx == 0) {

				image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Imagens/outer.png"));
			}

			else if (inx == 1) {

				image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Imagens/vazioinex.png"));

			} else {

				if (carta != null)
					image = ImageIO.read(
							getClass().getClassLoader().getResourceAsStream("Imagens/" + carta.getNome() + ".png"));
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

	private BufferedImage devolveCuboImg(Cubo cubo) {

		BufferedImage amarelo, vermelho, azul, preto, cinzento;

		try {

			image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Imagens/uijog.png"));

			amarelo = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Imagens/icuboam.png"));

			vermelho = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Imagens/icubovm.png"));

			azul = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Imagens/icuboaz.png"));

			preto = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Imagens/icubopt.png"));

			cinzento = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Imagens/icubogr.png"));

			if (cubo == null)
				return cinzento;

			if (cubo.obtemCor() == Color.yellow)
				return amarelo;

			if (cubo.obtemCor() == Color.red)
				return vermelho;

			if (cubo.obtemCor() == Color.black)
				return preto;

			if (cubo.obtemCor() == Color.blue)
				return azul;

		} catch (Exception e) {
			/* handled in paintComponent() */
			JOptionPane.showMessageDialog(null, e.getMessage());

		}

		return null;

	}

	private BufferedImage devolveStockImagem(int idx) {

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

		BufferedImage imgcb;

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
