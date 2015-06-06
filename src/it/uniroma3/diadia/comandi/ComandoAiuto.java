package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

/**
 * Comando che stampa l'elenco dei comandi disponibili al giocatore.
 * @author Marco
 * @version 4.0
 */

public class ComandoAiuto extends AbstractComando {
	private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa",
			"guarda","saluta", "interagisci","regala"};

	@Override
	public String esegui(Partita partita) {
		String s = new String(); 
		for(int i=0; i< elencoComandi.length; i++) 
			s+= elencoComandi[i] + " ";
		return s;

	}

}