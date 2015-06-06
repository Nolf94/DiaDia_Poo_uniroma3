package it.uniroma3.diadia.attrezzi;
import it.uniroma3.diadia.ambienti.Stanza;

/**
 * Una semplice classe che modella un attrezzo.
 * Gli attrezzi possono trovarsi all'interno delle stanze
 * del labirinto.
 * Ogni attrezzo ha un nome ed un peso.
 *
 * @author  Paolo Merialdo
 * @see Stanza
 * @version 4.0
 *
 */
public class Attrezzo implements Comparable<Attrezzo> {
	private String nome;
	private int peso;

	/**
	 * Crea un attrezzo
	 * @param nome il nome che identifica l'attrezzo
	 * @param peso il peso dell'attrezzo (deve essere > 0)
	 * @throws IllegalArgumentException se il peso e' negativo
	 */
	public Attrezzo(String nome, int peso) {
		this.peso = peso;
		this.nome = nome;
		if (this.peso<0)
			throw new IllegalArgumentException("Peso negativo!");
	}

	/**
	 * Restituisce il nome identificatore dell'attrezzo
	 * @return il nome identificatore dell'attrezzo
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Restituisce il peso dell'attrezzo
	 * @return il peso dell'attrezzo
	 */
	public int getPeso() {
		return this.peso;
	}

	public void setPeso(int peso){
		this.peso = peso;
	}

	public String getDescrizione(){
		return this.getNome() + " ("+this.getPeso()+" kg)";
	}

	/**
	 * Restituisce una rappresentazione stringa di questo attrezzo
	 * @return la rappresentazione stringa
	 */

	@Override
	public String toString() {
		return this.getNome();
	}
	@Override
	public boolean equals(Object o){
		Attrezzo attrezzo = (Attrezzo) o;
		return this.getNome().equals(attrezzo.getNome()) && this.getPeso() == attrezzo.getPeso();
	}

	@Override 
	public int hashCode() {
		return this.getNome().hashCode() + this.getPeso();
	}

	@Override 
	public int compareTo(Attrezzo a) {
		return this.getNome().compareTo(a.getNome());

	}

}