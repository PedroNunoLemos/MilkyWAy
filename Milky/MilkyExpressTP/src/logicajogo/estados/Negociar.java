package logicajogo.estados;

import java.io.Serializable;

import logicajogo.Jogo;
import logicajogo.cartas.Carta;
import logicajogo.cartas.galaxia.planetas.PlanetaBase;
import logicajogo.cartas.galaxia.planetas.PlanetaPirata;
import logicajogo.cartas.naves.Nave;
import logicajogo.cubos.Cubo;

public class Negociar implements Estado, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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

		if (j.obterIAJogo().noRadar(j, x, y))
			preco += 1;

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

		if (j.obterIAJogo().noRadar(j, x, y))
			preco += 1;

		j.consultaJogador().atualizaMoedas(preco);
		j.atualizaBanco(-preco);
		nave.retiraCarga(cubo);

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
		
		j.defineMensagem("Conseguiu subornar as entidades planetarias.");


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

		int moedas = j.consultaJogador().devolveMoedas();

		if (moedas == 0) {
			j.defineMensagem("Perdeu ficou sem moedas");
			j.defineEstadojogo(1);
			return new FimdeJogo();
		}

		if (j.devolveMapa().todasViradas() && moedas <= 10) {
			j.defineMensagem("Perdeu nao conseguiu pagar a divida");
			j.defineEstadojogo(1);
			return new FimdeJogo();
		}

		if (j.devolveMapa().todasViradas() && moedas > 10) {
			j.defineMensagem("Ganhou conseguiu pagar a divida e esta a caminho do sucesso");
			j.defineEstadojogo(2);
			return new FimdeJogo();
		}

		return new Movimentar(j);
	}

	@Override
	public Estado combaterPiratas(Jogo j) {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public Estado atualizarNave(Jogo j, int tipoatualizacao) {
		// TODO Auto-generated method stub

		Nave nave = j.consultaJogador().obterNave();

		if (tipoatualizacao == 1) {
			int val = nave.obterProximoCustoUpgradeForca();

			if (val > j.consultaJogador().devolveMoedas()) {

				j.defineMensagem("Nao tem dinheiro para atualizar a força");
				return this;

			}

			j.consultaJogador().atualizaMoedas(-val);
			j.atualizaBanco(val);
			nave.atualizarForca();

		}

		if (tipoatualizacao == 2) {
			int val = 3;

			if (val > j.consultaJogador().devolveMoedas()) {

				j.defineMensagem("Nao tem dinheiro para atualizar a carga");
				return this;

			}

			j.consultaJogador().atualizaMoedas(-val);
			j.atualizaBanco(val);
			nave.ativaCargaMaxima();

		}

		j.defineMensagem("Nave atualizada");
		return this;
	}

}
