package it.uniroma3.diadia;

import static org.junit.Assert.*;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.giocatore.Giocatore;

import org.junit.Before;
import org.junit.Test;

public class PartitaTest {
	private Labirinto labirinto;
	private Partita partita;
	private Giocatore giocatore;

	@Before
	public void setUp() throws Exception {
		partita = new Partita();
		labirinto = partita.getLabirinto();
		giocatore = partita.getGiocatore();
	}

	//test su vinta():
	@Test
	public void testVinta_StanzaCorrenteStanzaVincente() {
		partita.setStanzaCorrente(labirinto.getStanzaVincente());
		assertTrue(partita.vinta());
	}

	@Test
	public void testVinta_StanzaCorrenteNonStanzaVincente() {
		assertFalse(partita.vinta());
	}

	//test su isFinita():
	@Test
	public void testIsFinita_PartitaVinta() {
		partita.setStanzaCorrente(labirinto.getStanzaVincente());
		assertTrue(partita.isFinita());
	}

	@Test
	public void testIsFinita_CFUfiniti() {
		giocatore.setCfu(0);
		assertTrue(partita.isFinita());
	}

	@Test
	public void testIsFinita_PartitaVintaECFUFiniti() {
		giocatore.setCfu(0);
		partita.setStanzaCorrente(labirinto.getStanzaVincente());
		assertTrue(partita.isFinita());
	}

	@Test
	public void testIsFinita_PartitaNonFinita() {
		assertFalse(partita.isFinita());
	}

}
