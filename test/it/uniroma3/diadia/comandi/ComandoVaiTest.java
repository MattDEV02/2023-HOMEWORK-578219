package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;

class ComandoVaiTest { // 3 / 3

	private Stanza stanza1;
	private Stanza stanza2;
	private Partita partita;
	private Comando comandoVai;

	@BeforeEach
	public void setUp() throws Exception {
		Labirinto labirinto = new LabirintoBuilder().addStanzaIniziale("Atrio").addAttrezzo("martello", 3)
				.addStanzaVincente("Biblioteca").addAdiacenza("Atrio", "Biblioteca", "nord").getLabirinto();
		this.partita = new Partita(labirinto);
		this.stanza1 = new Stanza("aula 1");
		this.stanza2 = new Stanza("aula 2");
		this.comandoVai = new ComandoVai();
		this.comandoVai.setIo(new IOConsole());
	}

	@Test
	public void testVaiNull() {
		this.partita.getLabirinto().setStanzaCorrente(this.stanza1);
		this.comandoVai.esegui(this.partita);
		assertEquals(this.stanza1, this.partita.getLabirinto().getStanzaIniziale());
	}

	@Test
	public void testVaiDirezioneEsistente() {
		this.partita.getLabirinto().setStanzaCorrente(this.stanza1);
		this.stanza1.impostaStanzaAdiacente("sud-ovest", this.stanza2);
		this.comandoVai.setParametro("sud-ovest");
		this.comandoVai.esegui(this.partita);
		assertEquals(this.stanza2, this.partita.getLabirinto().getStanzaIniziale());
	}

	@Test
	public void testVaiDirezioneInesistente() {
		this.partita.getLabirinto().setStanzaCorrente(this.stanza1);
		this.stanza1.impostaStanzaAdiacente("sud-ovest", this.stanza2);
		this.comandoVai.setParametro("in fondo a destra");
		this.comandoVai.esegui(this.partita);
		assertNotEquals(this.stanza2, this.partita.getLabirinto().getStanzaIniziale());
	}
}
