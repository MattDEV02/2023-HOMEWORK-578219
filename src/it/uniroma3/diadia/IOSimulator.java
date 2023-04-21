package it.uniroma3.diadia;

public class IOSimulator implements IO {

	private String[] righeLette;
	private String[] messaggiProdotti;
	private String[] righeMostrate;
	private int indiceRigheLette;
	private int indiceMessaggiProdotti;
	private int indiceMessaggiMostrati;

	public IOSimulator(String[] righeDaLeggere) {
		this.righeLette = righeDaLeggere;
		this.indiceRigheLette = 0;
		this.indiceMessaggiMostrati = 0;
		this.messaggiProdotti = new String[42 * 23];
	}

	public String[] getRigheLette() {
		return this.righeLette;
	}

	public void setRigheLette(String[] righeLette) {
		this.righeLette = righeLette;
	}

	public String[] getMessaggiProdotti() {
		return this.messaggiProdotti;
	}

	public void setMessaggiProdotti(String[] messaggiProdotti) {
		this.messaggiProdotti = messaggiProdotti;
	}

	public String[] getRigheMostrate() {
		return this.righeMostrate;
	}

	public void setRigheMostrate(String[] righeMostrate) {
		this.righeMostrate = righeMostrate;
	}

	@Override
	public String leggiRiga() {
		String riga = this.righeLette[this.indiceRigheLette];
		this.indiceRigheLette++;
		return riga;
	}

	@Override
	public void mostraMessaggio(String messaggio) {
		this.messaggiProdotti[this.indiceMessaggiProdotti] = messaggio;
		this.indiceMessaggiProdotti++;
	}

	public String nextMessaggio() {
		String next = this.messaggiProdotti[this.indiceMessaggiMostrati];
		this.indiceMessaggiMostrati++;
		return next;
	}

	public boolean hasNextMessaggio() {
		return this.indiceMessaggiMostrati < this.indiceMessaggiProdotti;
	}

}
