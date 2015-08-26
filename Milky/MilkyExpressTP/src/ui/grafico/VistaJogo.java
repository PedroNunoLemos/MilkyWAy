package ui.grafico;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JPanel;

import logicajogo.Jogo;

public class VistaJogo extends JPanel implements Observer, MouseListener, MouseMotionListener, Serializable {

	/**
	 * 
	 */

	Jogo jogo;

	JPanel areainfo = new JPanel();
	GuiTabuleiro areamapa = new GuiTabuleiro();
	JPanel areaopcoes = new JPanel();

	public VistaJogo(Jogo j) {

		jogo = j;

		j.addObserver(this);

		// listeners
		// addMouseListener(this);
		// addMouseMotionListener(this);
		registaListeners();

		// janela

		areainfo.setLayout(new GridLayout(3, 1));
		areainfo.setPreferredSize(new Dimension(125, 500));
		areainfo.setMaximumSize(new Dimension(125, 500));

		areamapa.setLayout(new GridLayout(3, 1));
		areamapa.setPreferredSize(new Dimension(650, 500));
		areamapa.setMaximumSize(new Dimension(650, 500));

		areamapa.geraTabuleiro(j);

		areaopcoes.setLayout(new GridLayout(3, 1));
		areaopcoes.setPreferredSize(new Dimension(125, 500));
		areaopcoes.setMaximumSize(new Dimension(125, 500));

		JButton jb = new JButton("sadad");
		jb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Jogo jog = new Jogo();
				areamapa.geraTabuleiro(jog);

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

	}

}
