package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;

public class AbstractComandoTest {

	Comando comandoConcreto;
	AbstractComando comandoAstratto;
	Partita partita;

	@Before
	public void setUp() throws Exception {
		this.comandoConcreto = new ComandoVai(); // ad esempio...
		IO io = new IOConsole(new Scanner(System.in));
		this.comandoConcreto.setIo(io);
		this.comandoConcreto.setParametro("nord");
		this.comandoAstratto = new ComandoAiuto();
		this.comandoAstratto.setIo(io);
		this.partita = new Partita(Labirinto.newBuilder("labirinto2.txt").getLabirinto());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testConcreteComandoGetNome() {
		assertEquals("vai", this.comandoConcreto.getNome());
		assertEquals("aiuto", this.comandoAstratto.getNome());
		assertTrue(AbstractComando.isAbstract(this.comandoAstratto));
		assertFalse(AbstractComando.isAbstract(this.comandoConcreto));
	}

	@Test
	public void testConcreteComandoEsegui() {
		this.comandoAstratto.esegui(this.partita);
		this.comandoConcreto.esegui(this.partita);
		assertTrue(this.partita.isFinita());
	}

	@Test
	public void testConcreteComandoGetIO() {
		assertNotNull(this.comandoConcreto.getIo());
		assertNotNull(this.comandoAstratto.getIo());
		assertSame(this.comandoConcreto.getIo(), this.comandoAstratto.getIo());
	}

	@Test
	public void testComandoParametro() {
		assertNotNull(this.comandoConcreto.getParametro());
		assertNull(this.comandoAstratto.getParametro());
	}

}
