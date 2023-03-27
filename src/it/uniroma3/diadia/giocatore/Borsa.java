package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.attrezzi.Attrezzo;

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

	public final static int DEFAULT_PESO_MAX_BORSA = 10; // in kg (l'ho impostato a 60 kg per facilitarmi la vita nei
	public final static int DEFAULT_NUMERO_MAX_ATTREZZI_BORSA = 10; // test)
	private Attrezzo[] attrezzi; // array di attrezzi presenti nella borsa.
	private int numeroAttrezzi; // numero attrezzi attuale
	private int pesoMax; // della borsa

	/**
	 * Costruisce una borsa con certo peso massimo in kg.
	 * 
	 * @param pesoMax peso massimo borsa in kg.
	 * 
	 */
	public Borsa(int pesoMax) { // costruttore di riferimento
		if (Borsa.valid(pesoMax)) {
			this.pesoMax = pesoMax;
			this.attrezzi = new Attrezzo[Borsa.DEFAULT_NUMERO_MAX_ATTREZZI_BORSA]; // speriamo che bastino...
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
			this.attrezzi = attrezzi;
			this.numeroAttrezzi = attrezzi.length;
		}
	}

	/**
	 * 
	 * @return restituisce l'arrau di attrezzi attualmente contenuti nella borsa.
	 */
	public Attrezzo[] getAttrezzi() {
		return this.attrezzi;
	}

	/**
	 * 
	 * @return restituisce il numero attuale di attrezzi nella borsa.
	 */
	public int getNumAttrezzi() {
		return this.numeroAttrezzi;
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

	/**
	 * Controlla se il nome dell'attrezzo nel parametro corrisponde ad un attrezzo
	 * nella borsa e lo restituisce.
	 * 
	 * @param nomeAttrezzo id dell'attrezzo.
	 * @return attrezzo cercato in formato stringa, null altrimenti.
	 * 
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		for (int i = 0; i < this.numeroAttrezzi; i++) {
			if (this.attrezzi[i] != null && this.attrezzi[i].getNome().equals(nomeAttrezzo))
				return attrezzi[i];
		}
		return null;
	}

	/**
	 * Restituisce un attrezzo della collezione di attrezzi presenti nella stanza
	 * partendo dall'indice di riferimento.
	 * 
	 * @return l'attrezzo con indice pari ad indice nella collezione di attrezzi
	 *         della stanza, null se l'indice esce dai limiti imposti.
	 */
	public Attrezzo getAttrezzo(int indice) {
		if (indice >= 0 && indice < this.attrezzi.length)
			return this.attrezzi[indice];
		return null;
	}

	/**
	 * Calcola il peso totale (di tutti gli attrezzi) della borsa.
	 * 
	 * @return peso totale (di tutti gli attrezzi) della borsa.
	 */
	public int getPeso() {
		int peso = 0;
		for (int i = 0; i < this.numeroAttrezzi; i++)
			peso += this.attrezzi[i].getPeso();
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
			output.append(this.getPeso() + "kg / " + this.getPesoMax() + "kg ; ");
			for (int i = 0; i < this.numeroAttrezzi; i++)
				output.append(this.attrezzi[i].toString() + " ");
		} else
			output.append("Attrezzi non presenti.");
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
		Borsa b = (Borsa) (o); // down-casting.
		return this.stessiAttrezzi(b) && this.getNumAttrezzi() == b.getNumAttrezzi()
				&& this.getPesoMax() == b.getPesoMax();
	}

	/**
	 * Controlla se la borsa di riferimento ha gli stessi attrezzi della borsa
	 * passata come parametro.
	 * 
	 * 
	 * @param b La borsa per il confronto degli attrezzi.
	 * @return true e la borsa di riferimento ha gli stessi attrezzi della borsa
	 *         passata come parametro, false altrimenti.
	 */
	public boolean stessiAttrezzi(Borsa b) {
		for (int i = 0; i < this.numeroAttrezzi; i++) {
			if (!this.attrezzi[i].equals(b.getAttrezzo(i)))
				return false;
		}
		return true;
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
		for (int i = 0; i < this.numeroAttrezzi; i++) {
			if (this.attrezzi[i] != null && this.attrezzi[i].getNome().equals(nomeAttrezzo))
				return true;
		}
		return false;
	}

	/**
	 * Aggiunge un oggetto attrezzo nella borsa.
	 * 
	 * @param attrezzo da aggiungere nella borsa.
	 * @return true se è stato aggiunto, false altrimenti.
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (attrezzo != null && this.getNuovoPeso(attrezzo) <= this.getPesoMax()
				&& this.getNumAttrezzi() < Borsa.DEFAULT_NUMERO_MAX_ATTREZZI_BORSA) {
			this.attrezzi[this.getNumAttrezzi()] = attrezzo;
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
	 * Validazione costruttore Borsa(int pesoMax).
	 * 
	 * @param pesoMax peso massimo della borsa (in kg).
	 * @return true se il peso massimo della borsa (in kg) è > 0, false altrimenti.
	 */
	public static boolean valid(int pesoMax) {
		return pesoMax > 0;
	}
}