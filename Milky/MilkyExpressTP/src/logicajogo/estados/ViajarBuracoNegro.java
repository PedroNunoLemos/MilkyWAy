package logicajogo.estados;

import logicajogo.Jogo;

public class ViajarBuracoNegro implements Estado {
	
	public ViajarBuracoNegro (Jogo j){}

	

	@Override
	public String toString(){
		
		return "Viajar Buraco Negro";
		
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
