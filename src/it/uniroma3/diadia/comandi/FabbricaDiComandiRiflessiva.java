package it.uniroma3.diadia.comandi;

import java.util.NoSuchElementException;
import java.util.Scanner;

import it.uniroma3.diadia.IO;

public class FabbricaDiComandiRiflessiva implements FabbricaDiComandi {

	private IO io;

	public FabbricaDiComandiRiflessiva(IO io) {
		this.io = io;
	}

	public IO getIo() {
		return this.io;
	}

	public void setIo(IO io) {
		this.io = io;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Comando costruisciComando(String istruzione) throws Exception {
		@SuppressWarnings("resource")
		Scanner scannerDiParole = new Scanner(istruzione);
		String nomeComando = null;
		String parametro = null;
		Comando comando = null;
		try {
			if (scannerDiParole.hasNext())
				nomeComando = scannerDiParole.next();// prima parola: nome del comando
			if (scannerDiParole.hasNext())
				parametro = scannerDiParole.next();// seconda parola: eventuale parametro
		} catch (NoSuchElementException | IllegalStateException e) {
			e.printStackTrace();
			return null;
		}
		try {
			String nomeClasse = "it.uniroma3.diadia.comandi.Comando";
			nomeClasse += Character.toUpperCase(nomeComando.charAt(0));
			nomeClasse += nomeComando.substring(1);
			comando = (Comando) (Class.forName(nomeClasse).newInstance());
			comando.setParametro(parametro);
		} catch (Exception e) {
			comando = new ComandoNonValido();
			this.io.mostraMessaggio("Comando inesistente");
		}
		return comando;
	}
}