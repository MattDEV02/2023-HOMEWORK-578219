package it.uniroma3.diadia;

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

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""
			+ "Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n"
			+ "Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"
			+ "I locali sono popolati da strani personaggi, " + "alcuni amici, altri... chissa!\n"
			+ "Ci sono attrezzi che potrebbero servirti nell'impresa:\n"
			+ "puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n"
			+ "o regalarli se pensi che possano ingraziarti qualcuno.\n\n"
			+ "Per conoscere le istruzioni usa il comando 'aiuto'.";
	private Partita partita;
	private IO io;

	/**
	 * Costruttore classe DiaDia.
	 *
	 * @param io istanza input-output per gestire l'interazione con l'utente da
	 *           tastiera e schermo.
	 * 
	 */
	public DiaDia(IO IO) {
		this.io = IO;
		this.partita = new Partita();
	}

	/**
	 * Costruttore copia classe DiaDia.
	 *
	 * @param diaDia l'oggetto della classe DiaDia da copiare.
	 * 
	 */
	public DiaDia(DiaDia diaDia) {
		this(diaDia.io);
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
		this.io.mostraMessaggio(DiaDia.MESSAGGIO_BENVENUTO);
		String istruzione;
		do {
			istruzione = this.io.leggiRiga();
		} while (!processaIstruzione(istruzione));
	}

	/**
	 * Processa una istruzione dell'utente.
	 *
	 * @return true se l'istruzione e' stata eseguita correttamenrte ed il gioco
	 *         continua, false altrimenti.
	 */
	private boolean processaIstruzione(String istruzione) {
		FabbricaDiComandiFisarmonica factory = new FabbricaDiComandiFisarmonica(this.io);
		Comando comandoDaEseguire = factory.costruisciComando(istruzione);
		comandoDaEseguire.esegui(this.partita);
		if (this.partita.isVinta())
			io.mostraMessaggio("\nComplimenti hai vinto!");
		else if (this.partita.isPersa())
			io.mostraMessaggio("\nMi dispiace ma hai esaurito i CFU e dunque hai perso.");
		return this.partita.isFinita();
	}

	/**
	 * permette al giocatore di guardarsi attorno stampando a schermo il contenuto
	 * dell'attuale stanza e borsa.
	 * 
	 */

	/**
	 * Stampa informazioni di aiuto per il giocatore.
	 * 
	 */

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

	/**
	 * Raccoglie un attrezzo dalla stanza corrente (rimuovendolo da essa) e lo mette
	 * nella borsa.
	 * 
	 * @param attrezzo da prendere dalla stanza.
	 * 
	 */

	/**
	 * Prende un attrezzo dalla borsa, lo posa nella stanza e lo rimuove dalla
	 * borsa.
	 * 
	 * @param nome dell'attrezzo da posare.
	 *
	 */

	/**
	 * Comando "Fine" partita se si desidera smettere di giocare.
	 * 
	 */

	/**
	 * metodo principale del progetto.
	 * 
	 * @param args argomenti da linea di comando forniti.
	 * 
	 */
	public final static void main(String[] args) {
		final IO io = new IOConsole();
		new DiaDia(io).gioca();
		// N.B. = per i test ho creato una src folder chiamata test
	}
}