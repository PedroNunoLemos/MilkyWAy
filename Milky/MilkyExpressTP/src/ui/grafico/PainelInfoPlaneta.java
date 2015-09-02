package ui.grafico;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import logicajogo.Jogo;
import logicajogo.cartas.Carta;
import logicajogo.cartas.galaxia.planetas.Planeta;
import logicajogo.cartas.galaxia.planetas.PlanetaBase;
import logicajogo.cartas.galaxia.planetas.PlanetaPirata;
import logicajogo.cubos.Agua;
import logicajogo.cubos.Comida;
import logicajogo.cubos.Cubo;
import logicajogo.cubos.Ilegal;
import logicajogo.cubos.Medicamento;

public class PainelInfoPlaneta extends JPanel implements Observer, MouseMotionListener, Serializable {

	private static final long serialVersionUID = 1L;
	private BufferedImage image;
	private Jogo jogo;

	private boolean customCarta;
	private Carta carta;

	private int sx = 200, sy = 173;

	public PainelInfoPlaneta(Jogo j) {

		jogo = j;

		j.addObserver(this);

		customCarta = false;

		try {

			image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Imagens/vazioinex_bg.png"));

		} catch (Exception e) {
			/* handled in paintComponent() */
			JOptionPane.showMessageDialog(null, e.getMessage());

		}

		setPreferredSize(new Dimension(sx, sy));
		setMaximumSize(new Dimension(sx, sy));
		setOpaque(false);
		setVisible(true);

	}

	public void mostraCarta(Carta carta) {

		customCarta = true;

		defineCarta(carta);

		update(jogo, null);
		validate();
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
				return null;

			if (cubo instanceof Comida)
				return amarelo;

			if (cubo instanceof Medicamento)
				return vermelho;

			if (cubo instanceof Ilegal)
				return preto;

			if (cubo instanceof Agua)
				return azul;

			return cinzento;

		} catch (Exception e) {
			/* handled in paintComponent() */
			JOptionPane.showMessageDialog(null, e.getMessage());

		}

		return null;

	}

	private void defineCarta(Carta carta) {

		if (carta != null) {

			this.carta = carta;

			String cartanm = carta.getNome() + "_bg";

			try {
				image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Imagens/" + cartanm + ".png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else
			carta = null;

	}

	private void defineCartaJogador() {

		int x = this.jogo.consultaJogador().obterNave().posicaoAtual()[0];
		int y = this.jogo.consultaJogador().obterNave().posicaoAtual()[1];

		Carta carta = this.jogo.devolveMapa().obtemCarta(x, y);

		defineCarta(carta);

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

		if (!customCarta)
			defineCartaJogador();

		if (image != null)
			g.drawImage(image, 0, 0, sx, sy, this);

		BufferedImage img;

		if (this.carta instanceof Planeta) {

			img = devolveStockImagem(0);

			if (img != null)
				g.drawImage(img, 67, 131, 20, 20, this);

			img = devolveStockImagem(1);

			if (img != null)
				g.drawImage(img, 109, 131, 20, 20, this);
		}

		if (this.carta instanceof PlanetaPirata) {

			img = devolveStockImagem(0);

			if (img != null)
				g.drawImage(img, 91, 131, 20, 20, this);

		}

		customCarta = false;
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
		repaint();
		revalidate();

	}

}
