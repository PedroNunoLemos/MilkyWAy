package logicajogo.estados;

import logicajogo.Jogo;

public interface Estado {
	
	
	Estado proximoEstado(Jogo j);
	
	boolean processaAtaquePirata();
	boolean validaEstadoDeJogo();
	
	
	
}
