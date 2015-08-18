package logicajogo;

import java.util.ArrayList;

import logicajogo.estados.Estado;
import logicajogo.estados.IniciarJogo;

public class Jogo {

	private Estado estado;
	private Jogador jogador;
	private int banco = 30;

	private Tabuleiro tabuleiro;

	private String ultimoErro;

	public Jogo() {
		this.estado = new IniciarJogo(this);
		this.estado = this.estado.iniciarJogo(this);
		this.explorar();
	}

	public void explorar() {
		this.estado=this.estado.explorar(this);
	}

	public void moverNave(int x, int y) {

		this.estado=this.estado.moverNave(this, x, y);
	}

	public void comprarBens() {
		this.estado=this.estado.comprarBens(this);
	}

	public void venderBens() {
		this.estado=this.estado.venderBens(this);
	}

	public void atualizarMercados() {
		this.estado=this.estado.atualizaMercados(this);
		int x=0;
		x=0;
	}

	public String devolveErro() {

		return this.ultimoErro;
	}

	public Tabuleiro devolveMapa() {
		return this.tabuleiro;
	}

	public Estado devolveEstado() {

		return this.estado;

	}

	public void preparaJogo() {

		banco = 20;

		jogador = new Jogador(10);

		jogador.getNave().mover(0, 6);

		tabuleiro = new Tabuleiro();
		tabuleiro.geraGalaxia();

	}

	public void defineErro(String erro) {

		this.ultimoErro = erro;
	}

	public boolean temJogadores() {

		return this.jogador != null;

	}

	public Jogador consultaJogador() {

		return this.jogador;

	}
}
