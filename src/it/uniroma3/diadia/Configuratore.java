package it.uniroma3.diadia;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Properties;

public final class Configuratore {

	private static final String DIADIA_PROPERTIES_FILE_NAME = "diadia.properties";
	private static final String DIADIA_XML_PROPERTIES_FILE_NAME = "diadia.xml";
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

	private static void scarica() {
		try {
			Writer output = new FileWriter(Configuratore.DIADIA_PROPERTIES_FILE_NAME);
			Configuratore.prop.store(output, "Configurazione del gioco DIADIA.");
		} catch (IOException | IllegalArgumentException | NullPointerException e) {
			e.printStackTrace();
		}
	}

	private static void scaricaToXML() {
		try {
			OutputStream output = new FileOutputStream(Configuratore.DIADIA_XML_PROPERTIES_FILE_NAME);
			Configuratore.prop.storeToXML(output, "Configurazione del gioco DIADIA.");
		} catch (IOException | IllegalArgumentException | NullPointerException e) {
			e.printStackTrace();
		}
	}
}