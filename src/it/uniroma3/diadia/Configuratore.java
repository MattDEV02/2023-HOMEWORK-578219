package it.uniroma3.diadia;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public final class Configuratore {

	private static final String DIADIA_PROPERTIES_FILE_NAME = "diadia.properties";
	private static final String PESO_MAX_BORSA = "PESO_MAX_BORSA";
	private static final String CFU_INIZIALI_GIOCATORE = "CFU_INIZIALI_GIOCATORE";
	private static Properties prop = null;

	public static int getPesoMaxBorsa() {
		if (Configuratore.prop == null)
			Configuratore.carica();
		return Integer.parseInt(Configuratore.prop.getProperty(Configuratore.PESO_MAX_BORSA));
	}

	public static int getCFUInizialiGiocatore() {
		if (Configuratore.prop == null)
			Configuratore.carica();
		return Integer.parseInt(Configuratore.prop.getProperty(Configuratore.CFU_INIZIALI_GIOCATORE));
	}

	private static void carica() {
		Configuratore.prop = new Properties();
		try {
			FileInputStream input = new FileInputStream(Configuratore.DIADIA_PROPERTIES_FILE_NAME);
			Configuratore.prop.load(input);
		} catch (IOException | IllegalArgumentException | NullPointerException e) {
			e.printStackTrace();
		}
	}

}