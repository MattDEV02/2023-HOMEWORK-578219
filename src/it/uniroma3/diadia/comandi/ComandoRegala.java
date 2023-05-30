package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.giocatore.Giocatore;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoRegala implements Comando {

	private final static String NOME = "regala";

	private IO io;
	private String nomeAttrezzoRegalo; // parametro

	@Override
	public void esegui(Partita partita) {
		AbstractPersonaggio personaggio = partita.getLabirinto().getStanzaCorrente().getPersonaggio();
		Giocatore giocatore = partita.getGiocatore();
		Borsa borsa = giocatore.getBorsa();
		if (borsa.hasAttrezzo(this.nomeAttrezzoRegalo)) {
			Attrezzo attrezzoRegalo = borsa.getNome2attrezzi().get(this.nomeAttrezzoRegalo);
			personaggio.riceviRegalo(attrezzoRegalo, partita);
		} else {
			this.io.mostraMessaggio(
					"Non puoi regalare l'attrezzo " + this.nomeAttrezzoRegalo + " perche non ce l'hai.");
		}

	}

	@Override
	public void setParametro(String nomeAttrezzoRegalo) {
		this.nomeAttrezzoRegalo = nomeAttrezzoRegalo;

	}

	@Override
	public String getNome() {
		return ComandoRegala.NOME;
	}

	@Override
	public String getParametro() {
		return this.nomeAttrezzoRegalo;
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
