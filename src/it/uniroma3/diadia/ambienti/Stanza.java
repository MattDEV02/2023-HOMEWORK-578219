package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * 
 * Classe Stanza. Una stanza in un gioco di ruolo. Una stanza e' un luogo fisico
 * nel gioco. E' collegata ad altre stanze attraverso delle uscite. Ogni uscita
 * e' associata ad una direzione. Possiede anche un nome (identificativo) ed una
 * serie di attrezzi.
 * 
 * 
 * @author Lambertucci Matteo
 * @see Attrezzo
 * @see Labirinto
 * @version 1
 * 
 */

public class Stanza {

	static final private int NUMERO_MASSIMO_ATTREZZI = 10;
	static final private int NUMERO_MASSIMO_DIREZIONI = 4;
	private String nome;
	private String[] direzioni;
	private Attrezzo[] attrezzi;
	private int numeroAttrezzi;
	private Stanza[] stanzeAdiacenti;
	private int numeroStanzeAdiacenti;

	/**
	 * Crea una stanza. Non ci sono stanze adiacenti e non ci sono attrezzi.
	 * 
	 * @param nome il nome identificativo della stanza.
	 */
	public Stanza(String nome) { // costruttore di riferimento
		if (Stanza.valid(nome)) {
			this.nome = nome;
			this.numeroStanzeAdiacenti = 0;
			this.numeroAttrezzi = 0;
			this.direzioni = new String[Stanza.NUMERO_MASSIMO_DIREZIONI];
			this.stanzeAdiacenti = new Stanza[Stanza.NUMERO_MASSIMO_DIREZIONI];
			this.attrezzi = new Attrezzo[Stanza.NUMERO_MASSIMO_ATTREZZI];
		}
	}

	/**
	 * Restituisce la stanza adiacente nella direzione specificata.
	 * 
	 * @param direzione della stanza adiacente (nord-sud-ovest-est).
	 * @return la stanza adiacente a seconda della direzione input.
	 */
	public Stanza getStanzaAdiacente(String direzione) { // testata nella classe LabirintoTest
		if (direzione != null && !direzione.equals("")) {
			for (int i = 0; i < this.numeroStanzeAdiacenti; i++)
				if (this.direzioni[i].equals(direzione))
					return this.stanzeAdiacenti[i];
		}
		return null;
	}

	/**
	 * Restituisce la nome della stanza.
	 * 
	 * @return il nome della stanza.
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Restituisce la descrizione della stanza.
	 * 
	 * @return la descrizione della stanza.
	 */
	public String getDescrizione() {
		return this.toString();
	}

	/**
	 * Restituisce l'array di attrezzi presenti nella stanza.
	 * 
	 * @return l'array di attrezzi nella stanza.
	 */
	public Attrezzo[] getAttrezzi() {
		return this.attrezzi;
	}

	/**
	 * 
	 * @return restituisce il numero attuale di attrezzi nella stanza.
	 */
	public int getNumAttrezzi() {
		return this.numeroAttrezzi;
	}

	/**
	 * 
	 * @return restituisce il numero di stanze adiacenti alla stanza.
	 */
	public int getNumStanzeAdiacenti() {
		return this.numeroStanzeAdiacenti;
	}

	/**
	 * 
	 * @return restituisce l'array di stanze adiacenti alla stanza.
	 */
	public Stanza[] getStanzeAdiacenti() {
		return this.stanzeAdiacenti;
	}

	/**
	 * Restituisce l'oggetto attrezzo con nome nomeAttrezzo se presente nella
	 * stanza.
	 * 
	 * @param nomeAttrezzo in formato stringa.
	 * @return l'oggetto attrezzo presente nella stanza, null altrimenti.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		for (int i = 0; i < this.numeroAttrezzi; i++) {
			if (this.attrezzi[i] != null && this.attrezzi[i].getNome().equals(nomeAttrezzo))
				return this.attrezzi[i];
		}
		return null;
	}

	/**
	 * Restituisce un attrezzo dell' array di attrezzi presenti nella stanza
	 * partendo dall'indice di riferimento.
	 * 
	 * @return l'attrezzo con indice pari ad indice nella collezione di attrezzi
	 *         della stanza, null se l'indice esce dai limiti imposti.
	 */
	public Attrezzo getAttrezzo(int indice) {
		if (indice >= 0 && indice < this.getAttrezzi().length)
			return this.attrezzi[indice];
		return null;
	}

	/**
	 * 
	 * @return restituisce l'insieme di posizioni possibili in una determinata
	 *         stanza.
	 */
	public String[] getDirezioni() {
		String[] direzioniOutput = new String[this.numeroStanzeAdiacenti];
		for (int i = 0; i < this.numeroStanzeAdiacenti; i++)
			direzioniOutput[i] = this.direzioni[i];
		return direzioniOutput;
	}

	/**
	 * 
	 * @param il nuovo nome della stanza.
	 */
	public void setNome(String nome) {
		if (Stanza.valid(nome))
			this.nome = nome;
	}

	/**
	 * Restituisce una rappresentazione stringa di questa stanza, stampadone la
	 * descrizione, le uscite e gli eventuali attrezzi contenuti
	 * 
	 * @return la rappresentazione in formato stringa.
	 */
	@Override // overrides toString() di java.lang.Object
	public String toString() {
		StringBuilder output = new StringBuilder("Stanza corrente: " + this.getNome() + ".\nUscite:");
		for (String direzione : this.getDirezioni()) {
			if (direzione != null)
				output.append(" " + direzione);
		}
		output.append(".\nAttrezzi nella stanza " + this.getNome() + " ( " + this.getNumAttrezzi() + " ): ");
		if (this.isEmpty())
			output.append("Attrezzi non presenti");
		else {
			for (int i = 0; i < this.getNumAttrezzi(); i++) {
				if (this.attrezzi[i] != null)
					output.append(this.attrezzi[i].toString() + " ");
			}
		}
		return output.toString();
	}

	/**
	 * 
	 * @param o l'oggetto da confrontare con la stanza corrente.
	 * 
	 * @return Restituisce true se la stanza corrente e quella passato tramite
	 *         parametro hanno stesse proprietà, false altrimenti.
	 */
	@Override // overrides equals(Object o) di java.lang.Object
	public boolean equals(Object o) {
		Stanza s = (Stanza) (o); // down-casting.
		return this.getNome().equals(s.getNome());
	}

	/**
	 * Imposta una stanza adiacente.
	 *
	 * @param direzione direzione (in formato stringa) in cui sara' posta la stanza
	 *                  adiacente.
	 * @param stanza    stanza adiacente nella direzione indicata dal primo
	 *                  parametro.
	 */
	public void impostaStanzaAdiacente(String direzione, Stanza stanza) {
		boolean aggiornato = false;
		for (int i = 0; i < this.direzioni.length; i++)
			if (direzione != null) {
				if (direzione.equals(this.direzioni[i])) {
					this.stanzeAdiacenti[i] = stanza;
					aggiornato = true;
				}
			}
		if (!aggiornato)
			if (this.numeroStanzeAdiacenti < Stanza.NUMERO_MASSIMO_DIREZIONI) {
				this.direzioni[numeroStanzeAdiacenti] = direzione;
				this.stanzeAdiacenti[numeroStanzeAdiacenti] = stanza;
				this.numeroStanzeAdiacenti++;
			}
	}

	/**
	 * Calcola se la borsa è vuota oppure no.
	 * 
	 * @return true se la borsa non contiene attrezzi (è vuota), false altrimenti.
	 */
	public boolean isEmpty() { // || this.attrezzi == null || this.attrezzi[0] == null
		return this.getNumAttrezzi() == 0;
	}

	/**
	 * Controlla se un attrezzo è presente nella stanza tramite il nome dello
	 * stesso.
	 * 
	 * @return true se l'attrezzo è presente nella stanza, false altrimenti.
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		if (nomeAttrezzo == null || nomeAttrezzo.equals(""))
			return false;
		for (int i = 0; i < this.numeroAttrezzi; i++) {
			if (this.attrezzi[i] != null && this.attrezzi[i].getNome().equals(nomeAttrezzo))
				return true;
		}
		return false;
	}

	/**
	 * Mette un attrezzo nella stanza.
	 * 
	 * @param attrezzo l'attrezzo da mettere nella stanza.
	 * @return true se riesce ad aggiungere l'attrezzo nella stanza, false
	 *         atrimenti.
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (attrezzo != null && this.getNumAttrezzi() < Stanza.NUMERO_MASSIMO_ATTREZZI) {
			this.attrezzi[this.numeroAttrezzi] = attrezzo;
			this.numeroAttrezzi++;
			return true;
		}
		return false;
	}

	/**
	 * Rimuove un attrezzo con nome uguale al parametro dalla stanza .
	 * 
	 * @param nomeAttrezzo nome dell'attrezzo da rimuovere.
	 * @return true se l'attrezzo e' stato rimosso dalla stanza, false altrimenti
	 */
	public boolean removeAttrezzo(String nomeAttrezzo) {
		if (nomeAttrezzo == null || this.isEmpty() || !this.hasAttrezzo(nomeAttrezzo))
			return false;
		Attrezzo attrezzoDaRimuovere = this.getAttrezzo(nomeAttrezzo);
		for (int i = 0; i < this.numeroAttrezzi; i++) {
			if (this.attrezzi[i] != null && this.attrezzi[i].equals(attrezzoDaRimuovere)) {
				for (int j = i; j < this.numeroAttrezzi - 1; j++) {
					this.attrezzi[j] = this.attrezzi[j + 1];
				}
				this.numeroAttrezzi--;
				return true;
			}
		}
		return false;
	}

	/**
	 * Controlla se il nome di una stanza è valido.
	 * 
	 * @return true se il nome della stanza è valido, false altrimenti.
	 */
	public static boolean valid(String nomeStanza) {
		return nomeStanza != null && nomeStanza.length() > 0;
	}
}