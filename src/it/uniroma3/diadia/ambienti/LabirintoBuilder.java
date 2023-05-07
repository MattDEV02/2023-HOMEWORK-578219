package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilder {

	private Labirinto labirinto;
	private Stanza ultimaStanzaAggiunta;
	private Map<String, Stanza> nome2stanza;

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

	public LabirintoBuilder addStanzaBloccata(String nomeStanzaBloccata, String nomeAttrezzoSbloccante,
			String direzioneBloccata) {
		Stanza stanzaBloccata = new StanzaBloccata(nomeStanzaBloccata, nomeAttrezzoSbloccante, direzioneBloccata);
		this.UltimaStanzaAggiuntaEAggiorna(stanzaBloccata);
		return this;
	}

	public void UltimaStanzaAggiuntaEAggiorna(Stanza stanza) {
		this.ultimaStanzaAggiunta = stanza;
		this.nome2stanza.put(stanza.getNome(), stanza);
	}
}
