package logicajogo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class GestorFicheiros {

	private Jogo jogo;

	public GestorFicheiros(Jogo j) {
		this.jogo = j;
	}

	public Jogo Carregar() throws IOException {

		File f = new File(System.getProperty("user.dir") + "/" + "jogo.mwe");

		Jogo game = null;

		try {

			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));

			game = (Jogo) ois.readObject();

			ois.close();

		} catch (IOException | ClassNotFoundException ex) {
		}

		return game;

	}

}
