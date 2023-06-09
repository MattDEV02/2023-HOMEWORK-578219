package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBloccata;

class ComandoVaiTest { // 4 / 4

	private Stanza stanza1;
	private Stanza stanza2;
	private Partita partita;
	private Comando comandoVai;
	private IO io;

	@BeforeEach
	public void setUp() throws Exception {
		Labirinto labirinto = new Labirinto.LabirintoBuilder().addStanzaIniziale("Atrio").addAttrezzo("martello", 3)
				.addStanzaVincente("Biblioteca").addAdiacenza("Atrio", "Biblioteca", "nord").getLabirinto();
		this.partita = new Partita(labirinto);
		this.stanza1 = new Stanza("aula 1");
		this.stanza2 = new Stanza("aula 2");
		this.comandoVai = new ComandoVai();
		Scanner scanner = new Scanner(System.in);
		this.io = new IOConsole(scanner);
		this.comandoVai.setIo(this.io);
	}

	@Test
	public void testVaiNull() {
		this.partita.getLabirinto().setStanzaCorrente(this.stanza1);
		this.comandoVai.esegui(this.partita);
		assertEquals(this.stanza1, this.partita.getLabirinto().getStanzaCorrente());
	}

	@Test
	public void testVaiDirezioneEsistente() {
		this.partita.getLabirinto().setStanzaCorrente(this.stanza1);
		this.stanza1.impostaStanzaAdiacente("sud", this.stanza2);
		this.comandoVai.setParametro("sud");
		this.comandoVai.esegui(this.partita);
		assertEquals(this.stanza2, this.partita.getLabirinto().getStanzaCorrente());
	}

	@Test
	public void testVaiDirezioneEsistenteBloccata() {
		this.stanza1 = new StanzaBloccata("aula 3", "sud", "piedediporco");
		this.partita.getLabirinto().setStanzaCorrente(this.stanza1);
		this.stanza1.impostaStanzaAdiacente("sud", this.stanza2);
		this.comandoVai.setParametro("sud");
		this.comandoVai.esegui(this.partita);
		assertEquals(this.stanza1, this.partita.getLabirinto().getStanzaCorrente());
	}

	@Test
	public void testVaiDirezioneInesistente() {
		this.partita.getLabirinto().setStanzaCorrente(this.stanza1);
		this.stanza1.impostaStanzaAdiacente("sud", this.stanza2);
		this.comandoVai.setParametro("in fondo a destra");
		this.comandoVai.esegui(this.partita);
		assertNotEquals(this.stanza2, this.partita.getLabirinto().getStanzaCorrente());
	}
}
