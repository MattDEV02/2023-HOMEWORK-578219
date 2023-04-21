package it.uniroma3.diadia.fixture;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IOSimulator;

public class IOSimulatorTest {

	public static IOSimulator creaSimulazionePartitaEGioca(String... comandiDaLeggere) {
		IOSimulator io = new IOSimulator(comandiDaLeggere);
		DiaDia diaDia = new DiaDia(io);
		diaDia.gioca();
		for (int i = 0; i < comandiDaLeggere.length; i++) {
			// io.leggiRiga();
			diaDia.processaIstruzione(comandiDaLeggere[i]);
		}
		return io;
	}

	public static void main(String[] args) {
		System.out.print("ciao");
		creaSimulazionePartitaEGioca("vai nord");
	}
}
