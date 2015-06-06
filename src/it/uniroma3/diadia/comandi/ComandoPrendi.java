package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

/**
 * Una classe che modella un comando che prende un oggetto dalla stanza e lo ripone nella borsa.
 * @author Marco
 * @version 4.0
 *
 */

public class ComandoPrendi extends AbstractComando {

	@Override
	public String esegui(Partita partita) {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Borsa borsaGiocatore = partita.getGiocatore().getBorsa();
		Attrezzo attrezzoDaPrendere = null;
		if (this.getParametro() == null)
			return "Cosa vuoi prendere?";
		else {
			attrezzoDaPrendere = stanzaCorrente.getAttrezzo(this.getParametro());
			if (attrezzoDaPrendere == null)
				return "Attrezzo non presente nella stanza!";
			else {
				boolean preso = borsaGiocatore.addAttrezzo(attrezzoDaPrendere);
				if (preso) {
					stanzaCorrente.removeAttrezzo(this.getParametro());
					return "Attrezzo preso!";
				}
				else
					return "Attrezzo non preso, capienza massima borsa raggiunta!";
			}
		}
	}
}