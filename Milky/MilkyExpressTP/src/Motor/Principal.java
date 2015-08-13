package Motor;

import logicajogo.Jogo;
import logicajogo.estados.IniciarJogo;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Jogo os1 = new Jogo(new IniciarJogo());
		os1.mudarEstado();
		os1.mudarEstado();
		os1.mudarEstado();
		os1.mudarEstado();

	}

}
