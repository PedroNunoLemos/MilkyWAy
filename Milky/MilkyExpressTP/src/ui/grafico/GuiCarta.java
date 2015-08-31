package ui.grafico;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.Serializable;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import logicajogo.Jogo;
import logicajogo.Posicao;
import logicajogo.cartas.Carta;

public class GuiCarta extends JPanel implements MouseMotionListener, MouseListener, Serializable {

	private static final long serialVersionUID = 1L;
	private BufferedImage image;

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

		registaListeners();

		try {

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

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (image != null)
			g.drawImage(image, 0, 0, sx, sy, this);
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

		jogo.devolveMapa().definePosicaoSelecionada(pos);
		
		JOptionPane.showMessageDialog(null,pos.obterX());


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

}
