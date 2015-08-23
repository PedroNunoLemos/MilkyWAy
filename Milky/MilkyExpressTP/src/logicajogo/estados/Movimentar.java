package logicajogo.estados;

import logicajogo.Jogo;
import logicajogo.Posicao;
import logicajogo.cartas.Carta;
import logicajogo.cartas.galaxia.BuracoNegro;
import logicajogo.cartas.galaxia.planetas.PlanetaPirata;
import logicajogo.cubos.Cubo;

public class Movimentar implements Estado {

	public Movimentar(Jogo j) {
	}

	@Override
	public String toString() {

		return "Movimentar";

	}

	@Override
	public Estado iniciarJogo(Jogo j) {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public Estado moverNave(Jogo j, int x, int y) {
		// TODO Auto-generated method stub

		int oldx = j.consultaJogador().obterNave().posicaoAtual()[0];
		int oldy = j.consultaJogador().obterNave().posicaoAtual()[1];

		if (!j.devolveMapa().validaMovimentoAdjacentes(oldx, oldy, x, y)) {
			j.defineMensagem("Tem de se deslocar para uma posicao adjacente.");
			return this;
		}

		if (!j.devolveMapa().consultaPosicao(x, y).foiExplorada()) {
			j.defineMensagem("Posicao nao explorada");
			return this;
		} else {

			if (x == oldx && y == oldy) {
				j.defineMensagem("Tem de haver movimentacao");
				return this;

			}

			j.consultaJogador().obterNave().mover(x, y);
			j.consultaJogador().atualizaMoedas(-1);
			j.defineMensagem(" ");

		}

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

		String msgbr = "Não existe buraco negro nessa posicao";

		Carta carta = j.devolveMapa().consultaPosicao(x, y).obterCarta();

		if (carta == null) {
			j.defineMensagem(msgbr);
			return this;
		}

		if (!j.devolveMapa().consultaPosicao(x, y).foiExplorada()) {
			j.defineMensagem("Posicao Não Explorada");
			return this;
		}

		if (carta instanceof BuracoNegro) {

			j.consultaJogador().obterNave().mover(x, y);
			j.consultaJogador().atualizaMoedas(-1);
			j.defineMensagem(" ");

			return this;

		} else {
			j.defineMensagem(msgbr);
			return this;
		}
	}

	@Override
	public Estado efetuaSuborno(Jogo j, Cubo cubo) {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public Estado viajarModoWarp(Jogo j, int x, int y) {
		// TODO Auto-generated method stub

		Posicao pos = j.devolveMapa().consultaPosicao(x, y);

		if (pos == null) {
			j.defineMensagem("Out Of Bounds :P");
			return this;
		}

		if (!j.consultaJogador().obterNave().estaParada()) {
			j.defineMensagem("So pode ser usado sem movimentos");
			return this;
		}

		if (pos.obterCarta() == null) {
			j.defineMensagem("Out Of Bounds :P");
			return this;
		}

		pos.defExplorada(true);
		j.consultaJogador().obterNave().mover(x, y);
		j.consultaJogador().atualizaMoedas(-1);
		j.defineMensagem(" ");

		return new Explorar(j);
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

		if (j.consultaJogador().obterNave().estaParada()) {
			j.defineMensagem("Nave nao movimentada.");
			return this;
		} else {

			int x = j.consultaJogador().obterNave().posicaoAtual()[0];
			int y = j.consultaJogador().obterNave().posicaoAtual()[1];

			Carta carta = j.devolveMapa().obtemCarta(x, y);

			if (carta == null)
				return this;

			if (carta instanceof PlanetaPirata) {
				j.adicionaAtaquePirata();
				j.adicionaAtaquePirata();
				j.defineMensagem("FOI ATACADO POR PIRATAS");

				j.salvaEstadoAnterior(this);
				return new AtaquePirata(j);
			}

			return new Explorar(j);
		}
	}

	@Override
	public Estado combaterPiratas(Jogo j) {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public Estado atualizarNave(Jogo j, int tipoatualizacao) {
		// TODO Auto-generated method stub
		return this;
	}

}
