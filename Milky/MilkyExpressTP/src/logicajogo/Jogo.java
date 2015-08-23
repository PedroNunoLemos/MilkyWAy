package logicajogo;

import java.awt.Color;
import java.util.ArrayList;

import logicajogo.cubos.*;
import logicajogo.estados.Estado;
import logicajogo.estados.IniciarJogo;

public class Jogo {

	private Estado estado;
	private Estado estadoanterior;

	private Jogador jogador;
	private int banco = 30;

	private ArrayList<Cubo> reserva;

	private Tabuleiro tabuleiro;

	private String ultimaMsg;

	private int ataquesPiratas = 0;

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

		// this.estado = this.estado.continuarJogo(this);

	}

	public int temStockReserva(Color cor) {
		int cnt = 0;
		for (int i = 0; i < reserva.size(); i++) {

			if (reserva.get(i).obtemCor() == cor)
				cnt++;
		}

		return cnt;
	}

	public void moverNave(int x, int y) {

		this.estado = this.estado.moverNave(this, x, y);
	}

	public void comprarBens(Cubo cubo) {

		this.estado = this.estado.comprarBens(this, cubo);
	}

	public void venderBens(Cubo cubo) {

		this.estado = this.estado.venderBens(this, cubo);
	}

	public void ativarSuborno(Cubo cubo) {

		this.estado = this.estado.efetuaSuborno(this, cubo);
	}

	public void viajarBuracoNegro(int x, int y) {

		this.estado = this.estado.viajarProximoBuracoNegro(this, x, y);
	}

	public void viajarModoWarp(int x, int y) {

		this.estado = this.estado.viajarModoWarp(this, x, y);
	}

	public void continuarJogo() {

		this.estado = this.estado.continuarJogo(this);
	}

	public void combaterPiratas() {

		if (this.qtdsAtaquesPirata() > 0)
			this.estado = this.estado.combaterPiratas(this);
	}

	public void adicionaAtaquePirata() {

		this.ataquesPiratas += 1;
	}

	public void limpaAtaquesPirata() {

		this.ataquesPiratas = 0;
	}

	public int qtdsAtaquesPirata() {

		return this.ataquesPiratas;
	}

	public String devolveMensagem() {

		return this.ultimaMsg;
	}

	public Tabuleiro devolveMapa() {
		return this.tabuleiro;
	}

	public Estado devolveEstado() {

		return this.estado;

	}

	public void salvaEstadoAnterior(Estado estado) {

		this.estadoanterior = estado;

	}

	public Estado devolveEstadoAnterior() {

		return this.estadoanterior;

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

		jogador.obterNave().mover(0, 6);

		tabuleiro = new Tabuleiro();
		tabuleiro.geraGalaxia();

	}

	public void defineMensagem(String erro) {

		this.ultimaMsg = erro;
	}

	public boolean temJogadores() {

		return this.jogador != null;

	}

	public Jogador consultaJogador() {

		return this.jogador;

	}
}
