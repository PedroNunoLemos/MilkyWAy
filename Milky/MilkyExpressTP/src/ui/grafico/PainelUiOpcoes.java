package ui.grafico;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.Serializable;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import logicajogo.Jogo;

public class PainelUiOpcoes extends JPanel implements MouseMotionListener, Serializable {

	private static final long serialVersionUID = 1L;

	private PainelOpcoesJogador opcoes;
	private PainelOpcoesEstado estinfo;
	private PainelOpcoesContinuar continuar;

	public PainelUiOpcoes(Jogo j) {

		estinfo = new PainelOpcoesEstado(j);
		opcoes = new PainelOpcoesJogador(j);
		continuar = new PainelOpcoesContinuar(j);

		this.setAlignmentX(BOTTOM_ALIGNMENT);
		this.setAlignmentY(CENTER_ALIGNMENT);

		add(estinfo);
		add(opcoes);
		add(continuar);

		estinfo.setBorder(BorderFactory.createLineBorder(Color.lightGray));
		opcoes.setBorder(BorderFactory.createLineBorder(Color.lightGray));
		continuar.setBorder(BorderFactory.createLineBorder(Color.lightGray));

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
