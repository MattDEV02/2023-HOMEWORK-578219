package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoPosa implements Comando {

	private IO io;
	static final private String NOME = "posa";
	private String nomeAttrezzo;

	@Override
	public void esegui(Partita partita) {
		Borsa borsa = partita.getGiocatore().getBorsa();
		if (borsa.hasAttrezzo(nomeAttrezzo)) {
			Stanza stanzaCorrente = partita.getLabirinto().getStanzaCorrente();
			Attrezzo attrezoDaPosare = borsa.getAttrezzo(nomeAttrezzo);
			if (borsa.removeAttrezzo(nomeAttrezzo)) {
				if (stanzaCorrente.addAttrezzo(attrezoDaPosare)) {
					this.io.mostraMessaggio("L'attrezzo " + attrezoDaPosare.getNome() + " e' stato posato nella stanza "
							+ stanzaCorrente.getNome() + " e tolto dallo zaino con successo.");
				} else
					this.io.mostraMessaggio("La stanza e piena !");
			} else
				this.io.mostraMessaggio("La borsa e' vuota, quindi non puoi rimuovere attrezzi da essa.");
		} else
			this.io.mostraMessaggio("Attrezzo " + nomeAttrezzo + " non presente nella borsa.");
	}

	@Override
	public String getParametro() {
		return this.nomeAttrezzo;
	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;
	}

	@Override
	public String getNome() {
		return ComandoPosa.NOME;
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
