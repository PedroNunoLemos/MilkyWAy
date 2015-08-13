package logicajogo.dados;

import java.awt.Color;
import java.util.ArrayList;

public class DadoCor extends Dado {

	private Color resultado;
	private ArrayList<Color> cores;
	
    public DadoCor() {
		// TODO Auto-generated constructor stub
    	
    	cores.add(Color.black);
    	cores.add(Color.blue);
    	cores.add(Color.red);
    	cores.add(Color.yellow);

    	cores.add(Color.white);
    	cores.add(Color.white);
 	
	}

	@Override
	void lancarDado() {
		
		int rnd =(int) Math.random() * (6) + 1;

		setResultado(cores.get(rnd));

	}

	public Color getResultado() {
		return resultado;
	}

	private void setResultado(Color resultado) {
		this.resultado = resultado;
	}

}
