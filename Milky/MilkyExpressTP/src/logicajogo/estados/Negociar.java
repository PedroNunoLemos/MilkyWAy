package logicajogo.estados;

import logicajogo.Jogo;
import logicajogo.cartas.Carta;
import logicajogo.cartas.galaxia.planetas.PlanetaBase;
import logicajogo.cartas.galaxia.planetas.PlanetaPirata;
import logicajogo.cartas.naves.Nave;
import logicajogo.cubos.Cubo;

public class Negociar implements Estado {

	public Negociar(Jogo j) {
	}

	@Override
	public String toString() {

		return "Negociar";

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

		if (cubo == null)
			return this;

		Nave nave = j.consultaJogador().obterNave();

		int x = nave.posicaoAtual()[0];
		int y = nave.posicaoAtual()[1];

		Carta carta = j.devolveMapa().obtemCarta(x, y);
		PlanetaBase pl = (PlanetaBase) carta;

		int preco = 0;

		if (carta instanceof PlanetaPirata)
			preco = 1;
		else
			preco = pl.obtemPreco(cubo.obtemNome());

		if (!nave.podeLevarCarga()) {
			j.defineMensagem("Nao tem espaços de carga disponiveis");
			return this;
		}

		if (j.consultaJogador().devolveMoedas() < preco) {
			j.defineMensagem("Nao tem dinheiro suficiente");
			return this;
		}

		j.consultaJogador().atualizaMoedas(-preco);
		j.atualizaBanco(preco);
		pl.mercadoRetirarCubo(cubo);
		nave.adicionaCarga(cubo);

		j.defineMensagem("Comprou " + cubo.obtemNome() + " a um preco de " + preco + " moedas ");
		return this;
	}

	@Override
	public Estado venderBens(Jogo j, Cubo cubo) {

		if (cubo == null)
			return this;

		Nave nave = j.consultaJogador().obterNave();

		int x = nave.posicaoAtual()[0];
		int y = nave.posicaoAtual()[1];

		Carta carta = j.devolveMapa().obtemCarta(x, y);
		PlanetaBase pl = (PlanetaBase) carta;

		if (pl.mercadoComprador()) {
			j.defineMensagem("Este planeta ja efetuou compras nao pode vender");
			return this;
		}

		int preco = 0;

		preco = pl.obtemPreco(cubo.obtemNome());

		if (preco == 0) {
			j.defineMensagem("Este planeta nao compra este produto ...");
			return this;
		}

		int corinc = 0;
		for (int i = 0; i < pl.obterStock().length; i++)
			if ((pl.obterStock()[i] != null && (pl.obterStock()[i].obtemCor() != cubo.obtemCor()))
					|| pl.obterStock()[i] == null)
				corinc++;

		preco = preco + corinc;

		if (j.consultaJogador().ativouSuborno())
			preco = preco * 2;

		j.consultaJogador().atualizaMoedas(preco);
		j.atualizaBanco(-preco);
		nave.retiraCarga(cubo.obtemNome());

		j.defineMensagem("Vendeu " + cubo.obtemNome() + " por " + preco + " moedas ");
		return this;

	}

	@Override
	public Estado viajarProximoBuracoNegro(Jogo j, int x, int y) {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public Estado efetuaSuborno(Jogo j, Cubo cubo) {

		if (cubo == null)
			return this;

		Nave nave = j.consultaJogador().obterNave();

		int x = nave.posicaoAtual()[0];
		int y = nave.posicaoAtual()[1];

		Carta carta = j.devolveMapa().obtemCarta(x, y);
		PlanetaBase pl = (PlanetaBase) carta;

		if (j.consultaJogador().ativouSuborno()) {
			j.defineMensagem("Suborno previamente ativado");
			return this;
		}

		int moeda = (j.consultaJogador().devolveMoedas() - 1);

		j.consultaJogador().atualizaMoedas(-moeda);
		j.atualizaBanco(moeda);
		j.consultaJogador().ativaSuborno();

		for (int i = 0; i < nave.obterCarga().length; i++)
			if (!nave.obterCarga()[i].equals(cubo))
				nave.retiraCarga(nave.obterCarga()[i]);

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
		return new Movimentar(j);
	}

	@Override
	public Estado combaterPiratas(Jogo j) {
		// TODO Auto-generated method stub
		return this;
	}

}
