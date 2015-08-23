package logicajogo.dados;

import java.io.Serializable;
import java.util.Random;

public class DadoNormal extends Dado implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int resultado;

	public DadoNormal() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void lancarDado() {

		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());

		int random_integer = rand.nextInt(6 - 1) + 1;

		this.resultado = random_integer;

	}

	public int getResultado() {
		return resultado;
	}

}
