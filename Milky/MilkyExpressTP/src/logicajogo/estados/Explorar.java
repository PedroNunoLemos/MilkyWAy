package logicajogo.estados;

import logicajogo.Jogo;

public class Explorar implements Estado {

	private Jogo _jogo;
	
	public Explorar (Jogo j){this._jogo=j;}

	@Override
	public Estado proximoEstado() {
		// TODO Auto-generated method stub
		return new AtualizarMercados(this._jogo) ;
	}

	@Override
	public void processaInformacaoJogo() {
		// TODO Auto-generated method stub
		
	}


}
