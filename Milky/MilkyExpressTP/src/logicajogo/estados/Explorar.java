package logicajogo.estados;

import java.awt.Color;

import logicajogo.Jogo;
import logicajogo.Posicao;
import logicajogo.cartas.galaxia.planetas.PlanetaPirata;
import logicajogo.cubos.Ilegal;
import logicajogo.dados.DadoCor;

public class Explorar implements Estado {

	public Explorar(Jogo j) {

		int cordx = j.consultaJogador().getNave().posicaoAtual()[0];
		int cordy = j.consultaJogador().getNave().posicaoAtual()[1];

		j.devolveMapa().viraCartasAdjacentes(cordx, cordy);
		j.consultaJogador().getNave().paraNave();		
		
	}

	@Override
	public String toString() {

		return "Explorar";

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
		// TODO Auto-generated method stub

		return  new AtualizarMercados(j);


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
