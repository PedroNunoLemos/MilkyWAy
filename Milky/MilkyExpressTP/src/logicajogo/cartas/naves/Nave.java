package logicajogo.cartas.naves;

import java.util.ArrayList;

import logicajogo.cubos.Cinzento;
import logicajogo.cubos.Cubo;

public class Nave {

	private int x, y = -1;
	private boolean parada;
	private boolean cargmax;

	private Cubo[] forca;
	private Cubo[] carga;

	public Nave() {

		this.forca = new Cubo[3];
		this.carga = new Cubo[3];

		this.cargmax = false;

		atualizar(0);
		this.x = -1;
		this.y = -1;

	}

	public void mover(int x, int y) {

		this.x = x;
		this.y = y;

		this.parada = false;

	}

	public int obterForca() {

		int cnt = 0;

		for (int i = 0; i < 3; i++) {
			if (forca[i] != null)
				cnt++;

		}

		return cnt;

	}

	public boolean maxForca() {
		return (obterForca() == 3);
	}

	public void atualizar(int index) {

		for (int i = 0; i < 3; i++) {
			if (forca[i] == null && i == index)
				forca[i] = new Cinzento();

		}

	}

	public int obterTotalCargaOcupada() {

		int cnt = 0;

		for (int i = 0; i < 3; i++) {
			if (carga[i] != null)
				cnt++;

		}

		return cnt;

	}

	public void ativaCargaMaxima() {

		this.cargmax = true;

	}

	public int adicionaCarga(Cubo cargacap) {

		if (!this.cargmax && obterTotalCargaOcupada() == 2)
			return -1;

		for (int i = 0; i < 3; i++) {
			if (carga[i] == null)
				carga[i] = cargacap;

		}

		return 0;

	}

	public boolean retiraCarga(String nome) {

		for (int i = 0; i < 3; i++) {
			if (carga[i] != null && carga[i].obtemNome() == nome) {
				carga[i] = null;
				return true;
			}
		}

		return false;
	}

	public String consultaCuboCarga(int index) {

		for (int i = 0; i < 3; i++) {
			if (i == index && carga[i] != null)
				return carga[i].obtemNome();
		}

		return "-";
	}
	
	public boolean naveCargaAtualizada(){
		return this.cargmax;
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

	public void paraNave() {
		this.parada = true;
	}

}
