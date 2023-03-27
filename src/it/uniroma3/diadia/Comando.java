package it.uniroma3.diadia;

/**
 * 
 * Questa classe modella un comando. Un comando può avere una o due parole: il
 * nome del comando ed un eventuale parametro sul quale si applica il comando.
 * (Ad esempio alla riga digitata in input dall'utente "vai nord" corrisponde un
 * comando di nome "vai" e parametro "nord").
 * 
 *
 * @author Lambertucci Matteo
 * @version 1
 * @see Partita
 * @see DiaDia
 * 
 */

public class Comando {

	private String nome;
	private String parametro;

	/**
	 * 
	 * Costruttore (di riferimento) della classe Comando.
	 * 
	 * @param array di stringhe riferito alla "frase" inserita in input da tastiera.
	 * 
	 */
	public Comando(String[] istruzioni) {
		int numeroParole = istruzioni.length;
		if (numeroParole > 0 && numeroParole <= 2) { // i comandi devono avere una o due parole.
			if (numeroParole == 1)
				this.setNome(istruzioni, numeroParole - 1);
			if (numeroParole == 2) {
				this.setNome(istruzioni, numeroParole - 2);
				this.setParametro(istruzioni, numeroParole - 1);
			}
		}
	}

	/**
	 * 
	 * @return nome del comando.
	 * 
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * 
	 * @param istruzioni inserite da tastiera.
	 * @param indice     dell'array di stringhe riferito "frase" inserita in input
	 *                   da tastiera.
	 * 
	 */
	public void setNome(String[] istruzioni, int indice) {
		this.nome = istruzioni[indice];
	}

	/**
	 * 
	 * @return parametro del comando.
	 * 
	 */
	public String getParametro() {
		return this.parametro;
	}

	/**
	 * 
	 * @param istruzioni inserite da tastiera.
	 * @param indice     dell'array di stringhe riferito "frase" inserita in input
	 *                   da tastiera.
	 * 
	 */
	public void setParametro(String[] istruzioni, int indice) {
		this.parametro = istruzioni[indice];
	}

	/**
	 * 
	 * @return true se l'attuale comando è sconosciuto (è nullo), false altrimenti.
	 * 
	 */
	public boolean isSconosciuto() {
		return this.getNome() == null || this.getNome().equals("");
	}
}
