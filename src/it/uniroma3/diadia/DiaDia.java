package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;
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

public final class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""
			+ "Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n"
			+ "Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"
			+ "I locali sono popolati da strani personaggi, " + "alcuni amici, altri... chissa!\n"
			+ "Ci sono attrezzi che potrebbero servirti nell'impresa:\n"
			+ "puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n"
			+ "o regalarli se pensi che possano ingraziarti qualcuno.\n\n"
			+ "Per conoscere le istruzioni usa il comando 'aiuto'.";
	private Partita partita;
	private IOConsole ioConsole;

	/**
	 * Costruttore classe DiaDia.
	 *
	 * @param io        istanza input-output per gestire l'interazione con l'utente
	 *                  da tastiera e schermo.
	 * @param labirinto istanza del labirinto fisico che compone il gioco DiaDia.
	 * 
	 */
	public DiaDia(IOConsole ioConsole, Labirinto labirinto) {
		this.ioConsole = ioConsole;
		this.partita = new Partita(labirinto);
	}
	/*
	 * public DiaDia(IO IO) { this.io = IO; Labirinto labirinto = new Labirinto();
	 * this.partita = new Partita(labirinto); }
	 */

	/**
	 * Costruttore copia classe DiaDia.
	 *
	 * @param diaDia l'oggetto della classe DiaDia da copiare.
	 * 
	 */
	public DiaDia(DiaDia diaDia) {
		this(diaDia.getIOConsole(), diaDia.getPartita().getLabirinto());
	}

	public IOConsole getIOConsole() {
		return this.ioConsole;
	}

	public void setIoConsole(IOConsole ioConsole) {
		this.ioConsole = ioConsole;
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
	public final void gioca() {
		this.ioConsole.mostraMessaggio(DiaDia.MESSAGGIO_BENVENUTO);
		String istruzione;
		do {
			istruzione = this.ioConsole.leggiRiga();
		} while (!processaIstruzione(istruzione));
	}

	/**
	 * Processa una istruzione dell'utente.
	 *
	 * @return true se l'istruzione e' stata eseguita correttamenrte ed il gioco
	 *         continua, false altrimenti.
	 */
	public boolean processaIstruzione(String istruzione) {
		FabbricaDiComandiFisarmonica factory = new FabbricaDiComandiFisarmonica(this.ioConsole);
		Comando comandoDaEseguire = factory.costruisciComando(istruzione);
		comandoDaEseguire.esegui(this.partita);
		if (this.partita.isVinta()) {
			ioConsole.mostraMessaggio("\nComplimenti hai vinto! Hai trovato la stanza vincente ("
					+ this.partita.getLabirinto().getStanzaVincente().getNome() + ") con "
					+ this.partita.getGiocatore().getCfu() + " CFU.\n");
			this.partita.setFinita();
		} else if (this.partita.isPersa()) {
			ioConsole.mostraMessaggio("\nMi dispiace ma hai esaurito i CFU e dunque hai perso.");
			this.partita.setFinita();
		}
		return this.partita.isFinita();
	}
}