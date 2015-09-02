package logicajogo.cubos;

import java.awt.Color;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

public class Cinzento extends Cubo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private static final AtomicInteger contador = new AtomicInteger(0);
	private final String cuboID;
	
	private Color cor;
	private String nome;

	public Cinzento() {

		this.cor = Color.gray;
		this.nome = "Cinzento";

		this.cuboID = "Z" + String.valueOf(contador.getAndIncrement());

		
	}

	public String devolveID() {

		return this.cuboID;

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

}
