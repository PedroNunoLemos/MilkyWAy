package logicajogo.dados;

import java.util.Random;

public class DadoNormal extends Dado {

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

	private void setResultado(int resultado) {
		this.resultado = resultado;
	}

}
