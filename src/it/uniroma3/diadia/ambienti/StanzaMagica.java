package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagica extends Stanza {

	private int contatoreAttrezziPosati;
	private int sogliaMagica;

	private static final int SOGLIA_MAGICA_DEFAULT = 1;

	public StanzaMagica(String nome, int soglia) {
		super(nome);
		this.contatoreAttrezziPosati = 0;
		this.sogliaMagica = soglia;
	}

	public StanzaMagica(String nome) {
		this(nome, StanzaMagica.SOGLIA_MAGICA_DEFAULT);
	}

	protected Attrezzo modificaAttrezzo(Attrezzo attrezzo) {
		if (attrezzo == null)
			return null;
		int pesoX2 = 2 * attrezzo.getPeso();
		StringBuilder nomeInvertito = new StringBuilder(attrezzo.getNome()).reverse();
		return new Attrezzo(nomeInvertito.toString(), pesoX2);
	}

	@Override
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (attrezzo == null)
			return false;
		if (this.contatoreAttrezziPosati > this.sogliaMagica)
			attrezzo = this.modificaAttrezzo(attrezzo);
		this.contatoreAttrezziPosati++;
		return super.addAttrezzo(attrezzo);
	}

}
