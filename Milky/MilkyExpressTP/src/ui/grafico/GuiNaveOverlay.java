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

public class GuiNaveOverlay extends JPanel implements MouseMotionListener, Serializable {

	private static final long serialVersionUID = 1L;
	private BufferedImage image;

	private int sx = 40, sy = 39;

	public GuiNaveOverlay(boolean ai) {

		try {

			image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Imagens/nv.png"));

			if (ai)
				image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Imagens/nvai.png"));

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
