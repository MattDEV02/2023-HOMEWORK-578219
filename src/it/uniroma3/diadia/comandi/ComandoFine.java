package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoFine extends AbstractComando {

	private IO io;
	static final private String NOME = "fine";

	@Override
	public void esegui(Partita partita) {
		// TODO: stampa info partita...
		partita.setFinita();
		this.io.mostraMessaggio("Partita finita, grazie per aver giocato!");
	}

	@Override
	public String getParametro() {
		return null;
	}

	@Override
	public String getNome() {
		return ComandoFine.NOME;
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
