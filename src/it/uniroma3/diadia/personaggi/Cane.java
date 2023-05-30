package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Giocatore;

public class Cane extends AbstractPersonaggio {

	private final static String MESSAGGIO_MORSO = "Ti ho morso! -1 CFU";
	private final static String CIBO_PREFERITO_DEFAULT = "carne";

	private String nomeCiboPreferito;
	private Attrezzo attrezzo;

	public Cane(String nome, String presentazione, String nomeCiboPreferito, Attrezzo attrezzo) {
		super(nome, presentazione);
		this.nomeCiboPreferito = nomeCiboPreferito;
		this.attrezzo = attrezzo;
	}

	public Cane(String nome, String presentazione) {
		super(nome, presentazione);
		this.nomeCiboPreferito = Cane.CIBO_PREFERITO_DEFAULT;
		this.attrezzo = null;
	}

	@Override
	public String agisci(Partita partita) { // morso
		Giocatore giocatore = partita.getGiocatore();
		giocatore.setCfu(giocatore.getCfu() - 1);
		return Cane.MESSAGGIO_MORSO;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		StringBuilder risposta = new StringBuilder("Bau grazie per avermi regalato ");
		if (attrezzo.getNome().equals(this.nomeCiboPreferito)) {
			risposta.append("il mio cibo preferito.");
			partita.getLabirinto().getStanzaCorrente().addAttrezzo(this.attrezzo);
		} else {
			this.agisci(partita);
		}
		return risposta.toString();
	}

}
