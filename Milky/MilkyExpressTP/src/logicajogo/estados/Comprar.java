package logicajogo.estados;

import logicajogo.Jogo;

public class Comprar implements Estado {

	
	private Jogo _jogo;

	public Comprar (Jogo j){this._jogo=j;}
	
	@Override
	public Estado proximoEstado() {
		// TODO Auto-generated method stub
		return new Movimentar(this._jogo);
	}

	@Override
	public void processaInformacaoJogo() {
		// TODO Auto-generated method stub
		
	}



}
