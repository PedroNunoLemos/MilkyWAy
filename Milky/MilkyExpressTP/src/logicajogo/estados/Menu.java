package logicajogo.estados;

import logicajogo.Jogo;

public class Menu implements Estado {



	public Menu(Jogo j) {

	}

	
	@Override
	public String toString(){
		
		return "Menu";
		
	}


	@Override
	public Estado iniciarJogo(Jogo j) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Estado moverNave(Jogo j, int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Estado comprarBens(Jogo j) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Estado venderBens(Jogo j) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Estado explorar(Jogo j) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Estado atualizaMercados(Jogo j) {
		// TODO Auto-generated method stub
		return null;
	}



}
