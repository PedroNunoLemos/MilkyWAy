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

public class PainelOpcoesJogador extends JPanel implements Observer, MouseMotionListener, Serializable {

	private static final long serialVersionUID = 1L;

	private int sx = 100, sy = 100;

	private Jogo jogo;

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

		String estado = String.valueOf(this.jogo.devolveEstado());

		g.setFont(new Font("Arial", Font.BOLD, 15));

		g.setColor(Color.getHSBColor(170, 15, 100));

		g.drawString(estado, 90, 65);

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
