package it.uniroma3.diadia.ambienti;
import it.uniroma3.diadia.attrezzi.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class StanzaMagicaTest {
	private StanzaMagica  stanzaMagica;
	private Attrezzo attrezzoTestCambiato;
	private Attrezzo attrezzo;

	@Before
	public void setUp() {
		stanzaMagica = new StanzaMagica("magic",2);

		attrezzoTestCambiato = new Attrezzo("orbil",2);
		attrezzo = new Attrezzo("libro", 1);
	}

	@Test
	public void testAddAttrezzoNormalmente() {
		stanzaMagica.addAttrezzo(attrezzo);
		assertEquals(attrezzo,stanzaMagica.getAttrezzo("libro"));

	}

	@Test
	public void testAddAttrezzoInvertente() {
		stanzaMagica.addAttrezzo(attrezzo);
		stanzaMagica.addAttrezzo(attrezzo);
		stanzaMagica.addAttrezzo(attrezzo);
		assertEquals(attrezzoTestCambiato,stanzaMagica.getAttrezzo("orbil"));
	}

}



