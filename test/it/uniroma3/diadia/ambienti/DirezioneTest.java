package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

public class DirezioneTest {

	@Test
	public void testOrdinal() {
		assertEquals(0, Direzione.nord.ordinal());
		assertEquals(1, Direzione.sud.ordinal());
		assertEquals(2, Direzione.est.ordinal());
		assertEquals(3, Direzione.ovest.ordinal());
	}

	@Test
	public void testGetDeclaringClass() {
		assertSame(Direzione.class, Direzione.nord.getDeclaringClass());
		assertNotEquals(Direzione.class, Direzione.nord.getClass());
		assertSame(Direzione.class, Direzione.sud.getDeclaringClass());
		assertNotEquals(Direzione.class, Direzione.sud.getClass());
		assertSame(Direzione.class, Direzione.est.getDeclaringClass());
		assertNotEquals(Direzione.class, Direzione.est.getClass());
		assertSame(Direzione.class, Direzione.ovest.getDeclaringClass());
		assertNotEquals(Direzione.class, Direzione.ovest.getClass());
		assertNotEquals(Direzione.est.getClass(), Direzione.nord.getClass());
		assertNotEquals(Direzione.ovest.getClass(), Direzione.nord.getClass());
		assertNotEquals(Direzione.sud.getClass(), Direzione.nord.getClass());
		assertNotEquals(Direzione.est.getClass(), Direzione.sud.getClass());
		assertNotEquals(Direzione.ovest.getClass(), Direzione.sud.getClass());
		assertNotEquals(Direzione.nord.getClass(), Direzione.sud.getClass());
		assertNotEquals(Direzione.nord.getClass(), Direzione.est.getClass());
		assertNotEquals(Direzione.ovest.getClass(), Direzione.est.getClass());
		assertNotEquals(Direzione.sud.getClass(), Direzione.est.getClass());
		assertNotEquals(Direzione.nord.getClass(), Direzione.ovest.getClass());
		assertNotEquals(Direzione.est.getClass(), Direzione.ovest.getClass());
		assertNotEquals(Direzione.sud.getClass(), Direzione.ovest.getClass());
	}

	@Test
	public void testTuttiSingleton() {
		assertSame(Direzione.nord, Direzione.valueOf("nord"));
		final Direzione singleton = Direzione.valueOf("nord");
		assertSame(singleton, Direzione.nord);
		assertNotSame(Direzione.est, Direzione.nord);
		assertNotSame(Direzione.ovest, Direzione.nord);
		assertNotSame(Direzione.sud, Direzione.nord);
		// similarmente per le altre direzioni
	}

	@Test
	public void testCriterioDiEquivalenza() {
		assertEquals(Direzione.nord, Direzione.nord);
		assertNotEquals(Direzione.nord, Direzione.est);
		assertNotEquals(Direzione.nord, Direzione.sud);
		// similarmente per le altre direzioni
	}

	@Test
	public void testCompareTo() {
		assertTrue(Direzione.nord.compareTo(Direzione.est) < 0);
		assertFalse(Direzione.est.compareTo(Direzione.sud) < 0);
		assertTrue(Direzione.sud.compareTo(Direzione.ovest) < 0);
		assertTrue(Direzione.ovest.compareTo(Direzione.nord) > 0);
	}

	@Test
	public void testToStringAndName() {
		assertEquals("nord", Direzione.nord.toString());
		assertEquals("nord", Direzione.nord.name());
		assertSame(Direzione.nord.toString(), Direzione.nord.name());
	}

	@Test
	public void testValues() {
		final Direzione[] expected = { Direzione.nord, Direzione.sud, Direzione.est, Direzione.ovest };
		assertArrayEquals(expected, Direzione.values());
	}
}