package it.uniroma3.diadia;

import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Labirinto;

public final class Main {

	/**
	 * metodo principale del progetto.
	 * 
	 * @param args argomenti da linea di comando forniti.
	 * 
	 */
	public final static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		IOConsole ioConsole = new IOConsole(scanner);
		Labirinto labirinto = Labirinto.newBuilder("labirinto5.txt").getLabirinto();
		DiaDia diaDia = new DiaDia(ioConsole, labirinto);
		diaDia.gioca();
		diaDia.getIOConsole().close();
		// N.B. = per i test ho creato una src folder chiamata test. 283 / 283
	}

}
