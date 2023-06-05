package it.uniroma3.diadia.personaggi;

import static org.junit.Assert.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class FakePersonaggioTest { // per testare la classe astratta
							// it.uniroma3.diadia.personaggi.AbstractPersonaggio

	private AbstractPersonaggio fakePersonaggio;
	private Labirinto labirinto;
	private Partita partita;

	@BeforeEach
	void setUp() throws Exception {
		this.fakePersonaggio = new FakePersonaggio("Falso", "Descrizione NON presente.");
		this.labirinto = Labirinto.newBuilder("labirinto1.txt").getLabirinto();
		this.partita = new Partita(this.labirinto);
	}

	@Test
	void testFakePersonaggio() {
		assertEquals("Falso", this.fakePersonaggio.getNome());
		assertEquals("Descrizione NON presente.", this.fakePersonaggio.getPresentazione());
		assertEquals("FakePersonaggio Falso, Descrizione NON presente.", this.fakePersonaggio.toString());
		assertEquals("class it.uniroma3.diadia.personaggi.FakePersonaggio", this.fakePersonaggio.getClass().toString());
		assertEquals(new FakePersonaggio("Falso", "Descrizione NON presente."), this.fakePersonaggio);
		assertNotSame(new FakePersonaggio("Falso", "Descrizione NON presente."), this.fakePersonaggio);
		this.fakePersonaggio.setNome("Falso2");
		this.fakePersonaggio.setPresentazione("Descrizione NON presente 2.");
		assertEquals("Falso2", this.fakePersonaggio.getNome());
		assertEquals("Descrizione NON presente 2.", this.fakePersonaggio.getPresentazione());
		assertEquals("FakePersonaggio Falso2, Descrizione NON presente 2.", this.fakePersonaggio.toString());
		assertEquals(new FakePersonaggio("Falso2", "Descrizione NON presente 2."), this.fakePersonaggio);
		assertNotSame(new FakePersonaggio("Falso2", "Descrizione NON presente 2."), this.fakePersonaggio);
	}

	@Test
	void testFakePersonaggioAgisci() {
		assertEquals("Fatto.", this.fakePersonaggio.agisci(this.partita));
	}

	@Test
	void testFakePersonaggioRiceviRegalo() {
		Attrezzo osso = new Attrezzo("osso", 1);
		assertEquals("Regalo " + osso.toString() + " ricevuto.", this.fakePersonaggio.riceviRegalo(osso, this.partita));
	}

	@Test
	void testFakePersonaggioHaSalutato() {
		assertFalse(this.fakePersonaggio.haSalutato());
		this.fakePersonaggio.setHaSalutato();
		assertTrue(this.fakePersonaggio.haSalutato());
	}

	@Test
	void testFakePersonaggioSaluta() {
		assertEquals("Ciao, io sono Falso. Descrizione NON presente.", this.fakePersonaggio.saluta());
		this.fakePersonaggio.setHaSalutato();
		assertEquals("Ciao, io sono Falso. Ci siamo gia' presentati!", this.fakePersonaggio.saluta());
	}
}
