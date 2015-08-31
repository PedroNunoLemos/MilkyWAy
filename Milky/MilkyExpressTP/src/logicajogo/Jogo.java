package logicajogo;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;

import ai.MotorIA;
import logicajogo.cubos.*;
import logicajogo.estados.Estado;
import logicajogo.estados.IniciarJogo;

public class Jogo extends Observable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Estado estado;
	private Estado estadoanterior;

	private Jogador jogador;
	private MotorIA iajogo;
	private int banco = 30;
	private int tipomov;

	private ArrayList<Cubo> reserva;

	private Tabuleiro tabuleiro;

	private String ultimaMsg;

	private int ataquesPiratas = 0;

	public Jogo() {

		this.estado = new IniciarJogo(this);
		this.estado = this.estado.iniciarJogo(this);
		this.reserva = new ArrayList<Cubo>();
		this.iajogo = new MotorIA(this);

		for (int i = 0; i < 10; i++)
			this.reserva.add(new Comida());

		for (int i = 0; i < 10; i++)
			this.reserva.add(new Agua());

		for (int i = 0; i < 10; i++)
			this.reserva.add(new Medicamento());

		for (int i = 0; i < 6; i++)
			this.reserva.add(new Ilegal());

		// this.estado = this.estado.continuarJogo(this);

		setChanged();
		notifyObservers();

	}

	public int temStockReserva(Color cor) {
		int cnt = 0;
		for (int i = 0; i < reserva.size(); i++) {

			if (reserva.get(i).obtemCor() == cor)
				cnt++;
		}

		return cnt;
	}

	public MotorIA obterIAJogo() {
		return this.iajogo;
	}

	public void moverNave(int x, int y) {

		this.estado = this.estado.moverNave(this, x, y);

		setChanged();
		notifyObservers();
	}

	public void atualizarNave(int tipo) {

		this.estado = this.estado.atualizarNave(this, tipo);

		setChanged();
		notifyObservers();
	}

	public void comprarBens(Cubo cubo) {

		this.estado = this.estado.comprarBens(this, cubo);

		setChanged();
		notifyObservers();
	}

	public void venderBens(Cubo cubo) {

		this.estado = this.estado.venderBens(this, cubo);

		setChanged();
		notifyObservers();
	}

	public void ativarSuborno(Cubo cubo) {

		this.estado = this.estado.efetuaSuborno(this, cubo);

		setChanged();
		notifyObservers();

	}

	public void viajarBuracoNegro(int x, int y) {

		this.estado = this.estado.viajarProximoBuracoNegro(this, x, y);

		setChanged();
		notifyObservers();

	}

	public void viajarModoWarp(int x, int y) {

		this.estado = this.estado.viajarModoWarp(this, x, y);

		setChanged();
		notifyObservers();

	}

	public void continuarJogo() {

		this.estado = this.estado.continuarJogo(this);

		setChanged();
		notifyObservers();

	}

	public void combaterPiratas() {

		if (this.qtdsAtaquesPirata() > 0) {
			this.estado = this.estado.combaterPiratas(this);

			setChanged();
			notifyObservers();

		}
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

		setChanged();
		notifyObservers();

	}

	public void preparaJogo() {

		banco = 20;

		jogador = new Jogador(10);

		jogador.obterNave().mover(0, 6);

		tabuleiro = new Tabuleiro();
		tabuleiro.geraGalaxia();

		setChanged();
		notifyObservers();

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

	public int obterTipomov() {
		return tipomov;
	}

	public void defineTipomov(int tipomov) {
		this.tipomov = tipomov;
	}
}
