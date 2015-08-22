package logicajogo.dados;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class DadoCor extends Dado {

	private Color resultado;
	private ArrayList<Color> cores;

	public DadoCor() {
		// TODO Auto-generated constructor stub

		cores = new ArrayList<Color>();

		cores.add(Color.black);
		cores.add(Color.blue);
		cores.add(Color.red);
		cores.add(Color.yellow);

		cores.add(Color.white);
		cores.add(Color.white);

	}

	@Override
	public void lancarDado() {

		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());

		int random_integer = rand.nextInt(6 - 1) + 1;

		Collections.shuffle(cores);
		this.resultado = cores.get(random_integer);

	}

	public Color getResultado() {
		return resultado;
	}

}
