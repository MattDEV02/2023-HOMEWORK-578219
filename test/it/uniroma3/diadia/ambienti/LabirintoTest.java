package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/*
	classe di test atta a testare l'efficacia della "connessione" tra Stanze (e
	attrezzi) del Labirinto del gioco DiaDia. Tale connessione è stata definita
 	nel costruttore della classe this.labirinto. 
*/
class LabirintoTest {

	private Labirinto labirinto; // per questa tipologia di classe testing mi basta una singola
									// istanza della classe Labirinto.

	@BeforeEach // Denotes that the annotated method should be executed before each @Test
	void setUp() {
		this.labirinto = new Labirinto();
	}

	@Test
	void testLabirintoGetStanzaVincente() { // testo l'efficacia del metodo getStanzaVincente() della classe Labirinto
		assertEquals("La stanza vincente deve essere la biblioteca.", new Stanza("Biblioteca"),
				this.labirinto.getStanzaVincente());
	}

	@Test
	void testLabirintoGetStanzaVincenteNo() { // testo l'efficacia del metodo getStanzaVincente() della classe Labirinto
		assertNotEquals("La stanza vincente deve essere la biblioteca e non  l'Aula N11.", new Stanza("Aula N11"),
				this.labirinto.getStanzaVincente());
	}

	@Test
	void testLabirintoGetStanzaIngresso() { // testo l'efficacia del metodo getStanzaCorrente() della classe Labirinto
		assertEquals("La stanza d'ingresso deve essere l'atrio.", new Stanza("Atrio"),
				this.labirinto.getStanzaCorrente());
	}

	@Test
	void testLabirintoGetStanzaIngressoNo() { // testo l'efficacia del metodo getStanzaCorrente() della classe Labirinto
		assertNotEquals("La stanza d'ingresso deve essere l'atrio e non  l'Aula N10.", new Stanza("Aula N10"),
				this.labirinto.getStanzaCorrente());
	}

	@Test
	void testLabirintoGetStanzaVincenteTramiteStanzeAdiacenti() { // testo l'efficacia del metodo getStanzaVincente()
																	// della classe Labirinto
		assertEquals("La stanza vincente acceduta tramite sud-nord deve essere la biblioteca.",
				new Stanza("Biblioteca"),
				this.labirinto.getStanzaVincente().getStanzaAdiacente("sud").getStanzaAdiacente("nord"));
	}

	@Test
	void testLabirintoGetStanzaVincenteTramiteStanzeAdiacentiNo() { // testo l'efficacia del metodo getStanzaVincente()
																	// della classe Labirinto
		assertNotEquals("La stanza vincente acceduta tramite sud-nord-sud-est NON può deve essere la biblioteca.",
				new Stanza("Biblioteca"), this.labirinto.getStanzaVincente().getStanzaAdiacente("sud")
						.getStanzaAdiacente("nord").getStanzaAdiacente("sud").getStanzaAdiacente("est"));
	}

	@Test
	void testLabirintoGetStanzaCorrenteTramiteStanzeAdiacenti() { // testo l'efficacia del metodo getStanzaCorrente()
																	// della classe Labirinto
		assertEquals(new Stanza("Atrio"),
				this.labirinto.getStanzaCorrente().getStanzaAdiacente("sud").getStanzaAdiacente("nord"));
	}

	@Test
	void testLabirintoGetStanzaCorrenteTramiteStanzeAdiacentiNo() { // testo l'efficacia del metodo getStanzaCorrente()
																	// della classe Labirinto
		assertNotEquals("La stanza corrente acceduta tramite sud-nord-sud-est NON può deve essere la biblioteca.",
				new Stanza("Atrio"), this.labirinto.getStanzaCorrente().getStanzaAdiacente("sud")
						.getStanzaAdiacente("nord").getStanzaAdiacente("sud").getStanzaAdiacente("est"));
	}

	@Test
	void testLabirintoGetStanzaCorrenteStrumenti() {// testo l'efficacia del metodo getStanzaCorrente()
													// della classe Labirinto
		assertTrue(this.labirinto.getStanzaCorrente().hasAttrezzo("osso"),
				"La stanza atrio deve contenere l'attrezzo osso.");
	}

	@Test
	void testLabirintoGetStanzaVincenteStrumenti() {
		assertEquals("La stanza biblioteca NON deve aere attrezzi,", 0,
				this.labirinto.getStanzaVincente().getNumAttrezzi());
	}

	@Test
	void testLabirintoGetStanzaCorrenteStrumentiNo() {
		assertFalse(this.labirinto.getStanzaCorrente().hasAttrezzo("lanterna"),
				"La stanza atrio NON deve contenere l'attrezzo osso.");
	}

	@Test
	void testLabirintoGetStanzaVincenteStrumentiNo() {
		assertFalse(this.labirinto.getStanzaVincente().hasAttrezzo("osso"),
				"La stanza bibliotecs NON deve contenere l'attrezzo osso.");
	}

	@Test
	void testLabirintoGetStanzaAdiacenteDirezioneInesistente() {
		assertNull(this.labirinto.getStanzaCorrente().getStanzaAdiacente("direzione_inesistente"),
				"La direzione inesistente alla stanza corrente deve tornare null.");
	}

	@Test
	void testLabirintoGetStanzaVincenteDirezioneInesistente() {
		assertNull(this.labirinto.getStanzaVincente().getStanzaAdiacente("direzione_inesistente"),
				"La direzione inesistente alla stanza corrente deve tornare null.");
	}
}
