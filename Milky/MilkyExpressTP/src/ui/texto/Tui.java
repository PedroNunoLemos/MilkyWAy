package ui.texto;

import java.util.ArrayList;
import java.util.Scanner;

import logicajogo.Jogo;
import logicajogo.Posicao;
import logicajogo.cartas.*;
import logicajogo.cartas.galaxia.*;
import logicajogo.cartas.galaxia.planetas.*;
import logicajogo.cartas.naves.Nave;
import logicajogo.cubos.Cubo;
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
		System.out.println("|        3. Sair           |");
		System.out.println("============================");

		System.out.print("Escolha: ");
	}

	int trataMenuPrincipal() {

		int res;

		while (!scanner.hasNextInt()) {
			System.out.print("\nIsso e tudo menos um numero !! ");
			mostraMenuPrincipal();
			scanner.nextLine();
		}

		res = scanner.nextInt();

		if (res > 3 || res < 0) {
			System.out.print("\nA serio ? Aprende  a ler :)\n> ");
		}

		return res;

	}

	public void mostraInterface() {

		int opt = 0;
		while (opt != 3) {
			this.mostraMenuPrincipal();
			opt = this.trataMenuPrincipal();
			processaMenuPrincipal(opt);
		}

	}

	int processaMenuPrincipal(int opt) {

		switch (opt) {

		case 1:

			String estado = this.jogo.devolveEstado().toString();
			while (estado != "Fim de Jogo") {

				this.processaEstado();
				estado = this.jogo.devolveEstado().toString();
			}

			break;
		case 2:

			Jogo j = this.jogo.lerJogo();

			if (j != null) {
				
				this.jogo = j;
				this.processaMenuPrincipal(1);

			}

			break;

		}

		return opt;

	}

	private char converteCartaParaChar(Carta carta) {

		if (carta instanceof Vazio)
			return '*';
		if (carta instanceof BuracoNegro)
			return '@';
		if (carta instanceof PlanetaPirata)
			return 'º';
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

					char posc = '.';

					Carta card = this.jogo.devolveMapa().obtemCarta(x, y);
					Posicao pos = this.jogo.devolveMapa().consultaPosicao(x, y);

					int aix = this.jogo.obterIAJogo().obterNave().posicaoAtual()[0];
					int aiy = this.jogo.obterIAJogo().obterNave().posicaoAtual()[1];

					int jx = this.jogo.consultaJogador().obterNave().posicaoAtual()[0];
					int jy = this.jogo.consultaJogador().obterNave().posicaoAtual()[1];

					if (aix == x && aiy == y && pos.foiExplorada()) {
						posc = '«';
					}

					if (jx == x && jy == y) {
						posc = '»';
					}

					if (jx == aix && aiy == jy && jx == x && jy == y) {
						posc = 'ª';
					}

					if (posc == '.') {

						if (pos.foiExplorada() && card != null)
							posc = converteCartaParaChar(card);

						if (!pos.foiExplorada() && card != null)
							posc = '#';

					}

					System.out.print(posc);

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

		this.menu.add("» -> Nave");
		this.menu.add("« -> Nave Inimiga");
		this.menu.add("@ -> Buraco Negro");
		this.menu.add("# -> Espaço Não explorado");
		this.menu.add("º -> Planeta Pirata");
		this.menu.add("o -> Planeta");
		this.menu.add("-------------------------");
		this.menu.add("8 -> Voltar ao Menu  ");

	}

	private void mostramenuComprar() {

		this.menu.clear();

		int x = this.jogo.consultaJogador().obterNave().posicaoAtual()[0];
		int y = this.jogo.consultaJogador().obterNave().posicaoAtual()[1];

		Carta carta = this.jogo.devolveMapa().obtemCarta(x, y);
		PlanetaBase pl = (PlanetaBase) carta;

		if (carta != null && (carta instanceof Planeta || carta instanceof PlanetaPirata)) {

			Cubo[] stock = pl.obterStock();

			this.menu.add("Planeta : " + carta.getNome());
			this.menu.add("Mercado : ");

			if (stock[0] != null)
				this.menu.add("1 -> Comprar " + stock[0].obtemNome());

			if (stock.length > 1)
				if (stock[1] != null)
					this.menu.add("2 -> Comprar " + stock[1].obtemNome());

			this.menu.add("-------------------------");
			this.menu.add("8 -> Voltar ao Menu  ");

		}

	}

	private void mostramenuVender() {

		this.menu.clear();

		int x = this.jogo.consultaJogador().obterNave().posicaoAtual()[0];
		int y = this.jogo.consultaJogador().obterNave().posicaoAtual()[1];

		Carta carta = this.jogo.devolveMapa().obtemCarta(x, y);
		Nave nave = this.jogo.consultaJogador().obterNave();

		if (carta != null && (carta instanceof Planeta || carta instanceof PlanetaPirata)) {

			Cubo[] carga = nave.obterCarga();

			this.menu.add("Planeta : " + carta.getNome());
			this.menu.add("Nave : ");

			if (nave.obterTotalCargaOcupada() > 0) {
				if (carga[0] != null)
					this.menu.add("1 -> Vender " + carga[0].obtemNome());

				if (carga.length > 1)
					if (carga[1] != null)
						this.menu.add("2 -> Vender " + carga[1].obtemNome());

				if (carga.length > 2)
					if (carga[2] != null)
						this.menu.add("3 -> Vender " + carga[2].obtemNome());
			}

			this.menu.add("-------------------------");
			this.menu.add("8 -> Voltar ao Menu  ");

		}

	}

	private void mostramenuSuborno() {

		this.menu.clear();

		int x = this.jogo.consultaJogador().obterNave().posicaoAtual()[0];
		int y = this.jogo.consultaJogador().obterNave().posicaoAtual()[1];

		Carta carta = this.jogo.devolveMapa().obtemCarta(x, y);
		Nave nave = this.jogo.consultaJogador().obterNave();

		if (carta != null && (carta instanceof Planeta || carta instanceof PlanetaPirata)) {

			Cubo[] carga = nave.obterCarga();

			this.menu.add("Planeta : " + carta.getNome());
			this.menu.add("Nave (Subornar) : ");

			if (nave.obterTotalCargaOcupada() > 0) {
				if (carga[0] != null)
					this.menu.add("1 -> Guardar " + carga[0].obtemNome());

				if (carga.length > 1)
					if (carga[1] != null)
						this.menu.add("2 -> Guardar " + carga[1].obtemNome());

				if (carga.length > 2)
					if (carga[2] != null)
						this.menu.add("3 -> Guardar " + carga[2].obtemNome());
			}

			this.menu.add("-------------------------");
			this.menu.add("8 -> Voltar ao Menu  ");

		}

	}

	private void mostramenuAtualizarNave() {

		this.menu.clear();

		int x = this.jogo.consultaJogador().obterNave().posicaoAtual()[0];
		int y = this.jogo.consultaJogador().obterNave().posicaoAtual()[1];

		Carta carta = this.jogo.devolveMapa().obtemCarta(x, y);
		Nave nave = this.jogo.consultaJogador().obterNave();

		if (carta != null && (carta instanceof Planeta || carta instanceof PlanetaPirata)) {

			this.menu.add("Planeta : " + carta.getNome());
			this.menu.add("Nave : Atualizar ");

			if (!nave.maxForca())
				this.menu.add("1 -> Força (" + nave.obterProximoCustoUpgradeForca() + " Moedas)");

			if (!nave.naveCargaMaxima())
				this.menu.add("2 -> Carga (3 Moedas)");

		}

		this.menu.add("-------------------------");
		this.menu.add("8 -> Voltar ao Menu  ");

	}

	private void mostraMenuEstados() {

		this.menu.clear();

		this.menu.add("    " + this.jogo.devolveEstado().toString());
		this.menu.add("1 -> Ver Info. Carta");

		if (this.jogo.devolveEstado() instanceof Movimentar) {

			int x = this.jogo.consultaJogador().obterNave().posicaoAtual()[0];
			int y = this.jogo.consultaJogador().obterNave().posicaoAtual()[1];

			Carta carta = this.jogo.devolveMapa().obtemCarta(x, y);

			if ((carta instanceof BuracoNegro && !this.jogo.consultaJogador().obterNave().viajandoBuracoNegro()
					|| !(carta instanceof BuracoNegro)))
				this.menu.add("2 -> Mover Nave");

			if (carta != null && carta instanceof BuracoNegro) {
				this.menu.add("3 -> Viajar Buraco Negro");
			}

			if (!(carta instanceof BuracoNegro))
				this.menu.add("4 -> Efetuar Viagem Warp");

		}

		if (this.jogo.devolveEstado() instanceof Negociar) {

			int x = this.jogo.consultaJogador().obterNave().posicaoAtual()[0];
			int y = this.jogo.consultaJogador().obterNave().posicaoAtual()[1];

			Carta carta = this.jogo.devolveMapa().obtemCarta(x, y);

			if (carta != null && (carta instanceof Planeta || carta instanceof PlanetaPirata)) {
				this.menu.add("2 -> Comprar Bens");
				this.menu.add("3 -> Vender Bens");

				if (!this.jogo.consultaJogador().ativouSuborno())
					this.menu.add("4 -> Subornar");

				if (!this.jogo.consultaJogador().obterNave().maxForca()
						|| !this.jogo.consultaJogador().obterNave().naveCargaMaxima())
					this.menu.add("5 -> Atualizar Nave");

			}

		}

		if (this.jogo.devolveEstado() instanceof AtaquePirata && this.jogo.qtdsAtaquesPirata() > 0) {
			this.menu.add("2 -> Combater");

		}

		this.menu.add("9 -> Mostra Legenda");

		// this.menu.add("-------------------------");

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
		case 3:
			this.mostramenuComprar();
			break;

		case 4:
			this.mostramenuVender();
			break;

		case 5:
			this.mostramenuSuborno();
			break;

		case 6:
			this.mostramenuAtualizarNave();
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

	private int[] pedeCoords() {

		int[] crds = new int[2];

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

		crds[0] = xi;
		crds[1] = yi;

		return crds;
	}

	void processaEstado() {

		System.out.println("Menu Jogo | 99 -> Salva o Jogo Atual | 100 -> Carrega o ultimo Jogo |");

		char car1 = this.jogo.consultaJogador().obterNave().consultaCuboCarga(0).toUpperCase().charAt(0);
		char car2 = this.jogo.consultaJogador().obterNave().consultaCuboCarga(1).toUpperCase().charAt(0);
		char car3 = 'x';

		if (this.jogo.consultaJogador().obterNave().naveCargaMaxima())
			car3 = this.jogo.consultaJogador().obterNave().consultaCuboCarga(3).toUpperCase().charAt(0);

		System.out.printf("Jogador 1 [ %d Moedas ] [Nave  Força:%d Carga |%c|%c|%c|]",
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

		if (this.jogo.devolveMensagem() != null && !this.jogo.devolveMensagem().isEmpty())
			System.out.println(this.jogo.devolveMensagem());

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

		if (this.ecraativo == 6) {

			Nave nave = this.jogo.consultaJogador().obterNave();

			int x = nave.posicaoAtual()[0];
			int y = nave.posicaoAtual()[1];

			Carta carta = this.jogo.devolveMapa().obtemCarta(x, y);

			switch (res) {

			case 8:
				this.mostraMenuEstados();
				this.ecraativo = 1;

				break;
			}

			if (carta != null && (carta instanceof Planeta || carta instanceof PlanetaPirata)) {
				if (res >= 1 && res <= 2)
					this.jogo.atualizarNave(res);
			}
		}

		if (this.ecraativo == 5) {

			Nave nave = this.jogo.consultaJogador().obterNave();

			int x = nave.posicaoAtual()[0];
			int y = nave.posicaoAtual()[1];

			Carta carta = this.jogo.devolveMapa().obtemCarta(x, y);

			switch (res) {

			case 8:
				this.mostraMenuEstados();
				this.ecraativo = 1;

				break;
			}

			if (carta != null && (carta instanceof Planeta || carta instanceof PlanetaPirata)) {
				Cubo[] carga = nave.obterCarga();

				if (res >= 1 && res <= 3)
					if (carga[res - 1] != null) {
						this.jogo.ativarSuborno((carga[res - 1]));
					}
			}
		}

		if (this.ecraativo == 4) {

			Nave nave = this.jogo.consultaJogador().obterNave();

			int x = nave.posicaoAtual()[0];
			int y = nave.posicaoAtual()[1];

			Carta carta = this.jogo.devolveMapa().obtemCarta(x, y);

			switch (res) {

			case 8:
				this.mostraMenuEstados();
				this.ecraativo = 1;

				break;
			}

			if (carta != null && (carta instanceof Planeta || carta instanceof PlanetaPirata)) {

				Cubo[] carga = nave.obterCarga();

				if (res >= 1 && res <= 3)
					if (carga[res - 1] != null) {
						this.jogo.venderBens(carga[res - 1]);
					}
			}

		}

		if (this.ecraativo == 3) {

			int x = this.jogo.consultaJogador().obterNave().posicaoAtual()[0];
			int y = this.jogo.consultaJogador().obterNave().posicaoAtual()[1];

			Carta carta = this.jogo.devolveMapa().obtemCarta(x, y);
			PlanetaBase pl = (PlanetaBase) carta;

			switch (res) {

			case 8:
				this.mostraMenuEstados();
				this.ecraativo = 1;

				break;

			case 1:

				if (carta != null && (carta instanceof Planeta || carta instanceof PlanetaPirata)) {

					Cubo[] stock = pl.obterStock();

					if (stock[0] != null) {
						this.jogo.comprarBens(stock[0]);
					}
				}

				break;

			case 2:
				if (carta != null && (carta instanceof Planeta || carta instanceof PlanetaPirata)) {

					Cubo[] stock = pl.obterStock();

					if (stock[1] != null) {
						this.jogo.comprarBens(stock[1]);

					}
				}
				break;

			}

		}

		if (ecraativo == 2) {

			switch (res) {
			case 8:
				this.mostraMenuEstados();
				this.ecraativo = 1;
				break;
			}

		}

		if (this.ecraativo == 1) {

			switch (res) {

			case 0:
				this.jogo.defineMensagem("");
				this.jogo.continuarJogo();
				break;

			case 1:

				int crds[] = this.pedeCoords();

				infox = crds[0];
				infoy = crds[1];

				this.pedeinfo = true;

				break;

			case 2:

				if (this.jogo.devolveEstado() instanceof Negociar) {

					this.ecraativo = 3;
					this.mostramenuComprar();

				}

				if (this.jogo.devolveEstado() instanceof Movimentar) {

					int x = this.jogo.consultaJogador().obterNave().posicaoAtual()[0];
					int y = this.jogo.consultaJogador().obterNave().posicaoAtual()[1];

					Carta carta = this.jogo.devolveMapa().obtemCarta(x, y);

					if ((carta instanceof BuracoNegro && !this.jogo.consultaJogador().obterNave().viajandoBuracoNegro())
							|| !(carta instanceof BuracoNegro)) {

						int crdsxy[] = this.pedeCoords();

						int mvnx = crdsxy[0];
						int mvny = crdsxy[1];

						this.jogo.moverNave(mvnx, mvny);
					}
				}

				if (this.jogo.devolveEstado() instanceof AtaquePirata) {
					this.jogo.combaterPiratas();
				}

				break;

			case 3:

				if (this.jogo.devolveEstado() instanceof Negociar) {

					this.ecraativo = 4;
					this.mostramenuVender();

				}

				if (this.jogo.devolveEstado() instanceof Movimentar) {

					int x = this.jogo.consultaJogador().obterNave().posicaoAtual()[0];
					int y = this.jogo.consultaJogador().obterNave().posicaoAtual()[1];

					if (this.jogo.devolveMapa().consultaPosicao(x, y).obterCarta() instanceof BuracoNegro) {

						int crdsxy[] = this.pedeCoords();

						int xi = crdsxy[0];
						int yi = crdsxy[1];

						this.jogo.viajarBuracoNegro(xi, yi);

					}
				}

				break;

			case 4:

				if (this.jogo.devolveEstado() instanceof Negociar) {

					this.ecraativo = 5;
					this.mostramenuSuborno();
				}

				if (this.jogo.devolveEstado() instanceof Movimentar) {

					int x = this.jogo.consultaJogador().obterNave().posicaoAtual()[0];
					int y = this.jogo.consultaJogador().obterNave().posicaoAtual()[1];

					int crdsxy[] = this.pedeCoords();

					int xi = crdsxy[0];
					int yi = crdsxy[1];

					Carta carta = this.jogo.devolveMapa().obtemCarta(x, y);

					if (!(carta instanceof BuracoNegro))
						this.jogo.viajarModoWarp(xi, yi);
				}

				break;

			case 5:

				if (this.jogo.devolveEstado() instanceof Negociar) {

					this.ecraativo = 6;
					this.mostramenuAtualizarNave();

				}

				break;
			case 9:

				this.mostraMenuLegenda();
				this.ecraativo = 2;

				break;

			}

		}

		if (res == 99) {

			jogo.salvarJogo();

		}

		if (res == 100) {

			jogo.lerJogo();
		}

	}

}
