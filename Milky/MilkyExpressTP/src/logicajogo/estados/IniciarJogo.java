package logicajogo.estados;

import java.io.Serializable;

import logicajogo.Jogo;
import logicajogo.cubos.Cubo;

public class IniciarJogo implements Estado,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
			j.defineMensagem("Sem Jogadores Criados");
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
	public Estado efetuaSuborno(Jogo j, Cubo cubo) {
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
		// TODO Auto-generated method stub
		return this;
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
