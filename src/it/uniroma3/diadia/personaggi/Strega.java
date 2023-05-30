package it.uniroma3.diadia.personaggi;

import java.util.Collection;
import java.util.Collections;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio {

	private static final String MESSAGGIO_SALUTATO = "Mi hai salutato quindi ti trasferirò nella stanza con piu attrezzi !";
	private static final String MESSAGGIO_NON_SALUTATO = "NON mi hai salutato quindi ti trasferirò nella stanza con meno attrezzi !";
	private static final String MESSAGGIO_REGALO_RICEVUTO = "AHAHAHAHAHAHHA";

	public Strega(String nome, String presentazione) {
		super(nome, presentazione);
	}

	@Override
	public String agisci(Partita partita) {
		boolean haSalutato = super.haSalutato();
		String msg = null;
		Stanza stanzaCorrente = partita.getLabirinto().getStanzaCorrente();
		Collection<Stanza> stanzeAdiacenti = stanzaCorrente.getStanzeAdiacenti();
		Stanza stanzaMin = Collections.min(stanzeAdiacenti);
		Stanza stanzaMax = Collections.max(stanzeAdiacenti);
		if (haSalutato) {
			partita.getLabirinto().setStanzaCorrente(stanzaMax);
			msg = Strega.MESSAGGIO_SALUTATO;
		} else {
			msg = Strega.MESSAGGIO_NON_SALUTATO;
			partita.getLabirinto().setStanzaCorrente(stanzaMin);
		}
		return msg;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		return Strega.MESSAGGIO_REGALO_RICEVUTO;
	}

}
