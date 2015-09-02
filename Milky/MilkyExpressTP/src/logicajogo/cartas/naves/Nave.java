package logicajogo.cartas.naves;

import java.io.Serializable;

import logicajogo.cubos.*;

public class Nave implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int x, y = -1;
	private boolean parada;
	private boolean cargmax;
	private boolean viagemBuracoNegro;

	private Cubo[] forca;
	private Cubo[] carga;

	private int cargamaxcap = 2;

	public Nave() {

		this.forca = new Cubo[3];
		this.carga = new Cubo[3];

		this.cargmax = false;

		this.cargamaxcap = 2;

		atualizarForca();

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

		return cnt + 2;

	}

	public Cubo[] obterCarga() {

		if (this.obterTotalCargaOcupada() > 0) {
			Cubo[] cubos = new Cubo[3];

			for (int i = 0; i < 3; i++)
				cubos[i] = carga[i];

			return cubos;

		} else
			return null;
	}

	public boolean maxForca() {
		return (obterForca() == 5);
	}

	public boolean atualizarForca() {

		for (int i = 0; i < 3; i++) {
			if (forca[i] == null) {
				forca[i] = new Cinzento();
				return true;
			}

		}

		return false;

	}

	public int obterProximoCustoUpgradeForca() {

		if (this.maxForca())
			return 0;

		if (forca[1] == null)
			return 4;

		if (forca[1] != null && forca[2] == null)
			return 5;

		return 3;
	}

	public int obterTotalCargaOcupada() {

		int cnt = 0;

		for (int i = 0; i < cargamaxcap; i++) {
			if (carga[i] != null)
				cnt++;

		}

		return cnt;

	}

	public boolean podeLevarCarga() {

		for (int i = 0; i < cargamaxcap; i++) {
			if (carga[i] == null)
				return true;

		}

		return false;

	}

	public void ativaCargaMaxima() {

		this.cargmax = true;
		this.cargamaxcap = 3;

	}

	public boolean adicionaCarga(Cubo cargacap) {

		for (int i = 0; i < cargamaxcap; i++) {
			if (carga[i] == null) {

				carga[i] = cargacap;

				return true;
			}
		}

		return false;

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

	public boolean retiraCarga(Cubo cubo) {

		for (int i = 0; i < 3; i++) {
			if (carga[i] != null && carga[i].equals(cubo)) {
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

	public boolean naveCargaMaxima() {
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

	public boolean viajandoBuracoNegro() {
		return viagemBuracoNegro;
	}

	public void viagemBuracoNegro(boolean viajouBuracoNegro) {
		this.viagemBuracoNegro = viajouBuracoNegro;
	}

}
