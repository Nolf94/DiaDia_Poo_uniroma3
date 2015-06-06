package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.ComandoPosa;
import it.uniroma3.diadia.giocatore.Borsa;

import org.junit.Before;
import org.junit.Test;

public class ComandoPosaTest {
	private Partita partitaTest;
	private AbstractComando comandoPosa;
	private Stanza stanzaTest;
	private Borsa borsaTest;

	@Before
	public void setUp() throws Exception {
		partitaTest = new Partita();
		comandoPosa = new ComandoPosa();
		stanzaTest = new Stanza("Camera");
		borsaTest = partitaTest.getGiocatore().getBorsa();
		partitaTest.setStanzaCorrente(stanzaTest);
	}

	@Test
	public void posaAttrezzoNonValido() {
		comandoPosa.setParametro("Libro");
		comandoPosa.esegui(partitaTest);
		assertFalse(stanzaTest.hasAttrezzo("Libro"));
	}

	@Test
	public void posaAttrezzoValido() {
		borsaTest.addAttrezzo(new Attrezzo("Piccone", 4));
		comandoPosa.setParametro("Piccone");
		comandoPosa.esegui(partitaTest);
		assertTrue(stanzaTest.hasAttrezzo("Piccone"));
	}

	@Test
	public void posaAttrezzoNull() {
		comandoPosa.setParametro(null);
		comandoPosa.esegui(partitaTest);
		assertTrue(stanzaTest.isEmpty());
	}


}
