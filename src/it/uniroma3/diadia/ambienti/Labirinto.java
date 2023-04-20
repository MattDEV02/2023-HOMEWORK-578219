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

	private Stanza stanzaCorrente;
	private Stanza stanzaVincente;

	/**
	 * 
	 * Costruttore classe labirinto.
	 * 
	 */
	public Labirinto() {
		this.init();
	}

	/**
	 * 
	 * Crea le stanze con gli attrezzi e le vie di collegamento.
	 * 
	 */
	private void init() { // creaStanze()
		/* crea gli attrezzi */
		Attrezzo lanterna = new Attrezzo("lanterna", 3);
		Attrezzo osso = new Attrezzo("osso", 1);
		Attrezzo piedediporco = new Attrezzo("piedediporco", 2);

		/* crea stanze del labirinto */
		Stanza atrio = new Stanza("Atrio");
		Stanza aulaN11 = new StanzaMagica("Aula N11");
		Stanza aulaN10 = new StanzaBloccata("Aula N10", "est", "piedediporco");
		Stanza laboratorio = new StanzaBuia("Laboratorio Campus", "lanterna");
		Stanza biblioteca = new Stanza("Biblioteca");

		/* collega le stanze */
		atrio.impostaStanzaAdiacente("nord", biblioteca);
		atrio.impostaStanzaAdiacente("est", aulaN11);
		atrio.impostaStanzaAdiacente("sud", aulaN10);
		atrio.impostaStanzaAdiacente("ovest", laboratorio);
		aulaN11.impostaStanzaAdiacente("est", laboratorio);
		aulaN11.impostaStanzaAdiacente("ovest", atrio);
		aulaN10.impostaStanzaAdiacente("nord", atrio);
		aulaN10.impostaStanzaAdiacente("est", aulaN11);
		aulaN10.impostaStanzaAdiacente("ovest", laboratorio);
		laboratorio.impostaStanzaAdiacente("est", atrio);
		laboratorio.impostaStanzaAdiacente("ovest", aulaN11);
		biblioteca.impostaStanzaAdiacente("sud", atrio);

		/* pone gli attrezzi nelle stanze */
		aulaN10.addAttrezzo(lanterna);
		atrio.addAttrezzo(osso);
		aulaN11.addAttrezzo(piedediporco);

		// il gioco comincia nell'atrio
		this.stanzaCorrente = atrio;
		this.stanzaVincente = biblioteca;
	}

	/**
	 * 
	 * @return la stanza d'ingresso (atrio).
	 */
	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}

	/**
	 * 
	 * @param la stanza nuova d'ingresso.
	 */
	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
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

}
