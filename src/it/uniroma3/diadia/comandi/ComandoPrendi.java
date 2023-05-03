package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi implements Comando {

	private IO io;
	static final private String NOME = "prendi";
	private String nomeAttrezzo;

	@Override
	public void esegui(Partita partita) {
		Stanza stanzaCorrente = partita.getLabirinto().getStanzaIniziale();
		if (stanzaCorrente.hasAttrezzo(nomeAttrezzo)) {
			Attrezzo attrezzoDaPrendere = stanzaCorrente.getAttrezzo(this.nomeAttrezzo);
			if (stanzaCorrente.removeAttrezzo(nomeAttrezzo)) {
				if (partita.getGiocatore().getBorsa().addAttrezzo(attrezzoDaPrendere)) {
					this.io.mostraMessaggio(
							"L'attrezzo " + attrezzoDaPrendere.getNome() + " e' stato preso dalla stanza "
									+ stanzaCorrente.getNome() + " e messo nello zaino con successo.");
				} else
					this.io.mostraMessaggio("La borsa va oltre il limite di peso.");
			} else
				this.io.mostraMessaggio("La stanza e' vuota, quindi non puoi rimuovere attrezzi da essa.");
		} else
			this.io.mostraMessaggio("Attrezzo " + nomeAttrezzo + " non presente nella stanza.");
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
		return ComandoPrendi.NOME;
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
