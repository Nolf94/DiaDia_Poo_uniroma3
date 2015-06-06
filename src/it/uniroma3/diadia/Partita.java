package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  Paolo Merialdo, Valter Crescenzi (da un'idea di Michael Kolling and David J. Barnes)
 * @see Stanza 
 * @see Labirinto
 * @see Giocatore
 * @version 4.0
 */

public class Partita {
	private Labirinto labirintoPartita;
	private boolean finita;
	private Giocatore giocatore;
	private Stanza stanzaCorrente;


	public Partita (){
		this(new Labirinto());
	}
	
	public Partita(String nomefile){
		this(new Labirinto(nomefile));
	}

	public Partita(Labirinto labirinto){
		this.labirintoPartita = labirinto;
		this.finita = false;
		this.giocatore = new Giocatore();
		this.stanzaCorrente = this.getLabirinto().getStanzaIniziale();

	}

	/**
	 * Restituisce il labirinto dove si svolge la partita.
	 * @return labirinto della partita
	 */
	public Labirinto getLabirinto() {
		return this.labirintoPartita;
	}

	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}

	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}

	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return this.stanzaCorrente == labirintoPartita.getStanzaVincente();
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || (this.getGiocatore().getCfu() == 0);
	}

	public boolean giocatoreIsVivo() {
		return this.getGiocatore().getCfu() > 0;
	}

	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}

	public Giocatore getGiocatore() {
		return this.giocatore;
	}
}
