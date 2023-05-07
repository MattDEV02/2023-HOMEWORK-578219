package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaMagicaTest { // 31 / 31 N.B. = stesso discorso per la protected...

	private StanzaMagica riferimentoStanzaMagicaVuota;
	private StanzaMagica riferimentoStanzaMagicaSingleton;
	private StanzaMagica riferimentoStanzaMagicaPiena;

	@BeforeEach // Denotes that the annotated method should be executed before each @Test
	public void setUp() throws Exception {
		this.riferimentoStanzaMagicaVuota = stanzaMagicaVuota();
		this.riferimentoStanzaMagicaSingleton = stanzaMagicaSingleton(new Attrezzo("osso", 1));
		this.riferimentoStanzaMagicaPiena = stanzaMagicaPiena();
	}

	@Before
	private StanzaMagica stanzaMagica(Attrezzo... attrezzi) {
		final int numeroAttrezzi = attrezzi.length;
		StanzaMagica stanzaOutput = new StanzaMagica("Aula N-x");
		for (int i = 0; i < numeroAttrezzi; i++) {
			stanzaOutput.addAttrezzo(attrezzi[i]);
		}
		return stanzaOutput;
	}

	@Before
	private StanzaMagica stanzaMagicaVuota() {
		return stanzaMagica();
	}

	@Before
	private StanzaMagica stanzaMagicaSingleton(Attrezzo attrezzo) {
		return stanzaMagica(attrezzo);
	}

	@Before
	private StanzaMagica stanzaMagicaPiena() {
		return stanzaMagica(new Attrezzo("osso", 1), new Attrezzo("martello", 2), new Attrezzo("chiodo", 1),
				new Attrezzo("trapano", 3), new Attrezzo("spatola", 1), new Attrezzo("cofana", 3),
				new Attrezzo("spada", 3), new Attrezzo("scudo", 1), new Attrezzo("pala", 4), new Attrezzo("metro", 2));
	}

	@Test
	void testStanzaMagicaVuotaSize() {
		assertEquals(0, this.riferimentoStanzaMagicaVuota.getNumAttrezzi());
	}

	@Test
	void testStanzaMagicaSingletonSize() {
		assertEquals(1, this.riferimentoStanzaMagicaSingleton.getNumAttrezzi());
	}

	@Test
	void testStanzaMagicaSingletonNullSize() {
		assertEquals(0, this.stanzaMagicaSingleton(null).getNumAttrezzi());
	}

	@Test
	void testStanzaMagicaPienaSize() {
		assertEquals(10, this.riferimentoStanzaMagicaPiena.getNumAttrezzi());
	}

	@Test
	void testStanzaMagicaHasAttrezzoVuotaAttrezzi() {
		assertFalse(this.riferimentoStanzaMagicaVuota.hasAttrezzo("osso"),
				"La stanza Magica vuota NON può avere attrezzi.");
	}

	@Test
	void testStanzaMagicaHasAttrezzoSingletonAttrezzi() {
		assertTrue(this.riferimentoStanzaMagicaSingleton.hasAttrezzo("osso"),
				"La stanza Magica singleton deve avere l'attrezzo osso.");
	}

	@Test
	void testStanzaMagicaHasAttrezzoSingletonAttrezziNo() {
		assertFalse(this.riferimentoStanzaMagicaSingleton.hasAttrezzo("chiodo"),
				"La stanza Magica singleton NON deve avere l'attrezzo lanterna.");
	}

	@Test
	void testStanzaMagicaHasAttrezzoNullStanza() {
		assertFalse(stanzaMagica(new Attrezzo("osso", 1), new Attrezzo("martello", 2), new Attrezzo("chiodo", 1))
				.hasAttrezzo(null), "La stanza Magica NON può avere l'attrezzo nullo con valore null.");
	}

	@Test
	void testStanzaMagicaHasAttrezzoPienaAttrezzi() {
		assertTrue(this.riferimentoStanzaMagicaPiena.hasAttrezzo("alap"),
				"La stanza Magica piena deve avere l'attrezzo pala inverito alap.");
	}

	@Test
	void testStanzaMagicaHasAttrezzoPienaAttrezziNo() { // piena ==> 10 attrezzi
		assertFalse(this.riferimentoStanzaMagicaPiena.hasAttrezzo("busta"),
				"La stanza Magica piena NON deve avere l'attrezzo busta.");
	}

	@Test
	void testStanzaMagicaAddAttrezzoStanzaVuotaAttrezzi() {
		assertTrue(this.riferimentoStanzaMagicaVuota.addAttrezzo(new Attrezzo("osso", 1)),
				"La stanza Magica vuota deve consentire l'aggiunta dell'attrezzo osso.");
	}

	@Test
	void testStanzaMagicaAddAttrezzoStanzaSingletonAttrezzi() {
		assertTrue(this.riferimentoStanzaMagicaSingleton.addAttrezzo(new Attrezzo("lanterna", 3)),
				"La stanza Magica singleton deve consentire l'aggiunta dell'attrezzo lanterna.");
	}

	@Test
	void testStanzaMagicaAddAttrezzoNullStanza() {
		assertFalse(stanzaMagica(new Attrezzo("osso", 1), new Attrezzo("martello", 2), new Attrezzo("chiodo", 1))
				.addAttrezzo(null), "NON si può aggiungere l'attrezzo null nella stanza Magica.");
	}

	@Test
	void testStanzaMagicaAddAttrezzoCopioneStanza() {
		assertTrue(
				stanzaMagica(new Attrezzo("osso", 1), new Attrezzo("osso", 1), new Attrezzo("osso", 1))
						.addAttrezzo(new Attrezzo("osso", 1)),
				"La stanza Magica deve poter permettere l'aggiunta di attrezzi copioni.");
	}

	@Test
	void testStanzaMagicaRemoveCopioneStanza() {
		assertTrue(stanzaMagica(new Attrezzo("osso", 1), new Attrezzo("osso", 1), new Attrezzo("osso", 1))
				.removeAttrezzo("osso"), "La stanza Magica deve poter permettere la rimozione di attrezzi copioni.");
	}

	@Test
	void testStanzaMagicaAddAttrezzoStanzaPienaAttrezzi() { // piena ==> 10 attrezzi
		assertFalse(this.riferimentoStanzaMagicaPiena.addAttrezzo(new Attrezzo("lanterna", 3)),
				"NON si può aggiungere un attrezzo nella stanza Magica piena.");
	}

	@Test
	void testStanzaMagicaRemoveAttrezzoStanzaVuotaAttrezzi() {
		assertFalse(this.riferimentoStanzaMagicaVuota.removeAttrezzo("osso"),
				"NON si può rimuovere un attrezzo dalla stanza Magica vuota.");
	}

	@Test
	void testStanzaMagicaRemoveAttrezzoStanzaSingletonAttrezzi() {
		assertTrue(this.riferimentoStanzaMagicaSingleton.removeAttrezzo("osso"),
				"La stanza Magica singleton deve poter permettere la rimozione dell'attrezzo osso.");
	}

	@Test
	void testStanzaMagicaRemoveAttrezzoStanzaSingletonAttrezziNo() {
		assertFalse(this.riferimentoStanzaMagicaSingleton.removeAttrezzo("ossoo"),
				"La stanza Magica singleton NON deve poter permettere la rimozione dell'attrezzo ossoo.");
	}

	@Test
	void testStanzaMagicaRemoveAttrezzoNullStanza() {
		assertFalse(
				stanzaMagica(new Attrezzo("osso", 1), new Attrezzo("martello", 2), new Attrezzo("chiodo", 1))
						.removeAttrezzo(null),
				"NON è possibile rimuovere l'attrezzo nullo con il valore null dalla stanza.");
	}

	@Test
	void testStanzaMagicaRemoveAttrezzoStanzaPienaAttrezzi() {
		assertTrue(this.riferimentoStanzaMagicaPiena.removeAttrezzo("ortem"),
				"La stanza Magica piena deve poter permettere la rimozione dell'attrezzo metro invertito (ortem).");
	}

	@Test
	void testStanzaMagicaRemoveAttrezzoStanzaPienaAttrezziNo() {
		assertFalse(this.riferimentoStanzaMagicaPiena.removeAttrezzo("busta"),
				"La stanza Magica piena NON deve poter permettere la rimozione dell'attrezzo busta.");
	}

	@Test
	void testStanzaMagicaIsEmptyStanzaVuota() {
		assertTrue(this.riferimentoStanzaMagicaVuota.isEmpty(), "La stanza Magica vuota NON contiene attrezzi !");
	}

	@Test
	void testStanzaMagicaIsEmptyStanzaSingleton() {
		assertFalse(this.riferimentoStanzaMagicaSingleton.isEmpty(),
				"La stanza Magica singleton contiene un attrezzo !");
	}

	@Test
	void testStanzaMagicaIsEmptyStanzaPiena() {
		assertFalse(this.riferimentoStanzaMagicaPiena.isEmpty(),
				"La stanza Magica piena contiene molti attrezzi, non è vuota !");
	}

	@Test
	void testStanzaMagicaVuotaModificaAttrezzoNull() {
		assertNull(this.riferimentoStanzaMagicaVuota.modificaAttrezzo(null));
	}

	@Test
	void testStanzaMagicaSingleonModificaAttrezzo() {
		assertEquals(this.riferimentoStanzaMagicaSingleton.modificaAttrezzo(new Attrezzo("osso", 1)),
				new Attrezzo("osso", 2));
	}

	@Test
	void testStanzaMagicaPienaModificaAttrezzo() {
		assertEquals(this.riferimentoStanzaMagicaPiena.modificaAttrezzo(new Attrezzo("trapano", 3)),
				new Attrezzo("onapart", 6));
	}

	@Test
	void testStanzaMagicaVuotaIsMagica() {
		assertTrue(this.riferimentoStanzaMagicaVuota.isMagica());
	}

	@Test
	void testStanzaMagicaSingletonIsMagica() {
		assertTrue(this.riferimentoStanzaMagicaSingleton.isMagica());
	}

	@Test
	void testStanzaMagicaPienaIsMagica() {
		assertTrue(this.riferimentoStanzaMagicaPiena.isMagica());
	}
}