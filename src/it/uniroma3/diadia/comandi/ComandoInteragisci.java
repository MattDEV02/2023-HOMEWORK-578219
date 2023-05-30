package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoInteragisci extends AbstractComando {

	private static final String NOME = "interagisci";
	private static final String MESSAGGIO_CON_CHI = "Con chi dovrei interagire?...";

	private String messaggio;
	private IO io;

	@Override
	public void esegui(Partita partita) {
		AbstractPersonaggio personaggio = partita.getLabirinto().getStanzaCorrente().getPersonaggio();
		if (personaggio != null) {
			this.messaggio = personaggio.agisci(partita);
			this.io.mostraMessaggio(this.messaggio);
		} else
			this.io.mostraMessaggio(ComandoInteragisci.MESSAGGIO_CON_CHI);
	}

	public String getMessaggio() {
		return this.messaggio;
	}

	@Override
	public String getNome() {
		return ComandoInteragisci.NOME;
	}

	@Override
	public String getParametro() {
		return null;
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
