package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBloccataTest { // 6 / 6

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

}
