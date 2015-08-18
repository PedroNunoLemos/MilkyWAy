package logicajogo.estados;

import logicajogo.Jogo;

public class AtualizarMercados implements Estado {
	
	public AtualizarMercados (Jogo j){}


	
	@Override
	public String toString(){
		
		return "Atualizar Mercados";
		
	}



	@Override
	public Estado iniciarJogo(Jogo j) {
		// TODO Auto-generated method stub
		return this;
	}



	@Override
	public Estado moverNave(Jogo j, int x, int y) {
		// TODO Auto-generated method stub
		return this;
	}



	@Override
	public Estado comprarBens(Jogo j) {
		// TODO Auto-generated method stub
		return new Comprar(j);

	}



	@Override
	public Estado venderBens(Jogo j) {
		// TODO Auto-generated method stub
		return this;
	}



	@Override
	public Estado explorar(Jogo j) {
		// TODO Auto-generated method stub
		return this;
	}



	@Override
	public Estado atualizaMercados(Jogo j) {
		// TODO Auto-generated method stub

		return this;
	}



}
