package it.uniroma3.diadia;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;

class PartitaTest { // 15 / 15

	@Before
	private Partita partitaAppenaIniziata() {
		return new Partita(); // condizioni iniziali partita appena iniziata definite nel costruttore della
								// classe Partita.
	}

	@Before
	private Partita partitaVinta() {
		Partita partitaVintaOutput = new Partita();
		partitaVintaOutput.getGiocatore().setCfu(15); // ad esempio...
		partitaVintaOutput.setFinita();
		partitaVintaOutput.setStanzaCorrente(partitaVintaOutput.getStanzaVincente());
		return partitaVintaOutput;
	}

	@Before
	private Partita partitaPersa() {
		Partita partitaPersaOutput = new Partita();
		partitaPersaOutput.setFinita();
		partitaPersaOutput.getGiocatore().setCfu(0); // 0 cfu ==> partita persa.
		partitaPersaOutput.setStanzaCorrente(new Stanza("Aula N10")); // per esempio...
		return partitaPersaOutput;
	}

	@Test
	void testPartitaAppenaIniziataIsPersa() {
		assertFalse(partitaAppenaIniziata().isPersa(), "Una partita appena iniziata NON è una è partita persa.");
	}

	@Test
	void testPartitaAppenaIniziataIsVinta() {
		assertFalse(partitaAppenaIniziata().isVinta(), "Una partita appena iniziata NON è una è partita vincente.");
	}

	@Test
	void testPartitaAppenaIniziataIsFinita() {
		assertFalse(partitaAppenaIniziata().isFinita(), "Una partita appena iniziata NON è una è partita finita.");
	}

	@Test
	void testPartitaVintaIsPersa() {
		assertFalse(partitaVinta().isPersa(), "Una partita vinta NON è una partita persa.");
	}

	@Test
	void testPartitaVintaIsVinta() {
		assertTrue(partitaVinta().isVinta(), "Una partita vinta è una partita vinta.");
	}

	@Test
	void testPartitaVintaIsFinita() {
		assertTrue(partitaVinta().isFinita(), "Una partita vinta è una partita finita.");
	}

	@Test
	void testPartitaPersaisPersa() {
		assertTrue(partitaPersa().isPersa(), "Una partita persa è una partita persa.");
	}

	@Test
	void testPartitaPersaIsVinta() {
		assertFalse(partitaPersa().isVinta(), "Una partita persa NON è una partita vinta.");
	}

	@Test
	void testPartitaPersaIsFinita() {
		assertTrue(partitaPersa().isFinita(), "Una partita persa è una partita finita.");
	}

	@Test
	void testPartitaAppenaIniziataStanzaVincente() {
		assertNotEquals("Una partita appena iniziata deve avere come stanza corrente atrio e NON biblioteca.",
				partitaAppenaIniziata().getStanzaCorrente(), new Stanza("Biblioteca"));
	}

	@Test
	void testPartitaAppenaIniziataStanzaCorrente() {
		assertEquals("Una partita appena iniziata deve avere come stanza corrente atrio e non altre stanze.",
				partitaAppenaIniziata().getStanzaCorrente(), new Stanza("Atrio"));
	}

	@Test
	void testPartitaVintaStanzaVincente() {
		assertEquals("Una partita vinta ha come stanza vincente la biblioteca e NON altre stanze.",
				partitaVinta().getStanzaCorrente(), new Stanza("Biblioteca"));
	}

	@Test
	void testPartitaPersaStanzaVincente() {
		assertNotEquals("Una partita persa NON può avere come stanza corrente la biblioteca.",
				partitaPersa().getStanzaCorrente(), new Stanza("Biblioteca"));
	}

	@Test
	void testPartitaPersaStanzaCorrente() {
		assertEquals("Questa particolare partita persa deve avere come stanza corrente Aula N10.",
				partitaPersa().getStanzaCorrente(), new Stanza("Aula N10"));
	}

	@Test
	void testPartitaAppenaIniziataStanzaVincente2() {
		assertEquals("Questa particolare partita persa deve avere come stanza corrente Aula N10.",
				partitaAppenaIniziata().getStanzaVincente(), partitaVinta().getStanzaCorrente());
	}
}
