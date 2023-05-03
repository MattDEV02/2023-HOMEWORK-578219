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
	public void setUp() throws Exception {
		this.labirinto = new LabirintoBuilder().addStanzaIniziale("Atrio").addAttrezzo("osso", 1)
				.addStanzaVincente("Biblioteca").addStanzaMagica("Aula N11", 1).addAttrezzo("piedediporco", 3)
				.addStanzaBloccata("Aula N10", "est", "piedediporco").addAttrezzo("lanterna", 2)
				.addStanzaBuia("Laboratorio", "lanterna").addAdiacenza("Atrio", "Biblioteca", "nord")
				.addAdiacenza("Atrio", "Aula N11", "est").addAdiacenza("Atrio", "Aula N10", "sud")
				.addAdiacenza("Atrio", "Laboratorio", "ovest").addAdiacenza("Aula N11", "Laboratorio", "est")
				.addAdiacenza("Aula N11", "Atrio", "ovest").addAdiacenza("Aula N10", "Atrio", "nord")
				.addAdiacenza("Aula N10", "Aula N11", "est").addAdiacenza("Aula N10", "Laboratorio", "ovest")
				.addAdiacenza("Laboratorio", "Atrio", "est").addAdiacenza("Laboratorio", "Aula N11", "ovest")
				.addAdiacenza("Biblioteca", "Atrio", "sud").getLabirinto();
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
				this.labirinto.getStanzaIniziale());
	}

	@Test
	void testLabirintoGetStanzaIngressoNo() { // testo l'efficacia del metodo getStanzaCorrente() della classe Labirinto
		assertNotEquals("La stanza d'ingresso deve essere l'atrio e non  l'Aula N10.", new Stanza("Aula N10"),
				this.labirinto.getStanzaIniziale());
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
						.getStanzaAdiacente("nord").getStanzaAdiacente("sud"));
	}

	@Test
	void testLabirintoGetStanzaCorrenteTramiteStanzeAdiacenti() { // testo l'efficacia del metodo getStanzaCorrente()
																	// della classe Labirinto
		assertEquals(new Stanza("Atrio"),
				this.labirinto.getStanzaIniziale().getStanzaAdiacente("sud").getStanzaAdiacente("nord"));
	}

	@Test
	void testLabirintoGetStanzaCorrenteTramiteStanzeAdiacentiNo() { // testo l'efficacia del metodo getStanzaCorrente()
																	// della classe Labirinto
		assertNotEquals("La stanza corrente acceduta tramite sud-nord-sud-est NON può deve essere la biblioteca.",
				new Stanza("Atrio"), this.labirinto.getStanzaIniziale().getStanzaAdiacente("sud")
						.getStanzaAdiacente("nord").getStanzaAdiacente("sud").getStanzaAdiacente("est"));
	}

	@Test
	void testLabirintoGetStanzaCorrenteStrumenti() {// testo l'efficacia del metodo getStanzaCorrente()
													// della classe Labirinto
		assertTrue(this.labirinto.getStanzaIniziale().hasAttrezzo("osso"),
				"La stanza atrio deve contenere l'attrezzo osso.");
	}

	@Test
	void testLabirintoGetStanzaVincenteStrumenti() {
		assertEquals("La stanza biblioteca NON deve aere attrezzi,", 0,
				this.labirinto.getStanzaVincente().getNumAttrezzi());
	}

	@Test
	void testLabirintoGetStanzaCorrenteStrumentiNo() {
		assertFalse(this.labirinto.getStanzaIniziale().hasAttrezzo("lanterna"),
				"La stanza atrio NON deve contenere l'attrezzo osso.");
	}

	@Test
	void testLabirintoGetStanzaVincenteStrumentiNo() {
		assertFalse(this.labirinto.getStanzaVincente().hasAttrezzo("osso"),
				"La stanza bibliotecs NON deve contenere l'attrezzo osso.");
	}

	@Test
	void testLabirintoGetStanzaAdiacenteDirezioneInesistente() {
		assertNull(this.labirinto.getStanzaIniziale().getStanzaAdiacente("direzione_inesistente"),
				"La direzione inesistente alla stanza corrente deve tornare null.");
	}

	@Test
	void testLabirintoGetStanzaVincenteDirezioneInesistente() {
		assertNull(this.labirinto.getStanzaVincente().getStanzaAdiacente("direzione_inesistente"),
				"La direzione inesistente alla stanza corrente deve tornare null.");
	}
}
