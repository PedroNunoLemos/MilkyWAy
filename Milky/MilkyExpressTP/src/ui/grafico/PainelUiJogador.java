package ui.grafico;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.Serializable;

import javax.swing.JPanel;

import logicajogo.Jogo;
import logicajogo.cartas.Carta;

public class PainelUiJogador extends JPanel implements MouseMotionListener, Serializable {

	private static final long serialVersionUID = 1L;

	private PainelInfoJogador joginfo;
	private PainelInfoPlaneta plinfo;

	public PainelUiJogador(Jogo j) {

		joginfo = new PainelInfoJogador(j);
		plinfo = new PainelInfoPlaneta(j);

		this.setAlignmentX(BOTTOM_ALIGNMENT);
		this.setAlignmentY(CENTER_ALIGNMENT);

		JPanel dummy = new JPanel();
		dummy.setOpaque(true);
		dummy.setVisible(false);

		add(joginfo);
		add(plinfo);

		add(dummy);

		setOpaque(false);
		setVisible(true);

	}

	public void mostraCarta(Carta carta) {

		plinfo.mostraCarta(carta);

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
