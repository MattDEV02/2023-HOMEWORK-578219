package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoNonValido extends AbstractComando {

	private IO io;

	final static private String NOME = "Comando NON valido";

	@Override
	public void esegui(Partita partita) {
		this.io.mostraMessaggio(
				"Comando vuoto / nullo / NON valido / sconosciuto, riprova con un altro comando ricordando che i comandi hanno una o due parole.");
	}

	@Override
	public String getParametro() {
		return null;
	}

	@Override
	public String getNome() {
		return ComandoNonValido.NOME;
	}

	@Override
	public IO getIo() {
		return this.io;
	}

	@Override
	public void setIo(IO io) {
		this.io = io;
	}
}
