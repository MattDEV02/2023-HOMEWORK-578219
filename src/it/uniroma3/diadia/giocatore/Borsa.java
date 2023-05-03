package it.uniroma3.diadia.giocatore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.attrezzi.ComparatoreAttrezziPerNome;
import it.uniroma3.diadia.attrezzi.ComparatoreAttrezziPerPesoPoiNome;

/**
 * 
 * Classe Borsa. Oggetto fisico ad uso del giocatore che è in grado di contenere
 * Attrezzi.
 *
 * @author Lambertucci Matteo
 * @see Attrezzo
 * @see Giocatore
 * @version 1
 * 
 */
public class Borsa {

	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	Map<String, Attrezzo> nome2attrezzi;
	private int numeroAttrezzi; // numero attrezzi attuale
	private int pesoMax; // della borsa
	private int pesoAttuale;

	/**
	 * Costruisce una borsa con certo peso massimo in kg.
	 * 
	 * @param pesoMax peso massimo borsa in kg.
	 * 
	 */
	public Borsa(int pesoMax) { // costruttore di riferimento
		if (Borsa.valid(pesoMax)) {
			this.pesoMax = pesoMax;
			this.nome2attrezzi = new TreeMap<String, Attrezzo>();
			this.numeroAttrezzi = 0;
		}
	}

	/**
	 * 
	 * Costruttore che istanzia una borsa con peso massimo sostenibile in kg pari a
	 * Borsa.DEFAULT_PESO_MAX_BORSA facendo uso del costruttore di riferimento.
	 * 
	 */
	public Borsa() {
		this(Borsa.DEFAULT_PESO_MAX_BORSA);
	}

	/**
	 * 
	 * Costruttore che istanzia una borsa con peso massimo sostenibile in kg pari a
	 * Borsa.DEFAULT_PESO_MAX_BORSA e con una serie di attrezzi passati come
	 * parametro. Costruttore usato nel testing.
	 * 
	 * @param numeroAttrezzi il numero di attrezzi da mettere nella borsa.
	 * @param attrezzi       gli attrezzi da mettere nella borsa
	 * 
	 */
	public Borsa(int numeroAttrezzi, Attrezzo... attrezzi) {
		if (numeroAttrezzi > 0 && attrezzi != null) {
			this.pesoMax = Borsa.DEFAULT_PESO_MAX_BORSA;
			// this.attrezzi = attrezzi;
			this.numeroAttrezzi = attrezzi.length;
		}
	}

	/**
	 * 
	 * @return restituisce l'insieme di attrezzi attualmente contenuti nella borsa.
	 */
	public Collection<Attrezzo> getAttrezzi() {
		return this.nome2attrezzi.values();
	}

	public Set<String> getNomiAttrezzi() {
		return this.nome2attrezzi.keySet();
	}

	public Map<String, Attrezzo> getNome2attrezzi() {
		return this.nome2attrezzi;
	}

	public void setNome2attrezzi(Map<String, Attrezzo> nome2attrezzi) {
		this.nome2attrezzi = nome2attrezzi;
	}

	/**
	 * 
	 * @return restituisce il numero attuale di attrezzi nella borsa.
	 */
	public int getNumAttrezzi() {
		return this.numeroAttrezzi;
	}

	public void setNumAttrezzi(int numeroAttrezzi) {
		if (numeroAttrezzi >= 0)
			this.numeroAttrezzi = numeroAttrezzi;
	}

	/**
	 * 
	 * @return restituisce il peso massimo sostenibile dalla borsa in kg.
	 */
	public int getPesoMax() {
		return this.pesoMax;
	}

	/**
	 * 
	 * @return restituisce il nuovo peso della borsa in kg.
	 */
	public int getNuovoPeso(Attrezzo attrezzoDaAggiungere) {
		return this.getPeso() + attrezzoDaAggiungere.getPeso();
	}

	public int getPesoAttuale() {
		return this.pesoAttuale;
	}

	public void setPesoAttuale(int pesoAttuale) {
		this.pesoAttuale = pesoAttuale;
	}

	/**
	 * Controlla se il nome dell'attrezzo nel parametro corrisponde ad un attrezzo
	 * nella borsa e lo restituisce.
	 * 
	 * @param nomeAttrezzo id dell'attrezzo.
	 * @return attrezzo cercato in formato stringa, null altrimenti.
	 * 
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		if (nomeAttrezzo == null || nomeAttrezzo.equals(""))
			return null;
		return this.nome2attrezzi.get(nomeAttrezzo);
	}

	/**
	 * Calcola il peso totale (di tutti gli attrezzi) della borsa.
	 * 
	 * @return peso totale (di tutti gli attrezzi) della borsa.
	 */
	public int getPeso() {
		int peso = 0;
		for (Attrezzo attrezzo : this.nome2attrezzi.values())
			peso += attrezzo.getPeso();
		return peso;
	}

	/**
	 * Aggiorna il peso massimo (in kg) che la borsa può sostenere.
	 * 
	 * @param pesoMax nuovo peso massimo della borsa in kg.
	 */
	public void setPesoMax(int pesoMax) {
		if (Borsa.valid(pesoMax) && pesoMax != this.pesoMax)
			this.pesoMax = pesoMax;
	}

	/**
	 * Restituisce una rappresentazione stringa di questa norsa, stampadone la
	 * descrizione, il peso massimo sostenibile in kg e gli attrezzi contenuti.
	 * 
	 * @return la rappresentazione stringa.
	 */
	@Override // overrides toString() di java.lang.Object
	public String toString() {
		StringBuilder output = new StringBuilder("Attrezzi nella borsa ( " + this.getNumAttrezzi() + " ): ");
		if (!this.isEmpty()) {
			output.append(this.getPeso() + "kg / " + this.getPesoMax() + "kg: ( ");
			for (Attrezzo attrezzo : this.nome2attrezzi.values()) {
				output.append(attrezzo.getNome().toString() + ", " + attrezzo.toString() + " ; ");
			}
			output.deleteCharAt(output.length() - 2);
			output.append(")");
		} else
			output.append("Attrezzi non presenti nella Borsa.");
		return output.toString();
	}

	/**
	 * 
	 * @param o l'oggetto da confrontare con la borsa corrente.
	 * 
	 * @return Restituisce true se la borsa corrente e quella passato tramite
	 *         parametro hanno stesse proprietà, false altrimenti.
	 */
	@Override // overrides equals(Object o) di java.lang.Object
	public boolean equals(Object o) {
		if (this == null || o == null || this.getClass() != o.getClass())
			return false;
		Borsa b = (Borsa) (o); // down-casting.
		return this.stessiAttrezzi(b) && this.getNumAttrezzi() == b.getNumAttrezzi()
				&& this.getPesoMax() == b.getPesoMax();
	}

	/**
	 * Controlla se la borsa di riferimento ha gli stessi attrezzi della borsa
	 * passata come parametro.
	 * 
	 * @param b La borsa per il confronto degli attrezzi.
	 * @return true e la borsa di riferimento ha gli stessi attrezzi della borsa
	 *         passata come parametro, false altrimenti.
	 */
	public boolean stessiAttrezzi(Borsa b) {
		if (this.isEmpty() && b.isEmpty())
			return true;
		if (this.getNumAttrezzi() != b.getNumAttrezzi())
			return false;
		return this.nome2attrezzi.equals(b.getNome2attrezzi());
	}

	/**
	 * Calcola se la borsa è vuota oppure no.
	 * 
	 * @return true se la borsa non contiene attrezzi (è vuota), false altrimenti.
	 */
	public boolean isEmpty() {
		return this.getNumAttrezzi() == 0;
	}

	/**
	 * Controlla se un attrezzo è presente nella borsa tramite il nome dello stesso.
	 * 
	 * @param nomeAttrezzo nome dell'attrezzo da cercare.
	 * @return true se l'attrezzo è presente nella borsa, false altrimenti.
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		if (nomeAttrezzo == null || nomeAttrezzo.equals(""))
			return false;
		return this.nome2attrezzi.containsKey(nomeAttrezzo);
	}

	/**
	 * Aggiunge un oggetto attrezzo nella borsa.
	 * 
	 * @param attrezzo da aggiungere nella borsa.
	 * @return true se è stato aggiunto, false altrimenti.
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (attrezzo != null && this.getNuovoPeso(attrezzo) <= this.getPesoMax()) {
			this.nome2attrezzi.put(attrezzo.getNome(), attrezzo);
			this.pesoAttuale += attrezzo.getPeso();
			this.numeroAttrezzi++;
			return true;
		}
		return false;
	}

	/**
	 * Rimuove un attrezzo con nome uguale al parametro dalla borsa.
	 * 
	 * @param nomeAttrezzo.
	 * @return true se l'attrezzo e' stato rimosso dalla borsa, false altrimenti.
	 */
	public boolean removeAttrezzo(String attrezzo) {
		if (attrezzo == null || this.isEmpty() || !this.hasAttrezzo(attrezzo))
			return false;
		Attrezzo attrezzoDaRimuovere = this.getAttrezzo(attrezzo);
		if (this.nome2attrezzi.remove(attrezzoDaRimuovere.getNome(), attrezzoDaRimuovere)) {
			this.numeroAttrezzi--;
			return true;
		}
		return false;
	}

	/**
	 * Validazione costruttore Borsa(int pesoMax).
	 * 
	 * @param pesoMax peso massimo della borsa (in kg).
	 * @return true se il peso massimo della borsa (in kg) è > 0, false altrimenti.
	 */
	public static boolean valid(int pesoMax) {
		return pesoMax > 0;
	}

	SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso() {
		SortedSet<Attrezzo> s = new TreeSet<Attrezzo>(new ComparatoreAttrezziPerPesoPoiNome());
		s.addAll(this.nome2attrezzi.values());
		return s;
	}

	SortedSet<Attrezzo> getContenutoOrdinatoPerNome() {
		SortedSet<Attrezzo> s = new TreeSet<Attrezzo>(new ComparatoreAttrezziPerNome());
		s.addAll(this.nome2attrezzi.values());
		return s;
	}

	List<Attrezzo> getContenutoOrdinatoPerPeso() {
		List<Attrezzo> l = new ArrayList<>();
		l.addAll(this.nome2attrezzi.values());
		Collections.sort(l, new ComparatoreAttrezziPerPesoPoiNome());
		return l;
	}

	Map<Integer, Set<Attrezzo>> getContenutoRaggruppatoPerPeso() {
		Set<Attrezzo> tmp;
		Map<Integer, Set<Attrezzo>> mappa = new HashMap<Integer, Set<Attrezzo>>();
		for (Attrezzo attrezzo : this.nome2attrezzi.values()) {
			tmp = mappa.get(attrezzo.getPeso());
			if (tmp == null)
				tmp = new TreeSet<Attrezzo>(new ComparatoreAttrezziPerNome());
			tmp.add(attrezzo);
			mappa.put(attrezzo.getPeso(), tmp);
		}
		return mappa;
	}
}