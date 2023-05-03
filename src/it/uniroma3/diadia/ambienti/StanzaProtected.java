package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaProtected {

	static final protected int NUMERO_MASSIMO_ATTREZZI = 10;
	static final protected int NUMERO_MASSIMO_DIREZIONI = 4;
	protected String nome;
	protected Map<String, Attrezzo> nome2attrezzi;
	protected int numeroAttrezzi;
	protected Map<String, Stanza> direzioni2stanze;
	protected int numeroStanzeAdiacenti;

	/**
	 * Crea una stanza. Non ci sono stanze adiacenti e non ci sono attrezzi.
	 * 
	 * @param nome il nome identificativo della stanza.
	 */
	public StanzaProtected(String nome) { // costruttore di riferimento
		if (Stanza.valid(nome)) {
			this.nome = nome;
			this.direzioni2stanze = new HashMap<>();
			this.nome2attrezzi = new HashMap<>();
			this.numeroStanzeAdiacenti = 0;
			this.numeroAttrezzi = 0;
		}
	}

	/**
	 * Restituisce la stanza adiacente nella direzione specificata.
	 * 
	 * @param direzione della stanza adiacente (nord-sud-ovest-est).
	 * @return la stanza adiacente a seconda della direzione input.
	 */
	public Stanza getStanzaAdiacente(String direzione) { // testata nella classe LabirintoTest
		Stanza stanza = null;
		if (this.direzioni2stanze.containsKey(direzione))
			stanza = this.direzioni2stanze.get(direzione);
		return stanza;
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
	public Map<String, Attrezzo> getAttrezzi() {
		return this.nome2attrezzi;
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
	public Map<String, Stanza> getStanzeAdiacenti() {
		return this.direzioni2stanze;
	}

	/**
	 * Restituisce l'oggetto attrezzo con nome nomeAttrezzo se presente nella
	 * stanza.
	 * 
	 * @param nomeAttrezzo in formato stringa.
	 * @return l'oggetto attrezzo presente nella stanza, null altrimenti.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return this.nome2attrezzi.get(nomeAttrezzo);
	}

	/**
	 * 
	 * @return restituisce l'insieme di posizioni possibili in una determinata
	 *         stanza.
	 */
	public Set<String> getDirezioni() {
		return this.direzioni2stanze.keySet();
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
		output.append("[ ");
		for (String direzione : this.getDirezioni()) {
			output.append(direzione + ", ");
		}
		output.deleteCharAt(output.length() - 2);
		output.append("]");
		output.append(".\nAttrezzi nella stanza " + this.getNome() + " ( " + this.getNumAttrezzi() + " ): ");
		if (this.isEmpty())
			output.append("Attrezzi non presenti nella Stanza.");
		else {
			output.append("( ");
			for (Attrezzo attrezzo : this.nome2attrezzi.values()) {
				output.append(attrezzo.getNome().toString() + ", " + attrezzo.toString() + " ; ");
			}
			output.deleteCharAt(output.length() - 2);
			output.append(")");
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
		if (direzioni2stanze.containsKey(direzione)) {
			this.direzioni2stanze.put(direzione, stanza);
			aggiornato = true;
		}
		if (!aggiornato)
			if (this.numeroStanzeAdiacenti < StanzaProtected.NUMERO_MASSIMO_DIREZIONI) {
				this.direzioni2stanze.put(direzione, stanza);
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
		return this.nome2attrezzi.containsKey(nomeAttrezzo);
	}

	/**
	 * Mette un attrezzo nella stanza.
	 * 
	 * @param attrezzo l'attrezzo da mettere nella stanza.
	 * @return true se riesce ad aggiungere l'attrezzo nella stanza, false
	 *         atrimenti.
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (attrezzo != null && this.getNumAttrezzi() < StanzaProtected.NUMERO_MASSIMO_ATTREZZI) {
			this.nome2attrezzi.put(attrezzo.getNome(), attrezzo);
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
		if (this.nome2attrezzi.remove(nomeAttrezzo, attrezzoDaRimuovere)) {
			this.numeroAttrezzi--;
			return true;
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
