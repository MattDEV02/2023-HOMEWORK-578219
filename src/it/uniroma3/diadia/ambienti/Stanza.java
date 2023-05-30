package it.uniroma3.diadia.ambienti;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

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

public class Stanza implements Comparable<Stanza> {

	private String nome;
	private Map<String, Attrezzo> nome2attrezzi;
	private int numeroAttrezzi;
	private Map<Direzione, Stanza> direzioni2stanze;
	private int numeroStanzeAdiacenti;
	private AbstractPersonaggio personaggio;

	static final private int NUMERO_MASSIMO_ATTREZZI = 10;
	static final private int NUMERO_MASSIMO_DIREZIONI = 4;

	/**
	 * Crea una stanza. Non ci sono stanze adiacenti e non ci sono attrezzi.
	 * 
	 * @param nome il nome identificativo della stanza.
	 */
	public Stanza(String nome) { // costruttore di riferimento
		if (Stanza.valid(nome)) {
			this.nome = nome;
			this.direzioni2stanze = new HashMap<Direzione, Stanza>();
			this.nome2attrezzi = new HashMap<String, Attrezzo>();
			this.numeroStanzeAdiacenti = 0;
			this.numeroAttrezzi = 0;
		}
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
	 * Restituisce l'insieme di attrezzi presenti nella stanza.
	 * 
	 * @return l'array di attrezzi nella stanza.
	 */
	public Collection<Attrezzo> getAttrezzi() {
		return this.nome2attrezzi.values();
	}

	public Map<String, Attrezzo> getNome2attrezzi() {
		return this.nome2attrezzi;
	}

	public void setNome2attrezzi(Map<String, Attrezzo> nome2attrezzi) {
		this.nome2attrezzi = nome2attrezzi;
	}

	/**
	 * 
	 * @return restituisce il numero attuale di attrezzi nella stanza.
	 */
	public int getNumAttrezzi() {
		return this.numeroAttrezzi;
	}

	public void setNumAttrezzi(int numeroAttrezzi) {
		if (numeroAttrezzi > 0) {
			this.numeroAttrezzi = numeroAttrezzi;
		}
	}

	/**
	 * 
	 * @return restituisce il numero di stanze adiacenti alla stanza.
	 */
	public int getNumStanzeAdiacenti() {
		return this.numeroStanzeAdiacenti;
	}

	public void setNumStanzeAdiacenti(int numeroStanzeAdiacenti) {
		if (numeroStanzeAdiacenti > 0) {
			this.numeroStanzeAdiacenti = numeroStanzeAdiacenti;
		}
	}

	/**
	 * 
	 * @return restituisce l'insieme di stanze adiacenti alla stanza.
	 */
	public Collection<Stanza> getStanzeAdiacenti() {
		return this.direzioni2stanze.values();
	}

	public Map<Direzione, Stanza> getDirezioni2stanze() {
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
	public Set<Direzione> getDirezioni() {
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

	public AbstractPersonaggio getPersonaggio() {
		return this.personaggio;
	}

	public void setPersonaggio(AbstractPersonaggio personaggio) {
		this.personaggio = personaggio;
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
		output.append(" [ ");
		for (Direzione direzione : this.getDirezioni()) {
			output.append(direzione.toString() + ", ");
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
		if (this == null || o == null)
			return false;
		Stanza s = (Stanza) (o); // down-casting.
		return this.getNome().equals(s.getNome());
	}

	@Override
	public int hashCode() {
		return this.getNome().hashCode();
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
	 * Restituisce la stanza adiacente nella direzione specificata.
	 * 
	 * @param direzione della stanza adiacente (nord-sud-ovest-est).
	 * @return la stanza adiacente a seconda della direzione input.
	 */
	public Stanza getStanzaAdiacente(String direzione) { // testata nella classe LabirintoTest
		Stanza stanza = null;
		try {
			Direzione dir = Direzione.valueOf(direzione);
			if (this.direzioni2stanze.containsKey(dir))
				stanza = this.direzioni2stanze.get(dir);
		} catch (IllegalArgumentException e) {
			return null;
		}
		return stanza;
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
		try {
			Direzione dir = Direzione.valueOf(direzione);
			if (this.direzioni2stanze.containsKey(dir)) {
				this.direzioni2stanze.put(dir, stanza);
				aggiornato = true;
			}
			if (!aggiornato)
				if (this.numeroStanzeAdiacenti < Stanza.NUMERO_MASSIMO_DIREZIONI) {
					this.direzioni2stanze.put(dir, stanza);
					this.numeroStanzeAdiacenti++;
				}
		} catch (IllegalArgumentException e) {
			return;
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
		if (attrezzo != null && this.getNumAttrezzi() < Stanza.NUMERO_MASSIMO_ATTREZZI) {
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

	@Override
	public int compareTo(Stanza stanza) {
		if (this.getNumAttrezzi() - stanza.getNumAttrezzi() == 0)
			return this.getNome().compareTo(stanza.getNome());
		return this.getNumAttrezzi() - stanza.getNumAttrezzi();
	}
}