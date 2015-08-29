package ui.grafico;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import logicajogo.Jogo;

public class VistaJogo extends JPanel implements Observer, MouseListener, MouseMotionListener, Serializable {

	/**
	 * 
	 */

	Jogo jogo;

	private BufferedImage image;
	JPanel areainfo = new JPanel();
	GuiTabuleiro areamapa = new GuiTabuleiro();
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

		addFundo();

		areainfo.setOpaque(false);
		areaopcoes.setOpaque(false);

		areainfo.setLayout(new GridLayout(3, 1));
		areainfo.setPreferredSize(new Dimension(125, 500));
		areainfo.setMaximumSize(new Dimension(125, 500));

		areamapa.setLayout(new GridLayout(3, 1));
		areamapa.setPreferredSize(new Dimension(750, 500));
		areamapa.setMaximumSize(new Dimension(750, 500));

		areaopcoes.setLayout(new GridLayout(3, 1));
		areaopcoes.setPreferredSize(new Dimension(125, 500));
		areaopcoes.setMaximumSize(new Dimension(125, 500));

		JButton jb = new JButton("sadad");
		jb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Jogo jog = new Jogo();
				areamapa.atualizaMapa(jog);

			}
		});

		jb.setVisible(true);

		this.areainfo.add(jb);

		this.add(areainfo);
		this.add(areamapa);
		this.add(areaopcoes);

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
		

		//JOptionPane.showMessageDialog(null, "mvc rules");

	}

}
