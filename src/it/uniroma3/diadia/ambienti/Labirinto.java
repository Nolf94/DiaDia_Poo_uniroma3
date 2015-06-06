package it.uniroma3.diadia.ambienti;

import java.io.FileNotFoundException;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

/**
 * Questa classe modella il labirinto all'interno del quale il giocatore dovr� muoversi.
 * @author Marco
 * @see Stanza
 * @version 4.0
 */

public class Labirinto {
	private Object stanzaIniziale;
	private Stanza stanzaVincente;


	public Labirinto() {
		creaStanze();
	}

	public Labirinto(String nomeFile)  {
		CaricatoreLabirinto c = null;
		try {
			c = new CaricatoreLabirinto(nomeFile);

		} catch (FileNotFoundException e) {
			System.out.println("File non trovato.");
			e.printStackTrace();
		}
		try {
			c.carica();
		} catch (FormatoFileNonValidoException e) {
			System.out.println("Formato File non valido.");
			e.printStackTrace();
		}
		this.stanzaIniziale = c.getStanzaIniziale();
		this.stanzaVincente = c.getStanzaVincente(); 



	}





	private void creaStanze() {

		/* crea gli attrezzi */
		Attrezzo lanterna = new Attrezzo("lanterna",3);
		Attrezzo osso = new Attrezzo("osso",4);
		Attrezzo pala = new Attrezzo("pala", 4);
		Attrezzo spada = new Attrezzo("spada", 6);
		Attrezzo libro = new Attrezzo("libro", 1);
		Attrezzo chiave = new Attrezzo("chiave", 1);
		Attrezzo scudo = new Attrezzo("scudo",1);
		Attrezzo pozione = new Attrezzo("pozione",1);


		/* crea stanze del labirinto */
		Stanza atrio = new Stanza("Atrio");
		Stanza aulaN11 = new Stanza("Aula N11");
		Stanza aulaN10 = new Stanza("Aula N10");
		Stanza laboratorio = new Stanza("Laboratorio Campus");
		Stanza biblioteca = new Stanza("Biblioteca");
		Stanza ds2 = new StanzaMagica("DS2");
		Stanza aulaN12 = new StanzaBuia("Aula N12", "lanterna");
		Stanza segreteria = new StanzaBloccata("Segreteria", "nord", "chiave");
		Stanza bar = new Stanza("Bar");

		/*crea personaggi nelle stanza*/
		AbstractPersonaggio morgana = new Strega("Morgana","Sono una strega molto permalosa.");
		AbstractPersonaggio cerbero = new Cane("Cerbero","Sono uno spaventoso cane a tre teste.",osso,chiave);
		AbstractPersonaggio merlino = new Mago("Merlino","Sono un mago buono.",pozione);
		/* collega le stanze */
		atrio.impostaStanzaAdiacente("nord", biblioteca);
		atrio.impostaStanzaAdiacente("est", aulaN11);
		atrio.impostaStanzaAdiacente("sud", aulaN10);
		atrio.impostaStanzaAdiacente("ovest", laboratorio);
		aulaN11.impostaStanzaAdiacente("ovest", atrio);
		aulaN11.impostaStanzaAdiacente("sud", ds2);
		aulaN11.impostaStanzaAdiacente("est", bar);
		aulaN10.impostaStanzaAdiacente("nord", atrio);
		aulaN10.impostaStanzaAdiacente("est", ds2);
		aulaN10.impostaStanzaAdiacente("ovest", aulaN12);
		laboratorio.impostaStanzaAdiacente("est", atrio);
		laboratorio.impostaStanzaAdiacente("sud", aulaN12);
		biblioteca.impostaStanzaAdiacente("sud", atrio);
		ds2.impostaStanzaAdiacente("nord", aulaN11);
		ds2.impostaStanzaAdiacente("est", segreteria);
		ds2.impostaStanzaAdiacente("ovest", aulaN10);
		aulaN12.impostaStanzaAdiacente("nord", laboratorio);
		aulaN12.impostaStanzaAdiacente("est", aulaN10);
		segreteria.impostaStanzaAdiacente("nord", bar);
		segreteria.impostaStanzaAdiacente("ovest", ds2);
		bar.impostaStanzaAdiacente("sud", segreteria);
		bar.impostaStanzaAdiacente("ovest", aulaN11);


		/* pone gli attrezzi nelle stanze */
		aulaN10.addAttrezzo(lanterna);
		laboratorio.addAttrezzo(spada);
		atrio.addAttrezzo(osso);
		atrio.addAttrezzo(pala);
		atrio.addAttrezzo(scudo);
		aulaN12.addAttrezzo(libro);
		ds2.addAttrezzo(chiave);

		// il gioco comincia nell'atrio
		stanzaIniziale = atrio;  
		stanzaVincente = biblioteca;

		aulaN10.setPersonaggio(morgana);
		aulaN11.setPersonaggio(merlino);
		atrio.setPersonaggio(cerbero);
	}

	public Stanza getStanzaVincente() {
		return stanzaVincente;
	}

	public Stanza getStanzaIniziale() {
		return (Stanza) this.stanzaIniziale;
	}


}


