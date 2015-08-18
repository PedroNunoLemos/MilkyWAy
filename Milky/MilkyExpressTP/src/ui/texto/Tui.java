package ui.texto;

import java.io.IOException;
import java.util.Scanner;

import logicajogo.Jogo;
import logicajogo.Posicao;
import logicajogo.Tabuleiro;
import logicajogo.cartas.*;
import logicajogo.cartas.galaxia.*;
import logicajogo.cartas.galaxia.planetas.*;
import logicajogo.estados.*;

public class Tui {

	private Jogo jogo;
	Scanner scanner = new Scanner(System.in);

	public Tui() {

		this.jogo = new Jogo();
	}

	private void limpaEcra() {
		for (int i = 0; i < 5; ++i)
			System.out.println();
	}

	private void mostraMenu() {

		System.out.println();
		System.out.println("============================");
		System.out.println("|  Milky Way Express - TUI |");
		System.out.println("============================");
		System.out.println("| Opcoes:                  |");
		System.out.println("|        1. Iniciar Jogo   |");
		System.out.println("|        2. Ler Jogo       |");
		System.out.println("|        3. Salvar Jogo    |");
		System.out.println("|        4. Sair           |");
		System.out.println("============================");

		System.out.print("Escolha: ");
	}

	int inputMenu() {

		int res;

		do {

			while (!scanner.hasNextInt()) {
				System.out.print("\nIsso e tudo menos um numero !! ");
				mostraMenu();
				scanner.nextLine();
			}

			res = scanner.nextInt();

			if (res > 3 || res < 0) {
				System.out.print("\nA serio ? Aprende  a ler :)\n> ");
			}

		} while (res > 6 || res < 0);

		return res;

	}

	public void mostraInterface() {

		this.mostraMenu();
		processaMenu(this.inputMenu());

	}

	void processaMenu(int opt) {

		switch (opt) {

		case 1:

			while (!(this.jogo.devolveEstado() instanceof FimdeJogo))
				this.processaEstado();

			if (this.jogo.devolveErro() != null && !this.jogo.devolveErro().isEmpty()) {

				System.out.println(this.jogo.devolveErro());
				return;

			}

			break;

		}

	}

	private char converteCartaParaChar(Carta carta) {

		if (carta instanceof Vazio)
			return 'x';
		if (carta instanceof BuracoNegro)
			return '@';
		if (carta instanceof PlanetaPirata)
			return 'Ç';
		if (carta instanceof Planeta)
			return 'C';

		return ' ';

	}

	private void desenhaMapa() {

		System.out.println();

		for (int y = -1; y < 7; y++) {
			for (int x = -1; x < 9; x++) {

				if (x >= 0 && x < 9 && y >= 0 && y < 7) {

					System.out.print("|");

					Carta card = this.jogo.devolveMapa().obtemCarta(x, y);
					Posicao pos = this.jogo.devolveMapa().consultaPosicao(x, y);

					if (this.jogo.consultaJogador().getNave().posicaoAtual()[0] == x
							&& this.jogo.consultaJogador().getNave().posicaoAtual()[1] == y) {

						System.out.print("L");

					} else {

						if (pos.foiExplorada() && card != null)
							System.out.print(converteCartaParaChar(card));
						else if (!pos.foiExplorada() && card != null)
							System.out.print("#");
						else
							System.out.print(".");
					}

					System.out.print("|");

				} else {

					if (x >= 0 || y >= 0)
						System.out.print("|");

					if (y == -1 && x >= 0)
						System.out.print(x);
					if (x == -1 && y >= 0)
						System.out.print(y);

					if (x == -1 && y == -1)
						System.out.print("   ");

					if (x >= 0 || y >= 0)
						System.out.print("|");

				}

			}
			System.out.println();
		}

	}

	private void menuEstado() {

		String res = "";

		if (this.jogo.devolveEstado() instanceof Movimentar) {
			res = "3. Movimentar Nave";
		}

		if (this.jogo.devolveEstado() instanceof Comprar) {
			res = "3. Comprar";
		}

		if (this.jogo.devolveEstado() instanceof Vender) {
			res = "3. Vender";
		}

		System.out.printf("%s -> 1. Info Nave 2. Info Carta  %s 0. Avançar", this.jogo.devolveEstado(), res);
		System.out.println();

	}

	void processaEstado() {

		System.out.println();
		this.menuEstado();

		if (this.jogo.devolveErro() != null && !this.jogo.devolveErro().isEmpty())
			System.out.println(this.jogo.devolveErro());

		this.desenhaMapa();

		System.out.flush();
		System.out.print("Escolha:");
		while (!scanner.hasNextInt()) {
			scanner.nextLine();
		}

		int res = scanner.nextInt();

		// if (res == 0)
		// this.jogo.mudarEstado();

		this.executaMenu(res);

		this.limpaEcra();


		res = -1;
	}

	private void executaMenu(int res) {

		if (res == 0) {

			if (this.jogo.devolveEstado().toString() == "Explorar") {
				this.jogo.atualizarMercados();
				return;
			}

			if (this.jogo.devolveEstado().toString() == "Atualizar Mercados") {
				this.jogo.comprarBens();
				return;
			}

			if (this.jogo.devolveEstado().toString() == "Comprar") {
				this.jogo.venderBens();
				return;
			}

			if (this.jogo.devolveEstado().toString() == "Vender") {
				this.jogo.venderBens();
				return;
			}

			if (this.jogo.devolveEstado().toString() == "Movimentar") {

				if (this.jogo.consultaJogador().getNave().estaParada())
					this.jogo.defineErro("Nave Tem de se movimentar neste turno");

				return;
			}

		}

		if (res == 3) {

			if (this.jogo.devolveEstado() instanceof Movimentar) {

				System.out.print("X:");

				while (!scanner.hasNextInt()) {
					scanner.nextLine();
				}

				int x = scanner.nextInt();

				System.out.print("Y:");

				while (!scanner.hasNextInt()) {
					scanner.nextLine();
				}

				int y = scanner.nextInt();

				this.jogo.moverNave(x, y);

			}
		}
	}

}
