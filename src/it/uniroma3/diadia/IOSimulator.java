package it.uniroma3.diadia;

import java.util.ArrayList;
import java.util.List;

public class IOSimulator implements IO {

	private List<String> righeLette;
	private int indiceRigheLette;
	private List<String> messaggiProdotti;
	private int indiceMessaggiProdotti;
	private List<String> messaggiMostrati;
	private int indiceMessaggiMostrati;

	public IOSimulator(List<String> righeDaLeggere) {
		this.righeLette = righeDaLeggere;
		this.indiceRigheLette = 0;
		this.messaggiMostrati = new ArrayList<String>();
		this.indiceMessaggiMostrati = 0;
		this.messaggiProdotti = new ArrayList<String>();
		this.indiceMessaggiProdotti = 0;
	}

	public List<String> getMessaggiProdotti() {
		return this.messaggiProdotti;
	}

	public void setMessaggiProdotti(List<String> messaggiProdotti) {
		this.messaggiProdotti = messaggiProdotti;
	}

	public List<String> getMessaggiMostrati() {
		return this.messaggiMostrati;
	}

	public void setMessaggiMostrati(List<String> messaggiMostrati) {
		this.messaggiMostrati = messaggiMostrati;
	}

	public List<String> getRigheLette() {
		return this.righeLette;
	}

	public void setRigheLette(List<String> righeLette) {
		this.righeLette = righeLette;
	}

	@Override
	public String leggiRiga() {
		if (this.righeLette == null)
			return null;
		String riga = this.righeLette.get(indiceRigheLette);
		this.indiceRigheLette++;
		return riga;
	}

	@Override
	public void mostraMessaggio(String messaggio) {
		this.messaggiProdotti.add(this.indiceMessaggiProdotti, messaggio);
		this.indiceMessaggiProdotti++;
	}

	public String nextMessaggio() {
		String next = this.messaggiProdotti.get(indiceMessaggiMostrati);
		this.indiceMessaggiMostrati++;
		return next;
	}

	public boolean hasNextMessaggio() {
		return this.indiceMessaggiMostrati < this.indiceMessaggiProdotti;
	}

	@Override
	public String toString() {
		return "IOSimulator [righeLette=" + this.getRigheLette() + ", indiceRigheLette=" + this.indiceRigheLette
				+ ", messaggiProdotti=" + this.getMessaggiProdotti() + ", indiceMessaggiProdotti="
				+ this.indiceMessaggiProdotti + ", messaggiMostrati=" + this.getMessaggiMostrati()
				+ ", indiceMessaggiMostrati=" + this.indiceMessaggiMostrati + "]";
	}

}
