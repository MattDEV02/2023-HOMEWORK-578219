package it.uniroma3.diadia.attrezzi;

import java.util.Comparator;

public class ComparatoreAttrezziPerNome implements Comparator<Attrezzo> {

	@Override
	public int compare(Attrezzo attrezzo1, Attrezzo attrezzo2) {
		return attrezzo1.getNome().compareTo(attrezzo2.getNome());
	}
}
