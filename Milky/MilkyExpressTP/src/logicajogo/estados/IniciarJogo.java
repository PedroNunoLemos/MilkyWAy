package logicajogo.estados;

import logicajogo.Jogo;

public class IniciarJogo implements Estado {
	
	private Jogo _jogo;
	
	public IniciarJogo (Jogo j){
		this._jogo=j;
		
		
		
	}

	@Override
	public Estado proximoEstado() {
		// TODO Auto-generated method stub
		return new Explorar(this._jogo);
	}

	@Override
	public void processaInformacaoJogo() {
		// TODO Auto-generated method stub
		
	}

	

}
