package logicajogo.estados;

import logicajogo.Jogo;

public class Movimentar implements Estado {

	private Jogo _jogo;
	
	public Movimentar (Jogo j){this._jogo=j;}

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
