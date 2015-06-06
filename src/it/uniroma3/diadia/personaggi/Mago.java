package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
@author Marco
@version 4.0
 **/

public  class Mago extends AbstractPersonaggio {
	private static final String MESSAGGIO_DONO = "Sei un vero simpaticone, " +
			"con una mia magica azione, \ntroverai un nuovo oggetto " +
			"per il tuo bel borsone!";
	private static final String MESSAGGIO_SCUSE = "Mi spiace, ma non ho piu' nulla...";
	private static final String MESSAGGIO_GRAZIE =  ": Grazie, per ricompensa ti darò un oggetto identico con peso dimezzato.";

	public Mago(){
		super(null,null);
	}
	public Mago(String nome, String presentazione, Attrezzo regalo) {
		super(nome, presentazione);
	}

	@Override
	public String agisci(Partita partita) {
		String msg;
		if(this.getRegalo()!=null) {
			partita.getStanzaCorrente().addAttrezzo(this.getRegalo());
			this.setRegalo(null);
			msg = MESSAGGIO_DONO;
		}
		else {
			msg = MESSAGGIO_SCUSE;
		}
		return msg;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita){
		attrezzo.setPeso(attrezzo.getPeso()/2);
		partita.getStanzaCorrente().addAttrezzo(attrezzo);
		return this.getNome() + MESSAGGIO_GRAZIE;
	}
}


