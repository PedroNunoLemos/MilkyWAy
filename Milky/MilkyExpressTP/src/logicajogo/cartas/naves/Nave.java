package logicajogo.cartas.naves;

public class Nave {

	private int forca = 0;
	private int x, y = -1;
	private boolean parada;

	public Nave() {

		atualizar();
		this.x = -1;
		this.y = -1;

	}

	public void mover(int x, int y) {

		this.x = x;
		this.y = y;

		this.parada = false;

	}

	public int getForca() {
		return forca;
	}

	public void atualizar() {

		if (getForca() < 3)
			this.forca++;

	}

	public int[] posicaoAtual() {

		int cords[] = new int[2];

		cords[0] = this.x;
		cords[1] = this.y;

		return cords;
	}

	public boolean estaParada() {
		return parada;
	}

	public void paraNave(){
		this.parada=true;
	}
	
}
