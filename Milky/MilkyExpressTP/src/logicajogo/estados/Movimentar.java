package logicajogo.estados;

import logicajogo.Jogo;

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

		int oldx = j.consultaJogador().getNave().posicaoAtual()[0];
		int oldy = j.consultaJogador().getNave().posicaoAtual()[1];

		if (!j.devolveMapa().consultaPosicao(x, y).foiExplorada()) {
			j.defineErro("Posicao nao explorada");
			return this;
		} else {

			if (x == oldx && y == oldy) {
				j.defineErro("Tem de haver movimentacao");
				return this;

			}

			j.consultaJogador().getNave().mover(x, y);
		}


		return new Explorar(j);

	}

	@Override
	public Estado comprarBens(Jogo j) {
		// TODO Auto-generated method stub
		return this;
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
		// TODO Auto-generated method stub
		return this;
	}

}
