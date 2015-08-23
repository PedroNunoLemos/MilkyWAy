package logicajogo.estados;

import java.awt.Color;
import java.util.ArrayList;

import logicajogo.Jogo;
import logicajogo.Posicao;
import logicajogo.cartas.galaxia.planetas.Planeta;
import logicajogo.cartas.galaxia.planetas.PlanetaPirata;
import logicajogo.cubos.*;
import logicajogo.dados.DadoCor;

public class AtualizarMercados implements Estado {

	private void reabestecePirata(Jogo j) {

		for (Posicao pos : j.devolveMapa().listaPlanetasPiratas(true)) {

			PlanetaPirata carta = (PlanetaPirata) pos.obterCarta();

			if (carta.mercadoEspacosDisponiveis() > 0)
				carta.mercadoReabastecer(new Ilegal());

		}
	}

	private void reabestecePlanetaNormal(Jogo j) {

		ArrayList<Posicao> planetas = j.devolveMapa().listaPlanetas(true, true);

		for (Posicao pos : planetas) {

			Planeta carta = (Planeta) pos.obterCarta();

			if (carta.mercadoEspacosDisponiveis() > 0) {
				DadoCor dado = new DadoCor();
				dado.lancarDado();

				Color cor = dado.getResultado();

				if (cor == Color.white)
					j.consultaJogador().obterNave().retiraCarga("Ilegal");

				if (cor == Color.black)
					j.adicionaAtaquePirata();

				if (cor == Color.yellow)
					carta.mercadoReabastecer(new Comida());

				if (cor == Color.red)
					carta.mercadoReabastecer(new Medicamento());

				if (cor == Color.blue)
					carta.mercadoReabastecer(new Agua());

			}
		}

	}

	private void validaPosseBensIlegais(Jogo j) {

		boolean il = false;

		for (int k = 0; k < 2; k++)
			if (j.consultaJogador().obterNave().consultaCuboCarga(k) == "Ilegal")
				il = true;

		if (il)
			for (int i = 0; i < 2; i++) {
				
				DadoCor dado = new DadoCor();
				dado.lancarDado();

				Color cor = dado.getResultado();

				if (cor == Color.white)
					j.consultaJogador().obterNave().retiraCarga("Ilegal");

				if (cor == Color.black)
					j.adicionaAtaquePirata();
			}

	}

	public AtualizarMercados(Jogo j) {

		reabestecePirata(j);
		reabestecePlanetaNormal(j);
		validaPosseBensIlegais(j);
		
	}

	@Override
	public String toString() {

		return "Atualizar Mercados";

	}

	@Override
	public Estado iniciarJogo(Jogo j) {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public Estado moverNave(Jogo j, int x, int y) {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public Estado comprarBens(Jogo j, Cubo cubo) {
		// TODO Auto-generated method stub
		return this;

	}

	@Override
	public Estado venderBens(Jogo j, Cubo cubo) {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public Estado viajarProximoBuracoNegro(Jogo j, int x, int y) {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public Estado efetuaSuborno(Jogo j) {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public Estado viajarModoWarp(Jogo j, int x, int y) {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public Estado fimJogo(Jogo j) {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public Estado acederMenu(Jogo j) {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public Estado continuarJogo(Jogo j) {

		if (j.qtdsAtaquesPirata() > 0) {
			j.defineErro("FOI ATACADO POR PIRATAS");
			j.salvaEstadoAnterior(this);

			return new AtaquePirata(j);
		} else
			return new Negociar(j);
	}

	@Override
	public Estado combaterPiratas(Jogo j) {
		// TODO Auto-generated method stub
		return this;
	}

}
