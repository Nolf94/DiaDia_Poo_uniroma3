package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import org.junit.Before;
import org.junit.Test;

public class StanzaTest {
	private Stanza biblioteca;
	private Stanza mensa;
	private Stanza bar;
	private Attrezzo attrezzo1;


	@Before
	public void setUp() {
		biblioteca = new Stanza("Biblioteca");
		mensa = new Stanza("Mensa");
		bar = new Stanza("Bar");
		attrezzo1 = new Attrezzo("Pala", 1);
	}

	//test su impostaStanzaAdiacente(String, Stanza):
	@Test
	public void testGetStanzaAdiacente_StanzaStandard() {
		biblioteca.impostaStanzaAdiacente("est", mensa);
		assertEquals(mensa, biblioteca.getStanzaAdiacente("est"));
	}

	@Test
	public void testGetStanzaAdiacente_StanzaNull() {
		biblioteca.impostaStanzaAdiacente("nord", null);
		assertNull(biblioteca.getStanzaAdiacente("nord"));
	}

	@Test
	public void testGetStanzaAdiacente_DirezioneErrata() {
		biblioteca.impostaStanzaAdiacente("sud", mensa);
		assertNull(biblioteca.getStanzaAdiacente("ovest"));
	}

	//test su addAttrezzo(Attrezzo):
	@Test
	public void testAddAttrezzo_AttrezzoStandard() {
		assertTrue(biblioteca.addAttrezzo(attrezzo1));
	}

	@Test
	public void testAddAttrezzo_AttrezzoNull() {
		assertFalse(biblioteca.addAttrezzo(null));
	}

	//test su hasAttrezzo(String):
	@Test
	public void testHasHattrezzo_StanzaVuota() {
		assertFalse(bar.hasAttrezzo("Pala"));
	}

	@Test
	public void testHasHattrezzo_StanzaConOggetto() {
		biblioteca.addAttrezzo(new Attrezzo("Coltello", 1));
		assertTrue(biblioteca.hasAttrezzo("Coltello"));
	}

	@Test
	public void testHasHattrezzo_StanzaConOggettoMaNonQuelloCercato() {
		assertFalse(biblioteca.hasAttrezzo("Martello"));
	}

	//test su removeAttrezzo(String):
	@Test
	public void testRemoveAttrezzo_StanzaSenzaAttrezzi() {
		assertFalse(bar.removeAttrezzo("Pala"));
	}

	@Test
	public void testRemoveAttrezzo_StanzaConUnAttrezzo() {
		mensa.addAttrezzo(new Attrezzo("Spada", 2));
		assertTrue(mensa.removeAttrezzo("Spada"));
	}

	@Test
	public void testRemoveAttrezzo_StanzaConUnAttrezzoMaNonQuelloDaRimuovere() {
		assertFalse(mensa.removeAttrezzo("Martello"));
	}

	@Test
	public void testGetNome(){
		assertEquals("Biblioteca",biblioteca.getNome());
	}


	@Test
	public void testToString(){
		assertEquals("Biblioteca" + "\nUscite: " +  "\nAttrezzi nella stanza: ",biblioteca.toString());
	}

	@Test
	public void testGetAttrezzi(){
		biblioteca.addAttrezzo(attrezzo1);
		assertTrue(biblioteca.getAttrezzi().contains(attrezzo1));
	}

	@Test
	public void testGetAttrezzo_StanzaConAttrezzoCercato(){
		biblioteca.addAttrezzo(attrezzo1);
		assertEquals(attrezzo1,biblioteca.getAttrezzo("Pala"));
	}

	@Test
	public void testGetAttrezzo_StanzaSenzaOggettoCercato(){
		assertNull(biblioteca.getAttrezzo("Pala"));
	}

	@Test
	public void testGetAttrezzo_StanzaConAttrezzoDiverso(){
		Attrezzo attrezzo2 = new Attrezzo("martello",2);
		biblioteca.addAttrezzo(attrezzo1);
		assertNotEquals( attrezzo2,biblioteca.getAttrezzo("Pala"));
	}

	@Test
	public void testIsEmpty_StanzaSenzaOggetti(){
		assertTrue(biblioteca.isEmpty());
	}

	@Test
	public void testIsEmpty_StanzaConOggetto(){
		biblioteca.addAttrezzo(attrezzo1);
		assertFalse(biblioteca.isEmpty());
	}

	@Test
	public void testGetDirezioni_NessunaDirezione(){
		assertFalse(biblioteca.getDirezioni().contains("est"));
	}

	@Test
	public void testGetDirezioni_ConDirezione(){
		biblioteca.impostaStanzaAdiacente("est", mensa);
		assertTrue(biblioteca.getDirezioni().contains("est"));
	}

	@Test
	public void testGetDirezioni_ConDirezioneDiversa(){
		biblioteca.impostaStanzaAdiacente("ovest", mensa);
		assertFalse(biblioteca.getDirezioni().contains("est"));
	}

	@Test
	public void testGetNumeroAttrezzi_StanzaSenzaAttrezzi(){
		assertEquals(0,biblioteca.getNumeroAttrezzi());
	}

	@Test
	public void testGetNumeroAttrezzi_StanzaConAttrezzo(){
		biblioteca.addAttrezzo(attrezzo1);
		assertEquals(1,biblioteca.getNumeroAttrezzi());
	}

	@Test
	public void testGetNumeroStanzeAdiacenti_SenzaStanzeAdiacenti(){
		assertEquals(0,biblioteca.getNumeroStanzeAdiacenti());
	}

	@Test
	public void testGetNumeroStanzeAdiacenti_ConStanzaAdiacente(){
		biblioteca.impostaStanzaAdiacente("est", mensa);
		assertEquals(1,biblioteca.getNumeroStanzeAdiacenti());
	}

	@Test
	public void testGetStanzeAdiacenti_ConUnaStanzaAdiacente(){
		biblioteca.impostaStanzaAdiacente("est", mensa);
		assertTrue(biblioteca.getStanzeAdiacenti().contains(mensa));
	}

	@Test
	public void testGetStanzeAdiacenti_SenzaStanzaAdiacente(){
		assertFalse(biblioteca.getStanzeAdiacenti().contains(mensa));
	}

	@Test
	public void testGetStanzeAdiacenti_ConUnaStanzaAdiacenteDiversa(){
		biblioteca.impostaStanzaAdiacente("est", bar);
		assertFalse(biblioteca.getStanzeAdiacenti().contains(mensa));
	}

}

