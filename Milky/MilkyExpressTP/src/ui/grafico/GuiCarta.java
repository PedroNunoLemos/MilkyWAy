package ui.grafico;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.Serializable;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import logicajogo.cartas.Carta;

public class GuiCarta extends JPanel implements MouseMotionListener, Serializable {

	private static final long serialVersionUID = 1L;
	private BufferedImage image;

	public GuiCarta(Carta carta) {

		try {
			image = ImageIO
					.read(getClass().getClassLoader().getResourceAsStream("Imagens/" + carta.getNome() + ".png"));

		} catch (Exception e) {
			/* handled in paintComponent() */
			JOptionPane.showMessageDialog(null, e.getMessage());

		}
		
		//setPreferredSize(new Dimension(180/4, 263/4));
		//setMaximumSize(new Dimension(180/4, 263/4));
		setOpaque(false);
		setVisible(true);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (image != null)
			g.drawImage(image, 0, 0, 180, 263, this);
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
