package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Mago extends AbstractPersonaggio {

	private static final String MESSAGGIO_DONO = "Sei un vero simpaticone, "
			+ "con una mia magica azione, troverai un nuovo oggetto " + "per il tuo borsone!";
	private static final String MESSAGGIO_SCUSE = "Mi spiace, ma non ho piu' nulla...";

	private Attrezzo attrezzo;

	public Mago(String nome, String presentazione, Attrezzo attrezzo) {
		super(nome, presentazione);
		this.attrezzo = attrezzo;
	}

	@Override
	public String agisci(Partita partita) {
		String msg = null;
		if (this.attrezzo != null) {
			partita.getLabirinto().getStanzaCorrente().addAttrezzo(this.attrezzo);
			this.attrezzo = null;
			msg = Mago.MESSAGGIO_DONO;
		} else
			msg = Mago.MESSAGGIO_SCUSE;
		return msg;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		StringBuilder risposta = new StringBuilder("Grazie per avermi regalato ");
		risposta.append(attrezzo.getNome() + ",");
		risposta.append(" Lo modificherò e lo lascerò qui nella stanza!");
		Attrezzo attrezzoModificato = new Attrezzo(attrezzo.getNome(), attrezzo.getPeso() / 2);
		partita.getLabirinto().getStanzaCorrente().addAttrezzo(attrezzoModificato);
		return risposta.toString();
	}
}