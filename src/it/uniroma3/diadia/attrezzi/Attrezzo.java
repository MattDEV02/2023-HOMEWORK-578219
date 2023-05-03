package it.uniroma3.diadia.attrezzi;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Borsa;

/**
 * 
 * Una semplice classe che modella un attrezzo. Gli attrezzi possono trovarsi
 * all'interno delle stanze del labirinto. Ogni attrezzo ha un nome
 * (identificativo) ed un peso in kg.
 *
 *
 * @author Lambertucci Matteo
 * @see Stanza
 * @see Borsa
 * @version 1
 * 
 */
public class Attrezzo implements Comparable<Attrezzo> { // extends Object

	private String nome; // nome che identifica l'attrezzo.
	private int peso; // il peso dell'attrezzo.

	/**
	 * Crea un attrezzo
	 * 
	 * @param nome il nome che identifica l'attrezzo.
	 * @param peso il peso dell'attrezzo.
	 */
	public Attrezzo(String nome, int peso) { // costruttore di riferimento
		if (Attrezzo.valid(nome, peso)) {
			this.peso = peso;
			this.nome = nome;
		}
	}

	/**
	 * Crea un attrezzo copiandolo dal parametro.
	 * 
	 * @param a Attrezzo da copiare.
	 */
	public Attrezzo(Attrezzo a) { // copy-constructor
		this(a.nome, a.peso); // copio a in un altro attrezzo.
	}

	/**
	 * Restituisce il nome identificatore dell'attrezzo.
	 * 
	 * @return il nome identificatore dell'attrezzo.
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Restituisce il peso dell'attrezzo in kg.
	 * 
	 * @return il peso dell'attrezzo in kg.
	 */
	public int getPeso() {
		return this.peso;
	}

	/**
	 * Aggiorna il nome dell'attrezzo.
	 * 
	 * @param nome il nuovo nome identificatore dell'attrezzo.
	 */
	public void setNome(String nome) {
		if (nome != null && !nome.equals("") && nome.length() > 0)
			this.nome = nome;
	}

	/**
	 * Aggiorna il nome dell'attrezzo.
	 * 
	 * @param peso il nuovo peso dell'attrezzo in kg.
	 */
	public void setPeso(int peso) {
		if (peso > 0)
			this.peso = peso;
	}

	/**
	 * Restituisce una rappresentazione stringa di questo attrezzo.
	 * 
	 * @return la rappresentazione stringa dell'attrezzo.
	 */
	@Override // overrides toString() di java.lang.Object
	public String toString() {
		return this.getNome() + ":" + this.getPeso();
	}

	/**
	 * 
	 * @param o l'oggetto da confrontare con l'attrezzo corrente.
	 * 
	 * @return Restituisce true se l'attrezzo corrente e quello passato tramite
	 *         parametro hanno stesso nome e peso, false altrimenti.
	 */
	@Override // overrides equals(Object o) di java.lang.Object
	public boolean equals(Object o) {
		if (this == null || o == null || this.getClass() != o.getClass())
			return false;
		Attrezzo a = (Attrezzo) (o); // down-casting
		return this.getNome().equals(a.getNome()) && this.getPeso() == a.getPeso();
	}

	@Override
	public int hashCode() {
		Integer pesoInteger = this.getPeso();
		return this.getClass().hashCode() + this.getNome().hashCode() + pesoInteger.hashCode();
	}

	@Override
	public int compareTo(Attrezzo attrezzo) {
		if (attrezzo.getNome().equals(this.getNome()))
			return this.getPeso() - attrezzo.getPeso();
		return attrezzo.getNome().compareTo(this.getNome());
	}

	/**
	 * @param nome il nome che identifica l'attrezzo
	 * @param peso il peso dell'attrezzo in kg.
	 * @return Restituisce true la stringa nome ha lunghezza maggiore di 0 e se
	 *         l'intero peso ha valore maggiore di 0 , false altrimenti.
	 */
	public static boolean valid(String nomeAttrezzo, int pesoAttrezzo) {
		return nomeAttrezzo != null && nomeAttrezzo.length() > 0 && pesoAttrezzo > 0;
	}
}