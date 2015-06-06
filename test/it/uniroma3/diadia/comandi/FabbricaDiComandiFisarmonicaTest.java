package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;
import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;

import org.junit.Before;
import org.junit.Test;

public class FabbricaDiComandiFisarmonicaTest {
	private FabbricaDiComandiRiflessiva fabbrica;
	private AbstractComando comando;

	@Before
	public void setUp() {
		fabbrica = new FabbricaDiComandiRiflessiva();
		comando = fabbrica.costruisciComando("vai nord");
	}

	@Test
	public void testNome() {
		assertEquals("vai",comando.getNome());
	}
	@Test
	public void testParametro() {
		assertEquals("nord",comando.getParametro());
	}

}
