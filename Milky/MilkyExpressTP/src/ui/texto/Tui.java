package ui.texto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import logicajogo.Jogo;
import logicajogo.Posicao;
import logicajogo.cartas.*;
import logicajogo.cartas.galaxia.*;
import logicajogo.cartas.galaxia.planetas.*;
import logicajogo.estados.*;

public class Tui {

	private Jogo jogo;
	Scanner scanner = new Scanner(System.in);

	private int infox = 0, infoy = 0;
	private boolean pedeinfo = false;
	private int ecraativo = 1;

	private ArrayList<String> menu;

	public Tui() {

		this.jogo = new Jogo();
		this.menu = new ArrayList<String>();
	}

	private void limpaEcra() {
		for (int i = 0; i < 5; ++i)
			System.out.println();
	}

	private void mostraMenuPrincipal() {

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

	int trataMenuPrincipal() {

		int res;

		do {

			while (!scanner.hasNextInt()) {
				System.out.print("\nIsso e tudo menos um numero !! ");
				mostraMenuPrincipal();
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

		this.mostraMenuPrincipal();
		processaMenuPrincipal(this.trataMenuPrincipal());

	}

	void processaMenuPrincipal(int opt) {

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
			return '*';
		if (carta instanceof BuracoNegro)
			return '@';
		if (carta instanceof PlanetaPirata)
			return '�';
		if (carta instanceof Planeta)
			return 'o';

		return ' ';

	}

	private void desenhaAreaCentral() {

		System.out.println();

		for (int y = -1; y < 7; y++) {
			for (int x = -1; x < 10; x++) {

				// desenha mapa
				if (x >= 0 && x < 9 && y >= 0 && y < 7) {

					System.out.print("|");

					Carta card = this.jogo.devolveMapa().obtemCarta(x, y);
					Posicao pos = this.jogo.devolveMapa().consultaPosicao(x, y);

					if (this.jogo.consultaJogador().obterNave().posicaoAtual()[0] == x
							&& this.jogo.consultaJogador().obterNave().posicaoAtual()[1] == y) {

						System.out.print("H");

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

					// desenha coordenadas
					if (x < 9) {

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

					// Desenha area de Menu

					if (x == 9) {

						for (int k = 0; k < menu.size() && k < 8; k++)
							if (y == k - 1) {

								String spc = " ";

								int space = (25 - menu.get(k).length());

								for (int p = 0; p < space; p++)
									spc = spc + " ";

								System.out.print("  |" + menu.get(k) + spc + "|");
							}
					}

				}

			}
			System.out.println();
		}

	}

	private void mostraMenuLegenda() {

		this.menu.clear();

		this.menu.add("H -> Nave");
		this.menu.add("@ -> Buraco Negro");
		this.menu.add(". -> Espa�o Profundo");
		this.menu.add("# -> Espa�o N�o explorado");
		this.menu.add("� -> Planeta Pirata");
		this.menu.add("o -> Planeta");
		this.menu.add("-------------------------");
		this.menu.add("0 -> Voltar ao Menu  ");

	}

	private void mostraMenuEstados() {

		this.menu.clear();

		this.menu.add("    " + this.jogo.devolveEstado().toString());
		this.menu.add("1 -> Ver Info. Carta");

		if (this.jogo.devolveEstado() instanceof Movimentar) {

			int x = this.jogo.consultaJogador().obterNave().posicaoAtual()[0];
			int y = this.jogo.consultaJogador().obterNave().posicaoAtual()[1];

			Carta carta = this.jogo.devolveMapa().obtemCarta(x, y);

			this.menu.add("2 -> Mover Nave");

			if (carta != null && carta instanceof BuracoNegro) {
				this.menu.add("3 -> Viajar Buraco Negro");
			}

			this.menu.add("4 -> Efetuar Viagem Warp");

		}

		if (this.jogo.devolveEstado() instanceof Negociar) {

			int x = this.jogo.consultaJogador().obterNave().posicaoAtual()[0];
			int y = this.jogo.consultaJogador().obterNave().posicaoAtual()[1];

			Carta carta = this.jogo.devolveMapa().obtemCarta(x, y);

			if (carta != null && (carta instanceof Planeta || carta instanceof PlanetaPirata)) {
				this.menu.add("2 -> Comprar Bens");
				this.menu.add("3 -> Vender Bens");
			}

			this.menu.add("4 -> Atualizar Nave");

		}

		if (this.jogo.devolveEstado() instanceof AtaquePirata && this.jogo.qtdsAtaquesPirata() > 0) {
			this.menu.add("2 -> Combater");

		}

		this.menu.add("9 -> Mostra Legenda");
		this.menu.add("-------------------------");
		this.menu.add("0 -> Continuar");

	}

	private void defineMenu(int men) {

		switch (men) {
		case 1:
			this.mostraMenuEstados();
			break;
		case 2:
			this.mostraMenuLegenda();
			break;

		}
	}

	void imprimeInfoCarta(int x, int y) {

		String infocarta = "";

		int medicamento = 0, agua = 0, comida = 0, ilegal = 0;

		Posicao pos = this.jogo.devolveMapa().consultaPosicao(x, y);

		if (pos == null)
			return;

		Carta carta = pos.obterCarta();

		if (carta != null && pos.foiExplorada()) {

			if (carta instanceof PlanetaPirata)
				infocarta = "Planeta Pirata";

			if (carta instanceof Planeta)
				infocarta = "Planeta";

			if (carta instanceof Vazio)
				infocarta = "Vazio";

			if (carta instanceof BuracoNegro)
				infocarta = "Buraco Negro";

			if (carta instanceof Planeta) {
				Planeta pl = (Planeta) carta;
				agua = pl.obtemPreco("Agua");
				comida = pl.obtemPreco("Comida");
				medicamento = pl.obtemPreco("Medicamento");
				ilegal = pl.obtemPreco("Ilegal");
			}

			if (carta instanceof PlanetaPirata) {
				PlanetaPirata pl = (PlanetaPirata) carta;
				agua = pl.obtemPreco("Agua");
				comida = pl.obtemPreco("Comida");
				medicamento = pl.obtemPreco("Medicamento");
				ilegal = pl.obtemPreco("Ilegal");
			}

			if (carta instanceof Planeta || carta instanceof PlanetaPirata) {
				String stock = "";

				if (carta instanceof Planeta) {
					Planeta pl = (Planeta) carta;

					stock = "Stock [" + pl.veMercado(0) + "]|[" + pl.veMercado(1) + "]";

				}

				if (carta instanceof PlanetaPirata) {
					PlanetaPirata pl = (PlanetaPirata) carta;

					stock = "Stock [" + pl.veMercado(0) + "]";
				}

				System.out.printf("Carta : %s|Tipo: %s|Precario: [Comida:%d][Agua:%d][Medicamento:%d][Ilegal:%d] %s",
						carta.getNome().toUpperCase(), infocarta, comida, agua, medicamento, ilegal, stock);
			} else {
				System.out.printf("Carta : %s", carta.getNome().toUpperCase());
			}

		} else {
			System.out.printf("Carta : Indisponivel ");
		}

		System.out.println();
	}

	void processaEstado() {

		System.out.println();

		char car1 = this.jogo.consultaJogador().obterNave().consultaCuboCarga(0).toUpperCase().charAt(0);
		char car2 = this.jogo.consultaJogador().obterNave().consultaCuboCarga(0).toUpperCase().charAt(0);
		char car3 = 'x';

		if (this.jogo.consultaJogador().obterNave().naveCargaAtualizada())
			car3 = this.jogo.consultaJogador().obterNave().consultaCuboCarga(0).toUpperCase().charAt(0);

		System.out.printf("Jogador 1 [ %d Moedas ] [Nave  For�a:%d Carga |%c|%c|%c|]",
				this.jogo.consultaJogador().devolveMoedas(), this.jogo.consultaJogador().obterNave().obterForca(), car1,
				car2, car3);
		System.out.println();

		int x = this.jogo.consultaJogador().obterNave().posicaoAtual()[0];
		int y = this.jogo.consultaJogador().obterNave().posicaoAtual()[1];

		if ((infox != x || infoy != y) && pedeinfo) {
			x = infox;
			y = infoy;
		}

		this.imprimeInfoCarta(x, y);

		if (this.jogo.devolveErro() != null && !this.jogo.devolveErro().isEmpty())
			System.out.println(this.jogo.devolveErro());

		infox = this.jogo.consultaJogador().obterNave().posicaoAtual()[0];
		infoy = this.jogo.consultaJogador().obterNave().posicaoAtual()[1];

		this.pedeinfo = false;

		this.defineMenu(this.ecraativo);
		this.desenhaAreaCentral();

		System.out.print("Escolha:");
		while (!scanner.hasNextInt()) {
			scanner.nextLine();
		}

		int res = scanner.nextInt();

		this.executaMenu(res);

		this.limpaEcra();

		res = -1;
	}

	private void executaMenu(int res) {

		if (res == 9) {

			this.mostraMenuLegenda();
			this.ecraativo = 2;
		}

		if (res == 0 && ecraativo == 2) {
			this.mostraMenuEstados();
			this.ecraativo = 1;
		}

		if (res == 0 && this.ecraativo == 1) {

			this.jogo.defineErro("");
			this.jogo.continuarJogo();

		}

		if (res == 1) {

			System.out.print("X:");

			while (!scanner.hasNextInt()) {
				scanner.nextLine();
			}

			int xi = scanner.nextInt();

			System.out.print("Y:");

			while (!scanner.hasNextInt()) {
				scanner.nextLine();
			}

			int yi = scanner.nextInt();

			infox = xi;
			infoy = yi;

			this.pedeinfo = true;

		}

		if (res == 2) {

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

			if (this.jogo.devolveEstado() instanceof AtaquePirata) {
				this.jogo.combaterPiratas();
			}
		}

		if (res == 3) {

			if (this.jogo.devolveEstado() instanceof Movimentar) {

				int x = this.jogo.consultaJogador().obterNave().posicaoAtual()[0];
				int y = this.jogo.consultaJogador().obterNave().posicaoAtual()[1];

				if (this.jogo.devolveMapa().consultaPosicao(x, y).obterCarta() instanceof BuracoNegro) {

					System.out.print("X:");

					while (!scanner.hasNextInt()) {
						scanner.nextLine();
					}

					int xi = scanner.nextInt();

					System.out.print("Y:");

					while (!scanner.hasNextInt()) {
						scanner.nextLine();
					}

					int yi = scanner.nextInt();
					this.jogo.viajarBuracoNegro(xi, yi);

				}
			}
		}

		if (res == 4) {

			if (this.jogo.devolveEstado() instanceof Movimentar) {

				System.out.print("X:");

				while (!scanner.hasNextInt()) {
					scanner.nextLine();
				}

				int xi = scanner.nextInt();

				System.out.print("Y:");

				while (!scanner.hasNextInt()) {
					scanner.nextLine();
				}

				int yi = scanner.nextInt();

				this.jogo.viajarModoWarp(xi, yi);

			}

		}

	}
}
