package it.uniroma3.diadia.attrezzi;

import java.util.Comparator;

public class ComparatoreAttrezziPerPesoPoiNome implements Comparator<Attrezzo> {

	@Override
	public int compare(Attrezzo attrezzo1, Attrezzo attrezzo2) {
		if (attrezzo1.getPeso() - attrezzo2.getPeso() == 0)
			return attrezzo1.getNome().compareTo(attrezzo2.getNome());
		return attrezzo1.getPeso() - attrezzo2.getPeso();
	}

}
