package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class GiocatoreTest { // 12 / 12

	@Before
	private Giocatore giocatore(int cfu, Borsa borsa) {
		Giocatore giocatoreOutput = new Giocatore();
		giocatoreOutput.setCfu(cfu);
		giocatoreOutput.setBorsa(borsa);
		return giocatoreOutput;
	}

	@Before
	private Giocatore giocatorePartitaAppenaIniziata() {
		return giocatore(20, new Borsa());
	}

	@Before
	private Giocatore giocatorePartitaNonIniziale() {
		return giocatore(15, new Borsa(2, new Attrezzo("osso", 1), new Attrezzo("lanterna", 3)));// ad esempio...
	}

	@Before
	private Giocatore giocatorePartitaPersa() {
		return giocatore(0, new Borsa(2, new Attrezzo("osso", 1), new Attrezzo("lanterna", 3)));// ad esempio...
	}

	@Before
	private Giocatore giocatorePartitaVinta() {
		return giocatore(15, new Borsa(2, new Attrezzo("osso", 1))); // ad esempio...
	}

	@Test
	void testGiocatoreCFUPartitaIniziale() {
		assertEquals("I CFU del giocatore che ha appena iniziato la partita devono essere 20.", 20,
				giocatorePartitaAppenaIniziata().getCfu());
	}

	@Test
	void testGiocatoreCFUPartitaNoNIniziale() {
		assertTrue(giocatorePartitaNonIniziale().getCfu() < 20,
				"I CFU del giocatore partita non iniziale devono essere minori di 20.");
	}

	@Test
	void testGiocatoreCFUPartitaVinta() {
		assertTrue(giocatorePartitaVinta().getCfu() > 0,
				"I CFU del giocatore che ha appena vinto la partita devono essere maggiori di 0.");
	}

	@Test
	void testGiocatoreCFUPartitaPersa() {
		assertEquals("I CFU del giocatore che ha appena perso la partita devono essere 0.", 0,
				giocatorePartitaPersa().getCfu());
	}

	@Test
	void testGiocatoreBorsaPartitaIniziale() {
		assertEquals("La borsa iniziale del giocatore partita NON iniziale deve essere vuota.", new Borsa(),
				giocatorePartitaAppenaIniziata().getBorsa());
	}

	@Test
	void testGiocatoreBorsaPartitaNonIniziale() {
		assertTrue(giocatorePartitaNonIniziale().getBorsa().getNumAttrezzi() > 0,
				"In questo test-case abbiamo stabilito che la borsa del giocatore partita NON iniziale è NON vuota");
	}

	@Test
	void testGiocatoreBorsaPartitaVinta() {
		assertTrue(giocatorePartitaVinta().getBorsa().getNumAttrezzi() > 0,
				"In questo test-case abbiamo stabilito che la borsa del giocatore partita vinta è NON vuota");
	}

	@Test
	void testGiocatoreBorsaPartitaPersa() {
		assertTrue(giocatorePartitaPersa().getBorsa().getNumAttrezzi() > 0,
				"In questo test-case abbiamo stabilito che la borsa del giocatore partita NON iniziale è NON vuota");
	}

	@Test
	void testGiocatoreBorsaInizialeNotNull() {
		assertNotNull("La borsa del giocatore che ha appena inziato la partita NON può essere nulla (valore null).",
				giocatorePartitaAppenaIniziata().getBorsa());
	}

	@Test
	void testGiocatoreBorsaPartitaNonInizialeNotNull() {
		assertNotNull("La borsa del giocatore partita NON iniziale NON può essere nulla (valore null).",
				giocatorePartitaNonIniziale().getBorsa());
	}

	@Test
	void testGiocatoreBorsaPartitaVintaNotNull() {
		assertNotNull("La borsa del giocatore partita vinta NON può essere nulla (valore null).",
				giocatorePartitaVinta().getBorsa());
	}

	@Test
	void testGiocatoreBorsaPartitaPersaNotNull() {
		assertNotNull("La borsa del giocatore che ha appena perso la partita NON può essere nulla (valore null).",
				giocatorePartitaPersa().getBorsa());
	}
}
