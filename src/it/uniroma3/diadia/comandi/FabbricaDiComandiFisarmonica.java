package it.uniroma3.diadia.comandi;

import java.util.Scanner;

import it.uniroma3.diadia.IO;

public class FabbricaDiComandiFisarmonica implements FabbricaDiComandi {

	private IO io;

	public FabbricaDiComandiFisarmonica(IO io) {
		this.io = io;
	}

	@Override
	public Comando costruisciComando(String istruzione) {
		@SuppressWarnings("resource")
		Scanner scannerDiParole = new Scanner(istruzione);
		String nomeComando = null;
		String parametro = null;
		Comando comando = null;
		if (scannerDiParole.hasNext())
			nomeComando = scannerDiParole.next();// prima parola: nome del comando
		if (scannerDiParole.hasNext())
			parametro = scannerDiParole.next(); // seconda parola: parametro del comando.
		if (scannerDiParole.hasNext()) {
			comando = new ComandoNonValido(); // terza parola: non valida.
			parametro = null;
		}
		if (nomeComando == null)
			comando = new ComandoNonValido();
		else if (nomeComando.equals("vai") && parametro != null)
			comando = new ComandoVai();
		else if (nomeComando.equals("prendi") && parametro != null)
			comando = new ComandoPrendi();
		else if (nomeComando.equals("posa") && parametro != null)
			comando = new ComandoPosa();
		else if (nomeComando.equals("aiuto"))
			comando = new ComandoAiuto();
		else if (nomeComando.equals("fine"))
			comando = new ComandoFine();
		else if (nomeComando.equals("guarda"))
			comando = new ComandoGuarda();
		else
			comando = new ComandoNonValido();
		comando.setParametro(parametro);
		comando.setIo(this.io);
		// scannerDiParole.close();
		return comando;
	}
}