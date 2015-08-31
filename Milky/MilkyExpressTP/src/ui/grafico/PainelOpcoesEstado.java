package ui.grafico;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import logicajogo.Jogo;

public class PainelOpcoesEstado extends JPanel implements Observer, MouseMotionListener, Serializable {

	private static final long serialVersionUID = 1L;

	private int sx = 200, sy = 100;

	private Jogo jogo;

	public PainelOpcoesEstado(Jogo j) {

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

		Graphics2D g2d = (Graphics2D) g;
		FontMetrics fm = g2d.getFontMetrics();
		Rectangle2D r = fm.getStringBounds(estado, g2d);
		
		int x = (this.getWidth() - (int) r.getWidth()) / 2;		
		int y = (this.getHeight() - (int) r.getHeight()) / 2 + fm.getAscent();
		
		g.drawString(estado, x, y);

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
