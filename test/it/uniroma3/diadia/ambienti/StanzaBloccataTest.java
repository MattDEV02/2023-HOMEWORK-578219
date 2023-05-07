package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBloccataTest { // 33 / 33

	private StanzaBloccata riferimentoStanzaBloccataVuota;
	private StanzaBloccata riferimentoStanzaBloccataSingleton;
	private StanzaBloccata riferimentoStanzaBloccataPiena;

	@BeforeEach // Denotes that the annotated method should be executed before each @Test
	public void setUp() throws Exception { // collega le stanze per testare i metodi impostaStanzaAdiacente e
											// getStanzaAdiacente
		this.riferimentoStanzaBloccataVuota = stanzaBloccataVuota();
		this.riferimentoStanzaBloccataSingleton = stanzaBloccataSingleton(new Attrezzo("lanterna", 1));
		this.riferimentoStanzaBloccataPiena = stanzaPiena();
		this.riferimentoStanzaBloccataSingleton.impostaStanzaAdiacente("nord", this.riferimentoStanzaBloccataVuota);
		this.riferimentoStanzaBloccataVuota.impostaStanzaAdiacente("sud", this.riferimentoStanzaBloccataSingleton);
		this.riferimentoStanzaBloccataPiena.impostaStanzaAdiacente("nord", this.riferimentoStanzaBloccataSingleton);
		this.riferimentoStanzaBloccataSingleton.impostaStanzaAdiacente("sud", this.riferimentoStanzaBloccataPiena);
	}

	@Before
	private StanzaBloccata stanzaBloccata(String nomeAttrezzoSbloccante, Attrezzo... attrezzi) {
		final int numeroAttrezzi = attrezzi.length;
		StanzaBloccata stanzaOutput = new StanzaBloccata("Aula N-x", "nord", nomeAttrezzoSbloccante);
		for (int i = 0; i < numeroAttrezzi; i++) {
			stanzaOutput.addAttrezzo(attrezzi[i]);
		}
		return stanzaOutput;
	}

	@Before
	private StanzaBloccata stanzaBloccataVuota() {
		return stanzaBloccata("lanterna");
	}

	@Before
	private StanzaBloccata stanzaBloccataSingleton(Attrezzo attrezzo) {
		return stanzaBloccata("osso", attrezzo);
	}

	@Before
	private StanzaBloccata stanzaPiena() {
		return stanzaBloccata("spatola", new Attrezzo("osso", 1), new Attrezzo("martello", 2),
				new Attrezzo("chiodo", 1), new Attrezzo("trapano", 3), new Attrezzo("spatola", 1),
				new Attrezzo("cofana", 3), new Attrezzo("spada", 3), new Attrezzo("scudo", 1), new Attrezzo("pala", 4),
				new Attrezzo("metro", 2));
	}

	@Test
	void testStanzaBloccataVuotaSize() {
		assertEquals(0, this.riferimentoStanzaBloccataVuota.getNumAttrezzi());
	}

	@Test
	void testStanzaBloccataSingletonSize() {
		assertEquals(1, this.riferimentoStanzaBloccataSingleton.getNumAttrezzi());
	}

	@Test
	void testStanzaBloccataSingletonNullSize() {
		assertEquals(0, this.stanzaBloccataSingleton(null).getNumAttrezzi());
	}

	@Test
	void testStanzaBloccataPienaSize() {
		assertEquals(10, this.riferimentoStanzaBloccataPiena.getNumAttrezzi());
	}

	@Test
	void testStanzaBloccataVuotaGetStanzaAdiacenteBloccata() {
		assertEquals(this.riferimentoStanzaBloccataVuota.getStanzaAdiacente("nord"), stanzaBloccataVuota());
	}

	@Test
	void testStanzaBloccataVuotaGetStanzaAdiacenteNonBloccata() {
		assertEquals(this.riferimentoStanzaBloccataVuota.getStanzaAdiacente("sud"),
				this.riferimentoStanzaBloccataSingleton);
	}

	@Test
	void testStanzaBloccataSingletonGetStanzaAdiacenteBloccata() {
		assertEquals(this.riferimentoStanzaBloccataSingleton.getStanzaAdiacente("nord"),
				this.riferimentoStanzaBloccataSingleton);
	}

	@Test
	void testStanzaBloccataSingletonGetStanzaAdiacenteNonBloccata() {
		assertEquals(this.riferimentoStanzaBloccataSingleton.getStanzaAdiacente("sud"),
				this.riferimentoStanzaBloccataPiena);
	}

	@Test
	void testStanzaBloccataPienaGetStanzaAdiacenteBloccata() {
		assertNull(this.riferimentoStanzaBloccataPiena.getStanzaAdiacente("sud"));
	}

	@Test
	void testStanzaBloccataPienaGetStanzaAdiacenteNonBloccata() {
		assertEquals(this.riferimentoStanzaBloccataPiena.getStanzaAdiacente("nord"),
				this.riferimentoStanzaBloccataSingleton);
	}

	@Test
	void testStanzaHasAttrezzoVuotaAttrezzi() {
		assertFalse(stanzaBloccataVuota().hasAttrezzo("osso"), "La stanza vuota NON può avere attrezzi.");
	}

	@Test
	void testStanzaHasAttrezzoSingletonAttrezzi() {
		assertTrue(stanzaBloccataSingleton(new Attrezzo("osso", 1)).hasAttrezzo("osso"),
				"La stanza singleton deve avere l'attrezzo osso.");
	}

	@Test
	void testStanzaHasAttrezzoSingletonAttrezziNo() {
		assertFalse(stanzaBloccataSingleton(new Attrezzo("osso", 1)).hasAttrezzo("chiodo"),
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
		assertTrue(stanzaBloccata("piedediporco").addAttrezzo(new Attrezzo("osso", 1)),
				"La stanza vuota deve consentire l'aggiunta dell'attrezzo osso.");
	}

	@Test
	void testAddAttrezzoStanzaSingletonAttrezzi() {
		assertTrue(stanzaBloccataSingleton(new Attrezzo("osso", 1)).addAttrezzo(new Attrezzo("lanterna", 3)),
				"La stanza singleton deve consentire l'aggiunta dell'attrezzo lanterna.");
	}

	@Test
	void testAddAttrezzoCopioneStanza() {
		assertTrue(
				stanzaBloccata("piedediporco", new Attrezzo("osso", 1), new Attrezzo("osso", 1),
						new Attrezzo("osso", 1)).addAttrezzo(new Attrezzo("osso", 1)),
				"La stanza deve poter permettere l'aggiunta di attrezzi copioni.");
	}

	@Test
	void testRemoveCopioneStanza() {
		assertTrue(
				stanzaBloccata("piedediporco", new Attrezzo("osso", 1), new Attrezzo("osso", 1),
						new Attrezzo("osso", 1)).removeAttrezzo("osso"),
				"La stanza deve poter permettere la rimozione di attrezzi copioni.");
	}

	@Test
	void testAddAttrezzoStanzaPienaAttrezzi() { // piena ==> 10 attrezzi
		assertFalse(stanzaPiena().addAttrezzo(new Attrezzo("lanterna", 3)),
				"NON si può aggiungere un attrezzo nella stanza piena.");
	}

	@Test
	void testRemoveAttrezzoStanzaVuotaAttrezzi() {
		assertFalse(this.riferimentoStanzaBloccataVuota.removeAttrezzo("osso"),
				"NON si può rimuovere un attrezzo dalla stanza vuota.");
		assertEquals(0, this.riferimentoStanzaBloccataVuota.getNumAttrezzi());
	}

	@Test
	void testRemoveAttrezzoStanzaSingletonAttrezzi() {
		assertTrue(this.riferimentoStanzaBloccataSingleton.removeAttrezzo("lanterna"),
				"La stanza singleton deve poter permettere la rimozione dell'attrezzo lanterna.");
		assertEquals(0, this.riferimentoStanzaBloccataSingleton.getNumAttrezzi());
	}

	@Test
	void testRemoveAttrezzoStanzaSingletonAttrezziNo() {
		assertFalse(this.riferimentoStanzaBloccataSingleton.removeAttrezzo("osso"),
				"La stanza singleton NON deve poter permettere la rimozione dell'attrezzo osso.");
		assertEquals(1, this.riferimentoStanzaBloccataSingleton.getNumAttrezzi());
	}

	@Test
	void testRemoveAttrezzoNullStanza() {
		assertFalse(
				stanzaBloccata("piedediporco", new Attrezzo("osso", 1), new Attrezzo("martello", 2),
						new Attrezzo("chiodo", 1)).removeAttrezzo(null),
				"NON è possibile rimuovere l'attrezzo nullo con il valore null dalla stanza.");
	}

	@Test
	void testRemoveAttrezzoStanzaPienaAttrezzi() {
		assertTrue(this.riferimentoStanzaBloccataPiena.removeAttrezzo("metro"),
				"La stanza piena deve poter permettere la rimozione dell'attrezzo metro.");
		assertEquals(9, this.riferimentoStanzaBloccataPiena.getNumAttrezzi());
	}

	@Test
	void testRemoveAttrezzoStanzaPienaAttrezziNo() {
		assertFalse(this.riferimentoStanzaBloccataPiena.removeAttrezzo("busta"),
				"La stanza piena NON deve poter permettere la rimozione dell'attrezzo busta.");
		assertEquals(10, this.riferimentoStanzaBloccataPiena.getNumAttrezzi());
	}

	@Test
	void testIsEmptyStanzaVuota() {
		assertTrue(this.riferimentoStanzaBloccataVuota.isEmpty(), "La stanza vuota NON contiene attrezzi !");
	}

	@Test
	void testIsEmptyStanzaSingleton() {
		assertFalse(this.riferimentoStanzaBloccataSingleton.isEmpty(), "La stanza singleton contiene un attrezzo !");
	}

	@Test
	void testIsEmptyStanzaPiena() {
		assertFalse(this.riferimentoStanzaBloccataPiena.isEmpty(),
				"La stanza piena contiene molti attrezzi, non è vuota !");
	}

	@Test
	void testStanzaBloccataVuotaIsBloccata() {
		assertTrue(this.riferimentoStanzaBloccataVuota.isBloccata());
	}

	@Test
	void testStanzaBuiaSingletonIsBloccata() {
		assertTrue(this.riferimentoStanzaBloccataVuota.isBloccata());
	}

	@Test
	void testStanzaBloccataPienaIsBloccata() {
		assertTrue(this.riferimentoStanzaBloccataPiena.isBloccata());
	}
}
