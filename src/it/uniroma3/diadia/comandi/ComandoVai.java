package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.*;

/**
 * Classe che modella un comando che fa muovere il giocatore in una stanza adiacente, se esiste la direzione, 
 * oppure restituisce un messaggio di errore.
 * @author Marco
 * @version 4.0
 */

public class ComandoVai extends AbstractComando {

	@Override
	public String esegui(Partita partita) {
		if (this.getParametro() == null) 
			return "Dove vuoi andare? Devi specificare una direzione"; 
		Stanza prossimaStanza = null;
		prossimaStanza = partita.getStanzaCorrente().getStanzaAdiacente(this.getParametro());
		if(prossimaStanza == null)
			return "Direzione inesistente";

		else {
			partita.setStanzaCorrente(prossimaStanza);
			partita.getGiocatore().decrementaCfu();
			return partita.getStanzaCorrente().getNome();
		}
	}
}

