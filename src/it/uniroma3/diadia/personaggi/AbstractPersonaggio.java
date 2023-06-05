package it.uniroma3.diadia.personaggi;

import java.util.Objects;

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
		risposta.append(this.getNome() + ". ");
		if (!this.haSalutato)
			risposta.append(this.presentazione);
		else
			risposta.append("Ci siamo gia' presentati!");
		this.haSalutato = true;
		return risposta.toString();
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " " + this.getNome() + ", " + this.getPresentazione();
	}

	public String getPresentazione() {
		return this.presentazione;
	}

	public void setPresentazione(String presentazione) {
		this.presentazione = presentazione;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getClass(), this.getNome());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (this == null || obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		AbstractPersonaggio other = (AbstractPersonaggio) obj;
		return Objects.equals(this.getNome(), other.getNome());
	}

	abstract public String agisci(Partita partita);

	abstract public String riceviRegalo(Attrezzo attrezzo, Partita partita);
}
