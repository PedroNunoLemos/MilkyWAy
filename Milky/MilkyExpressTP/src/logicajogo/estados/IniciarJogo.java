package logicajogo.estados;

import logicajogo.Jogo;

public class IniciarJogo implements Estado {

	public IniciarJogo(Jogo j) {

		j.preparaJogo();

	}

	@Override
	public String toString() {

		return "Iniciar Jogo";

	}

	@Override
	public Estado iniciarJogo(Jogo j) {

		if (!j.temJogadores()) {
			j.defineErro("Sem Jogadores Criados");
			return this;

		} else
			return new Explorar(j);


	}

	@Override
	public Estado moverNave(Jogo j, int x, int y) {
		// TODO Auto-generated method stub
		return this;
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

		return this;
	}

	@Override
	public Estado atualizaMercados(Jogo j) {
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
