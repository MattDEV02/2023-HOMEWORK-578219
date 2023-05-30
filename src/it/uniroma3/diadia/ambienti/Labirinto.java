package it.uniroma3.diadia.ambienti;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.uniroma3.diadia.CaricatoreLabirinto;
import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

/**
 * 
 * Classe che ha la responsabilità di creare il labirinto, di memorizzare la
 * stanza iniziale (entrata) e quella finale (uscita), aggiungere un riferimento
 * ad un'istanza di Labirinto nella classe Partita.
 * 
 * 
 * @author Lambertucci Matteo
 * @version 1
 * @see Stanza
 * @see Partita
 * @see Attrezzo
 *
 */

public class Labirinto {

	private Stanza stanzaCorrente; // inizialmente stanza iniziale
	private Stanza stanzaVincente;

	private Labirinto() {

	}

	public Labirinto(String nomeFile) {
		try {
			CaricatoreLabirinto caricatoreLabirinto = new CaricatoreLabirinto(nomeFile);
			caricatoreLabirinto.carica();
			this.stanzaCorrente = caricatoreLabirinto.getStanzaIniziale();
			this.stanzaVincente = caricatoreLabirinto.getStanzaVincente();
		} catch (FileNotFoundException | FormatoFileNonValidoException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @return la stanza d'ingresso (atrio).
	 */
	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}

	/**
	 * 
	 * @param la stanza nuova d'ingresso.
	 */
	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}

	/**
	 * 
	 * @return restituisce la stanza vincente (biblioteca).
	 */
	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}

	/**
	 * 
	 * @param la nuova stanza vincente.
	 */
	public void setStanzaVincente(Stanza stanzaVincente) {
		this.stanzaVincente = stanzaVincente;
	}

	public static LabirintoBuilder newBuilder(String nomeFile) {
		return new LabirintoBuilder(nomeFile);
	}

	public static class LabirintoBuilder {

		private Labirinto labirinto;
		private Stanza ultimaStanzaAggiunta;
		private Map<String, Stanza> nome2stanza;

		public LabirintoBuilder(String nomeFile) {
			this.labirinto = new Labirinto(nomeFile);
			this.nome2stanza = new HashMap<String, Stanza>();
		}

		public LabirintoBuilder() {
			this.labirinto = new Labirinto();
			this.nome2stanza = new HashMap<String, Stanza>();
		}

		public Map<String, Stanza> getNome2stanza() {
			return this.nome2stanza;
		}

		public void setNome2stanza(Map<String, Stanza> nome2stanza) {
			this.nome2stanza = nome2stanza;
		}

		public Labirinto getLabirinto() {
			return this.labirinto;
		}

		public void setLabirinto(Labirinto labirinto) {
			this.labirinto = labirinto;
		}

		public Stanza getUltimaStanzaAggiunta() {
			return this.ultimaStanzaAggiunta;
		}

		public void setUltimaStanzaAggiunta(Stanza ultimaStanzaAggiunta) {
			this.ultimaStanzaAggiunta = ultimaStanzaAggiunta;
		}

		public Set<String> getNomiStanze() {
			return this.nome2stanza.keySet();
		}

		public List<Stanza> getListaStanze() {
			return (List<Stanza>) (this.nome2stanza.values());
		}

		public LabirintoBuilder addStanzaIniziale(String nomeStanzaIniziale) {
			Stanza stanzaIniziale = new Stanza(nomeStanzaIniziale);
			this.labirinto.setStanzaCorrente(stanzaIniziale);
			this.UltimaStanzaAggiuntaEAggiorna(stanzaIniziale);
			return this;
		}

		public LabirintoBuilder addStanzaVincente(String nomeStanzaVincente) {
			Stanza stanzaVincente = new Stanza(nomeStanzaVincente);
			this.labirinto.setStanzaVincente(stanzaVincente);
			this.UltimaStanzaAggiuntaEAggiorna(stanzaVincente);
			return this;
		}

		public LabirintoBuilder addStanza(String nomeStanza) {
			Stanza stanza = new Stanza(nomeStanza);
			this.UltimaStanzaAggiuntaEAggiorna(stanza);
			return this;
		}

		public LabirintoBuilder addAttrezzo(String nomeAttrezzo, int pesoAttrezzo) {
			Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, pesoAttrezzo);
			if (this.ultimaStanzaAggiunta == null)
				return this;
			this.ultimaStanzaAggiunta.addAttrezzo(attrezzo);
			return this;
		}

		public LabirintoBuilder addMago(String nome, String presentazione, Attrezzo attrezzo) {
			AbstractPersonaggio mago = new Mago(nome, presentazione, attrezzo);
			if (this.ultimaStanzaAggiunta == null)
				return this;
			this.ultimaStanzaAggiunta.setPersonaggio(mago);
			return this;
		}

		public LabirintoBuilder addCane(String nome, String presentazione, String nomeCiboPreferito,
				Attrezzo attrezzo) {
			AbstractPersonaggio cane = new Cane(nome, presentazione, nomeCiboPreferito, attrezzo);
			if (this.ultimaStanzaAggiunta == null)
				return this;
			this.ultimaStanzaAggiunta.setPersonaggio(cane);
			return this;
		}

		public LabirintoBuilder addStrega(String nome, String presentazione) {
			AbstractPersonaggio strega = new Strega(nome, presentazione);
			if (this.ultimaStanzaAggiunta == null)
				return this;
			this.ultimaStanzaAggiunta.setPersonaggio(strega);
			return this;
		}

		public LabirintoBuilder addAdiacenza(String nomeStanzaCorrente, String nomeStanzaAdiacente, String direzione) {
			Stanza stanzaCorrente = this.nome2stanza.get(nomeStanzaCorrente);
			Stanza stanzaAdiacente = this.nome2stanza.get(nomeStanzaAdiacente);
			stanzaCorrente.impostaStanzaAdiacente(direzione, stanzaAdiacente);
			return this;
		}

		public LabirintoBuilder addStanzaMagica(String nomeStanzaMagica, int sogliaMagica) {
			Stanza stanzaMagica = new StanzaMagica(nomeStanzaMagica, sogliaMagica);
			this.UltimaStanzaAggiuntaEAggiorna(stanzaMagica);
			return this;
		}

		public LabirintoBuilder addStanzaBuia(String nomeStanzaBuia, String nomeAttrezzoPerVedere) {
			Stanza stanzaBuia = new StanzaBuia(nomeStanzaBuia, nomeAttrezzoPerVedere);
			this.UltimaStanzaAggiuntaEAggiorna(stanzaBuia);
			return this;
		}

		public LabirintoBuilder addStanzaBloccata(String nomeStanzaBloccata, String direzioneBloccata,
				String nomeAttrezzoSbloccante) {
			Stanza stanzaBloccata = new StanzaBloccata(nomeStanzaBloccata, direzioneBloccata, nomeAttrezzoSbloccante);
			this.UltimaStanzaAggiuntaEAggiorna(stanzaBloccata);
			return this;
		}

		public void UltimaStanzaAggiuntaEAggiorna(Stanza stanza) {
			this.ultimaStanzaAggiunta = stanza;
			this.nome2stanza.put(stanza.getNome(), stanza);
		}
	}
}
