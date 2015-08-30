package ui.grafico;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.Serializable;

import javax.swing.JPanel;

public class PainelUiJogador extends JPanel implements MouseMotionListener, Serializable {

	private static final long serialVersionUID = 1L;
	public PainelUiJogador() {

		add(new PainelInfoJogador());
		add(new PainelInfoPlaneta());

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
