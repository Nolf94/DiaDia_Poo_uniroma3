package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;
import it.uniroma3.diadia.ambienti.Labirinto;

import org.junit.Before;
import org.junit.Test;

public class LabirintoTest {
	private Labirinto labirinto;

	@Before
	public void setUp() throws Exception {
		labirinto = new Labirinto();
	}

	@Test
	public void testGetStanzaVincente() {
		Stanza biblioteca = new Stanza("Biblioteca");
		assertEquals(biblioteca.getNome(),this.labirinto.getStanzaVincente().getNome());
	}

	@Test
	public void testGetStanzaCorrente(){
		Stanza atrio = new Stanza("Atrio");
		assertEquals(atrio.getNome(),this.labirinto.getStanzaIniziale().getNome());
	}

}
