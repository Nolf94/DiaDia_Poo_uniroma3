package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import java.io.StringReader;

import org.junit.Before;
import org.junit.Test;

public class CaricatoreLabirintoTest {
	private StringReader labirintoMonolocale;
	private StringReader labirintoBilocale;
	private StringReader labirintoTrilocale;
	private StringReader labirintoQuadrilocale;
	private CaricatoreLabirinto c1, c2, c3, c4;

	@Before
	public void setUp() throws Exception {
		this.labirintoMonolocale = new StringReader("Stanze: N10\nStanzeMagiche: \nStanzeBuie: \nStanzeBloccate: \nInizio: N10\nVincente: N10\nAttrezzi: osso 10 N10\nUscite: \nPersonaggi: ");
		this.labirintoBilocale = new StringReader("Stanze: N10, DS1\nStanzeMagiche: \nStanzeBuie: \nStanzeBloccate: \nInizio: N10\nVincente: DS1\nAttrezzi: osso 10 N10, palla 2 DS1\nUscite: DS1 nord N10, N10 sud DS1 \nPersonaggi: Mago Merlino DS1 \" Sei un gran burlone\" chiave 1 null ");
		this.labirintoTrilocale = new StringReader("Stanze: N10\nStanzeMagiche: DS1\nStanzeBuie: biblioteca osso \nStanzeBloccate: \nInizio: N10\nVincente: DS1\nAttrezzi: osso 10 N10, palla 2 DS1, libro 1 biblioteca\nUscite: DS1 nord N10, N10 sud DS1, biblioteca est DS1, DS1 ovest biblioteca\nPersonaggi: Mago Merlino DS1 \" Sei un gran burlone\" chiave 1 null , Strega Morgana biblioteca \"Cosa vuoi\" null 0 null");
		this.labirintoQuadrilocale = new StringReader("Stanze: N10\nStanzeMagiche: DS1\nStanzeBuie: biblioteca osso \nStanzeBloccate: N12 sud libro\nInizio: N10\nVincente: DS1\nAttrezzi: osso 10 N10, palla 2 DS1, libro 1 biblioteca, penna 1 N12\nUscite: DS1 nord N10, N10 sud DS1, biblioteca est DS1, DS1 ovest biblioteca, N12 est N10\nPersonaggi: Mago Merlino DS1 \" Sei un gran burlone\" chiave 1 null , Strega Morgana biblioteca \"Cosa vuoi\" null 0 null,  Cane Cerbero N12 \"\" pallina 1 osso");
		this.c1 = new CaricatoreLabirinto(labirintoMonolocale);	
		this.c2 = new CaricatoreLabirinto(labirintoBilocale);
		this.c3 = new CaricatoreLabirinto(labirintoTrilocale); 
		this.c4 = new CaricatoreLabirinto(labirintoQuadrilocale);
		try{
			this.c1.carica();
		}
		catch (FormatoFileNonValidoException e) {
			e.printStackTrace();
		}
		try{
			this.c2.carica();
		}
		catch (FormatoFileNonValidoException e) {
			e.printStackTrace();
		}
		try{
			this.c3.carica();
		}
		catch (FormatoFileNonValidoException e) {
			e.printStackTrace();
		}
		try{
			this.c4.carica();
		}
		catch (FormatoFileNonValidoException e) {
			e.printStackTrace();
		}

	}


	@Test
	public void testCarica_Monolocale() {
		assertEquals(1, this.c1.getNome2Stanza().size());
		assertTrue(this.c1.getNome2Stanza().containsKey("N10"));
	}

	@Test
	public void testLeggiInizialeEvincente_Monolocale(){
		assertEquals("N10", this.c1.getStanzaIniziale().getNome());
		assertEquals("N10", this.c1.getStanzaVincente().getNome());
	}

	@Test
	public void testLeggiEcollocaAttrezzi_Monolocale(){
		assertTrue(this.c1.getNome2Stanza().get("N10").hasAttrezzo("osso"));
	}

	@Test
	public void testCarica_Bilocale() {
		assertEquals(2, this.c2.getNome2Stanza().size());
		assertTrue(this.c2.getNome2Stanza().containsKey("N10"));
		assertTrue(this.c2.getNome2Stanza().containsKey("DS1"));
	}

	@Test
	public void testLeggiInizialeEvincente_Bilocale(){
		assertEquals("N10", this.c2.getStanzaIniziale().getNome());
		assertEquals("DS1", this.c2.getStanzaVincente().getNome());
	}

	@Test
	public void testLeggiEcollocaAttrezzi_Bilocale(){
		assertTrue(this.c2.getNome2Stanza().get("N10").hasAttrezzo("osso"));
		assertTrue(this.c2.getNome2Stanza().get("DS1").hasAttrezzo("palla"));
	}

	@Test
	public void testImpostaUscite_Bilocale(){
		assertEquals("N10", this.c2.getNome2Stanza().get("DS1").getStanzaAdiacente("nord").getNome());
		assertEquals("N10", this.c2.getNome2Stanza().get("DS1").getStanzaAdiacente("nord").getNome());
	}

	@Test
	public void testLeggiEcollocaPersonaggi_Bilocale(){
		assertEquals("Merlino",this.c2.getNome2Stanza().get("DS1").getPersonaggio().getNome());
	}


	@Test
	public void testCarica_Trilocale() {
		assertEquals(3, this.c3.getNome2Stanza().size());
		assertTrue(this.c3.getNome2Stanza().containsKey("biblioteca"));
		assertTrue(this.c3.getNome2Stanza().containsKey("N10"));
		assertTrue(this.c3.getNome2Stanza().containsKey("DS1"));
	}

	@Test
	public void testLeggiInizialeEvincente_Trilocale(){
		assertEquals("N10", this.c3.getStanzaIniziale().getNome());
		assertEquals("DS1", this.c3.getStanzaVincente().getNome());
	}

	@Test
	public void testLeggiEcollocaAttrezzi_Trilocale(){
		assertTrue(this.c3.getNome2Stanza().get("biblioteca").hasAttrezzo("libro"));
		assertTrue(this.c3.getNome2Stanza().get("N10").hasAttrezzo("osso"));
		assertTrue(this.c3.getNome2Stanza().get("DS1").hasAttrezzo("palla"));
	}

	@Test
	public void testImpostaUscite_Trilocale(){
		assertEquals("N10", this.c3.getNome2Stanza().get("DS1").getStanzaAdiacente("nord").getNome());
		assertEquals("DS1", this.c3.getNome2Stanza().get("N10").getStanzaAdiacente("sud").getNome());
		assertEquals("DS1", this.c3.getNome2Stanza().get("biblioteca").getStanzaAdiacente("est").getNome());
		assertEquals("biblioteca", this.c3.getNome2Stanza().get("DS1").getStanzaAdiacente("ovest").getNome());
	}

	@Test
	public void testLeggiEcollocaPersonaggi_Trilocale(){
		assertEquals("Merlino",this.c3.getNome2Stanza().get("DS1").getPersonaggio().getNome());
		assertEquals("Morgana",this.c3.getNome2Stanza().get("biblioteca").getPersonaggio().getNome());
	}

	@Test
	public void testCarica_Quadrilocale() {
		assertEquals(4, this.c4.getNome2Stanza().size());
		assertTrue(this.c4.getNome2Stanza().containsKey("biblioteca"));
		assertTrue(this.c4.getNome2Stanza().containsKey("N10"));
		assertTrue(this.c4.getNome2Stanza().containsKey("DS1"));
		assertTrue(this.c4.getNome2Stanza().containsKey("N12"));
	}

	@Test
	public void testLeggiInizialeEvincente_Quadrilocale(){
		assertEquals("N10", this.c4.getStanzaIniziale().getNome());
		assertEquals("DS1", this.c4.getStanzaVincente().getNome());
	}

	@Test
	public void testLeggiEcollocaAttrezzi_Quadrilocale(){
		assertTrue(this.c4.getNome2Stanza().get("biblioteca").hasAttrezzo("libro"));
		assertTrue(this.c4.getNome2Stanza().get("N10").hasAttrezzo("osso"));
		assertTrue(this.c4.getNome2Stanza().get("DS1").hasAttrezzo("palla"));
		assertTrue(this.c4.getNome2Stanza().get("N12").hasAttrezzo("penna"));
	}

	@Test
	public void testImpostaUscite_Quadrilocale(){
		assertEquals("N10", this.c4.getNome2Stanza().get("DS1").getStanzaAdiacente("nord").getNome());
		assertEquals("DS1", this.c4.getNome2Stanza().get("N10").getStanzaAdiacente("sud").getNome());
		assertEquals("DS1", this.c4.getNome2Stanza().get("biblioteca").getStanzaAdiacente("est").getNome());
		assertEquals("biblioteca", this.c4.getNome2Stanza().get("DS1").getStanzaAdiacente("ovest").getNome());
		assertEquals("N10", this.c4.getNome2Stanza().get("N12").getStanzaAdiacente("est").getNome());
	}

	@Test
	public void testLeggiEcollocaPersonaggi_Quadrilocale(){
		assertEquals("Merlino",this.c4.getNome2Stanza().get("DS1").getPersonaggio().getNome());
		assertEquals("Morgana",this.c4.getNome2Stanza().get("biblioteca").getPersonaggio().getNome());
		assertEquals("Cerbero",this.c4.getNome2Stanza().get("N12").getPersonaggio().getNome());
	}
}








