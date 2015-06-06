package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;
import it.uniroma3.diadia.giocatore.Giocatore;

import org.junit.Before;
import org.junit.Test;

public class GiocatoreTest {
	private Giocatore giocatore;

	@Before
	public void setUp() throws Exception {
		giocatore = new Giocatore();

	}

	@Test
	public void testSetCfu_ZEROCFU() {
		giocatore.setCfu(0);
		assertEquals(0, giocatore.getCfu());
	}

	@Test
	public void testSetCfu_RANDOMCFU() {
		giocatore.setCfu(35);
		assertEquals(35, giocatore.getCfu());
	}

}
