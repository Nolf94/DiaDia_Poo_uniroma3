package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

/**
 * Stampa un messaggio di errore se il comando non � presente tra l'elenco dei comandi.
 * @author Marco
 * @version 4.0
 */

public class ComandoNonValido extends AbstractComando {

	@Override
	public String esegui(Partita partita) {
		return "Comando non valido!";
	}
}
