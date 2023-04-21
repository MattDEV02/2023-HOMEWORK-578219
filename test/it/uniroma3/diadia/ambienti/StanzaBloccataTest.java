package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBloccataTest { // 26 / 26

	private StanzaBloccata riferimentoStanzaVuota;
	private StanzaBloccata riferimentoStanzaSingleton;
	private StanzaBloccata riferimentoStanzaPiena;

	@Before
	private StanzaBloccata stanza(String nomeAttrezzoSbloccante, Attrezzo... attrezzi) {
		final int numeroAttrezzi = attrezzi.length;
		StanzaBloccata stanzaOutput = new StanzaBloccata("Aula N-x", "nord", nomeAttrezzoSbloccante);
		for (int i = 0; i < numeroAttrezzi; i++) {
			stanzaOutput.addAttrezzo(attrezzi[i]);
		}
		return stanzaOutput;
	}

	@Before
	private StanzaBloccata stanzaVuota() {
		return stanza("lanterna");
	}

	@Before
	private StanzaBloccata stanzaSingleton(Attrezzo attrezzo) {
		return stanza("osso", attrezzo);
	}

	@Before
	private StanzaBloccata stanzaPiena() {
		return stanza("spatola", new Attrezzo("osso", 1), new Attrezzo("martello", 2), new Attrezzo("chiodo", 1),
				new Attrezzo("trapano", 3), new Attrezzo("spatola", 1), new Attrezzo("cofana", 3),
				new Attrezzo("spada", 3), new Attrezzo("scudo", 1), new Attrezzo("pala", 4), new Attrezzo("metro", 2));
	}

	@BeforeEach // Denotes that the annotated method should be executed before each @Test
	void setUp() { // collega le stanze per testare i metodi impostaStanzaAdiacente e
					// getStanzaAdiacente
		this.riferimentoStanzaVuota = stanzaVuota();
		this.riferimentoStanzaSingleton = stanzaSingleton(new Attrezzo("lanterna", 1));
		this.riferimentoStanzaPiena = stanzaPiena();
		this.riferimentoStanzaSingleton.impostaStanzaAdiacente("nord", this.riferimentoStanzaVuota);
		this.riferimentoStanzaVuota.impostaStanzaAdiacente("sud", this.riferimentoStanzaSingleton);
		this.riferimentoStanzaPiena.impostaStanzaAdiacente("nord", this.riferimentoStanzaSingleton);
		this.riferimentoStanzaSingleton.impostaStanzaAdiacente("sud", this.riferimentoStanzaPiena);
	}

	@Test
	void testStanzaBloccataVuotaGetStanzaAdiacenteBloccata() {
		assertEquals(this.riferimentoStanzaVuota.getStanzaAdiacente("nord"), stanzaVuota());
	}

	@Test
	void testStanzaBloccataVuotaGetStanzaAdiacenteNonBloccata() {
		assertEquals(this.riferimentoStanzaVuota.getStanzaAdiacente("sud"), this.riferimentoStanzaSingleton);
	}

	@Test
	void testStanzaBloccataSingletonGetStanzaAdiacenteBloccata() {
		assertEquals(this.riferimentoStanzaSingleton.getStanzaAdiacente("nord"), this.riferimentoStanzaSingleton);
	}

	@Test
	void testStanzaBloccataSingletonGetStanzaAdiacenteNonBloccata() {
		assertEquals(this.riferimentoStanzaSingleton.getStanzaAdiacente("sud"), this.riferimentoStanzaPiena);
	}

	@Test
	void testStanzaBloccataPienaGetStanzaAdiacenteBloccata() {
		assertNull(this.riferimentoStanzaPiena.getStanzaAdiacente("sud"));
	}

	@Test
	void testStanzaBloccataPienaGetStanzaAdiacenteNonBloccata() {
		assertEquals(this.riferimentoStanzaPiena.getStanzaAdiacente("nord"), this.riferimentoStanzaSingleton);
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
	void testHasAttrezzoNullStanzaPiena() {
		assertFalse(stanzaPiena().hasAttrezzo(null), "La stanza NON può avere l'attrezzo nullo con valore null.");
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
		assertTrue(stanza("piedediporco").addAttrezzo(new Attrezzo("osso", 1)),
				"La stanza vuota deve consentire l'aggiunta dell'attrezzo osso.");
	}

	@Test
	void testAddAttrezzoStanzaSingletonAttrezzi() {
		assertTrue(stanzaSingleton(new Attrezzo("osso", 1)).addAttrezzo(new Attrezzo("lanterna", 3)),
				"La stanza singleton deve consentire l'aggiunta dell'attrezzo lanterna.");
	}

	@Test
	void testAddAttrezzoCopioneStanza() {
		assertTrue(
				stanza("piedediporco", new Attrezzo("osso", 1), new Attrezzo("osso", 1), new Attrezzo("osso", 1))
						.addAttrezzo(new Attrezzo("osso", 1)),
				"La stanza deve poter permettere l'aggiunta di attrezzi copioni.");
	}

	@Test
	void testRemoveCopioneStanza() {
		assertTrue(stanza("piedediporco", new Attrezzo("osso", 1), new Attrezzo("osso", 1), new Attrezzo("osso", 1))
				.removeAttrezzo("osso"), "La stanza deve poter permettere la rimozione di attrezzi copioni.");
	}

	@Test
	void testAddAttrezzoStanzaPienaAttrezzi() { // piena ==> 10 attrezzi
		assertFalse(stanzaPiena().addAttrezzo(new Attrezzo("lanterna", 3)),
				"NON si può aggiungere un attrezzo nella stanza piena.");
	}

	@Test
	void testRemoveAttrezzoStanzaVuotaAttrezzi() {
		assertFalse(stanza("piedediporco").removeAttrezzo("osso"),
				"NON si può rimuovere un attrezzo dalla stanza vuota.");
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
		assertFalse(
				stanza("piedediporco", new Attrezzo("osso", 1), new Attrezzo("martello", 2), new Attrezzo("chiodo", 1))
						.removeAttrezzo(null),
				"NON è possibile rimuovere l'attrezzo nullo con il valore null dalla stanza.");
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
		assertTrue(stanza("piedediporco").isEmpty(), "La stanza vuota NON contiene attrezzi !");
	}

	@Test
	void testIsEmptyStanzaSingleton() {
		assertFalse(stanzaSingleton(new Attrezzo("osso", 1)).isEmpty(), "La stanza singleton contiene un attrezzo !");
	}

	@Test
	void testIsEmptyStanzaPiena() {
		assertFalse(stanzaPiena().isEmpty(), "La stanza piena contiene molti attrezzi, non è vuota !");
	}
}
