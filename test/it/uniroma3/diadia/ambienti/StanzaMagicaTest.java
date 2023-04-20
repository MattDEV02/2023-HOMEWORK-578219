package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaMagicaTest { // 24 / 24 N.B. = stesso discorso per la protected...

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
	void testStanzaMagicaHasAttrezzoVuotaAttrezzi() {
		assertFalse(stanzaMagicaVuota().hasAttrezzo("osso"), "La stanza Magica vuota NON può avere attrezzi.");
	}

	@Test
	void testStanzaMagicaHasAttrezzoSingletonAttrezzi() {
		assertTrue(stanzaMagicaSingleton(new Attrezzo("osso", 1)).hasAttrezzo("osso"),
				"La stanza Magica singleton deve avere l'attrezzo osso.");
	}

	@Test
	void testStanzaMagicaHasAttrezzoSingletonAttrezziNo() {
		assertFalse(stanzaMagicaSingleton(new Attrezzo("osso", 1)).hasAttrezzo("chiodo"),
				"La stanza Magica singleton NON deve avere l'attrezzo lanterna.");
	}

	@Test
	void testStanzaMagicaHasAttrezzoNullStanza() {
		assertFalse(stanzaMagica(new Attrezzo("osso", 1), new Attrezzo("martello", 2), new Attrezzo("chiodo", 1))
				.hasAttrezzo(null), "La stanza Magica NON può avere l'attrezzo nullo con valore null.");
	}

	@Test
	void testStanzaMagicaHasAttrezzoPienaAttrezzi() {
		assertTrue(stanzaMagicaPiena().hasAttrezzo("alap"),
				"La stanza Magica piena deve avere l'attrezzo pala inverito alap.");
	}

	@Test
	void testStanzaMagicaHasAttrezzoPienaAttrezziNo() { // piena ==> 10 attrezzi
		assertFalse(stanzaMagicaPiena().hasAttrezzo("busta"),
				"La stanza Magica piena NON deve avere l'attrezzo busta.");
	}

	@Test
	void testStanzaMagicaAddAttrezzoStanzaVuotaAttrezzi() {
		assertTrue(stanzaMagica().addAttrezzo(new Attrezzo("osso", 1)),
				"La stanza Magica vuota deve consentire l'aggiunta dell'attrezzo osso.");
	}

	@Test
	void testStanzaMagicaAddAttrezzoStanzaSingletonAttrezzi() {
		assertTrue(stanzaMagicaSingleton(new Attrezzo("osso", 1)).addAttrezzo(new Attrezzo("lanterna", 3)),
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
		assertFalse(stanzaMagicaPiena().addAttrezzo(new Attrezzo("lanterna", 3)),
				"NON si può aggiungere un attrezzo nella stanza Magica piena.");
	}

	@Test
	void testStanzaMagicaRemoveAttrezzoStanzaVuotaAttrezzi() {
		assertFalse(stanzaMagica().removeAttrezzo("osso"),
				"NON si può rimuovere un attrezzo dalla stanza Magica vuota.");
	}

	@Test
	void testStanzaMagicaRemoveAttrezzoStanzaSingletonAttrezzi() {
		assertTrue(stanzaMagicaSingleton(new Attrezzo("osso", 1)).removeAttrezzo("osso"),
				"La stanza Magica singleton deve poter permettere la rimozione dell'attrezzo osso.");
	}

	@Test
	void testStanzaMagicaRemoveAttrezzoStanzaSingletonAttrezziNo() {
		assertFalse(stanzaMagicaSingleton(new Attrezzo("lanterna", 3)).removeAttrezzo("osso"),
				"La stanza Magica singleton NON deve poter permettere la rimozione dell'attrezzo osso.");
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
		assertTrue(stanzaMagicaPiena().removeAttrezzo("ortem"),
				"La stanza Magica piena deve poter permettere la rimozione dell'attrezzo metro invertito (ortem).");
	}

	@Test
	void testStanzaMagicaRemoveAttrezzoStanzaPienaAttrezziNo() {
		assertFalse(stanzaMagicaPiena().removeAttrezzo("busta"),
				"La stanza Magica piena NON deve poter permettere la rimozione dell'attrezzo busta.");
	}

	@Test
	void testStanzaMagicaIsEmptyStanzaVuota() {
		assertTrue(stanzaMagica().isEmpty(), "La stanza Magica vuota NON contiene attrezzi !");
	}

	@Test
	void testStanzaMagicaIsEmptyStanzaSingleton() {
		assertFalse(stanzaMagicaSingleton(new Attrezzo("osso", 1)).isEmpty(),
				"La stanza Magica singleton contiene un attrezzo !");
	}

	@Test
	void testStanzaMagicaIsEmptyStanzaPiena() {
		assertFalse(stanzaMagicaPiena().isEmpty(), "La stanza Magica piena contiene molti attrezzi, non è vuota !");
	}

	@Test
	void testStanzaMagicaVuotaModificaAttrezzoNull() {
		assertNull(stanzaMagicaVuota().modificaAttrezzo(null));
	}

	@Test
	void testStanzaMagicaSingleonModificaAttrezzo() {
		assertEquals(stanzaMagicaSingleton(new Attrezzo("osso", 1)).modificaAttrezzo(new Attrezzo("osso", 1)),
				new Attrezzo("osso", 2));
	}

	@Test
	void testStanzaMagicaPienaModificaAttrezzo() {
		assertEquals(stanzaMagicaPiena().modificaAttrezzo(new Attrezzo("trapano", 3)), new Attrezzo("onapart", 6));
	}
}