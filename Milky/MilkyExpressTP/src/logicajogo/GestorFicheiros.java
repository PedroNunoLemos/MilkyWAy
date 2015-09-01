package logicajogo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class GestorFicheiros {

	String fich;

	public GestorFicheiros(String ficheiro) {
		this.fich = ficheiro;
	}

	public GestorFicheiros() {
		this.fich = System.getProperty("user.dir") + "/" + "jogo.mwe";
	}

	public Jogo lerJogo() {

		String fich = System.getProperty("user.dir") + "/" + "jogo.mwe";
		return this.lerJogo(fich);

	}

	public Jogo lerJogo(String fich) {

		GestorFicheiros fichs = new GestorFicheiros(fich);
		try {

			Jogo j = fichs.Carregar();

			if (j != null) {
				j.defineModeloAlterado();
			}

			return j;

		} catch (IOException e) {
		}

		return null;

	}

	private Jogo Carregar() throws IOException {

		File f = new File(fich);

		Jogo game = null;

		try {

			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));

			game = (Jogo) ois.readObject();

			ois.close();

		} catch (IOException | ClassNotFoundException ex) {
			game.defineMensagem(ex.getMessage());
		}

		if (game != null)
			game.defineMensagem("Jogo Carregado com sucesso");

		return game;

	}

	public boolean Salvar(Jogo game) throws IOException {

		try {

			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fich));

			oos.writeObject(game);

			oos.close();

			game.defineMensagem("Jogo Guardado com sucesso");

			return true;
		} catch (Exception ex) {
			ex.printStackTrace();

			return false;
		}

	}

}
