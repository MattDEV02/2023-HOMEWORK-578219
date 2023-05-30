package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public abstract class AbstractPersonaggio {

	private String nome;
	private String presentazione;
	private boolean haSalutato;

	public AbstractPersonaggio(String nome, String presentazione) {
		this.nome = nome;
		this.presentazione = presentazione;
		this.haSalutato = false;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean haSalutato() {
		return this.haSalutato;
	}

	public void setHaSalutato() {
		this.haSalutato = true;
	}

	public String saluta() {
		StringBuilder risposta = new StringBuilder("Ciao, io sono ");
		risposta.append(this.getNome() + ".");
		if (!this.haSalutato)
			risposta.append(this.presentazione);
		else
			risposta.append("Ci siamo gia' presentati!");
		this.haSalutato = true;
		return risposta.toString();
	}

	@Override
	public String toString() {
		return this.getNome();
		// TODO aggiunngi ha salutato e forse descrizione
	}

	abstract public String agisci(Partita partita);

	abstract public String riceviRegalo(Attrezzo attrezzo, Partita partita);
}
