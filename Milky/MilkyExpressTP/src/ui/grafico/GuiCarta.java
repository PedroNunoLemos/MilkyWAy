package ui.grafico;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import logicajogo.cartas.Carta;

public class GuiCarta extends JPanel implements MouseMotionListener, Serializable {

	private static final long serialVersionUID = 1L;
	private BufferedImage image;

	private int sx = 75, sy = 65;

	public GuiCarta(Carta carta, int inx) {

		try {

			if (inx == 0) {

				image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Imagens/outer.png"));
			}

			else if (inx == 1) {

				image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Imagens/vazioinex.png"));

			} else {

				image = ImageIO
						.read(getClass().getClassLoader().getResourceAsStream("Imagens/" + carta.getNome() + ".png"));
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

	public void naveNaArea(boolean nave) {

		BufferedImage img;
		try {
			img = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Imagens/nv.png"));

			

			if (nave)
				add(new GuiNaveOverlay());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

}
