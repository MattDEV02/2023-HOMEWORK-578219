package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza {

	private Direzione direzioneBloccata;
	private String nomeAttrezzoSbloccante;

	public StanzaBloccata(String nome, String direzioneBloccata, String nomeAttrezzoSbloccante) {
		super(nome);
		this.direzioneBloccata = Direzione.valueOf(direzioneBloccata);
		this.nomeAttrezzoSbloccante = nomeAttrezzoSbloccante;
	}

	public Direzione getDirezioneBloccata() {
		return this.direzioneBloccata;
	}

	public void setDirezioneBloccata(Direzione direzioneBloccata) {
		this.direzioneBloccata = direzioneBloccata;
	}

	public String getNomeAttrezzoSbloccante() {
		return nomeAttrezzoSbloccante;
	}

	public void setNomeAttrezzoSbloccante(String nomeAttrezzoSbloccante) {
		this.nomeAttrezzoSbloccante = nomeAttrezzoSbloccante;
	}

	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		if (direzione.equals(this.direzioneBloccata.toString()) && !this.hasAttrezzo(this.nomeAttrezzoSbloccante))
			return this;
		return super.getStanzaAdiacente(direzione);
	}

	@Override
	public String getDescrizione() {
		return super.getDescrizione() + " ; stanza bloccata con direzione bloccata " + this.direzioneBloccata
				+ " e nome attrezzo sbloccante " + this.nomeAttrezzoSbloccante + ".";
	}

	/**
	 * 
	 * @param o l'oggetto da confrontare con la stanza bloccata corrente.
	 * 
	 * @return Restituisce true se la stanza bloccata corrente e quella passato
	 *         tramite parametro hanno stesse proprietà, false altrimenti.
	 */
	@Override // overrides equals(Object o) di java.lang.Object
	public boolean equals(Object o) {
		StanzaBloccata s = (StanzaBloccata) (o); // down-casting.
		return super.equals(s) && this.direzioneBloccata.equals(s.direzioneBloccata)
				&& this.nomeAttrezzoSbloccante.equals(s.nomeAttrezzoSbloccante);
	}

	public boolean isBloccata() {
		return this.nomeAttrezzoSbloccante != null && this.direzioneBloccata != null
				&& this.getClass().toString().equals("class it.uniroma3.diadia.ambienti.StanzaBloccata");
	}
}
