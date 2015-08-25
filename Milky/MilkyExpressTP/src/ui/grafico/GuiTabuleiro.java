package ui.grafico;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.Serializable;

import javax.imageio.ImageIO;
import javax.swing.*;

public class GuiTabuleiro extends JPanel implements MouseMotionListener, Serializable {

	private static final long serialVersionUID = 1L;
	private BufferedImage image;

	public GuiTabuleiro() {

		try {
			image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Imagens/stars.jpg"));

		} catch (Exception e) {
			/* handled in paintComponent() */
			JOptionPane.showMessageDialog(null, e.getMessage());

		}

		setOpaque(false);
		setVisible(true);

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (image != null)
			g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

		JOptionPane.showMessageDialog(null, "My Goodness, this is so concise");

	}

}