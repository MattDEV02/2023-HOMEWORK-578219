package it.uniroma3.diadia.comandi;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto extends AbstractComando {

	private IO io;
	static final private String NOME = "aiuto";
	static final private Set<String> elencoComandi = new HashSet<String>(
			Arrays.asList("vai <direzione>", "aiuto", "fine", "prendi <nomeAttrezzo>", "posa <nomeAttrezzo>", "guarda",
					"regala <nomeAttrezzo>", "saluta", "interagisci"));

	@Override
	public void esegui(Partita partita) {
		// TODO: stampa info partita...
		this.io.mostraMessaggio("\n" + ComandoAiuto.elencoComandi.size() + " comandi: \n");
		for (String nomeComando : ComandoAiuto.elencoComandi)
			this.io.mostraMessaggio("   " + nomeComando + " ");
	}

	@Override
	public String getParametro() {
		return null;
	}

	@Override
	public String getNome() {
		return ComandoAiuto.NOME;
	}

	@Override
	public IO getIo() {
		return this.io;
	}

	@Override
	public void setIo(IO io) {
		this.io = io;

	}
}
