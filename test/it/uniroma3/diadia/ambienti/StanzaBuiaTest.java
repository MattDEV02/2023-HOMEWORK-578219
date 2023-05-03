package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBuiaTest { // 26 / 26

	private StanzaBuia riferimentoStanzaBuiaVuota;
	private StanzaBuia riferimentoStanzaBuiaSingleton;
	private StanzaBuia riferimentoStanzaBuiaPiena;

	@BeforeEach // Denotes that the annotated method should be executed before each @Test
	public void setUp() throws Exception {
		this.riferimentoStanzaBuiaVuota = stanzaBuiaVuota();
		this.riferimentoStanzaBuiaSingleton = stanzaBuiaSingleton(new Attrezzo("osso", 1));
		this.riferimentoStanzaBuiaPiena = stanzaBuiaPiena();
	}

	@Before
	private StanzaBuia stanzaBuia(String nomeAttrezzoRivelante, Attrezzo... attrezzi) {
		final int numeroAttrezzi = attrezzi.length;
		StanzaBuia stanzaOutput = new StanzaBuia("Aula N-x", nomeAttrezzoRivelante);
		for (int i = 0; i < numeroAttrezzi; i++) {
			stanzaOutput.addAttrezzo(attrezzi[i]);
		}
		return stanzaOutput;
	}

	@Before
	private StanzaBuia stanzaBuiaVuota() {
		return stanzaBuia("lanterna");
	}

	@Before
	private StanzaBuia stanzaBuiaSingleton(Attrezzo attrezzo) {
		return stanzaBuia("osso", attrezzo);
	}

	@Before
	private StanzaBuia stanzaBuiaPiena() {
		return stanzaBuia("lanterna", new Attrezzo("osso", 1), new Attrezzo("martello", 2), new Attrezzo("chiodo", 1),
				new Attrezzo("trapano", 3), new Attrezzo("spatola", 1), new Attrezzo("cofana", 3),
				new Attrezzo("spada", 3), new Attrezzo("scudo", 1), new Attrezzo("pala", 4), new Attrezzo("metro", 2));
	}

	@Test
	void testStanzaBuiaVuotaGetDescrizione() {
		assertEquals(this.riferimentoStanzaBuiaVuota.getDescrizione(), "Qui c'e un buio pesto.");
	}

	@Test
	void testStanzaBuiaSingletonGetDescrizione() {
		assertEquals(this.riferimentoStanzaBuiaSingleton.getDescrizione(),
				stanzaBuiaSingleton(new Attrezzo("osso", 1)).getDescrizione());
	}

	@Test
	void testStanzaBuiaPienaGetDescrizione() {
		assertEquals(this.riferimentoStanzaBuiaPiena.getDescrizione(), stanzaBuiaPiena().getDescrizione());
	}

	@Test
	void testStanzaHasAttrezzoVuotaAttrezzi() {
		assertFalse(stanzaBuiaVuota().hasAttrezzo("osso"), "La stanza vuota NON può avere attrezzi.");
	}

	@Test
	void testStanzaHasAttrezzoSingletonAttrezzi() {
		assertTrue(stanzaBuiaSingleton(new Attrezzo("osso", 1)).hasAttrezzo("osso"),
				"La stanza singleton deve avere l'attrezzo osso.");
	}

	@Test
	void testStanzaHasAttrezzoSingletonAttrezziNo() {
		assertFalse(stanzaBuiaSingleton(new Attrezzo("osso", 1)).hasAttrezzo("chiodo"),
				"La stanza singleton NON deve avere l'attrezzo lanterna.");
	}

	@Test
	void testHasAttrezzoNullStanzaPiena() {
		assertFalse(stanzaBuiaPiena().hasAttrezzo(null), "La stanza NON può avere l'attrezzo nullo con valore null.");
	}

	@Test
	void testStanzaHasAttrezzoPienaAttrezzi() {
		assertTrue(stanzaBuiaPiena().hasAttrezzo("pala"), "La stanza piena deve avere l'attrezzo pala.");
	}

	@Test
	void testStanzaHasAttrezzoPienaAttrezziNo() { // piena ==> 10 attrezzi
		assertFalse(stanzaBuiaPiena().hasAttrezzo("busta"), "La stanza piena NON deve avere l'attrezzo busta.");
	}

	@Test
	void testAddAttrezzoStanzaVuotaAttrezzi() {
		assertTrue(stanzaBuia("lanterna").addAttrezzo(new Attrezzo("osso", 1)),
				"La stanza vuota deve consentire l'aggiunta dell'attrezzo osso.");
	}

	@Test
	void testAddAttrezzoStanzaSingletonAttrezzi() {
		assertTrue(stanzaBuiaSingleton(new Attrezzo("osso", 1)).addAttrezzo(new Attrezzo("lanterna", 3)),
				"La stanza singleton deve consentire l'aggiunta dell'attrezzo lanterna.");
	}

	@Test
	void testAddAttrezzoCopioneStanza() {
		assertTrue(
				stanzaBuia("lanterna", new Attrezzo("osso", 1), new Attrezzo("osso", 1), new Attrezzo("osso", 1))
						.addAttrezzo(new Attrezzo("osso", 1)),
				"La stanza deve poter permettere l'aggiunta di attrezzi copioni.");
	}

	@Test
	void testRemoveCopioneStanza() {
		assertTrue(stanzaBuia("lanterna", new Attrezzo("osso", 1), new Attrezzo("osso", 1), new Attrezzo("osso", 1))
				.removeAttrezzo("osso"), "La stanza deve poter permettere la rimozione di attrezzi copioni.");
	}

	@Test
	void testAddAttrezzoStanzaPienaAttrezzi() { // piena ==> 10 attrezzi
		assertFalse(stanzaBuiaPiena().addAttrezzo(new Attrezzo("lanterna", 3)),
				"NON si può aggiungere un attrezzo nella stanza piena.");
	}

	@Test
	void testRemoveAttrezzoStanzaVuotaAttrezzi() {
		assertFalse(stanzaBuia("lanterna").removeAttrezzo("osso"),
				"NON si può rimuovere un attrezzo dalla stanza vuota.");
	}

	@Test
	void testRemoveAttrezzoStanzaSingletonAttrezzi() {
		assertTrue(stanzaBuiaSingleton(new Attrezzo("osso", 1)).removeAttrezzo("osso"),
				"La stanza singleton deve poter permettere la rimozione dell'attrezzo osso.");
	}

	@Test
	void testRemoveAttrezzoStanzaSingletonAttrezziNo() {
		assertFalse(stanzaBuiaSingleton(new Attrezzo("lanterna", 3)).removeAttrezzo("osso"),
				"La stanza singleton NON deve poter permettere la rimozione dell'attrezzo osso.");
	}

	@Test
	void testRemoveAttrezzoNullStanza() {
		assertFalse(
				stanzaBuia("lanterna", new Attrezzo("osso", 1), new Attrezzo("martello", 2), new Attrezzo("chiodo", 1))
						.removeAttrezzo(null),
				"NON è possibile rimuovere l'attrezzo nullo con il valore null dalla stanza.");
	}

	@Test
	void testRemoveAttrezzoStanzaPienaAttrezzi() {
		assertTrue(stanzaBuiaPiena().removeAttrezzo("metro"),
				"La stanza piena deve poter permettere la rimozione dell'attrezzo metro.");
	}

	@Test
	void testRemoveAttrezzoStanzaPienaAttrezziNo() {
		assertFalse(stanzaBuiaPiena().removeAttrezzo("busta"),
				"La stanza piena NON deve poter permettere la rimozione dell'attrezzo busta.");
	}

	@Test
	void testIsEmptyStanzaVuota() {
		assertTrue(stanzaBuia("lanterna").isEmpty(), "La stanza vuota NON contiene attrezzi !");
	}

	@Test
	void testIsEmptyStanzaSingleton() {
		assertFalse(stanzaBuiaSingleton(new Attrezzo("osso", 1)).isEmpty(),
				"La stanza singleton contiene un attrezzo !");
	}

	@Test
	void testIsEmptyStanzaPiena() {
		assertFalse(stanzaBuiaPiena().isEmpty(), "La stanza piena contiene molti attrezzi, non è vuota !");
	}

	@Test
	void testStanzaBuiaVuotaIsBuia() {
		assertTrue(this.riferimentoStanzaBuiaVuota.isBuia());
	}

	@Test
	void testStanzaBuiaSingletonIsMagica() {
		assertTrue(this.riferimentoStanzaBuiaSingleton.isBuia());
	}

	@Test
	void testStanzaBuiaPienaIsMagica() {
		assertTrue(this.riferimentoStanzaBuiaPiena.isBuia());
	}
}
