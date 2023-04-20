package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoGuarda implements Comando {

	private IO io;
	static final private String NOME = "guarda";

	@Override
	public void esegui(Partita partita) {
		this.io.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		this.io.mostraMessaggio(partita.getGiocatore().getBorsa().toString());
		this.io.mostraMessaggio("CFU correnti: " + partita.getGiocatore().getCfu() + ".\n");
	}

	@Override
	public String getNome() {
		return ComandoGuarda.NOME;
	}

	@Override
	public void setParametro(String parametro) {
	}

	@Override
	public String getParametro() {
		return "";
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
