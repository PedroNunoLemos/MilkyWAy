package logicajogo.estados;

import logicajogo.Jogo;

public class Comprar implements Estado {

	


	public Comprar (Jogo j){}


	@Override
	public String toString(){
		
		return "Comprar";
		
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
		return this;
	}


	@Override
	public Estado venderBens(Jogo j) {
		// TODO Auto-generated method stub
		return new Vender(j);
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
