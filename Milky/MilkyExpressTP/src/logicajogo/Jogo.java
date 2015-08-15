package logicajogo;

import java.util.ArrayList;

import logicajogo.estados.Estado;
import logicajogo.estados.IniciarJogo;

public class Jogo {

	private Estado estado;
	private Baralho baralhojogo;
	private ArrayList<Jogador> jogadores;
	private int banco = 30;
	
	private String ultimoErro;

	public Jogo() {
		this.estado = new IniciarJogo(this);
	}
	

	public void mudarEstado() {
		// System.out.println("Estado anterior: " + this.estado);
		this.estado = this.estado.proximoEstado();
		System.out.println("Estado atual: " + this.estado);
	}
	
	public  String devolveErro(){
		
		return this.ultimoErro;
	}

	int preparaJogo(int njog) {

		if (njog > 2 || njog < 1)
			return -1;

		baralhojogo = new Baralho();

		int moedas = 10 / njog;
		banco = 20;

		for (int i = 0; i < njog; i++)
			jogadores.add(new Jogador(moedas));

		return 1;
	}

}
