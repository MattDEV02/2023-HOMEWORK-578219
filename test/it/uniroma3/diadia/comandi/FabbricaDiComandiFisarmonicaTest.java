package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;

public class FabbricaDiComandiFisarmonicaTest {

	private FabbricaDiComandiFisarmonica fabbrica;
	private IO io;
	private Comando expected;

	@Before
	public void setUp() throws Exception {
		this.io = new IOConsole();
		this.fabbrica = new FabbricaDiComandiFisarmonica(this.io);
	}

	@Test
	public void testComandoNonValido() {
		this.expected = new ComandoNonValido();
		assertEquals(this.expected.getNome(), this.fabbrica.costruisciComando("comando_invalido").getNome());
	}

	@Test
	public void testComandoConParametro() {
		this.expected = new ComandoVai();
		this.expected.setParametro("nord");
		Comando current = this.fabbrica.costruisciComando("vai nord");
		assertEquals(this.expected.getNome(), current.getNome());
		assertEquals(this.expected.getParametro(), current.getParametro());
	}

	@Test
	public void testComandoSenzaParametro() {
		this.expected = new ComandoFine();
		assertEquals(this.expected.getNome(), this.fabbrica.costruisciComando("fine").getNome());
	}

}
