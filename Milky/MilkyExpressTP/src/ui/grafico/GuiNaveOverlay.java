package ui.grafico;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.Serializable;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import resources.ResourceLoader;

public class GuiNaveOverlay extends JPanel implements MouseMotionListener, Serializable {

	private static final long serialVersionUID = 1L;
	private Image image;

	private int sx = 40, sy = 39;

	public GuiNaveOverlay(boolean ai) {

		try {

			image = ResourceLoader.loadImage("nv.png");

			if (ai)
				image = ResourceLoader.loadImage("nvai.png");

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
