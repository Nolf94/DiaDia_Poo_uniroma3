package it.uniroma3.diadia.ambienti;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * @author Paolo Merialdo (a partire da un'idea di Michael Kolling e David J. Barnes)
 * @see Attrezzo
 * @version 4.0
 */

public class Stanza {
	private String nome;
	private Map<String,Attrezzo> attrezzi;
	private Map<String,Stanza> stanzeAdiacenti;
	private AbstractPersonaggio personaggio;

	/**
	 * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
	 * @param nome il nome della stanza
	 */
	public Stanza(String nome) {
		this.nome = nome;
		this.stanzeAdiacenti = new HashMap<>();
		this.attrezzi = new HashMap<>();
		this.personaggio = null;
	}

	/**
	 * Imposta una stanza adiacente.
	 *
	 * @param String direzione in cui sara' posta la stanza adiacente.
	 * @param Stanza stanza adiacente nella direzione indicata dal primo parametro.
	 */
	public void impostaStanzaAdiacente(String direzione, Stanza stanza) {
		this.stanzeAdiacenti.put(direzione, stanza);
	}

	/**
	 * Restituisce la stanza adiacente nella direzione specificata
	 * @param direzione
	 */
	public Stanza getStanzaAdiacente(String direzione) {
		return this.stanzeAdiacenti.get(direzione);
	}

	/**
	 * Restituisce la nome della stanza.
	 * @return il nome della stanza
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Restituisce la descrizione della stanza.
	 * @return la descrizione della stanza
	 */
	public String getDescrizione() {
		return this.toString();
	}

	/**
	 * Restituisce la collezione di attrezzi presenti nella stanza.
	 * @return la collezione di attrezzi nella stanza.
	 */
	public  List<Attrezzo> getAttrezzi() {
		return  new LinkedList<>( this.attrezzi.values());
	}

	/**
	 * Mette un attrezzo nella stanza.
	 * @param attrezzo l'attrezzo da mettere nella stanza.
	 * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
	 */

	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.attrezzi.containsKey(attrezzo) || attrezzo == null)
			return false;
		else 
			this.attrezzi.put(attrezzo.getNome(),attrezzo );
		return true;
	}

	/**
	 * Restituisce una rappresentazione stringa di questa stanza,
	 * stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	 * @return la rappresentazione stringa
	 */

	@Override
	public String toString() {
		String s = new String();
		s += this.nome;
		s += "\nUscite: ";
		Set <String> direzioni = this.stanzeAdiacenti.keySet();
		for (String direzione : direzioni)
			if (direzione!=null)
				s += " " + direzione;
		s += "\nAttrezzi nella stanza: ";
		for (Attrezzo attrezzo : this.getAttrezzi()) {
			if (attrezzo!=null)
				s += attrezzo.getDescrizione() + " ";
		}
		if(this.getPersonaggio() != null)
			s += "\nPersonaggi: " + this.getPersonaggio().getNome();

		return s;
	}

	/**
	 * Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	 * @return true se l'attrezzo esiste nella stanza, false altrimenti.
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.containsKey(nomeAttrezzo);
	}

	/**
	 * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
	 * 		   null se l'attrezzo non e' presente.
	 */

	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.get(nomeAttrezzo);
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(String nomeAttrezzo) {
		if( this.attrezzi.remove(nomeAttrezzo) == null)
			return false;
		else 
			return true;
	}

	/**
	 * Verifica se nella stanza non vi siano oggetti
	 * @return true se la stanza e' vuota, false altrimenti
	 */

	public boolean isEmpty() {
		return this.getAttrezzi().isEmpty();
	}

	public Set<String> getDirezioni() {
		return this.stanzeAdiacenti.keySet();
	}


	public int getNumeroAttrezzi() {
		return this.getAttrezzi().size();
	}

	public int getNumeroStanzeAdiacenti() {
		return this.getStanzeAdiacenti().size();
	}

	public List<Stanza> getStanzeAdiacenti() {
		return new LinkedList<>( this.stanzeAdiacenti.values());
	}

	public void setPersonaggio(AbstractPersonaggio personaggio) {
		this.personaggio = personaggio;
	}

	public AbstractPersonaggio getPersonaggio() {
		return this.personaggio;
	}

}


