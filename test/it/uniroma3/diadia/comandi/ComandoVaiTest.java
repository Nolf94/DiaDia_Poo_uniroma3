package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.comandi.ComandoVai;

import org.junit.Before;
import org.junit.Test;

public class ComandoVaiTest {
	private Partita partitaTest;
	private AbstractComando comandoVai;
	private Stanza stanzaTest;
	private Stanza altraStanza;

	@Before
	public void setUp() {
		partitaTest = new Partita();
		comandoVai = new ComandoVai();
		stanzaTest = new Stanza("StanzaTest");
		partitaTest.setStanzaCorrente(stanzaTest);
		altraStanza = new Stanza("AltraStanza");

	}


	@Test
	public void vaiStanzaSingleton() {
		comandoVai.setParametro("est");
		comandoVai.esegui(partitaTest);
		assertSame(stanzaTest,this.partitaTest.getStanzaCorrente());

	}

	@Test
	public void vaiStanzaAccanto() {
		stanzaTest.impostaStanzaAdiacente("est",altraStanza);
		comandoVai.setParametro("est");
		comandoVai.esegui(partitaTest);
		assertSame(altraStanza,this.partitaTest.getStanzaCorrente());

	}

	@Test
	public void vaiInStanzaNulla() {
		stanzaTest.impostaStanzaAdiacente("est",null);
		comandoVai.setParametro("est");
		comandoVai.esegui(partitaTest);
		assertSame(stanzaTest,this.partitaTest.getStanzaCorrente());

	}

}
