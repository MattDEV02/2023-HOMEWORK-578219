package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class BorsaTest { // 39 / 39

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
	private Borsa borsaDoppia(Attrezzo attrezzo1, Attrezzo attrezzo2) {
		return borsa(Borsa.DEFAULT_PESO_MAX_BORSA, attrezzo1, attrezzo2);
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
	void testBorsaHasAttrezzoPienaAttrezziNo() {
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

	@Test
	void testGetSortedSetOrdinatoPerPesoBorsaPiena() {
		Borsa borsaPiena = this.borsaPiena();
		SortedSet<Attrezzo> s = borsaPiena.getSortedSetOrdinatoPerPeso();
		Iterator<Attrezzo> it = s.iterator();
		assertEquals(10, s.size());
		assertTrue(it.hasNext());
		assertSame(borsaPiena.getNome2attrezzi().get("chiodo"), it.next());
		assertSame(borsaPiena.getNome2attrezzi().get("osso"), it.next());
		assertSame(borsaPiena.getNome2attrezzi().get("scudo"), it.next());
		assertSame(borsaPiena.getNome2attrezzi().get("spatola"), it.next());
		assertSame(borsaPiena.getNome2attrezzi().get("martello"), it.next());
		assertSame(borsaPiena.getNome2attrezzi().get("metro"), it.next());
		assertSame(borsaPiena.getNome2attrezzi().get("cofana"), it.next());
		assertSame(borsaPiena.getNome2attrezzi().get("spada"), it.next());
		assertSame(borsaPiena.getNome2attrezzi().get("trapano"), it.next());
		assertSame(borsaPiena.getNome2attrezzi().get("pala"), it.next());
		assertFalse(it.hasNext());
	}

	@Test
	void testGetContenutoOrdinatoPerNomeBorsaPiena() {
		Borsa borsaPiena = this.borsaPiena();
		SortedSet<Attrezzo> s = borsaPiena.getContenutoOrdinatoPerNome();
		Iterator<Attrezzo> it = s.iterator();
		assertEquals(10, s.size());
		assertTrue(it.hasNext());
		assertSame(borsaPiena.getNome2attrezzi().get("chiodo"), it.next());
		assertSame(borsaPiena.getNome2attrezzi().get("cofana"), it.next());
		assertSame(borsaPiena.getNome2attrezzi().get("martello"), it.next());
		assertSame(borsaPiena.getNome2attrezzi().get("metro"), it.next());
		assertSame(borsaPiena.getNome2attrezzi().get("osso"), it.next());
		assertSame(borsaPiena.getNome2attrezzi().get("pala"), it.next());
		assertSame(borsaPiena.getNome2attrezzi().get("scudo"), it.next());
		assertSame(borsaPiena.getNome2attrezzi().get("spada"), it.next());
		assertSame(borsaPiena.getNome2attrezzi().get("spatola"), it.next());
		assertSame(borsaPiena.getNome2attrezzi().get("trapano"), it.next());
		assertFalse(it.hasNext());
	}

	@Test
	void testGetContenutoOrdinatoPerPesoBorsaPiena() {
		Borsa borsaPiena = this.borsaPiena();
		List<Attrezzo> l = borsaPiena.getContenutoOrdinatoPerPeso();
		assertEquals(10, l.size());
		assertFalse(l.isEmpty());
		assertSame(borsaPiena.getNome2attrezzi().get("chiodo"), l.get(0));
		assertSame(borsaPiena.getNome2attrezzi().get("osso"), l.get(1));
		assertSame(borsaPiena.getNome2attrezzi().get("scudo"), l.get(2));
		assertSame(borsaPiena.getNome2attrezzi().get("spatola"), l.get(3));
		assertSame(borsaPiena.getNome2attrezzi().get("martello"), l.get(4));
		assertSame(borsaPiena.getNome2attrezzi().get("metro"), l.get(5));
		assertSame(borsaPiena.getNome2attrezzi().get("cofana"), l.get(6));
		assertSame(borsaPiena.getNome2attrezzi().get("spada"), l.get(7));
		assertSame(borsaPiena.getNome2attrezzi().get("trapano"), l.get(8));
		assertSame(borsaPiena.getNome2attrezzi().get("pala"), l.get(9));
	}

	@Test
	void testGetSortedSetOrdinatoPerPesoBorsaVuota() {
		Borsa borsaVuota = this.borsaVuota();
		SortedSet<Attrezzo> s = borsaVuota.getSortedSetOrdinatoPerPeso();
		Iterator<Attrezzo> it = s.iterator();
		assertEquals(0, s.size());
		assertFalse(it.hasNext());
	}

	@Test
	void testGetContenutoOrdinatoPerNomeBorsaVuota() {
		Borsa borsaVuota = this.borsaVuota();
		SortedSet<Attrezzo> s = borsaVuota.getContenutoOrdinatoPerNome();
		Iterator<Attrezzo> it = s.iterator();
		assertEquals(0, s.size());
		assertFalse(it.hasNext());
	}

	@Test
	void testGetContenutoOrdinatoPerPesoBorsaSingleton() {
		Borsa borsaSingleton = this.borsaSingleton(new Attrezzo("osso", 1));
		List<Attrezzo> l = borsaSingleton.getContenutoOrdinatoPerPeso();
		Iterator<Attrezzo> it = l.iterator();
		assertEquals(1, l.size());
		assertTrue(it.hasNext());
		assertSame(borsaSingleton.getNome2attrezzi().get("osso"), it.next());
		assertFalse(it.hasNext());
	}

	@Test
	void testGetSortedSetOrdinatoPerPesoBorsaSingleton() {
		Borsa borsaSingleton = this.borsaSingleton(new Attrezzo("lanterna", 2));
		SortedSet<Attrezzo> s = borsaSingleton.getSortedSetOrdinatoPerPeso();
		Iterator<Attrezzo> it = s.iterator();
		assertEquals(1, s.size());
		assertTrue(it.hasNext());
		assertSame(borsaSingleton.getNome2attrezzi().get("lanterna"), it.next());
		assertFalse(it.hasNext());
	}

	@Test
	void testGetContenutoOrdinatoPerNomeBorsaSingleton() {
		Borsa borsaSingleton = this.borsaSingleton(new Attrezzo("piedediporco", 3));
		SortedSet<Attrezzo> s = borsaSingleton.getContenutoOrdinatoPerNome();
		Iterator<Attrezzo> it = s.iterator();
		assertEquals(1, s.size());
		assertTrue(it.hasNext());
		assertSame(borsaSingleton.getNome2attrezzi().get("piedediporco"), it.next());
		assertFalse(it.hasNext());
	}

	@Test
	void testGetContenutoOrdinatoPerPesoBorsaDoppia() {
		Borsa borsaDoppia = this.borsaDoppia(new Attrezzo("osso", 1), new Attrezzo("martello", 2));
		List<Attrezzo> l = borsaDoppia.getContenutoOrdinatoPerPeso();
		Iterator<Attrezzo> it = l.iterator();
		assertEquals(2, l.size());
		assertTrue(it.hasNext());
		assertSame(borsaDoppia.getNome2attrezzi().get("osso"), it.next());
		assertTrue(it.hasNext());
		assertSame(borsaDoppia.getNome2attrezzi().get("martello"), it.next());
		assertFalse(it.hasNext());
	}

	@Test
	void testGetSortedSetOrdinatoPerPesoBorsaDoppia() {
		Borsa borsaDoppia = this.borsaDoppia(new Attrezzo("martello", 2), new Attrezzo("metro", 1));
		SortedSet<Attrezzo> s = borsaDoppia.getSortedSetOrdinatoPerPeso();
		Iterator<Attrezzo> it = s.iterator();
		assertEquals(2, s.size());
		assertTrue(it.hasNext());
		assertSame(borsaDoppia.getNome2attrezzi().get("metro"), it.next());
		assertTrue(it.hasNext());
		assertSame(borsaDoppia.getNome2attrezzi().get("martello"), it.next());
		assertFalse(it.hasNext());
	}

	@Test
	void testGetContenutoOrdinatoPerNomeBorsaDoppia() {
		Borsa borsaDoppia = this.borsaDoppia(new Attrezzo("piedediporco", 3), new Attrezzo("trapano", 3));
		SortedSet<Attrezzo> s = borsaDoppia.getContenutoOrdinatoPerNome();
		Iterator<Attrezzo> it = s.iterator();
		assertEquals(2, s.size());
		assertTrue(it.hasNext());
		assertSame(borsaDoppia.getNome2attrezzi().get("piedediporco"), it.next());
		assertTrue(it.hasNext());
		assertSame(borsaDoppia.getNome2attrezzi().get("trapano"), it.next());
		assertFalse(it.hasNext());
	}

	@Test
	void testGetContenutoRaggruppatoPerPesoBorsaPiena() {
		Borsa borsaPiena = borsaPiena();
		Map<Integer, Set<Attrezzo>> m = borsaPiena.getContenutoRaggruppatoPerPeso();
		// ComparatoreAttrezziPerNome cmp = new ComparatoreAttrezziPerNome();
		Set<Attrezzo> set1 = new TreeSet<Attrezzo>();
		Set<Attrezzo> set2 = new TreeSet<Attrezzo>();
		Set<Attrezzo> set3 = new TreeSet<Attrezzo>();
		Set<Attrezzo> set4 = new TreeSet<Attrezzo>();
		assertTrue(set1.add(new Attrezzo("chiodo", 1)));
		assertTrue(set1.add(new Attrezzo("osso", 1)));
		assertTrue(set1.add(new Attrezzo("scudo", 1)));
		assertTrue(set1.add(new Attrezzo("spatola", 1)));
		assertTrue(set2.add(new Attrezzo("martello", 2)));
		assertTrue(set2.add(new Attrezzo("metro", 2)));
		assertTrue(set3.add(new Attrezzo("cofana", 3)));
		assertTrue(set3.add(new Attrezzo("spada", 3)));
		assertTrue(set3.add(new Attrezzo("trapano", 3)));
		assertTrue(set4.add(new Attrezzo("pala", 4)));
		assertEquals(4, m.size());
		assertNotNull(m.get(1));
		assertEquals(set1, m.get(1));
		assertEquals(set2, m.get(2));
		assertEquals(set3, m.get(3));
		assertEquals(set4, m.get(4));
		assertNull(m.get(5));
	}

	@Test
	void testGetContenutoRaggruppatoPerPesoBorsaVuota() {
		Borsa borsaVuota = borsaVuota();
		Map<Integer, Set<Attrezzo>> m = borsaVuota.getContenutoRaggruppatoPerPeso();
		assertEquals(0, m.size());
		assertNull(m.get(1));
	}

	@Test
	void testGetContenutoRaggruppatoPerPesoBorsaSingleton() {
		Attrezzo martello = new Attrezzo("martello", 2);
		Borsa borsaSingleton = borsaSingleton(martello);
		Map<Integer, Set<Attrezzo>> m = borsaSingleton.getContenutoRaggruppatoPerPeso();
		// ComparatoreAttrezziPerNome cmp = new ComparatoreAttrezziPerNome();
		Set<Attrezzo> set2 = new TreeSet<Attrezzo>();
		assertTrue(set2.add(martello));
		assertNotNull(m.get(2));
		assertEquals(1, m.size());
		assertEquals(set2, m.get(2));
		assertNull(m.get(1));
		assertNull(m.get(3));
	}

	@Test
	void testGetContenutoRaggruppatoPerPesoBorsaDoppia() {
		Attrezzo osso = new Attrezzo("osso", 1);
		Attrezzo trapano = new Attrezzo("trapano", 3);
		Borsa borsaDoppia = borsaDoppia(trapano, osso);
		Map<Integer, Set<Attrezzo>> m = borsaDoppia.getContenutoRaggruppatoPerPeso();
		// ComparatoreAttrezziPerNome cmp = new ComparatoreAttrezziPerNome();
		Set<Attrezzo> set1 = new TreeSet<Attrezzo>();
		Set<Attrezzo> set3 = new TreeSet<Attrezzo>();
		assertTrue(set1.add(osso));
		assertTrue(set3.add(trapano));
		assertNotNull(m.get(1));
		assertNotNull(m.get(3));
		assertEquals(2, m.size());
		assertEquals(set1, m.get(1));
		assertEquals(set3, m.get(3));
		assertNull(m.get(0));
		assertNull(m.get(2));
		assertNull(m.get(4));
	}
}
