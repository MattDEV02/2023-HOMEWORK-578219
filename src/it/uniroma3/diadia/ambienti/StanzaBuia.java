package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza {
	private String nomeAttrezzoRivelatore;

	public StanzaBuia(String nome, String nomeAttrezzoRivelatore) {
		super(nome);
		this.nomeAttrezzoRivelatore = nomeAttrezzoRivelatore;
	}

	@Override
	public String getDescrizione() {
		if (this.hasAttrezzo(this.nomeAttrezzoRivelatore))
			return super.getDescrizione() + " ; stanza buia con nome attrezzo rivelatore " + this.nomeAttrezzoRivelatore
					+ ".";
		return "Qui c'e un buio pesto.";
	}

	/**
	 * 
	 * @param o l'oggetto da confrontare con la stanza buia corrente.
	 * 
	 * @return Restituisce true se la stanza buia corrente e quella passato tramite
	 *         parametro hanno stesse proprietà, false altrimenti.
	 */
	@Override // overrides equals(Object o) di java.lang.Object
	public boolean equals(Object o) {
		StanzaBuia s = (StanzaBuia) (o); // down-casting.
		return super.equals(s) && this.nomeAttrezzoRivelatore.equals(s.nomeAttrezzoRivelatore);
	}
}
