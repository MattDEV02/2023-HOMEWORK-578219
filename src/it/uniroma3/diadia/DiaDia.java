package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * 
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il metodo gioca.
 *
 * Questa è la classe principale, crea e istanzia tutte le altre (contiene anche
 * il metodo main).
 *
 *
 * @author Lambertucci Matteo
 * @version 1
 * @see Partita
 * @see Giocatore
 * @see IoConsole
 * 
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""
			+ "Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n"
			+ "Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"
			+ "I locali sono popolati da strani personaggi, " + "alcuni amici, altri... chissa!\n"
			+ "Ci sono attrezzi che potrebbero servirti nell'impresa:\n"
			+ "puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n"
			+ "o regalarli se pensi che possano ingraziarti qualcuno.\n\n"
			+ "Per conoscere le istruzioni usa il comando 'aiuto'.";
	static final private String[] elencoComandi = { "vai <direzione>", "aiuto", "fine", "prendi <nomeAttrezzo>",
			"posa <nomeAttrezzo>", "guarda" };
	private Partita partita;
	private IOConsole IO;

	/**
	 * Costruttore classe DiaDia.
	 *
	 * @param IO istanza input-output per gestire l'interazione con l'utente.
	 * 
	 */
	public DiaDia(IOConsole IO) {
		this.IO = IO;
		this.partita = new Partita();
	}

	/**
	 * Costruttore copia classe DiaDia.
	 *
	 * @param diaDia l'oggetto della classe DiaDia da copiare.
	 * 
	 */
	public DiaDia(DiaDia diaDia) {
		this(diaDia.IO);
	}

	/**
	 * Metodo getter attributo partita classe DiaDia.
	 *
	 * @return la partita del gioco DiaDia.
	 * 
	 */
	public Partita getPartita() {
		return this.partita;
	}

	/**
	 * Metodo setter attributo partita classe DiaDia.
	 *
	 * @param la nuova partita del gioco DiaDia.
	 * 
	 */
	public void setPartita(Partita partita) {
		if (partita != null)
			this.partita = partita;
	}

	/**
	 * Starta il gioco DiaDia.
	 *
	 * 
	 */
	public void gioca() {
		this.IO.mostraMessaggio(DiaDia.MESSAGGIO_BENVENUTO);
		String[] istruzione; // String-array
		do {
			istruzione = this.IO.leggiRiga().split(" ");
		} while (!processaIstruzione(istruzione));

	}

	/**
	 * Processa una istruzione dell'utente.
	 *
	 * @return true se l'istruzione e' stata eseguita correttamenrte ed il gioco
	 *         continua, false altrimenti.
	 */
	private boolean processaIstruzione(String[] istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);
		if (comandoDaEseguire.isSconosciuto()) {
			this.IO.mostraMessaggio(
					"Comando vuoto / nullo / NON valido / sconosciuto, riprova con un altro comando ricordando che i comandi hanno una o due parole.");
			return false;
		}
		if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine();
			return true;
		} else if (comandoDaEseguire.getNome().equals("vai"))
			this.vai(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("prendi"))
			this.prendi(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("posa"))
			this.posa(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("aiuto"))
			this.aiuto();
		else if (comandoDaEseguire.getNome().equals("guarda"))
			this.guarda();
		else
			this.IO.mostraMessaggio("Comando non presente, riprova con un altro comando.");
		if ((this.partita.isPersa())) {
			this.IO.mostraMessaggio("Mi dispiace per te ma hai terminato i cfu ed hai perso la partita.");
			this.fine();
			return true;
		}
		if (this.partita.isVinta()) {
			this.IO.mostraMessaggio("Hai vinto!");
			this.fine();
			return true;
		}
		return false;
	}

	/**
	 * permette al giocatore di guardarsi attorno stampando a schermo il contenuto
	 * dell'attuale stanza e borsa.
	 * 
	 */
	private void guarda() {
		this.IO.mostraMessaggio(this.partita.getStanzaCorrente().getDescrizione());
		this.IO.mostraMessaggio(this.partita.getGiocatore().getBorsa().toString());
		this.IO.mostraMessaggio("CFU correnti: " + this.partita.getGiocatore().getCfu() + ".\n");
	}

	/**
	 * Stampa informazioni di aiuto per il giocatore.
	 * 
	 */
	private void aiuto() {
		int numeroComandi = DiaDia.elencoComandi.length;
		for (int i = 0; i < numeroComandi; i++)
			this.IO.mostraMessaggio(DiaDia.elencoComandi[i] + " ");
	}

	/**
	 * Permette al giocatore di spostarsi in una determinata stanza partendo da data
	 * una direzione. Se c'e' una stanza (data la direzione) il giocatore ci entra e
	 * ne stampa il nome, altrimenti stampa su schermo un determinato messaggio di
	 * errore.
	 * 
	 * @param direzione direzione della stanza nella quale il giocatore desidera
	 *                  andare.
	 * 
	 */
	private void vai(String direzione) {
		if (direzione != null && !direzione.equals("")) {
			Stanza prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
			if (prossimaStanza == null)
				this.IO.mostraMessaggio("Direzione non esistente, riprova con un' altra direzione.\n");
			else {
				this.partita.setStanzaCorrente(prossimaStanza);
				int cfu = this.partita.getGiocatore().getCfu();
				cfu--; // tolgo un CFU
				this.partita.getGiocatore().setCfu(cfu);
			}
		} else
			this.IO.mostraMessaggio("Dove vuoi andare di bello ?");
		this.guarda();
	}

	/**
	 * Raccoglie un attrezzo dalla stanza corrente (rimuovendolo da essa) e lo mette
	 * nella borsa.
	 * 
	 * @param attrezzo da prendere dalla stanza.
	 * 
	 */
	public void prendi(String nomeAttrezzo) {
		Stanza stanzaCorrente = this.partita.getStanzaCorrente();
		if (stanzaCorrente.hasAttrezzo(nomeAttrezzo)) {
			Attrezzo attrezzoDaPrendere = stanzaCorrente.getAttrezzo(nomeAttrezzo);
			if (stanzaCorrente.removeAttrezzo(nomeAttrezzo)) {
				if (this.partita.getGiocatore().getBorsa().addAttrezzo(attrezzoDaPrendere)) {
					IO.mostraMessaggio("L'attrezzo " + attrezzoDaPrendere.getNome() + " e' stato preso dalla stanza "
							+ stanzaCorrente.getNome() + " e messo nello zaino con successo.");
				} else
					IO.mostraMessaggio("La borsa va oltre il limite di peso.");
			} else
				IO.mostraMessaggio("La stanza e' vuota, quindi non puoi rimuovere attrezzi da essa.");
		} else
			IO.mostraMessaggio("Attrezzo " + nomeAttrezzo + " non presente nella stanza.");
	}

	/**
	 * Prende un attrezzo dalla borsa, lo posa nella stanza e lo rimuove dalla
	 * borsa.
	 * 
	 * @param nome dell'attrezzo da posare.
	 *
	 */
	public void posa(String nomeAttrezzo) {
		Borsa borsa = this.partita.getGiocatore().getBorsa();
		if (borsa.hasAttrezzo(nomeAttrezzo)) {
			Stanza stanzaCorrente = this.partita.getStanzaCorrente();
			Attrezzo attrezoDaPosare = borsa.getAttrezzo(nomeAttrezzo);
			if (borsa.removeAttrezzo(nomeAttrezzo)) {
				if (stanzaCorrente.addAttrezzo(attrezoDaPosare)) {
					IO.mostraMessaggio("L'attrezzo " + attrezoDaPosare.getNome() + " e' stato posato nella stanza "
							+ stanzaCorrente.getNome() + " e tolto dallo zaino con successo.");
				} else
					IO.mostraMessaggio("La stanza e piena !");
			} else
				IO.mostraMessaggio("La borsa e' vuota, quindi non puoi rimuovere attrezzi da essa.");
		} else
			IO.mostraMessaggio("Attrezzo " + nomeAttrezzo + " non presente nella borsa.");

	}

	/**
	 * Comando "Fine" partita se si desidera smettere di giocare.
	 * 
	 */
	private void fine() {
		this.guarda();
		this.IO.mostraMessaggio("Partita finita, grazie per aver giocato!");
	}

	/**
	 * metodo principale del progetto.
	 * 
	 * @param args argomenti da linea di comando forniti.
	 * 
	 */
	public final static void main(String[] args) {
		final IOConsole IO = new IOConsole();
		new DiaDia(IO).gioca();
		// N.B. = per i test ho creato una src folder chiamata test
	}
}