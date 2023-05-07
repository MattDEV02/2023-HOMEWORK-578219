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
		assertTrue(this.partita.getLabirinto().getStanzaCorrente().hasAttrezzo("martello"));
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo("martello"));
	}

	@Test
	public void testAttrezzoPosatoNull() {
		this.comando.setParametro(null);
		this.comando.esegui(this.partita);
		assertFalse(this.partita.getLabirinto().getStanzaCorrente().hasAttrezzo("osso"));
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo("martello"));
	}

	@Test
	public void testTroppiAttrezzi() {
		int i;
		for (i = 0; i < 11; i++)
			this.partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("utensile_a_caso" + i, 1));
		this.partita.getGiocatore().getBorsa().addAttrezzo(this.attrezzo);
		this.comando.setParametro("martello");
		this.comando.esegui(this.partita);
		for (i = 0; i < 10; i++)
			assertTrue(this.partita.getGiocatore().getBorsa().hasAttrezzo("utensile_a_caso" + i));
		for (i = 0; i < 10; i++)
			assertFalse(this.partita.getLabirinto().getStanzaCorrente().hasAttrezzo("utensile_a_caso" + i));
		assertFalse(this.partita.getLabirinto().getStanzaCorrente().hasAttrezzo("utensile_a_caso" + 11));
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo("utensile_a_caso" + 11));
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo("martello"));
		assertTrue(this.partita.getLabirinto().getStanzaCorrente().hasAttrezzo("martello"));
		this.comando.setParametro("utensile_a_caso" + 11);
		this.comando.esegui(this.partita);
		assertFalse(this.partita.getLabirinto().getStanzaCorrente().hasAttrezzo("utensile_a_caso" + 11));
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo("utensile_a_caso" + 11));
		this.comando.setParametro("utensile_a_caso" + 1);
		this.comando.esegui(this.partita);
		assertTrue(this.partita.getLabirinto().getStanzaCorrente().hasAttrezzo("utensile_a_caso" + 1));
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo("utensile_a_caso" + 1));
	}
}
