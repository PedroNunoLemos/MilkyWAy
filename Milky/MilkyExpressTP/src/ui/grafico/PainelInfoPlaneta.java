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

public class PainelInfoPlaneta extends JPanel implements Observer, MouseMotionListener, Serializable {

	private static final long serialVersionUID = 1L;
	private BufferedImage image;
	private Jogo jogo;

	private boolean customCarta;

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

	private void defineCarta(Carta carta) {

		if (carta != null) {

			String cartanm = carta.getNome() + "_bg";

			try {
				image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Imagens/" + cartanm + ".png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	private void defineCartaJogador() {

		int x = this.jogo.consultaJogador().obterNave().posicaoAtual()[0];
		int y = this.jogo.consultaJogador().obterNave().posicaoAtual()[1];

		Carta carta = this.jogo.devolveMapa().obtemCarta(x, y);

		defineCarta(carta);

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (!customCarta)
			defineCartaJogador();

		if (image != null)
			g.drawImage(image, 0, 0, sx, sy, this);

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
