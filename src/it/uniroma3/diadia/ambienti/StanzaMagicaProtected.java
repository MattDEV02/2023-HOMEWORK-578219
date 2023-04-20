package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagicaProtected extends StanzaProtected {

	private int contatoreAttrezziPosati;
	private int sogliaMagica;

	private static final int SOGLIA_MAGICA_DEFAULT = 1;

	public StanzaMagicaProtected(String nome, int soglia) {
		super(nome);
		this.contatoreAttrezziPosati = 0;
		this.sogliaMagica = soglia;
	}

	public StanzaMagicaProtected(String nome) {
		this(nome, SOGLIA_MAGICA_DEFAULT);
	}

	protected Attrezzo modificaAttrezzo(Attrezzo attrezzo) {
		if (attrezzo == null)
			return null;
		int pesoX2 = attrezzo.getPeso() * 2;
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