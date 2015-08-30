package ui.grafico;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.Serializable;

import javax.swing.JPanel;

import logicajogo.Jogo;

public class PainelUiJogador extends JPanel implements MouseMotionListener, Serializable {

	private static final long serialVersionUID = 1L;

	public PainelUiJogador(Jogo j) {

		this.setAlignmentX(BOTTOM_ALIGNMENT);
		this.setAlignmentY(CENTER_ALIGNMENT);

		JPanel dummy = new JPanel();
		dummy.setOpaque(true);
		dummy.setVisible(false);

		add(new PainelInfoJogador(j));
		add(new PainelInfoPlaneta());
		
		add(dummy);

		setOpaque(false);
		setVisible(true);

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
