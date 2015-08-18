package logicajogo.estados;

import logicajogo.Jogo;

public interface Estado {
	
	Estado iniciarJogo(Jogo j);
	Estado moverNave(Jogo j,int x,int y);
	Estado comprarBens(Jogo j);
	Estado venderBens(Jogo j);
	Estado explorar(Jogo j);
	Estado atualizaMercados(Jogo j);
	
	public String toString();
	
}
