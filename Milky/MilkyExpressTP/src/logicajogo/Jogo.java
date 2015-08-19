package logicajogo;

import java.util.ArrayList;

import logicajogo.cubos.*;
import logicajogo.estados.Estado;
import logicajogo.estados.IniciarJogo;

public class Jogo {

	private Estado estado;
	private Jogador jogador;
	private int banco = 30;

	private ArrayList<Cubo> reserva;

	private Tabuleiro tabuleiro;

	private String ultimoErro;

	public Jogo() {

		this.estado = new IniciarJogo(this);
		this.estado = this.estado.iniciarJogo(this);
		this.reserva = new ArrayList<Cubo>();

		for (int i = 0; i < 10; i++)
			this.reserva.add(new Comida());

		for (int i = 0; i < 10; i++)
			this.reserva.add(new Agua());

		for (int i = 0; i < 10; i++)
			this.reserva.add(new Medicamento());

		for (int i = 0; i < 6; i++)
			this.reserva.add(new Ilegal());

		this.explorar();
	}

	public void explorar() {
		this.estado = this.estado.explorar(this);
	}

	public void moverNave(int x, int y) {

		this.estado = this.estado.moverNave(this, x, y);
	}

	public void comprarBens() {
		this.estado = this.estado.comprarBens(this);
	}

	public void venderBens() {
		this.estado = this.estado.venderBens(this);
	}

	public void atualizarMercados() {
		this.estado = this.estado.atualizaMercados(this);

	}

	public void viajarBuracoNegro(int x, int y) {
		this.estado = this.estado.viajarProximoBuracoNegro(this, x, y);
	}

	public void viajarModoWarp(int x, int y) {
		this.estado = this.estado.viajarModoWarp(this, x, y);
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

	public void atualizaBanco(int moeda) {

		this.banco = banco + moeda;

		if (this.banco <= 0)
			this.banco = 0;

		if (this.banco >= 30)
			this.banco = 30;
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
