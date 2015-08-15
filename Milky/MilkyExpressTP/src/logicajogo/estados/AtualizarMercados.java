package logicajogo.estados;

import logicajogo.Jogo;

public class AtualizarMercados implements Estado {
	
	private Jogo _jogo;
	
	public AtualizarMercados (Jogo j){this._jogo=j;}

	@Override
	public Estado proximoEstado() {
		// TODO Auto-generated method stub
		return new Vender(this._jogo);
	}

	@Override
	public void processaInformacaoJogo() {
		// TODO Auto-generated method stub
		
	}

	


}
