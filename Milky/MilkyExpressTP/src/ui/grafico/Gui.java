package ui.grafico;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.Serializable;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import logicajogo.Jogo;

public class Gui extends JFrame implements MouseListener, MouseMotionListener, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Jogo jogo;
	private BufferedImage image;
	

	public Gui() {
		try {
			image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Imagens/planets.jpg"));

			JLabel background=new JLabel(new ImageIcon(image));
			add(background);
			background.setLayout(new FlowLayout());
			
		} catch (Exception e) {
			/* handled in paintComponent() */
			JOptionPane.showMessageDialog(null, e.getMessage());

		}
	}




	public void mostrarInterface() {

		JMenuBar barraMenu = new JMenuBar();
		JMenu main = new JMenu("Jogo");
		JMenuItem main_iniciar = new JMenuItem("Iniciar Jogo");
		JMenuItem main_salvar = new JMenuItem("Salvar");
		JMenuItem main_ler = new JMenuItem("Carregar Ultimo Jogo");
		JMenuItem main_sair = new JMenuItem("Sair");

		main.add(main_iniciar);
		main.add(main_ler);
		main.add(main_salvar);
		main.addSeparator();
		main.add(main_sair);

		main_sair.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});

		main_iniciar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				jogo = new Jogo();

				VistaJogo vst = new VistaJogo(jogo);

				getContentPane().add(vst, BorderLayout.LINE_START);

				revalidate();
				repaint();

			}
		});

		barraMenu.add(main);

		setJMenuBar(barraMenu);

		setSize(1000, 700);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Milky Way Express - GUI");

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
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
}
