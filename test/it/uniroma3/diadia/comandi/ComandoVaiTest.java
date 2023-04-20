package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

class ComandoVaiTest { // 3 / 3

	private Stanza stanza1;
	private Stanza stanza2;
	private Partita partita;
	private Comando comandoVai;

	@BeforeEach
	public void setUp() {
		this.stanza1 = new Stanza("aula 1");
		this.stanza2 = new Stanza("aula 2");
		this.comandoVai = new ComandoVai();
		this.partita = new Partita();
		this.comandoVai.setIo(new IOConsole());
	}

	@Test
	public void testVaiNull() {
		this.partita.setStanzaCorrente(this.stanza1);
		this.comandoVai.esegui(this.partita);
		assertEquals(this.stanza1, this.partita.getStanzaCorrente());
	}

	@Test
	public void testVaiDirezioneEsistente() {
		this.partita.setStanzaCorrente(this.stanza1);
		this.stanza1.impostaStanzaAdiacente("sud-ovest", this.stanza2);
		this.comandoVai.setParametro("sud-ovest");
		this.comandoVai.esegui(this.partita);
		assertEquals(this.stanza2, this.partita.getStanzaCorrente());
	}

	@Test
	public void testVaiDirezioneInesistente() {
		this.partita.setStanzaCorrente(this.stanza1);
		this.stanza1.impostaStanzaAdiacente("sud-ovest", this.stanza2);
		this.comandoVai.setParametro("in fondo a destra");
		this.comandoVai.esegui(this.partita);
		assertNotEquals(this.stanza2, this.partita.getStanzaCorrente());
	}
}
