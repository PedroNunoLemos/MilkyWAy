package logicajogo.cubos;

import java.awt.Color;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

public class Comida extends Cubo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final AtomicInteger contador = new AtomicInteger(0);
	private final String cuboID;

	private Color cor;
	private String nome;

	public Comida() {

		this.cor = Color.yellow;
		this.nome = "Comida";
		
		this.cuboID = "C" + String.valueOf(contador.getAndIncrement());


	}

	@Override
	public String obtemNome() {
		// TODO Auto-generated method stub
		return nome;
	}

	@Override
	public Color obtemCor() {
		// TODO Auto-generated method stub
		return cor;
	}
	
	public String devolveID() {

		return this.cuboID;

	}


}
