package logicajogo.estados;

import logicajogo.Jogo;

public interface Estado {
	
	
	Estado proximoEstado();
	
   void processaInformacaoJogo();
   	
	
	
}
