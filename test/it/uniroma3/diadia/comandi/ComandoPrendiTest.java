package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendiTest { // 4 / 4

	private Partita partita;
	private Attrezzo attrezzo;
	private Comando comando;
	private IO io;

	@Before
	public void setUp() throws Exception {
		Labirinto labirinto = new Labirinto.LabirintoBuilder().addStanzaIniziale("Atrio").addAttrezzo("martello", 3)
				.addStanzaVincente("Biblioteca").addAdiacenza("Atrio", "Biblioteca", "nord").getLabirinto();
		this.partita = new Partita(labirinto);
		this.attrezzo = new Attrezzo("martello", 2);
		this.comando = new ComandoPrendi();
		Scanner scanner = new Scanner(System.in);
		this.io = new IOConsole(scanner);
		this.comando.setIo(this.io);
	}

	@Test
	public void testAttrezzoPresente() {
		this.partita.getLabirinto().getStanzaCorrente().addAttrezzo(this.attrezzo);
		this.comando.setParametro("martello");
		this.comando.esegui(this.partita);
		assertFalse(this.partita.getLabirinto().getStanzaCorrente().hasAttrezzo("martello"));
		assertTrue(this.partita.getGiocatore().getBorsa().hasAttrezzo("martello"));
	}

	@Test
	public void testAttrezzoNonPresente() {
		this.comando.setParametro("martello");
		this.comando.esegui(this.partita);
		assertFalse(this.partita.getLabirinto().getStanzaCorrente().hasAttrezzo("martello"));
		assertTrue(this.partita.getGiocatore().getBorsa().hasAttrezzo("martello"));
	}

	@Test
	public void testAttrezzoNull() {
		this.partita.getLabirinto().getStanzaCorrente().addAttrezzo(null);
		this.comando.setParametro(null);
		this.comando.esegui(this.partita);
		assertFalse(this.partita.getLabirinto().getStanzaCorrente().hasAttrezzo(null));
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo(null));
	}

	@Test
	public void testAttrezzoCheNonSiPuoPrendere() {
		this.partita.getLabirinto().getStanzaCorrente().addAttrezzo(new Attrezzo("utensile_pesante", 20));
		assertTrue(this.partita.getLabirinto().getStanzaCorrente().hasAttrezzo("utensile_pesante"));
		this.comando.setParametro("utensile_pesante");
		this.comando.esegui(this.partita);
		assertTrue(this.partita.getLabirinto().getStanzaCorrente().hasAttrezzo("utensile_pesante"));
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo("utensile_pesante"));
	}

}
