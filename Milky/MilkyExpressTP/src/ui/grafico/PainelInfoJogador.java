package ui.grafico;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import logicajogo.Jogo;
import logicajogo.cartas.naves.Nave;
import logicajogo.cubos.Cubo;

public class PainelInfoJogador extends JPanel implements Observer, MouseMotionListener, Serializable {

	private static final long serialVersionUID = 1L;
	private BufferedImage image, amarelo, vermelho, azul, preto, forca, cinzento;

	private int sx = 200, sy = 173;

	private Jogo jogo;

	public PainelInfoJogador(Jogo j) {

		jogo = j;

		j.addObserver(this);

		try {

			image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Imagens/uijog.png"));

			amarelo = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Imagens/icuboam.png"));

			vermelho = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Imagens/icubovm.png"));

			azul = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Imagens/icuboaz.png"));

			preto = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Imagens/icubopt.png"));

			forca = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Imagens/icubopr.png"));

			cinzento = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Imagens/icubogr.png"));

		} catch (Exception e) {
			/* handled in paintComponent() */
			JOptionPane.showMessageDialog(null, e.getMessage());

		}

		setPreferredSize(new Dimension(sx, sy));
		setMaximumSize(new Dimension(sx, sy));
		setOpaque(false);
		setVisible(true);

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
