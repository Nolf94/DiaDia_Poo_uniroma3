package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

/**
@author Marco
@version 4.0
 **/

public class ComandoSaluta extends AbstractComando {
	private String messaggio;

	@Override
	public String esegui(Partita partita) {
		AbstractPersonaggio personaggio;
		personaggio = partita.getStanzaCorrente().getPersonaggio();
		if(personaggio != null){
			this.messaggio = personaggio.saluta();
			return this.messaggio;
		}
		else 
			return "Non c'è nessuno da salutare in questa stanza.";
	}

	public String getMessaggio(){
		return this.messaggio;
	}
}