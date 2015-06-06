package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

/**
 * Una classe che modella un comando che rimuove un Attrezzo dalla borsa del giocatore e lo ripone nella stanza.
 * @author Marco
 * @version 4.0
 */

public class ComandoPosa extends AbstractComando {

	@Override
	public String esegui(Partita partita) {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Borsa borsaGiocatore = partita.getGiocatore().getBorsa();
		Attrezzo attrezzoDaPosare = null;
		if (this.getParametro()==null)
			return "Cosa vuoi posare?";
		else {
			attrezzoDaPosare = borsaGiocatore.getAttrezzo(this.getParametro());
			if (attrezzoDaPosare == null)
				return "Attrezzo non presente nella borsa del giocatore!";
		}
		borsaGiocatore.removeAttrezzo(this.getParametro());
		stanzaCorrente.addAttrezzo(attrezzoDaPosare);
		return "Attrezzo posato!";
	}
}
