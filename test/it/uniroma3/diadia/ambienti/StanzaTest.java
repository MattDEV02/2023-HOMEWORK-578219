package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaTest { // 25 / 25

	private Stanza bar;
	private Stanza mensa;

	@Before
	private Stanza stanza(Attrezzo... attrezzi) {
		final int numeroAttrezzi = attrezzi.length;
		Stanza stanzaOutput = new Stanza("Aula N-x");
		for (int i = 0; i < numeroAttrezzi; i++) {
			stanzaOutput.addAttrezzo(attrezzi[i]);
		}
		return stanzaOutput;
	}

	@Before
	private Stanza stanzaVuota() {
		return stanza();
	}

	@Before
	private Stanza stanzaSingleton(Attrezzo attrezzo) {
		return stanza(attrezzo);
	}

	@Before
	private Stanza stanzaPiena() {
		return stanza(new Attrezzo("osso", 1), new Attrezzo("martello", 2), new Attrezzo("chiodo", 1),
				new Attrezzo("trapano", 3), new Attrezzo("spatola", 1), new Attrezzo("cofana", 3),
				new Attrezzo("spada", 3), new Attrezzo("scudo", 1), new Attrezzo("pala", 4), new Attrezzo("metro", 2));
	}

	@BeforeEach // Denotes that the annotated method should be executed before each @Test
	void setUp() { // collega le stanze per testare i metodi impostaStanzaAdiacente e
					// getStanzaAdiacente
		this.bar = stanzaSingleton(new Attrezzo("tazzina", 1));
		this.mensa = stanzaSingleton(new Attrezzo("piatto", 2));
		this.bar.impostaStanzaAdiacente("nord", this.mensa);
		this.mensa.impostaStanzaAdiacente("sud", this.bar);
	}

	@Test
	void testStanzaHasAttrezzoVuotaAttrezzi() {
		assertFalse(stanzaVuota().hasAttrezzo("osso"), "La stanza vuota NON può avere attrezzi.");
	}

	@Test
	void testStanzaHasAttrezzoSingletonAttrezzi() {
		assertTrue(stanzaSingleton(new Attrezzo("osso", 1)).hasAttrezzo("osso"),
				"La stanza singleton deve avere l'attrezzo osso.");
	}

	@Test
	void testStanzaHasAttrezzoSingletonAttrezziNo() {
		assertFalse(stanzaSingleton(new Attrezzo("osso", 1)).hasAttrezzo("chiodo"),
				"La stanza singleton NON deve avere l'attrezzo lanterna.");
	}

	@Test
	void testHasAttrezzoNullStanza() {
		assertFalse(stanza(new Attrezzo("osso", 1), new Attrezzo("martello", 2), new Attrezzo("chiodo", 1))
				.hasAttrezzo(null), "La stanza NON può avere l'attrezzo nullo con valore null.");
	}

	@Test
	void testStanzaHasAttrezzoPienaAttrezzi() {
		assertTrue(stanzaPiena().hasAttrezzo("pala"), "La stanza piena deve avere l'attrezzo pala.");
	}

	@Test
	void testStanzaHasAttrezzoPienaAttrezziNo() { // piena ==> 10 attrezzi
		assertFalse(stanzaPiena().hasAttrezzo("busta"), "La stanza piena NON deve avere l'attrezzo busta.");
	}

	@Test
	void testAddAttrezzoStanzaVuotaAttrezzi() {
		assertTrue(stanza().addAttrezzo(new Attrezzo("osso", 1)),
				"La stanza vuota deve consentire l'aggiunta dell'attrezzo osso.");
	}

	@Test
	void testAddAttrezzoStanzaSingletonAttrezzi() {
		assertTrue(stanzaSingleton(new Attrezzo("osso", 1)).addAttrezzo(new Attrezzo("lanterna", 3)),
				"La stanza singleton deve consentire l'aggiunta dell'attrezzo lanterna.");
	}

	@Test
	void testAddAttrezzoNullStanza() {
		assertFalse(stanza(new Attrezzo("osso", 1), new Attrezzo("martello", 2), new Attrezzo("chiodo", 1))
				.addAttrezzo(null), "NON si può aggiungere l'attrezzo null nella stanza.");
	}

	@Test
	void testAddAttrezzoCopioneStanza() {
		assertTrue(stanza(new Attrezzo("osso", 1), new Attrezzo("osso", 1), new Attrezzo("osso", 1)).addAttrezzo(
				new Attrezzo("osso", 1)), "La stanza deve poter permettere l'aggiunta di attrezzi copioni.");
	}

	@Test
	void testRemoveCopioneStanza() {
		assertTrue(stanza(new Attrezzo("osso", 1), new Attrezzo("osso", 1), new Attrezzo("osso", 1))
				.removeAttrezzo("osso"), "La stanza deve poter permettere la rimozione di attrezzi copioni.");
	}

	@Test
	void testAddAttrezzoStanzaPienaAttrezzi() { // piena ==> 10 attrezzi
		assertFalse(stanzaPiena().addAttrezzo(new Attrezzo("lanterna", 3)),
				"NON si può aggiungere un attrezzo nella stanza piena.");
	}

	@Test
	void testRemoveAttrezzoStanzaVuotaAttrezzi() {
		assertFalse(stanza().removeAttrezzo("osso"), "NON si può rimuovere un attrezzo dalla stanza vuota.");
	}

	@Test
	void testRemoveAttrezzoStanzaSingletonAttrezzi() {
		assertTrue(stanzaSingleton(new Attrezzo("osso", 1)).removeAttrezzo("osso"),
				"La stanza singleton deve poter permettere la rimozione dell'attrezzo osso.");
	}

	@Test
	void testRemoveAttrezzoStanzaSingletonAttrezziNo() {
		assertFalse(stanzaSingleton(new Attrezzo("lanterna", 3)).removeAttrezzo("osso"),
				"La stanza singleton NON deve poter permettere la rimozione dell'attrezzo osso.");
	}

	@Test
	void testRemoveAttrezzoNullStanza() {
		assertFalse(stanza(new Attrezzo("osso", 1), new Attrezzo("martello", 2), new Attrezzo("chiodo", 1))
				.removeAttrezzo(null), "NON è possibile rimuovere l'attrezzo nullo con il valore null dalla stanza.");
	}

	@Test
	void testRemoveAttrezzoStanzaPienaAttrezzi() {
		assertTrue(stanzaPiena().removeAttrezzo("metro"),
				"La stanza piena deve poter permettere la rimozione dell'attrezzo metro.");
	}

	@Test
	void testRemoveAttrezzoStanzaPienaAttrezziNo() {
		assertFalse(stanzaPiena().removeAttrezzo("busta"),
				"La stanza piena NON deve poter permettere la rimozione dell'attrezzo busta.");
	}

	@Test
	void testIsEmptyStanzaVuota() {
		assertTrue(stanza().isEmpty(), "La stanza vuota NON contiene attrezzi !");
	}

	@Test
	void testIsEmptyStanzaSingleton() {
		assertFalse(stanzaSingleton(new Attrezzo("osso", 1)).isEmpty(), "La stanza singleton contiene un attrezzo !");
	}

	@Test
	void testIsEmptyStanzaPiena() {
		assertFalse(stanzaPiena().isEmpty(), "La stanza piena contiene molti attrezzi, non è vuota !");
	}

	@Test
	void testGetStanzaBarNord() {
		assertEquals("A nord della stanza bar ci deve essere la stanza mensa !", this.mensa,
				this.bar.getStanzaAdiacente("nord"));
	}

	@Test
	void testGetStanzaBarSud() {
		assertNull("A sud della stanza bar NON ci deve essere nulla (valore null) !",
				this.bar.getStanzaAdiacente("sud"));
	}

	@Test
	void testGetStanzaMensaNord() {
		assertNull("A nord della stanza mensa NON ci deve essere nulla (valore null) !",
				this.mensa.getStanzaAdiacente("nord"));
	}

	@Test
	void testGetStanzaMensaSud() {
		assertEquals("A sud della stanza mensa ci deve essere il bar !", this.bar,
				this.mensa.getStanzaAdiacente("sud"));
	}
}
