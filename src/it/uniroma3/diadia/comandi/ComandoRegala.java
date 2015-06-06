package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.attrezzi.*;
/**
@author Marco
@version 4.0
 **/

public class ComandoRegala extends AbstractComando{
	private String messaggio;

	@Override
	public String esegui(Partita partita) {

		Borsa borsaGiocatore =  partita.getGiocatore().getBorsa();
		Attrezzo attrezzoRegalato = borsaGiocatore.getAttrezzo(this.getParametro());
		if(this.getParametro() == null)
			return "Cosa vuoi regalare?";
		else 
			if(partita.getStanzaCorrente().getPersonaggio() != null)
				if(borsaGiocatore.hasAttrezzo(this.getParametro())){
					this.messaggio = partita.getStanzaCorrente().getPersonaggio().riceviRegalo(attrezzoRegalato, partita);
					borsaGiocatore.removeAttrezzo(attrezzoRegalato.getNome());
					return this.messaggio;
				}

				else return "Attrezzo non presente nella borsa!";
			else return "A chi vorresti regalare?";
	}

	public String getMessaggio(){
		return this.messaggio;
	}
}