package it.uniroma3.diadia;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Classe IOConsole che si occupa di gestire l'interazione IO con l'utente
 * tramite tastiera e schermo.
 *
 * @author Lambertucci Matteo
 * @see DiaDia
 * @version 1
 * 
 */
public class IOConsole implements IO {

	private Scanner scanner; // di linee

	public IOConsole(Scanner scanner) {
		this.scanner = scanner;
	}

	public Scanner getScanner() {
		return this.scanner;
	}

	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}

	/**
	 * 
	 * @param il messaggio da stampare su schermo per l'utente.
	 */
	public void mostraMessaggio(String msg) {
		System.out.println(msg);
	}

	/**
	 * 
	 * @return la riga scansionata da tastiera.
	 */
	public String leggiRiga() {
		String riga = null;
		try {
			riga = this.scanner.nextLine();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		// scannerDiLinee.close();
		return riga;
	}

	public void close() {
		try {
			this.scanner.close();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
	}
}