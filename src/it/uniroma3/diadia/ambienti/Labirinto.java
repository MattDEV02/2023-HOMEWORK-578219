package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * 
 * Classe che ha la responsabilità di creare il labirinto, di memorizzare la
 * stanza iniziale (entrata) e quella finale (uscita), aggiungere un riferimento
 * ad un'istanza di Labirinto nella classe Partita.
 * 
 * 
 * @author Lambertucci Matteo
 * @version 1
 * @see Stanza
 * @see Partita
 * @see Attrezzo
 *
 */

public class Labirinto {

	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;

	/**
	 * 
	 * @return la stanza d'ingresso (atrio).
	 */
	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}

	/**
	 * 
	 * @param la stanza nuova d'ingresso.
	 */
	public void setStanzaCorrente(Stanza stanzaIniziale) {
		this.stanzaIniziale = stanzaIniziale;
	}

	/**
	 * 
	 * @return restituisce la stanza vincente (biblioteca).
	 */
	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}

	/**
	 * 
	 * @param la nuova stanza vincente.
	 */
	public void setStanzaVincente(Stanza stanzaVincente) {
		this.stanzaVincente = stanzaVincente;
	}

	public static LabirintoBuilder newBuilder() {
		return new LabirintoBuilder();
	}
}
