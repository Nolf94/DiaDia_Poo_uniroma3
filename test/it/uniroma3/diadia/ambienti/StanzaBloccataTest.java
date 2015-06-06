package it.uniroma3.diadia.ambienti;
import it.uniroma3.diadia.attrezzi.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class StanzaBloccataTest {
	private StanzaBloccata stanzaBloccata;
	private Stanza altraStanza_1;
	private Stanza altraStanza_2;
	private Attrezzo attrezzoSbloccante;
	private Attrezzo altroAttrezzo;



	@Before
	public void setUp() {
		stanzaBloccata = new StanzaBloccata("stanzaChiusa","nord","chiave");
		stanzaBloccata.impostaStanzaAdiacente("nord", altraStanza_1);
		attrezzoSbloccante = new Attrezzo("chiave",2);
		stanzaBloccata.impostaStanzaAdiacente("sud", altraStanza_2);
	}

	@Test
	public void testGetStanzaAdiacente_SenzaChiave() {
		assertSame(this.stanzaBloccata,stanzaBloccata.getStanzaAdiacente("nord"));

	}

	@Test
	public void testGetStanzaAdiacente_ConChiave() {
		stanzaBloccata.addAttrezzo(attrezzoSbloccante);
		assertSame(altraStanza_1,stanzaBloccata.getStanzaAdiacente("nord"));

	} 
	@Test
	public void testGetStanzaAdiacente_InDirezioneNonBloccata() {
		assertSame(altraStanza_2,stanzaBloccata.getStanzaAdiacente("sud"));
	}

	@Test
	public void testGetStanzaAdiacente_ConAltroAttrezzo() {
		stanzaBloccata.addAttrezzo(altroAttrezzo);
		assertSame(stanzaBloccata,stanzaBloccata.getStanzaAdiacente("nord"));
	} 
}
