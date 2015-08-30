package ui.grafico;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import logicajogo.Jogo;

public class VistaJogo extends JPanel implements Observer, MouseListener, MouseMotionListener, Serializable {

	/**
	 * 
	 */

	Jogo jogo;

	private BufferedImage image;
	PainelUiJogador areainfo = new PainelUiJogador();
	PainelTabuleiro areamapa = new PainelTabuleiro();
	JPanel areaopcoes = new JPanel();

	private void addFundo() {
		try {
			image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Imagens/stars.jpg"));

		} catch (Exception e) {
			/* handled in paintComponent() */
			JOptionPane.showMessageDialog(null, e.getMessage());

		}

	}

	public VistaJogo(Jogo j) {

		jogo = j;

		j.addObserver(this);

		// listeners

		registaListeners();

		this.setLayout(new BorderLayout());

		addFundo();

		areainfo.setOpaque(false);
		areaopcoes.setOpaque(false);

		// 1000-700

		areainfo.setLayout(new GridLayout(3, 1));
		areainfo.setPreferredSize(new Dimension(250, 600));
		areainfo.setMaximumSize(new Dimension(250, 600));

		areamapa.setLayout(new GridLayout(3, 1));
		areamapa.setPreferredSize(new Dimension(750, 600));
		areamapa.setMaximumSize(new Dimension(750, 600));

		areaopcoes.setLayout(new GridLayout(1, 3));
		areaopcoes.setPreferredSize(new Dimension(1000, 150));
		areaopcoes.setMaximumSize(new Dimension(1000, 150));

		areainfo.setBorder(BorderFactory.createLineBorder(Color.cyan));
		areaopcoes.setBorder(BorderFactory.createLineBorder(Color.cyan));
		areamapa.setBorder(BorderFactory.createLineBorder(Color.cyan));

		this.add(areainfo, BorderLayout.WEST);
		this.add(areamapa, BorderLayout.CENTER);
		this.add(areaopcoes, BorderLayout.PAGE_END);

		setVisible(true);

		update(jogo, null);
		validate();

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (image != null)
			g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
	}

	private void registaListeners() {
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	private static final long serialVersionUID = 1L;

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

		Jogo j = (Jogo) arg0;
		areamapa.atualizaMapa(j);

		// JOptionPane.showMessageDialog(null, "mvc rules");

	}

}
