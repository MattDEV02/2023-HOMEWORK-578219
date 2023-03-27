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

	private Stanza stanzaIngresso;
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
	private void init() {
		/* creazione gli attrezzi */
		Attrezzo lanterna = new Attrezzo("lanterna", 3);
		Attrezzo osso = new Attrezzo("osso", 1);

		/* creazione stanze */
		Stanza atrio = new Stanza("Atrio");
		Stanza aulaN11 = new Stanza("Aula N11");
		Stanza aulaN10 = new Stanza("Aula N10");
		Stanza laboratorio = new Stanza("Laboratorio Campus");
		Stanza biblioteca = new Stanza("Biblioteca");

		/* unisce le stanze */
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

		/* metto gli attrezzi nelle stanze */
		aulaN10.addAttrezzo(lanterna);
		atrio.addAttrezzo(osso);

		// il gioco inzia nell'atrio
		this.stanzaIngresso = atrio;
		// il gioco si vince quando si raggiunge la biblioteca
		this.stanzaVincente = biblioteca;
	}

	/**
	 * 
	 * @return la stanza d'ingresso (atrio).
	 */
	public Stanza getStanzaCorrente() {
		return this.stanzaIngresso;
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
	 * @param la stanza nuova d'ingresso.
	 */
	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaIngresso = stanzaCorrente;
	}

	/**
	 * 
	 * @param la nuova stanza vincente.
	 */
	public void setStanzaVincente(Stanza stanzaVincente) {
		this.stanzaVincente = stanzaVincente;
	}

}
