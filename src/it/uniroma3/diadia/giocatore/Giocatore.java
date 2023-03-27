package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.Partita;

/**
 * 
 * Classe che ha la responsabilità di gestire i CFU del giocatore e di
 * memorizzare gli attrezzi in un oggetto istanza della classe Borsa e di
 * aggiungere un riferimento ad un'istanza di Giocatore nella classe Partita.
 * 
 * @author Lambertucci Matteo
 * @see Borsa
 * @see Partita
 * @version 1
 * 
 */
public class Giocatore {

	static final private int DEFAULT_CFU_INIZIALI = 20;
	private int cfu;
	private Borsa borsa;

	/**
	 * 
	 * Costruttore di riferimento per la classe giocatore, istanzia un giocatore con
	 * una borsa vuota e un numero di cfu pari a Giocatore.DEFAULT_CFU_INIZIALI.
	 * 
	 */
	public Giocatore() {
		if (Giocatore.valid(Giocatore.DEFAULT_CFU_INIZIALI)) {
			this.cfu = Giocatore.DEFAULT_CFU_INIZIALI;
			this.borsa = new Borsa();
		}
	}

	/**
	 * 
	 * @return il numero di CFU correnti.
	 */
	public int getCfu() {
		return this.cfu;
	}

	/**
	 * aggiorna il numero di Cfu.
	 * 
	 * @param cfu aggiornate.
	 */
	public void setCfu(int cfu) {
		if (Giocatore.valid(cfu))
			this.cfu = cfu;
	}

	/**
	 * 
	 * @return la borsa del giocatore.
	 */
	public Borsa getBorsa() {
		return this.borsa;
	}

	/**
	 * aggiorna la borsa.
	 * 
	 * @param borsa nuova.
	 */
	public void setBorsa(Borsa borsa) {
		this.borsa = borsa;
	}

	/**
	 * Restituisce una rappresentazione stringa di questo giocatore.
	 * 
	 * @return la rappresentazione stringa del giocatore.
	 */
	@Override // overrides toString() di java.lang.Object
	public String toString() {
		return "CFU giocatore: " + this.getCfu() + " Borsa giocatore: " + this.getBorsa().toString();
	}

	/**
	 * 
	 * @param o l'oggetto da confrontare con il giocatore corrente.
	 * 
	 * @return Restituisce true se il giocatore corrente e quello passato tramite
	 *         parametro hanno stessi cfu e borsa, false altrimenti.
	 */
	@Override // overrides equals(Object o) di java.lang.Object
	public boolean equals(Object o) {
		Giocatore g = (Giocatore) (o);// down-casting.
		return this.getCfu() == g.getCfu() && this.getBorsa().equals(g.getBorsa());
	}

	/**
	 * Funzione di validazione input del giocatore.
	 * 
	 * @return true se i cfu del giocatore sono >= 0, false altrimenti.
	 */
	public static boolean valid(int cfu) {
		return cfu >= 0;
	}
}
