package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class ComandoPosaTest { // 3 / 3

	private Partita partita;
	private Attrezzo attrezzo;
	private IO io;
	private Comando comando;

	@BeforeEach
	public void setUp() throws Exception {
		Labirinto labirinto = new LabirintoBuilder().addStanzaIniziale("Atrio").addAttrezzo("martello", 3)
				.addStanzaVincente("Biblioteca").addAdiacenza("Atrio", "Biblioteca", "nord").getLabirinto();
		this.partita = new Partita(labirinto);
		this.attrezzo = new Attrezzo("martello", 2);
		this.comando = new ComandoPosa();
		this.io = new IOConsole();
		this.comando.setIo(this.io);
	}

	@Test
	public void testAttrezzoPosato() {
		this.partita.getGiocatore().getBorsa().addAttrezzo(this.attrezzo);
		this.comando.setParametro("martello");
		this.comando.esegui(this.partita);
		assertTrue(this.partita.getLabirinto().getStanzaIniziale().hasAttrezzo("martello"));
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo("martello"));
	}

	@Test
	public void testAttrezzoPosatoNull() {
		this.comando.setParametro(null);
		this.comando.esegui(this.partita);
		assertFalse(this.partita.getLabirinto().getStanzaIniziale().hasAttrezzo("osso"));
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo("martello"));
	}

	@Test
	public void testTroppiAttrezzi() {
		for (int i = 0; i < 10; i++)
			this.partita.getLabirinto().getStanzaIniziale().addAttrezzo(new Attrezzo("utensile_a_caso " + i, 1));
		this.partita.getGiocatore().getBorsa().addAttrezzo(this.attrezzo);
		this.comando.setParametro("martello");
		this.comando.esegui(this.partita);
		assertTrue(this.partita.getLabirinto().getStanzaIniziale().hasAttrezzo("martello"));
	}
}
