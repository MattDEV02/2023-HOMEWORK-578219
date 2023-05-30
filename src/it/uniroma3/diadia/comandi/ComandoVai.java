package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

public class ComandoVai implements Comando {

	private String nomeDirezione;
	private IO io;
	private final static String NOME = "vai";
	private final static String MESSAGGIO_DIR_INESISTENTE = "Direzione inesistente.";

	@Override
	public void esegui(Partita partita) {
		Stanza stanzaCorrente = partita.getLabirinto().getStanzaCorrente();
		if (this.nomeDirezione == null || this.nomeDirezione.equals("")) {
			this.io.mostraMessaggio("Direzione " + this.nomeDirezione + " NON valida.");
			return;
		}
		Stanza prossimaStanza;
		try {
			prossimaStanza = stanzaCorrente.getStanzaAdiacente(this.getParametro());
		} catch (IllegalArgumentException e) {
			this.getIo().mostraMessaggio(ComandoVai.MESSAGGIO_DIR_INESISTENTE);
			return;
		}
		if (prossimaStanza == null) {
			this.io.mostraMessaggio(ComandoVai.MESSAGGIO_DIR_INESISTENTE);
			return;
		}
		partita.getLabirinto().setStanzaCorrente(prossimaStanza);
		Giocatore giocatore = partita.getGiocatore();
		giocatore.setCfu(giocatore.getCfu() - 1);
	}

	@Override
	public String getParametro() {
		return this.nomeDirezione;
	}

	@Override
	public void setParametro(String parametro) {
		this.nomeDirezione = parametro;
	}

	@Override
	public String getNome() {
		return ComandoVai.NOME;
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