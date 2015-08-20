package logicajogo.estados;

import logicajogo.Jogo;

public interface Estado {

	Estado iniciarJogo(Jogo j);

	Estado moverNave(Jogo j, int x, int y);

	Estado comprarBens(Jogo j);

	Estado venderBens(Jogo j);

	Estado explorar(Jogo j);
	
	Estado combaterPiratas(Jogo j);

	Estado atualizaMercados(Jogo j);
	
	Estado retomaMovimentoNormal(Jogo j);

	Estado viajarProximoBuracoNegro(Jogo j, int x, int y);
	
	Estado efetuaSuborno(Jogo j);
	
	Estado viajarModoWarp(Jogo j, int x,int y);

	Estado fimJogo(Jogo j);

	Estado acederMenu(Jogo j);

	public String toString();

}
