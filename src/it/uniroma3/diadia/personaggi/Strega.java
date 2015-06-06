package it.uniroma3.diadia.personaggi;


import java.util.Collections;
import java.util.List;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.ComparatorePerAttrezzi;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
@author Marco
@version 4.0
 **/

public class Strega extends AbstractPersonaggio{
	private static final String MESSAGGIO_CORTESE = "Sei stato molto cordiale a salutarmi ,per ringraziarti"
			+ " \ncon un'incantesimo ti trasferirò nella stanza che ha più attrezzi!";
	private static final String MESSAGGIO_SCORTESE = "Sei stato molto maleducato a non salutarmi, per punizione "
			+ " \nti trasferirò nella stanza con meno attrezzi con una magia nera!";
	private static final String MESSAGGIO_MALEFICO = ": AHAHAHAHAHAHHA NON RIAVRAI PIÙ IL TUO OGGETTO!";

	public Strega(){
		super(null,null);
	}
	public Strega(String nome, String presentazione) {
		super(nome, presentazione);
	}

	@Override
	public String agisci(Partita partita) {
		String msg;
		ComparatorePerAttrezzi comparatore = new ComparatorePerAttrezzi();
		List<Stanza>listaStanzeAdiacenti = partita.getStanzaCorrente().getStanzeAdiacenti();
		if(this.haSalutato()){
			partita.setStanzaCorrente(Collections.max(listaStanzeAdiacenti,comparatore));
			msg = MESSAGGIO_CORTESE;}
		else {
			partita.setStanzaCorrente(Collections.min(listaStanzeAdiacenti,comparatore));
			msg = MESSAGGIO_SCORTESE;}

		return msg;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita){
		return this.getNome() + MESSAGGIO_MALEFICO;
	}

}
