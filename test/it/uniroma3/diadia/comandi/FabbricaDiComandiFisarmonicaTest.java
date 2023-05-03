package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;

public class FabbricaDiComandiFisarmonicaTest { // 17 / 17

	private FabbricaDiComandiFisarmonica fabbrica;
	private IO io;
	private Comando expected;

	@Before
	public void setUp() throws Exception {
		this.io = new IOConsole();
		this.fabbrica = new FabbricaDiComandiFisarmonica(this.io);
	}

	@Test
	public void testComandoVuoto() {
		this.expected = new ComandoNonValido();
		this.expected.setParametro(null);
		Comando current = this.fabbrica.costruisciComando("");
		assertEquals("Comando NON valido", this.expected.getNome());
		assertEquals("Comando NON valido", current.getNome());
		assertNull(this.expected.getParametro());
		assertNull(current.getParametro());
	}

	@Test
	public void testComandoNonValido() {
		this.expected = new ComandoNonValido();
		this.expected.setParametro(null);
		Comando current = this.fabbrica.costruisciComando("comando_invalido");
		assertEquals("Comando NON valido", this.expected.getNome());
		assertEquals("Comando NON valido", current.getNome());
		assertNull(this.expected.getParametro());
		assertNull(current.getParametro());
	}

	@Test
	public void testComandoVaiConDueParametri() {
		this.expected = new ComandoVai();
		this.expected.setParametro("ovest nord");
		Comando current = this.fabbrica.costruisciComando("vai ovest nord");
		assertNotEquals(this.expected.getNome(), current.getNome());
		assertNotEquals(this.expected.getParametro(), current.getParametro());
		assertEquals(this.expected.getNome(), "vai");
		assertEquals(this.expected.getParametro(), "ovest nord");
		assertEquals(current.getNome(), "Comando NON valido");
		assertNull(current.getParametro());
	}

	@Test
	public void testComandoVaiConParametro() {
		this.expected = new ComandoVai();
		this.expected.setParametro("nord");
		Comando current = this.fabbrica.costruisciComando("vai nord");
		assertEquals(this.expected.getNome(), current.getNome());
		assertEquals(this.expected.getParametro(), current.getParametro());
	}

	@Test
	public void testComandoVaiSenzaParametro() {
		this.expected = new ComandoVai();
		this.expected.setParametro(null);
		Comando current = this.fabbrica.costruisciComando("vai");
		assertNotEquals(this.expected.getNome(), current.getNome());
		assertEquals("vai", this.expected.getNome());
		assertEquals("Comando NON valido", current.getNome());
		assertNull(this.expected.getParametro());
		assertNull(current.getParametro());
	}

	@Test
	public void testComandoFineSenzaParametro() {
		this.expected = new ComandoFine();
		Comando current = this.fabbrica.costruisciComando("fine");
		assertEquals(this.expected.getNome(), current.getNome());
	}

	@Test
	public void testComandoGuardaNessunParametro() {
		this.expected = new ComandoGuarda();
		Comando current = this.fabbrica.costruisciComando("guarda");
		assertEquals(this.expected.getNome(), current.getNome());
		assertNull(this.expected.getParametro());
	}

	@Test
	public void testComandoGuardaConParametro() {
		this.expected = new ComandoGuarda();
		Comando current = this.fabbrica.costruisciComando("guarda qui");
		assertEquals(this.expected.getNome(), current.getNome());
		assertNull(this.expected.getParametro());
	}

	@Test
	public void testComandoFineConParametro() {
		this.expected = new ComandoFine();
		this.expected.setParametro("parametro inutile");
		Comando current = this.fabbrica.costruisciComando("fine");
		assertEquals(this.expected.getNome(), current.getNome());
		assertNull(this.expected.getParametro());
	}

	@Test
	public void testComandoAiutoSenzaParametro() {
		this.expected = new ComandoAiuto();
		this.expected.setParametro(null);
		Comando current = this.fabbrica.costruisciComando("aiuto");
		assertEquals(this.expected.getNome(), current.getNome());
		assertEquals(this.expected.getParametro(), current.getParametro());
	}

	@Test
	public void testComandoAiutoConParametro() {
		this.expected = new ComandoAiuto();
		this.expected.setParametro("parametro inutile");
		Comando current = this.fabbrica.costruisciComando("aiuto");
		assertEquals(this.expected.getNome(), current.getNome());
		assertEquals(this.expected.getParametro(), current.getParametro());
	}

	@Test
	public void testComandoPrendiSenzaParametro() {
		this.expected = new ComandoPrendi();
		this.expected.setParametro(null);
		Comando current = this.fabbrica.costruisciComando("prendi");
		assertNotEquals(this.expected.getNome(), current.getNome());
		assertEquals("prendi", this.expected.getNome());
		assertEquals("Comando NON valido", current.getNome());
		assertNull(this.expected.getParametro());
		assertNull(current.getParametro());
	}

	@Test
	public void testComandoPrendiConParametro() {
		this.expected = new ComandoPrendi();
		this.expected.setParametro("attrezzo");
		Comando current = this.fabbrica.costruisciComando("prendi attrezzo");
		assertEquals(this.expected.getNome(), current.getNome());
		assertEquals(this.expected.getParametro(), current.getParametro());
	}

	@Test
	public void testComandoPrendiDueParametri() {
		this.expected = new ComandoPrendi();
		this.expected.setParametro("attrezzo1 attrezzo2");
		Comando current = this.fabbrica.costruisciComando("prendi attrezzo1 attrezzo2");
		assertNotEquals(this.expected.getNome(), current.getNome());
		assertNotEquals(this.expected.getParametro(), current.getParametro());
		assertEquals(this.expected.getNome(), "prendi");
		assertEquals(this.expected.getParametro(), "attrezzo1 attrezzo2");
		assertEquals(current.getNome(), "Comando NON valido");
		assertNull(current.getParametro());
	}

	@Test
	public void testComandoPosaNessunParametro() {
		this.expected = new ComandoPosa();
		this.expected.setParametro(null);
		Comando current = this.fabbrica.costruisciComando("posa");
		assertNotEquals(this.expected.getNome(), current.getNome());
		assertEquals("posa", this.expected.getNome());
		assertEquals("Comando NON valido", current.getNome());
		assertNull(this.expected.getParametro());
		assertNull(current.getParametro());
	}

	@Test
	public void testComandoPosaConParametro() {
		this.expected = new ComandoPosa();
		this.expected.setParametro("attrezzo");
		Comando current = this.fabbrica.costruisciComando("posa attrezzo");
		assertEquals(this.expected.getNome(), current.getNome());
		assertEquals(this.expected.getParametro(), current.getParametro());
	}

	@Test
	public void testComandoPosaDueParametri() {
		this.expected = new ComandoPosa();
		this.expected.setParametro("attrezzo1 attrezzo2");
		Comando current = this.fabbrica.costruisciComando("posa attrezzo1 attrezzo2");
		assertNotEquals(this.expected.getNome(), current.getNome());
		assertNotEquals(this.expected.getParametro(), current.getParametro());
		assertEquals(this.expected.getNome(), "posa");
		assertEquals(this.expected.getParametro(), "attrezzo1 attrezzo2");
		assertEquals(current.getNome(), "Comando NON valido");
		assertNull(current.getParametro());
	}

}
