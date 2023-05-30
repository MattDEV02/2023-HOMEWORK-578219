package it.uniroma3.diadia;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaMagica;

class PartitaTest { // 20 / 20

	private Labirinto labirinto;

	@BeforeEach
	public void setUp() throws Exception {
		this.labirinto = new Labirinto.LabirintoBuilder().addStanzaIniziale("Atrio").addAttrezzo("osso", 1)
				.addStanzaVincente("Biblioteca").addStanzaMagica("Aula N11", 1).addAttrezzo("piedediporco", 3)
				.addStanzaBloccata("Aula N10", "est", "piedediporco").addAttrezzo("lanterna", 2)
				.addStanzaBuia("Laboratorio", "lanterna").addAdiacenza("Atrio", "Biblioteca", "nord")
				.addAdiacenza("Atrio", "Aula N11", "est").addAdiacenza("Atrio", "Aula N10", "sud")
				.addAdiacenza("Atrio", "Laboratorio", "ovest").addAdiacenza("Aula N11", "Laboratorio", "est")
				.addAdiacenza("Aula N11", "Atrio", "ovest").addAdiacenza("Aula N10", "Atrio", "nord")
				.addAdiacenza("Aula N10", "Aula N11", "est").addAdiacenza("Aula N10", "Laboratorio", "ovest")
				.addAdiacenza("Laboratorio", "Atrio", "est").addAdiacenza("Laboratorio", "Aula N11", "ovest")
				.addAdiacenza("Biblioteca", "Atrio", "sud").getLabirinto();
	}

	@Before
	private Partita partitaAppenaIniziata() {
		return new Partita(this.labirinto); // condizioni iniziali partita appena iniziata definite nel costruttore
											// della classe Partita.
	}

	@Before
	private Partita partitaNonAppenaIniziata() {
		Partita partitaNonAppenaIniziataOutput = new Partita(this.labirinto);
		partitaNonAppenaIniziataOutput.getGiocatore().setCfu(17); // ad esempio...
		partitaNonAppenaIniziataOutput.getLabirinto().setStanzaCorrente(new StanzaMagica("Aula N11", 1));
		return partitaNonAppenaIniziataOutput;

	}

	@Before
	private Partita partitaVinta() {
		Partita partitaVintaOutput = new Partita(this.labirinto);
		partitaVintaOutput.getGiocatore().setCfu(15); // ad esempio...
		partitaVintaOutput.setFinita();
		partitaVintaOutput.getLabirinto().setStanzaCorrente(partitaVintaOutput.getLabirinto().getStanzaVincente());
		return partitaVintaOutput;
	}

	@Before
	private Partita partitaPersa() {
		Partita partitaPersaOutput = new Partita(this.labirinto);
		partitaPersaOutput.setFinita();
		partitaPersaOutput.getGiocatore().setCfu(0); // 0 cfu ==> partita persa.
		partitaPersaOutput.getLabirinto().setStanzaCorrente(new Stanza("Aula N10")); // per esempio...
		return partitaPersaOutput;
	}

	@Test
	void testPartitaAppenaIniziataIsPersa() {
		assertFalse(this.partitaAppenaIniziata().isPersa(), "Una partita appena iniziata NON è una è partita persa.");
	}

	@Test
	void testPartitaAppenaIniziataIsVinta() {
		assertFalse(this.partitaAppenaIniziata().isVinta(),
				"Una partita appena iniziata NON è una è partita vincente.");
	}

	@Test
	void testPartitaNonAppenaIniziataIsVinta() {
		assertFalse(this.partitaNonAppenaIniziata().isVinta(),
				"Una partita NON appena iniziata NON è una è partita vincente.");
	}

	@Test
	void testPartitaAppenaIniziataIsFinita() {
		assertFalse(this.partitaAppenaIniziata().isFinita(), "Una partita appena iniziata NON è una è partita finita.");
	}

	@Test
	void testPartitaNonAppenaIniziataIsFinita() {
		assertFalse(this.partitaNonAppenaIniziata().isFinita(),
				"Una partita NON appena iniziata NON è una è partita finita.");
	}

	@Test
	void testPartitaNonAppenaIniziataIsPersa() {
		assertFalse(this.partitaNonAppenaIniziata().isPersa(),
				"Una partita NON appena iniziata NON è una è partita persa.");
	}

	@Test
	void testPartitaVintaIsPersa() {
		assertFalse(this.partitaVinta().isPersa(), "Una partita vinta NON è una partita persa.");
	}

	@Test
	void testPartitaVintaIsVinta() {
		assertTrue(this.partitaVinta().isVinta(), "Una partita vinta è una partita vinta.");
	}

	@Test
	void testPartitaVintaIsFinita() {
		assertTrue(this.partitaVinta().isFinita(), "Una partita vinta è una partita finita.");
	}

	@Test
	void testPartitaPersaisPersa() {
		assertTrue(this.partitaPersa().isPersa(), "Una partita persa è una partita persa.");
	}

	@Test
	void testPartitaPersaIsVinta() {
		assertFalse(this.partitaPersa().isVinta(), "Una partita persa NON è una partita vinta.");
	}

	@Test
	void testPartitaPersaIsFinita() {
		assertTrue(this.partitaPersa().isFinita(), "Una partita persa è una partita finita.");
	}

	@Test
	void testPartitaAppenaIniziataStanzaVincente() {
		assertNotEquals("Una partita appena iniziata deve avere come stanza corrente atrio e NON biblioteca.",
				this.partitaAppenaIniziata().getLabirinto().getStanzaCorrente(), new Stanza("Biblioteca"));
	}

	@Test
	void testPartitaNonAppenaIniziataStanzaVincente() {
		assertNotEquals("Una partita NON appena iniziata deve avere come stanza corrente atrio e NON biblioteca.",
				this.partitaNonAppenaIniziata().getLabirinto().getStanzaCorrente(), new Stanza("Biblioteca"));
	}

	@Test
	void testPartitaAppenaIniziataStanzaCorrente() {
		assertEquals("Una partita appena iniziata deve avere come stanza corrente atrio e non altre stanze.",
				this.partitaAppenaIniziata().getLabirinto().getStanzaCorrente(), new Stanza("Atrio"));
	}

	@Test
	void testPartitaNonAppenaIniziataStanzaCorrente() {
		assertEquals("Una partita NON appena iniziata deve avere come stanza corrente Aula N11 e non altre stanze.",
				this.partitaAppenaIniziata().getLabirinto().getStanzaCorrente().getStanzaAdiacente("est"),
				new StanzaMagica("Aula N11", 1));
	}

	@Test
	void testPartitaVintaStanzaVincente() {
		assertEquals("Una partita vinta ha come stanza vincente la biblioteca e NON altre stanze.",
				this.partitaVinta().getLabirinto().getStanzaCorrente(), new Stanza("Biblioteca"));
	}

	@Test
	void testPartitaPersaStanzaVincente() {
		assertNotEquals("Una partita persa NON può avere come stanza corrente la biblioteca.",
				this.partitaPersa().getLabirinto().getStanzaCorrente(), new Stanza("Biblioteca"));
	}

	@Test
	void testPartitaPersaStanzaCorrente() {
		assertEquals("Questa particolare partita persa deve avere come stanza corrente Aula N10.",
				this.partitaPersa().getLabirinto().getStanzaCorrente(), new Stanza("Aula N10"));
	}

	@Test
	void testPartitaAppenaIniziataStanzaVincente2() {
		assertEquals("Questa particolare partita persa deve avere come stanza corrente Aula N10.",
				this.partitaAppenaIniziata().getLabirinto().getStanzaVincente(),
				partitaVinta().getLabirinto().getStanzaCorrente());
	}
}
