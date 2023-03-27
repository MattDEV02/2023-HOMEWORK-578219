package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class BorsaTest { // 25 / 25

	@Before
	private Borsa borsa(int pesoMax, Attrezzo... attrezzi) {
		final int numeroAttrezzi = attrezzi.length;
		Borsa borsaOutput = new Borsa(pesoMax);
		for (int i = 0; i < numeroAttrezzi; i++) {
			borsaOutput.addAttrezzo(attrezzi[i]);
		}
		return borsaOutput;
	}

	@Before
	private Borsa borsaDefaultPesoMax(Attrezzo... attrezzi) {
		return borsa(Borsa.DEFAULT_PESO_MAX_BORSA, attrezzi);
	}

	@Before
	private Borsa borsaVuota() {
		return borsa(Borsa.DEFAULT_PESO_MAX_BORSA);
	}

	@Before
	private Borsa borsaSingleton(Attrezzo attrezzo) {
		return borsa(Borsa.DEFAULT_PESO_MAX_BORSA, attrezzo);
	}

	@Before
	private Borsa borsaPiena() {
		return borsa(25, new Attrezzo("osso", 1), new Attrezzo("martello", 2), new Attrezzo("chiodo", 1),
				new Attrezzo("trapano", 3), new Attrezzo("spatola", 1), new Attrezzo("cofana", 3),
				new Attrezzo("spada", 3), new Attrezzo("scudo", 1), new Attrezzo("pala", 4), new Attrezzo("metro", 2));
	}

	@Test
	void testBorsaHasAttrezzoVuotaAttrezzi() {
		assertFalse(borsaVuota().hasAttrezzo("osso"), "La borsa vuota NON può avere l'attrezzo osso.");
	}

	@Test
	void testBorsaHasAttrezzoSingletonAttrezzi() {
		assertTrue(borsaSingleton(new Attrezzo("osso", 1)).hasAttrezzo("osso"),
				"La borsa singleton deve contenere l'attrezzo osso.");
	}

	@Test
	void testBorsaHasAttrezzoSingletonAttrezziNo() {
		assertFalse(borsaSingleton(new Attrezzo("osso", 1)).hasAttrezzo("chiodo"),
				"La borsa singleton NON deve avere l'attrezzo chiodo.");
	}

	@Test
	void testHasAttrezzoNullBorsa() {
		assertFalse(borsaDefaultPesoMax(new Attrezzo("osso", 1), new Attrezzo("martello", 2), new Attrezzo("chiodo", 1))
				.hasAttrezzo(null), "La borsa NON può avere l'attrezzo nullo con valore null.");
	}

	@Test
	void testBorsaHasAttrezzoPienaAttrezzi() {
		assertTrue(borsaPiena().hasAttrezzo("pala"), "La borsa piena deve contenere l'attrezzo pala.");
	}

	@Test
	void testBorsaHasAttrezzoPienaAttrezziNo() { // piena ==> 10 attrezzi
		assertFalse(borsaPiena().hasAttrezzo("busta"), "La borsa piena NON può avere l'attrezzo busta.");
	}

	@Test
	void testAddAttrezzoBorsaVuotaAttrezzi() {
		assertTrue(borsaVuota().addAttrezzo(new Attrezzo("osso", 1)),
				"La borsa vuota deve poter aggiungere l'attrezzo osso.");
	}

	@Test
	void testAddAttrezzoBorsaSingletonAttrezzi() {
		assertTrue(borsaSingleton(new Attrezzo("osso", 1)).addAttrezzo(new Attrezzo("lanterna", 3)),
				"La borsa singleton deve poter aggiungere l'attrezzo lanterna.");
	}

	@Test
	void testAddAttrezzoNullBorsa() {
		assertFalse(borsaDefaultPesoMax(new Attrezzo("osso", 1), new Attrezzo("martello", 2), new Attrezzo("chiodo", 1))
				.addAttrezzo(null), "La borsa NON deve poter aggiungere l'attrezzo nullo con il valore null.");
	}

	@Test
	void testAddAttrezzoCopioneBorsa() {
		assertTrue(
				borsaDefaultPesoMax(new Attrezzo("osso", 1), new Attrezzo("osso", 1), new Attrezzo("osso", 1))
						.addAttrezzo(new Attrezzo("osso", 1)),
				"La borsa deve poter permettere l'aggiunta di attrezzi copioni.");
	}

	@Test
	void testRemoveAttrezzoCopioneBorsa() {
		assertTrue(borsaDefaultPesoMax(new Attrezzo("osso", 1), new Attrezzo("osso", 1), new Attrezzo("osso", 1))
				.removeAttrezzo("osso"), "La borsa deve poter permettere la rimozione di attrezzi copioni.");
	}

	@Test
	void testAddAttrezzoBorsaPienaAttrezzi() { // piena ==> 10 attrezzi
		assertFalse(borsaPiena().addAttrezzo(new Attrezzo("lanterna", 3)),
				"La borsa piena NON deve poter contenere altri attrezzi.");
	}

	@Test
	void testAddAttrezzoPesante() {
		assertFalse(
				borsaDefaultPesoMax(new Attrezzo("osso", 1), new Attrezzo("martello", 2), new Attrezzo("chiodo", 1))
						.addAttrezzo(new Attrezzo("attrezzo_pesante", 100)),
				"La borsa NON deve potere contenere attrezzi che sforano il limite di peso stabilito.");
	}

	@Test
	void testRemoveAttrezzoBorsaVuotaAttrezzi() {
		assertFalse(borsaVuota().removeAttrezzo("osso"), "Non si possono rimuovere attrezzi da una borsa vuota.");
	}

	@Test
	void testRemoveAttrezzoBorsaSingletonAttrezzi() {
		assertTrue(borsaSingleton(new Attrezzo("osso", 1)).removeAttrezzo("osso"),
				"La borsa singleton deve poter permettere la rimozione dell'attrezzo contenuto in essa.");
	}

	@Test
	void testRemoveAttrezzoBorsaSingletonAttrezziNo() {
		assertFalse(borsaSingleton(new Attrezzo("lanterna", 3)).removeAttrezzo("osso"),
				"NON puoi rimuovere un attrezzo NON contenuto nella borsa singleton.");
	}

	@Test
	void testRemoveAttrezzoNullBorsa() {
		assertFalse(borsaDefaultPesoMax(new Attrezzo("osso", 1), new Attrezzo("martello", 2), new Attrezzo("chiodo", 1))
				.removeAttrezzo(null), "NON puoi rimuovere l'attrezzo nullo con il valore null dalla borsa.");
	}

	@Test
	void testRemoveAttrezzoBorsaPienaAttrezzi() {
		assertTrue(borsaPiena().removeAttrezzo("metro"),
				"La borsa piena deve poter permettere la rimozione dell'attrezzo contenuto in essa.");
	}

	@Test
	void testRemoveAttrezzoBorsaPienaAttrezziNo() {
		assertFalse(borsaPiena().removeAttrezzo("busta"),
				"NON puoi rimuovere un attrezzo NON contenuto nella borsa piena.");
	}

	@Test
	void testStessiAttrezziBorsaVuota() {
		assertTrue(borsaVuota().stessiAttrezzi(borsaVuota()),
				"Le borse vuote devono avere gli stessi attrezzi, cioè nessuno.");
	}

	@Test
	void testStessiAttrezziBorsaSingleton() {
		assertTrue(borsaSingleton(new Attrezzo("osso", 1)).stessiAttrezzi(borsaSingleton(new Attrezzo("osso", 1))),
				"Le borse singleton (attrezzo osso) devono avere gli stessi attrezzi.");
	}

	@Test
	void testStessiAttrezziBorsaPiena() {
		assertTrue(borsaPiena().stessiAttrezzi(borsaPiena()),
				"Le borse piene (con stessi attrezzi) devono avere gli stessi attrezzi.");
	}

	@Test
	void testIsEmptyStanzaVuota() {
		assertTrue(borsaVuota().isEmpty(), "La borsa vuota NON contiene attrezzi !");
	}

	@Test
	void testIsEmptyStanzaSingleton() {
		assertFalse(borsaSingleton(new Attrezzo("osso", 1)).isEmpty(), "La borsa singleton contiene un attrezzo !");
	}

	@Test
	void testIsEmptyStanzaPiena() {
		assertFalse(borsaPiena().isEmpty(), "La borsa piena contiene molti attrezzi !");
	}
}
