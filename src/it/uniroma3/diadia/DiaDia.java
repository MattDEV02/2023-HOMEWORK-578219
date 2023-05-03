package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
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
	public DiaDia(IO IO, Labirinto labirinto) {
		this.io = IO;
		this.partita = new Partita(labirinto);
	}

	public DiaDia(IO IO) {
		this.io = IO;
		Labirinto labirinto = new Labirinto();
		this.partita = new Partita(labirinto);
	}

	/**
	 * Costruttore copia classe DiaDia.
	 *
	 * @param diaDia l'oggetto della classe DiaDia da copiare.
	 * 
	 */
	public DiaDia(DiaDia diaDia) {
		this(diaDia.io, diaDia.getPartita().getLabirinto());
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
	public boolean processaIstruzione(String istruzione) {
		FabbricaDiComandiFisarmonica factory = new FabbricaDiComandiFisarmonica(this.io);
		Comando comandoDaEseguire = factory.costruisciComando(istruzione);
		comandoDaEseguire.esegui(this.partita);
		if (this.partita.isVinta()) {
			io.mostraMessaggio("\nComplimenti hai vinto! Hai trovato la stanza vincente ("
					+ this.partita.getLabirinto().getStanzaVincente().getNome() + ").");
			this.partita.setFinita();
		} else if (this.partita.isPersa()) {
			io.mostraMessaggio("\nMi dispiace ma hai esaurito i CFU e dunque hai perso.");
			this.partita.setFinita();
		}
		return this.partita.isFinita();
	}

	/**
	 * metodo principale del progetto.
	 * 
	 * @param args argomenti da linea di comando forniti.
	 * 
	 */
	public final static void main(String[] args) {
		final IO io = new IOConsole();
		Labirinto labirinto = new LabirintoBuilder().addStanzaIniziale("Atrio").addAttrezzo("osso", 1)
				.addStanzaVincente("Biblioteca").addStanzaMagica("Aula N11", 1).addAttrezzo("piedediporco", 3)
				.addStanzaBloccata("Aula N10", "est", "piedediporco").addAttrezzo("lanterna", 2)
				.addStanzaBuia("Laboratorio", "lanterna").addAdiacenza("Atrio", "Biblioteca", "nord")
				.addAdiacenza("Biblioteca", "Atrio", "sud").addAdiacenza("Atrio", "Aula N11", "est")
				.addAdiacenza("Atrio", "Aula N10", "sud").addAdiacenza("Atrio", "Laboratorio", "ovest")
				.addAdiacenza("Aula N11", "Laboratorio", "est").addAdiacenza("Aula N11", "Atrio", "ovest")
				.addAdiacenza("Aula N10", "Atrio", "nord").addAdiacenza("Aula N10", "Aula N11", "est")
				.addAdiacenza("Aula N10", "Laboratorio", "ovest").addAdiacenza("Laboratorio", "Atrio", "est")
				.addAdiacenza("Laboratorio", "Aula N11", "ovest").getLabirinto();
		new DiaDia(io, labirinto).gioca();
		// N.B. = per i test ho creato una src folder chiamata test
	}
}