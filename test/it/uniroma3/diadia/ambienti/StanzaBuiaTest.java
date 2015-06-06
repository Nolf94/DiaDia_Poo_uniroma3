package it.uniroma3.diadia.ambienti;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class StanzaBuiaTest {
	private StanzaBuia stanzaBuia;
	private Attrezzo fonteDiLuce;
	private Attrezzo altroAttrezzo;


	@Before
	public void setUp() {
		stanzaBuia = new StanzaBuia("stanzaTest","lanterna");
		fonteDiLuce = new Attrezzo("lanterna",2);
		altroAttrezzo = new Attrezzo("altroAttrezzo",1);

	}

	@Test
	public void testGetDescrizioneSenzaFonteDiLuce() {
		assertEquals("Qui c'è buio pesto.",stanzaBuia.getDescrizione());
	}

	@Test
	public void testGetDescrizioneConAltroAttrezzo() {
		stanzaBuia.addAttrezzo(altroAttrezzo);
		assertEquals("Qui c'è buio pesto.",stanzaBuia.getDescrizione());
	}

	@Test
	public void testGetDescrizioneConFonteDiLuce() {
		stanzaBuia.addAttrezzo(fonteDiLuce);
		assertNotEquals("Qui c'è buio pesto.",stanzaBuia.getDescrizione());
	}

}
