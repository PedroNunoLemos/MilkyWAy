package logicajogo.cartas.naves;

public  class Nave {


	private int forca=0; 
	private int posicao=-1;
	private boolean jaMovimentou;


	
	public Nave(){

		atualizar();
		this.jaMovimentou=false;
		this.posicao=-1;
		
	}
	
	
	public void mover(int pos){
		
		this.posicao=pos;
	}

	
	
	public boolean mudouPosicao() {
		return jaMovimentou;
	}
	
	
	public int getForca() {
		return forca;
	}

	public void atualizar() {
		
		if (getForca()<3)
		this.forca++;
		
	}


	public int posicaoAtual() {
		return posicao;
	}

	
}
