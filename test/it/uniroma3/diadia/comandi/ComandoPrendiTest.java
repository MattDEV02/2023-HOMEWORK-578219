package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendiTest { // 4 / 4

	private Partita partita;
	private Attrezzo attrezzo;
	private Comando comando;
	private IO io;

	@Before
	public void setUp() {
		this.partita = new Partita();
		this.attrezzo = new Attrezzo("martello", 2);
		this.comando = new ComandoPrendi();
		this.io = new IOConsole();
		this.comando.setIo(this.io);
	}

	@Test
	public void testAttrezzoPresente() {
		this.partita.getStanzaCorrente().addAttrezzo(attrezzo);
		this.comando.setParametro("martello");
		this.comando.esegui(this.partita);
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("martello"));
	}

	@Test
	public void testAttrezzoNonPresente() {
		this.comando.setParametro("martello");
		this.comando.esegui(this.partita);
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("martello"));
	}

	@Test
	public void testAttrezzoNull() {
		this.partita.getStanzaCorrente().addAttrezzo(null);
		this.comando.setParametro(null);
		this.comando.esegui(this.partita);
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo(null));
	}

	@Test
	public void testAttrezzoCheNonSiPuoPrendere() {
		for (int i = 0; i < 10; i++)
			this.partita.getStanzaCorrente().addAttrezzo(new Attrezzo("utensile_a_caso " + i, 1));
		this.comando.setParametro("utensile_a_caso 11");
		this.comando.esegui(this.partita);
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("incudine"));
	}

}
