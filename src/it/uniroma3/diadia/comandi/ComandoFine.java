package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

/**
 * Classe che modella il comando che termina la partita.
 * @author Marco
 * @version 4.0
 */

public class ComandoFine extends AbstractComando {

	@Override
	public String esegui(Partita partita) {
		return ("Grazie di aver giocato!");  // si desidera smettere
	}

}
