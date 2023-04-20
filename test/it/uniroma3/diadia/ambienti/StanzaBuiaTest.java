package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBuiaTest {

	private StanzaBuia riferimentoStanzaVuota;
	private StanzaBuia riferimentoStanzaSingleton;
	private StanzaBuia riferimentoStanzaPiena;

	@Before
	private StanzaBuia stanza(String nomeAttrezzoRivelante, Attrezzo... attrezzi) {
		final int numeroAttrezzi = attrezzi.length;
		StanzaBuia stanzaOutput = new StanzaBuia("Aula N-x", nomeAttrezzoRivelante);
		for (int i = 0; i < numeroAttrezzi; i++) {
			stanzaOutput.addAttrezzo(attrezzi[i]);
		}
		return stanzaOutput;
	}

	@Before
	private StanzaBuia stanzaVuota() {
		return stanza("lanterna");
	}

	@Before
	private StanzaBuia stanzaSingleton(Attrezzo attrezzo) {
		return stanza("osso", attrezzo);
	}

	@Before
	private StanzaBuia stanzaPiena() {
		return stanza("piedediporco", new Attrezzo("osso", 1), new Attrezzo("martello", 2), new Attrezzo("chiodo", 1),
				new Attrezzo("trapano", 3), new Attrezzo("spatola", 1), new Attrezzo("cofana", 3),
				new Attrezzo("spada", 3), new Attrezzo("scudo", 1), new Attrezzo("pala", 4), new Attrezzo("metro", 2));
	}

	@BeforeEach // Denotes that the annotated method should be executed before each @Test
	void setUp() {
		this.riferimentoStanzaVuota = stanzaVuota();
		this.riferimentoStanzaSingleton = stanzaSingleton(new Attrezzo("osso", 1));
		this.riferimentoStanzaPiena = stanzaPiena();
	}

	@Test
	void testStanzaBuiaVuotaGetDescrizione() {
		assertEquals(this.riferimentoStanzaVuota.getDescrizione(), "Qui c'è un buio pesto.");
	}

	@Test
	void testStanzaBuiaSingletonGetDescrizione() {
		assertEquals(this.riferimentoStanzaSingleton.getDescrizione(),
				stanzaSingleton(new Attrezzo("osso", 1)).getDescrizione());
	}

	@Test
	void testStanzaBuiaPienaGetDescrizione() {
		assertEquals(this.riferimentoStanzaPiena.getDescrizione(), stanzaPiena().getDescrizione());
	}

}
