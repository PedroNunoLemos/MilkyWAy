package ui.grafico;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import logicajogo.Jogo;
import logicajogo.estados.FimdeJogo;
import resources.ResourceLoader;

public class VistaJogo extends JPanel implements Observer, MouseListener, MouseMotionListener, Serializable {

	/**
	 * 
	 */

	Jogo jogo;

	private Image image;
	PainelUiJogador areainfo;
	PainelTabuleiro areamapa = new PainelTabuleiro();
	PainelUiOpcoes areaopcoes;

	private void addFundo() {
		try {
			image =  ResourceLoader.loadImage("stars.jpg");

		} catch (Exception e) {
			/* handled in paintComponent() */
			JOptionPane.showMessageDialog(null, e.getStackTrace()[1].toString());


		}

	}

	public VistaJogo(Jogo j) {

		jogo = j;

		areainfo = new PainelUiJogador(j);
		areaopcoes = new PainelUiOpcoes(j);

		j.addObserver(this);

		// listeners

		registaListeners();

		this.setLayout(new BorderLayout());

		addFundo();

		areainfo.setOpaque(false);
		areaopcoes.setOpaque(false);

		// 1000-700

		areainfo.setLayout(new GridLayout(3, 1, 0, 0));
		areainfo.setPreferredSize(new Dimension(200, 670));
		areainfo.setMaximumSize(new Dimension(200, 670));

		areamapa.setLayout(new GridLayout(3, 1));
		areamapa.setPreferredSize(new Dimension(750, 670));
		areamapa.setMaximumSize(new Dimension(750, 70));

		areaopcoes.setPreferredSize(new Dimension(1000, 130));
		areaopcoes.setMaximumSize(new Dimension(1000, 130));

		// areainfo.setBorder(BorderFactory.createLineBorder(Color.lightGray));
		// areaopcoes.setBorder(BorderFactory.createLineBorder(Color.lightGray));
		// areamapa.setBorder(BorderFactory.createLineBorder(Color.lightGray));

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

		if (j.devolveMensagem() != null && !j.devolveMensagem().isEmpty())
			JOptionPane.showMessageDialog(null, j.devolveMensagem());

		if (j.devolveEstado() instanceof FimdeJogo) {

			JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
			if (topFrame != null) {
				topFrame.remove(this);
				topFrame.repaint();
				topFrame.validate();
			}
		}

		j.defineMensagem("");

	}

}
