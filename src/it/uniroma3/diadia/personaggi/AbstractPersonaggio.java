package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
@author Marco
@version 4.0
 **/

public abstract class AbstractPersonaggio {
	private String nome;
	private String presentazione;
	private boolean haSalutato;
	private String ciboPreferito;
	private Attrezzo regalo;

	public AbstractPersonaggio(String nome, String presentaz) {
		this.nome = nome;
		this.presentazione = presentaz;
		this.haSalutato = false;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome){
		this.nome = nome;
	}

	public String getCiboPreferito(){
		return this.ciboPreferito;
	}
	public void setCiboPreferito(String cibo){
		this.ciboPreferito = cibo;
	}
	public Attrezzo getRegalo(){
		return this.regalo;
	}
	public void setRegalo(Attrezzo regalo){
		this.regalo = regalo;
	}

	public String getPresentazione(){
		return this.presentazione;
	}
	public void setPresentazione(String presentaz){
		this.presentazione = presentaz;
	}

	public boolean haSalutato() {
		return this.haSalutato;
	}

	public String saluta() {
		StringBuilder risposta = new StringBuilder("Ciao, io sono ");
		risposta.append(this.getNome()+". "); 
		if (!haSalutato)
			risposta.append(this.presentazione);
		else
			risposta.append("Ci siamo gia' presentati!");
		this.haSalutato = true;
		return risposta.toString();
	}

	abstract public String agisci(Partita partita);

	abstract public String riceviRegalo(Attrezzo attrezzo, Partita partita);


	@Override
	public String toString() {
		return this.getNome();
	}
}
