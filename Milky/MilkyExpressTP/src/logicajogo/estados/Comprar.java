package logicajogo.estados;

import logicajogo.Jogo;

public class Comprar implements Estado {

	
	private Jogo _jogo;

	@Override
	public Estado proximoEstado(Jogo j) {
		
		this._jogo=j;
		
		return null;
	}

	@Override
	public boolean processaAtaquePirata() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean validaEstadoDeJogo() {
		// TODO Auto-generated method stub
		return false;
	}

}
