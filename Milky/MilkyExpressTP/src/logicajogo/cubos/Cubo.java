
package logicajogo.cubos;

import java.awt.Color;

public abstract class Cubo{
	
	private String nome;
	private Color cor; 
	
    Cubo(Color cor){this.cor=cor;}

	public String obtemNome() {
		return nome;
	}

	public void defineNome(String nome) {
		this.nome = nome;
	}

	public Color obtemCor() {
		return cor;
	}
    


}
