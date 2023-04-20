package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto implements Comando {

	private IO io;
	static final private String NOME = "aiuto";
	static final private String[] elencoComandi = { "vai <direzione>", "aiuto", "fine", "prendi <nomeAttrezzo>",
			"posa <nomeAttrezzo>", "guarda" };

	@Override
	public void esegui(Partita partita) {
		// TODO: stampa info partita...
		int numeroComandi = ComandoAiuto.elencoComandi.length;
		for (int i = 0; i < numeroComandi; i++) {
			this.io.mostraMessaggio(ComandoAiuto.elencoComandi[i] + " ");
		}
	}

	@Override
	public String getParametro() {
		return "";
	}

	@Override
	public void setParametro(String parametro) {
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
