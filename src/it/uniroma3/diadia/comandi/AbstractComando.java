package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

/**@author Marco
   @version 4.0
 **/

public abstract class AbstractComando {
	private String nomeComando;
	private String parametro;


	abstract public String  esegui(Partita partita);

	public void setParametro(String parametro){
		this.parametro = parametro;
	}

	public void setNome(String nome){
		this.nomeComando = nome;
	}
	public String getNome(){
		return this.nomeComando;
	}
	public String getParametro(){
		return this.parametro;
	}
}