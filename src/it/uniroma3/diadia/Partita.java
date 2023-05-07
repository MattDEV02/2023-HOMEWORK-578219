package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * 
 * Questa classe modella una partita del gioco DiaDia.
 *
 * @author Lambertucci Matteo
 * @see Stanza
 * @see Giocatore
 * @see Labirinto
 * @see DiaDia
 * @version 1
 * 
 */

public class Partita {

	private boolean finita;
	private Labirinto labirinto;
	private Giocatore giocatore;

	/**
	 * 
	 * Costruttore classe Partita.
	 * 
	 */
	public Partita(Labirinto labirinto) {
		this.finita = false;
		this.labirinto = labirinto;
		this.giocatore = new Giocatore();
	}

	/**
	 * Imposta la partita come finita.
	 *
	 */
	public boolean getFinita() {
		return this.finita;
	}

	/**
	 * 
	 * Imposta la partita come finita.
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}

	/**
	 * 
	 * @return il giocatore corrente della partita.
	 */
	public Giocatore getGiocatore() {
		return this.giocatore;
	}

	/**
	 * 
	 * @param il nuovo giocatore della partita.
	 */
	public void setGiocatore(Giocatore giocatore) {
		this.giocatore = giocatore;
	}

	/**
	 * 
	 * @return il labirinto corrente della partita.
	 */
	public Labirinto getLabirinto() {
		return this.labirinto;
	}

	/**
	 * 
	 * @param il nuovo labirinto della partita.
	 */
	public void setLabirinto(Labirinto labirinto) {
		this.labirinto = labirinto;
	}

	/**
	 * Restituisce true se e solo se la partita è finita.
	 * 
	 * @return vero se partita finita.
	 */
	public boolean isFinita() {
		return this.getFinita() || this.isPersa() || this.isVinta();
	}

	/**
	 * Restituisce true se la partita è stata persa, false altrimenti.
	 * 
	 * @return true se il giocatore ha terminato i cfu, false altrimenti.
	 */
	public boolean isPersa() {
		return this.getGiocatore().getCfu() == 0;
	}

	/**
	 * Restituisce true se e solo se la partita è stata vinta.
	 * 
	 * @return vero se partita vinta.
	 */
	public boolean isVinta() {
		return this.getGiocatore().getCfu() > 0
				&& this.getLabirinto().getStanzaVincente().equals(this.getLabirinto().getStanzaCorrente());
	}

	@Override
	public String toString() {
		return "Partita [finita=" + this.getFinita() + ", labirinto=" + this.getLabirinto() + ", giocatore="
				+ this.getGiocatore() + "]";
	}

}
