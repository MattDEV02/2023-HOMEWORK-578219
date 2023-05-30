package it.uniroma3.diadia;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.io.FileNotFoundException;
import java.io.StringReader;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class CaricatoreLabirintoTest {

	private String monolocale;
	private String bilocale;
	private String Labirinto1NomeFile;

	private CaricatoreLabirinto caricatoreLabirinto;

	@Before
	public void setUp() throws Exception {
		this.monolocale = "Stanze:biblioteca\n" + "Magica:\n" + "Buia:\n" + "Bloccata:\n" + "Inizio:biblioteca\n"
				+ "Vincente:biblioteca\n" + "Mago:\n" + "Cane:\n" + "Strega:\n" + "Attrezzi:\n" + "Uscite:\n";
		this.bilocale = "Stanze:N12,N11\n" + "Magica:\n" + "Buia:\n" + "Bloccata:\n" + "Inizio:N12\n" + "Vincente:N11\n"
				+ "Mago:\n" + "Cane:\n" + "Strega:\n" + "Attrezzi:martello 3 N12\n" + "Uscite:\n";
		this.Labirinto1NomeFile = "C:\\Users\\matte\\Documents\\Università\\Secondo Anno\\2 semestre\\Programmazione orientata agli oggetti\\workspace\\diadia_HW\\labirinto1.txt";
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMonolocale() throws FormatoFileNonValidoException, FileNotFoundException {
		this.caricatoreLabirinto = new CaricatoreLabirinto(new StringReader(this.monolocale));
		this.caricatoreLabirinto.carica();
		assertSame(this.caricatoreLabirinto.getStanzaIniziale(), this.caricatoreLabirinto.getStanzaVincente());
	}

	@Test
	public void testBilocale() throws FormatoFileNonValidoException, FileNotFoundException {
		this.caricatoreLabirinto = new CaricatoreLabirinto(new StringReader(this.bilocale));
		this.caricatoreLabirinto.carica();
		assertEquals("N12", this.caricatoreLabirinto.getStanzaIniziale().getNome());
		assertEquals("N11", this.caricatoreLabirinto.getStanzaVincente().getNome());
	}

	@Test
	public void testLabirinto1NomeFileAttrezzo() throws FormatoFileNonValidoException, FileNotFoundException {
		this.caricatoreLabirinto = new CaricatoreLabirinto(this.Labirinto1NomeFile);
		this.caricatoreLabirinto.carica();
		assertEquals("biblioteca", this.caricatoreLabirinto.getStanzaIniziale().getNome());
		assertEquals("N11", this.caricatoreLabirinto.getStanzaVincente().getNome());
		Attrezzo expected = new Attrezzo("martello", 10);
		assertEquals(expected, this.caricatoreLabirinto.getStanzaIniziale().getAttrezzo("martello"));
	}
}
