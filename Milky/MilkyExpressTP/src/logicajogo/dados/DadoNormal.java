package logicajogo.dados;

public class DadoNormal extends Dado {

	private int resultado;
	
    public DadoNormal() {
		// TODO Auto-generated constructor stub
	}

	@Override
	void lancarDado() {

		setResultado((int) Math.random() * (6) + 1);

	}

	public int getResultado() {
		return resultado;
	}

	private void setResultado(int resultado) {
		this.resultado = resultado;
	}

}
