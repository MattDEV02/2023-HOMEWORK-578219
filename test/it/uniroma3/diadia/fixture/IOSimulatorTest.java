package it.uniroma3.diadia.fixture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOSimulator;

class IOSimulatorTest { // 5 / 5

	private IOSimulator io;
	private List<String> righeDaLeggere;

	@Test
	public void testComandoVuoto() {
		this.righeDaLeggere = new ArrayList<String>();
		this.righeDaLeggere.add("");
		assertEquals("", new IOSimulator(this.righeDaLeggere).leggiRiga());
	}

	@Test
	public void testComandoNullo() {
		assertNull(new IOSimulator(null).leggiRiga());
	}

	@Test
	public void testUnSoloComando() {
		this.righeDaLeggere = new ArrayList<String>();
		this.righeDaLeggere.add("fine");
		assertEquals("fine", new IOSimulator(this.righeDaLeggere).leggiRiga());
	}

	@Test
	public void testDueComandi() {
		this.righeDaLeggere = new ArrayList<String>();
		this.righeDaLeggere.add("vai sud");
		this.righeDaLeggere.add("fine");
		this.io = new IOSimulator(this.righeDaLeggere);
		assertEquals("vai sud", io.leggiRiga());
		assertEquals("fine", io.leggiRiga());
	}

	@Test
	public void testTantiComandi() {
		this.righeDaLeggere = new ArrayList<String>();
		this.righeDaLeggere.add("vai sud");
		this.righeDaLeggere.add("fine");
		this.righeDaLeggere.add("guarda");
		this.righeDaLeggere.add("prendi chiave");
		this.righeDaLeggere.add("aiuto");
		this.io = new IOSimulator(this.righeDaLeggere);
		assertEquals("vai sud", io.leggiRiga());
		assertEquals("fine", io.leggiRiga());
		assertEquals("guarda", io.leggiRiga());
		assertEquals("prendi chiave", io.leggiRiga());
		assertEquals("aiuto", io.leggiRiga());
	}
}