package logicajogo.estados;

import logicajogo.Jogo;
import logicajogo.cubos.Cubo;

public interface Estado {

	Estado iniciarJogo(Jogo j);

	Estado moverNave(Jogo j, int x, int y);

	Estado comprarBens(Jogo j, Cubo cubo);

	Estado venderBens(Jogo j, Cubo cubo);
	
	Estado combaterPiratas(Jogo j);
	
	Estado continuarJogo(Jogo j);

	Estado viajarProximoBuracoNegro(Jogo j, int x, int y);
	
	Estado efetuaSuborno(Jogo j);
	
	Estado viajarModoWarp(Jogo j, int x,int y);

	Estado fimJogo(Jogo j);

	Estado acederMenu(Jogo j);

	public String toString();

}
