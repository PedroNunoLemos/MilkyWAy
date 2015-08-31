package ui.grafico;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JPanel;

import logicajogo.Jogo;
import logicajogo.Posicao;
import logicajogo.estados.Movimentar;

public class PainelOpcoesContinuar extends JPanel implements Observer, MouseMotionListener, Serializable {

	private static final long serialVersionUID = 1L;

	private int sx = 200, sy = 200;

	private Jogo jogo;

	public PainelOpcoesContinuar(Jogo j) {

		jogo = j;

		jogo.addObserver(this);

		setPreferredSize(new Dimension(sx, sy));
		setMaximumSize(new Dimension(sx, sy));

		JButton continuar = new JButton("Continuar");

		continuar.setAlignmentX(BOTTOM_ALIGNMENT);
		continuar.setAlignmentY(CENTER_ALIGNMENT);

		continuar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				if (jogo.devolveEstado() instanceof Movimentar) {

					Posicao newpos = jogo.devolveMapa().consultaPosicaoSelecionada();

					if (newpos.obterCarta() != null) {

						switch (jogo.obterTipomov()) {

						case 1:

							jogo.moverNave(newpos.obterX(), newpos.obterY());
							break;

						case 2:

							jogo.viajarBuracoNegro(newpos.obterX(), newpos.obterY());
							break;

						case 3:

							jogo.viajarModoWarp(newpos.obterX(), newpos.obterY());
							break;

						}

					} // fim valida carta

				} // fim movimentar

				j.continuarJogo();
				
			}
		});

		add(continuar);

		this.setAlignmentX(BOTTOM_ALIGNMENT);
		this.setAlignmentY(CENTER_ALIGNMENT);

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

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

		jogo = (Jogo) arg0;
		
		repaint();
		revalidate();

	}

}
