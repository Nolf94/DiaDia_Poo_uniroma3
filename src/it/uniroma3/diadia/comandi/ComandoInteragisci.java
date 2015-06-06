package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

/**
@author Marco
@version 4.0
 **/

public class ComandoInteragisci extends AbstractComando {
	private static final String MESSAGGIO_CON_CHI = "Con chi dovrei interagire?...";
	private String messaggio;

	@Override
	public String esegui(Partita partita) {
		AbstractPersonaggio personaggio;
		personaggio = partita.getStanzaCorrente().getPersonaggio();
		if(personaggio != null) {
			this.messaggio = personaggio.agisci(partita);
			return this.messaggio;
		}
		else 
			return MESSAGGIO_CON_CHI;
	}

	public String getMessaggio() {
		return this.messaggio;
	}
}




