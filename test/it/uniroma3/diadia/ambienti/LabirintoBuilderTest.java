package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilderTest {

	private LabirintoBuilder labirintoBuilder;
	private String nomeStanzaIniziale = "Atrio";
	private String nomeStanzaVincente = "Uscita";

	@Before
	public void setUp() throws Exception {
		labirintoBuilder = new LabirintoBuilder();
	}

	@Test
	public void testMonolocale() {
		Labirinto monolocale = this.labirintoBuilder.addStanzaIniziale(nomeStanzaIniziale)
				.addStanzaVincente(nomeStanzaIniziale).getLabirinto();
		assertEquals(nomeStanzaIniziale, monolocale.getStanzaCorrente().getNome());
		assertEquals(nomeStanzaIniziale, monolocale.getStanzaVincente().getNome());
	}

	@Test
	public void testMonolocaleConAttrezzo() {
		Labirinto monolocale = this.labirintoBuilder.addStanzaIniziale(nomeStanzaIniziale).addAttrezzo("spada", 1)
				.addStanzaVincente(nomeStanzaIniziale).addAttrezzo("spadina", 3).getLabirinto();
		assertEquals(nomeStanzaIniziale, monolocale.getStanzaCorrente().getNome());
		assertEquals(nomeStanzaIniziale, monolocale.getStanzaVincente().getNome());
		assertEquals("spada", monolocale.getStanzaCorrente().getAttrezzo("spada").getNome());
		assertEquals("spadina", monolocale.getStanzaVincente().getAttrezzo("spadina").getNome());
	}

	@Test
	public void testMonolocaleConAttrezzoSingoloDuplicato() {
		Labirinto monolocale = this.labirintoBuilder.addStanzaIniziale(nomeStanzaIniziale).addAttrezzo("spada", 1)
				.addAttrezzo("spada", 1).getLabirinto();
		int size = monolocale.getStanzaCorrente().getAttrezzi().size();
		assertTrue(size == 1);
		List<Attrezzo> l = new ArrayList<Attrezzo>();
		l.addAll(monolocale.getStanzaCorrente().getAttrezzi());
		assertEquals(Arrays.asList(new Attrezzo("spada", 1)), l);
	}

	@Test
	public void testBilocale() {
		Labirinto bilocale = this.labirintoBuilder.addStanzaIniziale(nomeStanzaIniziale)
				.addStanzaVincente(nomeStanzaVincente).addAdiacenza(nomeStanzaIniziale, nomeStanzaVincente, "nord")
				.addAdiacenza(nomeStanzaVincente, nomeStanzaIniziale, "sud").getLabirinto();
		assertEquals(bilocale.getStanzaVincente(), bilocale.getStanzaCorrente().getStanzaAdiacente("nord"));
		List<String> l1 = new ArrayList<String>();
		l1.addAll(bilocale.getStanzaCorrente().getDirezioni());
		assertEquals(Collections.singletonList("nord"), l1);
		List<String> l2 = new ArrayList<String>();
		l2.addAll(bilocale.getStanzaVincente().getDirezioni());
		assertEquals(Collections.singletonList("sud"), l2);
	}

	@Test
	public void testTrilocale() {
		Labirinto trilocale = this.labirintoBuilder.addStanzaIniziale(nomeStanzaIniziale).addAttrezzo("sedia", 1)
				.addStanza("biblioteca").addAdiacenza(nomeStanzaIniziale, "biblioteca", "sud")
				.addAdiacenza("biblioteca", nomeStanzaIniziale, "nord").addAttrezzo("libro antico", 5)
				.addStanzaVincente(nomeStanzaVincente).addAdiacenza("biblioteca", nomeStanzaVincente, "est")
				.addAdiacenza(nomeStanzaVincente, "biblioteca", "ovest").getLabirinto();
		assertEquals(nomeStanzaIniziale, trilocale.getStanzaCorrente().getNome());
		assertEquals(nomeStanzaVincente, trilocale.getStanzaVincente().getNome());
		assertEquals("biblioteca", trilocale.getStanzaCorrente().getStanzaAdiacente("sud").getNome());
	}

	@Test
	public void testTrilocaleConStanzaDuplicata() {
		labirintoBuilder.addStanzaIniziale(nomeStanzaIniziale).addStanza("stanza generica").addStanza("stanza generica")
				.addAdiacenza(nomeStanzaIniziale, "stanza generica", "nord").getLabirinto();
		assertTrue(labirintoBuilder.getNome2stanza().size() <= 2);
	}

	@Test
	public void testPiuDiQuattroAdiacenti() {
		Labirinto maze = this.labirintoBuilder.addStanzaIniziale(nomeStanzaIniziale).addStanza("stanza 1")
				.addStanza("stanza 2").addStanza("stanza 3").addStanza("stanza 4").addStanza("stanza 5")
				.addAdiacenza(nomeStanzaIniziale, "stanza 1", "nord")
				.addAdiacenza(nomeStanzaIniziale, "stanza 2", "ovest")
				.addAdiacenza(nomeStanzaIniziale, "stanza 3", "sud").addAdiacenza(nomeStanzaIniziale, "stanza 4", "est")
				.addAdiacenza(nomeStanzaIniziale, "stanza 5", "nord-est") // non dovrebbe essere aggiunta
				.getLabirinto();
		Stanza StanzaTest = new Stanza("stanza 5");
		assertNull(maze.getStanzaCorrente().getStanzaAdiacente("nord-est"));
		assertTrue(maze.getStanzaCorrente().getStanzeAdiacenti().size() <= 4);
		assertTrue(!maze.getStanzaCorrente().getStanzeAdiacenti().contains(StanzaTest));
		Map<String, Stanza> mappa = new HashMap<>();
		mappa.put("nord", new Stanza("stanza 1"));
		mappa.put("ovest", new Stanza("stanza 2"));
		mappa.put("sud", new Stanza("stanza 3"));
		mappa.put("est", new Stanza("stanza 4"));
		assertEquals(mappa, maze.getStanzaCorrente().getDirezioni2stanze());
	}

	@Test
	public void testImpostaStanzaInizialeCambiandola() {
		Labirinto maze = this.labirintoBuilder.addStanzaIniziale(this.nomeStanzaIniziale).addStanza("nuova iniziale")
				.addStanzaIniziale("nuova iniziale").getLabirinto();
		assertEquals("nuova iniziale", maze.getStanzaCorrente().getNome());
	}

	@Test
	public void testAggiuntaAttrezziAStanze_Iniziale() {
		String nomeAttrezzo = "attrezzo";
		int peso = 1;
		Labirinto maze = this.labirintoBuilder.addStanzaIniziale(this.nomeStanzaIniziale)
				.addAttrezzo(nomeAttrezzo, peso).getLabirinto();
		Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
		assertEquals(attrezzo, maze.getStanzaCorrente().getAttrezzo(nomeAttrezzo));
	}

	@Test
	public void testAggiuntaAttrezziAStanze_AppenaAggiunte() {
		String nomeAttrezzo = "attrezzo";
		int peso = 1;
		String nomeStanza = "stanza 1";
		labirintoBuilder.addStanzaIniziale(nomeStanzaIniziale).addStanza(nomeStanza).addAttrezzo(nomeAttrezzo, peso)
				.getLabirinto();
		assertTrue(this.labirintoBuilder.getNome2stanza().get(nomeStanza).getAttrezzi()
				.contains(new Attrezzo(nomeAttrezzo, peso)));
		assertEquals(new Attrezzo(nomeAttrezzo, peso),
				this.labirintoBuilder.getNome2stanza().get(nomeStanza).getAttrezzo(nomeAttrezzo));
	}

	@Test
	public void testAggiuntaAttrezzoAStanze_AppenaAggiunteMultiple() {
		String nomeAttrezzo = "attrezzo";
		int peso = 1;
		String nomeStanza = "stanza 1";
		this.labirintoBuilder.addStanzaIniziale(nomeStanzaIniziale).addStanza(nomeStanza)
				.addAttrezzo(nomeAttrezzo, peso).getLabirinto();
		Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
		List<Attrezzo> attrezzi = new ArrayList<Attrezzo>();
		attrezzi.addAll(labirintoBuilder.getNome2stanza().get(nomeStanza).getAttrezzi());
		assertEquals(attrezzo, attrezzi.get(attrezzi.indexOf(attrezzo)));
	}

	@Test
	public void testAggiuntaAttrezzoAStanze_MoltepliciAttrezziStessaStanza() {
		String nomeAttrezzo1 = "attrezzo 1";
		String nomeAttrezzo2 = "attrezzo 2";
		int peso1 = 1;
		int peso2 = 2;
		String nomeStanza1 = "Stanza 1";
		this.labirintoBuilder.addStanza(nomeStanza1).addAttrezzo(nomeAttrezzo1, peso1).addAttrezzo(nomeAttrezzo2,
				peso2);
		Map<String, Stanza> listaStanze = this.labirintoBuilder.getNome2stanza();
		assertEquals(new Attrezzo(nomeAttrezzo2, peso2), listaStanze.get(nomeStanza1).getAttrezzo(nomeAttrezzo2));
		assertEquals(new Attrezzo(nomeAttrezzo1, peso1), listaStanze.get(nomeStanza1).getAttrezzo(nomeAttrezzo1));
	}

	@Test // verifico che gli attrezzi vengano aggiunti all'ultima stanza aggiunta
	public void testAggiuntaAttrezzoAStanze_AttrezzoAggiuntoAllaSecondaStanza() {
		String nomeAttrezzo1 = "attrezzo 1";
		String nomeAttrezzo2 = "attrezzo 2";
		int peso1 = 1;
		int peso2 = 2;
		String nomeStanza1 = "Stanza 1";
		String nomeStanza2 = "Stanza 2";
		this.labirintoBuilder.addStanza(nomeStanza1).addStanza(nomeStanza2).addAttrezzo(nomeAttrezzo1, peso1)
				.addAttrezzo(nomeAttrezzo2, peso2);
		Map<String, Stanza> listaStanze = this.labirintoBuilder.getNome2stanza();
		assertEquals(new Attrezzo(nomeAttrezzo1, peso1), listaStanze.get(nomeStanza2).getAttrezzo(nomeAttrezzo1));
		assertEquals(new Attrezzo(nomeAttrezzo2, peso2), listaStanze.get(nomeStanza2).getAttrezzo(nomeAttrezzo2));
	}

	@Test
	public void testAggiuntaAttrezzoAStanze_PrimoAttrezzoInUnaStanzaSecondoAttrezzoSecondaStanza() {
		String nomeAttrezzo1 = "attrezzo 1";
		String nomeAttrezzo2 = "attrezzo 2";
		int peso1 = 1;
		int peso2 = 2;
		String nomeStanza1 = "Stanza 1";
		String nomeStanza2 = "Stanza 2";
		this.labirintoBuilder.addStanza(nomeStanza1).addAttrezzo(nomeAttrezzo1, peso1).addStanza(nomeStanza2)
				.addAttrezzo(nomeAttrezzo2, peso2);
		Map<String, Stanza> listaStanze = this.labirintoBuilder.getNome2stanza();
		assertEquals(new Attrezzo(nomeAttrezzo1, peso1), listaStanze.get(nomeStanza1).getAttrezzo(nomeAttrezzo1));
		assertEquals(new Attrezzo(nomeAttrezzo2, peso2), listaStanze.get(nomeStanza2).getAttrezzo(nomeAttrezzo2));
	}

	@Test
	public void testLabirintoConStanzaMagica() {
		int sogliaMagica = 1;
		String nomeStanzaMagica = "Stanza Magica";
		this.labirintoBuilder.addStanzaMagica(nomeStanzaMagica, sogliaMagica);
		StanzaMagica stanzaMagica = (StanzaMagica) this.labirintoBuilder.getNome2stanza().get(nomeStanzaMagica);
		assertTrue(stanzaMagica.isMagica());
	}

	@Test
	public void testLabirintoConStanzaMagica_AggiuntaElementoOltreSogliaMagica() {
		String nomeAttrezzo1 = "attrezzo 1";
		String nomeAttrezzo2 = "attrezzo 2";
		String nomeAttrezzo2Inv = "2 ozzertta";
		int sogliaMagica = 1;
		int peso1 = 1;
		int peso2 = 2;
		int peso2_x2 = peso2 * 2;
		String nomeStanzaMagica = "Stanza Magica";
		this.labirintoBuilder.addStanzaMagica(nomeStanzaMagica, sogliaMagica).addAttrezzo(nomeAttrezzo1, peso1)
				.addAttrezzo(nomeAttrezzo2, peso2);
		Map<String, Stanza> listaStanze = this.labirintoBuilder.getNome2stanza();
		assertEquals(new Attrezzo(nomeAttrezzo2Inv, peso2_x2),
				listaStanze.get(nomeStanzaMagica).getAttrezzo(nomeAttrezzo2Inv));
		assertEquals(new Attrezzo(nomeAttrezzo1, peso1), listaStanze.get(nomeStanzaMagica).getAttrezzo(nomeAttrezzo1));
	}

	@Test
	public void testLabirintoConStanzaBloccata_ConPassepartout() {
		this.labirintoBuilder.addStanzaIniziale(nomeStanzaIniziale)
				.addStanzaBloccata("stanza bloccata", "nord", "chiave").addAttrezzo("chiave", 1)
				.addAdiacenza(nomeStanzaIniziale, "stanza bloccata", "nord")
				.addAdiacenza("stanza bloccata", nomeStanzaIniziale, "sud").addStanzaVincente(nomeStanzaVincente)
				.addAdiacenza("stanza bloccata", nomeStanzaVincente, "nord")
				.addAdiacenza(nomeStanzaVincente, "stanza bloccata", "sud");
		Stanza stanzaVincente = new Stanza(nomeStanzaVincente);
		// Asserisce che in presenza di passepartout, invocato il metodo
		// getStanzaAdiacente(), la stanza bloccata ritorna la corretta adiacenza
		assertEquals(stanzaVincente,
				labirintoBuilder.getNome2stanza().get("stanza bloccata").getStanzaAdiacente("nord"));
	}

	@Test
	public void testLabirintoConStanzaBloccata_SenzaPassepartout() {
		this.labirintoBuilder.addStanzaIniziale(nomeStanzaIniziale)
				.addStanzaBloccata("stanza bloccata", "nord", "chiave")
				.addAdiacenza(nomeStanzaIniziale, "stanza bloccata", "nord")
				.addAdiacenza("stanza bloccata", nomeStanzaIniziale, "sud").addStanzaVincente(nomeStanzaVincente)
				.addAdiacenza("stanza bloccata", nomeStanzaVincente, "nord")
				.addAdiacenza(nomeStanzaVincente, "stanza bloccata", "sud");
		Stanza stanzaBloccata = new StanzaBloccata("stanza bloccata", "nord", "chiave");
		// Asserisce che in caso di mancanza di passepartout, invocato il metodo
		// getStanzaAdiacente(), la stanza bloccata ritorna se stessa
		assertEquals(stanzaBloccata,
				labirintoBuilder.getNome2stanza().get("stanza bloccata").getStanzaAdiacente("nord"));
	}

	@Test
	public void testLabirintoCompletoConTutteLeStanze() {
		Labirinto labirintoCompleto = this.labirintoBuilder.addStanzaIniziale(nomeStanzaIniziale)
				.addStanzaVincente(nomeStanzaVincente).addStanza("corridoio").addAttrezzo("chiave", 1)
				.addAttrezzo("lanterna", 1).addStanzaBloccata("corridoio bloccato", "nord", "chiave")
				.addStanzaMagica("stanza magica", 1).addStanzaBuia("stanza buia", "lanterna").addStanza("Aula 1")
				.addAdiacenza(nomeStanzaIniziale, "corridoio", "nord")
				.addAdiacenza("corridoio", nomeStanzaIniziale, "sud")
				.addAdiacenza("corridoio", "corridoio bloccato", "nord")
				.addAdiacenza("corridoio bloccato", "corridoio", "sud")
				.addAdiacenza("corridoio bloccato", "Aula 1", "nord")
				.addAdiacenza("Aula 1", "corridoio bloccato", "sud").addAdiacenza("Aula 1", nomeStanzaVincente, "nord")
				.addAdiacenza(nomeStanzaVincente, "Aula 1", "sud").addAdiacenza("corridoio", "stanza magica", "est")
				.addAdiacenza("stanza magica", "corridoio", "ovest").addAdiacenza("corridoio", "stanza buia", "ovest")
				.addAdiacenza("stanza buia", "corridoio", "est").getLabirinto();
		assertEquals(nomeStanzaIniziale, labirintoCompleto.getStanzaCorrente().getNome());
		assertEquals(nomeStanzaVincente, labirintoCompleto.getStanzaVincente().getNome());
		Stanza corridoio = labirintoCompleto.getStanzaCorrente().getStanzaAdiacente("nord");
		assertEquals("corridoio", corridoio.getNome());
		assertTrue(corridoio.getDirezioni().containsAll(Arrays.asList("ovest", "est", "nord", "sud")));
		Map<String, Stanza> mapAdiacenti = new HashMap<>();
		mapAdiacenti.put("nord", new Stanza("corridoio bloccato"));
		mapAdiacenti.put("sud", new Stanza(nomeStanzaIniziale));
		mapAdiacenti.put("est", new Stanza("stanza magica"));
		mapAdiacenti.put("ovest", new Stanza("stanza buia"));
		assertEquals(mapAdiacenti, corridoio.getDirezioni2stanze());
		Attrezzo a1 = new Attrezzo("chiave", 1);
		Attrezzo a2 = new Attrezzo("lanterna", 1);
		List<Attrezzo> l = new ArrayList<Attrezzo>();
		l.addAll(corridoio.getAttrezzi());
		assertEquals(Arrays.asList(a1, a2), l);
	}
}