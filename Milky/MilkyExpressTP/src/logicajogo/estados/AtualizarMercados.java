package logicajogo.estados;

import java.awt.Color;

import logicajogo.Jogo;
import logicajogo.Posicao;
import logicajogo.cartas.galaxia.planetas.PlanetaPirata;
import logicajogo.cubos.Ilegal;
import logicajogo.dados.DadoCor;

public class AtualizarMercados implements Estado {

	private void reabestecePirata(Jogo j) {

		for (Posicao pos : j.devolveMapa().listaPlanetasPiratas(true)) {

			PlanetaPirata carta = (PlanetaPirata) pos.obterCarta();

			if (carta.validaMercadoAberto() > 0)
				carta.atualizaMercado(new Ilegal());

		}
	}

	private void reabestecePlanetaNormal(Jogo j) {

		for (Posicao pos : j.devolveMapa().listaPlanetas(true, true)) {

			PlanetaPirata carta = (PlanetaPirata) pos.obterCarta();

			for (int i = 0; i < carta.validaMercadoAberto(); i++) {

				DadoCor dado = new DadoCor();
				dado.lancarDado();

				Color cor = dado.getResultado();

				if (cor == Color.white)
					j.consultaJogador().getNave().retiraCarga("Ilegal");

				if (cor == Color.black) {

				}

			}
		}
	}

	public AtualizarMercados(Jogo j) {

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
	public Estado comprarBens(Jogo j) {
		// TODO Auto-generated method stub
		return new Comprar(j);

	}

	@Override
	public Estado venderBens(Jogo j) {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public Estado explorar(Jogo j) {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public Estado atualizaMercados(Jogo j) {

	

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
	public Estado retomaMovimentoNormal(Jogo j) {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public Estado combaterPiratas(Jogo j) {
		// TODO Auto-generated method stub
		return this;
	}

}
