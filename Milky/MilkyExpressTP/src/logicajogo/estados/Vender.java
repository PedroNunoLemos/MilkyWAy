package logicajogo.estados;

import logicajogo.Jogo;

public class Vender implements Estado {

	
	private Jogo _jogo;
	
	public Vender (Jogo j){this._jogo=j;}

	@Override
	public Estado proximoEstado() {
		// TODO Auto-generated method stub
		return new Comprar(this._jogo);
	}

	@Override
	public void processaInformacaoJogo() {
		// TODO Auto-generated method stub
		
	}



}
