package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
@author Marco
@version 4.0
 **/

public class Cane extends AbstractPersonaggio {
	private static final  String MESSAGGIO_MINACCIA = "Grrrr, *morde* \nHai perso un Cfu.";
	private static final String MESSAGGIO_APPREZZAMENTO = ": Gnam Gnam!";
	private static final String MESSAGGIO_DISGUSTATO = ": Bleah...";
	public Cane() {
		super(null, null);
	}

	public Cane(String nome, String presentazione,Attrezzo ciboPreferito,Attrezzo regalo) {
		super(nome, presentazione);
	}

	@Override
	public String agisci(Partita partita) {
		String msg = MESSAGGIO_MINACCIA;
		partita.getGiocatore().decrementaCfu();
		return msg;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita){
		if(attrezzo.getNome().equals(this.getCiboPreferito())){
			partita.getStanzaCorrente().addAttrezzo(this.getRegalo());
			return this.getNome() + MESSAGGIO_APPREZZAMENTO;}
		else {
			partita.getStanzaCorrente().addAttrezzo(attrezzo);
			return this.getNome() + MESSAGGIO_DISGUSTATO;
		}

	}
}