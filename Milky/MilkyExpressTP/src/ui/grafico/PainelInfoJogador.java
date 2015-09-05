package ui.grafico;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import logicajogo.Jogo;
import logicajogo.cartas.naves.Nave;
import logicajogo.cubos.*;
import resources.ResourceLoader;

public class PainelInfoJogador extends JPanel implements Observer, MouseMotionListener, Serializable {

	private static final long serialVersionUID = 1L;
	Image image;
	private Image amarelo, vermelho, azul, preto, forca, cinzento;

	private int sx = 200, sy = 173;

	private Jogo jogo;

	public PainelInfoJogador(Jogo j) {

		jogo = j;

		j.addObserver(this);

		try {

			image = ResourceLoader.loadImage("uijog.png");

			amarelo = ResourceLoader.loadImage("icuboam.png");

			vermelho = ResourceLoader.loadImage("icubovm.png");

			azul = ResourceLoader.loadImage("icuboaz.png");

			preto = ResourceLoader.loadImage("icubopt.png");

			forca = ResourceLoader.loadImage("icubopr.png");

			cinzento = ResourceLoader.loadImage("icubogr.png");

		} catch (Exception e) {
			/* handled in paintComponent() */
			JOptionPane.showMessageDialog(null, e.getStackTrace()[1].toString());

		}

		setPreferredSize(new Dimension(sx, sy));
		setMaximumSize(new Dimension(sx, sy));
		setOpaque(false);
		setVisible(true);

	}

	private Image devolveCuboImg(Cubo cubo) {

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

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (image != null)
			g.drawImage(image, 0, 0, sx, sy, this);

		String moedas = String.valueOf(this.jogo.consultaJogador().devolveMoedas());

		g.setFont(new Font("Arial", Font.BOLD, 15));

		g.setColor(Color.getHSBColor(170, 15, 100));

		g.drawString(moedas, 90, 65);

		Nave nave = this.jogo.consultaJogador().obterNave();

		if (nave.obterForca() == 4)
			g.drawImage(forca, 121, 92, 15, 16, this);

		if (nave.obterForca() == 5) {
			g.drawImage(forca, 121, 92, 15, 16, this);
			g.drawImage(forca, 141, 92, 15, 16, this);

		}

		if (nave.naveCargaMaxima())
			g.drawImage(cinzento, 131, 135, 15, 16, this);

		Cubo[] carga = this.jogo.consultaJogador().obterNave().obterCarga();

		if (nave.obterTotalCargaOcupada() > 0) {

			if (carga[0] != null)
				g.drawImage(devolveCuboImg(carga[0]), 88, 135, 15, 16, this);

			if (carga[1] != null)
				g.drawImage(devolveCuboImg(carga[1]), 110, 135, 15, 16, this);

			if (nave.naveCargaMaxima())
				if (carga[2] != null)
					g.drawImage(devolveCuboImg(carga[2]), 131, 135, 15, 16, this);
		}

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
