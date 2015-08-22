package logicajogo.estados;

import logicajogo.Jogo;
import logicajogo.dados.DadoNormal;

public class AtaquePirata implements Estado {

	public AtaquePirata(Jogo j) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {

		return "Ataque Pirata";

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
	public Estado continuarJogo(Jogo j) {
		// TODO Auto-generated method stub

		if(j.qtdsAtaquesPirata()>0)
		{
			j.defineErro("Não pode saltar o combate");
			return this;
		}
		
		if (j.devolveEstadoAnterior() instanceof AtualizarMercados)
			return new Negociar(j);
		
		if (j.devolveEstadoAnterior() instanceof Movimentar)
			return new Explorar(j);
		
		return j.devolveEstadoAnterior();

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
	public Estado combaterPiratas(Jogo j) {

		int atn = j.consultaJogador().obterNave().obterForca();
		int cntwin = 0;
		int totpen = 0;

		for (int i = 0; i < j.qtdsAtaquesPirata(); i++) {
			DadoNormal dado = new DadoNormal();
			dado.lancarDado();

			int pen = (dado.getResultado() - atn);

			if (atn < dado.getResultado()) {
				j.atualizaBanco(-pen);
				totpen += pen;

			} else
				cntwin++;
		}

		j.limpaAtaquesPirata();

		j.defineErro("Foi atacado e apos combate ganhou " + cntwin + " combates e perdeu " + totpen + " moedas");

		return this;
	}

}
