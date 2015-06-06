package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.attrezzi.ComparatorePerPeso;


import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 * Classe che modella la borsa dell'equipaggiamento del giocatore.
 * @author Marco
 * @see Giocatore
 * @version 4.0
 */
public class Borsa {
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private int pesoMax;
	private Map<String,Attrezzo> attrezzi;

	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}

	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new HashMap<>();
	}

	public  List<Attrezzo> getAttrezzi() {
		return  new LinkedList<>( this.attrezzi.values());
	}


	public boolean addAttrezzo(Attrezzo attrezzo) {
		if(attrezzo == null)
			return false;
		if( this.getPeso() + attrezzo.getPeso()>this.pesoMax  || this.attrezzi.containsKey(attrezzo))
			return false;
		else 
			this.attrezzi.put(attrezzo.getNome(),attrezzo );
		return true;
	}

	public int getPesoMax() {
		return pesoMax;
	}

	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.get(nomeAttrezzo);
	}

	public int getPeso() {
		int peso = 0;
		for (Attrezzo a: this.getAttrezzi())
			peso += a.getPeso();
		return peso;
	}

	public boolean isEmpty() {
		return this.getAttrezzi().isEmpty();
	}

	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.containsKey(nomeAttrezzo);
	}

	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.remove(nomeAttrezzo);
	}
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			Map<Integer,Set<Attrezzo>> mappaAttrezzi = this.getContenutoRaggruppatoPerPeso();
			Set<Integer> a = mappaAttrezzi.keySet();
			for ( Integer  p : a){
				s.append("(");
				s.append(p);
				s.append(", ");
				s.append(mappaAttrezzi.get(p));
				s.append(") ");
			}
		}
		else
			s.append("Borsa vuota");

		return s.toString();
	}

	public List<Attrezzo> getContenutoOrdinatoPerPeso(){
		List<Attrezzo> listaAttrezzi = this.getAttrezzi();
		ComparatorePerPeso comp = new ComparatorePerPeso();
		Collections.sort(listaAttrezzi,comp);
		return listaAttrezzi;
	}

	public List<Attrezzo> getContenutoOrdinatoPerNome(){
		List<Attrezzo> listaAttrezzi = this.getAttrezzi();
		Collections.sort(listaAttrezzi);
		return listaAttrezzi;
	}

	public Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
		Set<Attrezzo> tmp;
		Map<Integer,Set<Attrezzo>> mappa = new HashMap<Integer,Set<Attrezzo>>();
		Set<Attrezzo> a = new HashSet <> (this.getAttrezzi());
		for(Attrezzo attrezzo : a){
			if(mappa.containsKey(attrezzo.getPeso())){
				tmp = mappa.get(attrezzo.getPeso());
				tmp.add(attrezzo);
			}
			else {
				tmp = new HashSet<Attrezzo>();
				tmp.add(attrezzo);
				mappa.put(attrezzo.getPeso(), tmp); 
			}
		}
		return mappa;
	}

	public int getNumeroAttrezzi(){
		return this.getAttrezzi().size();
	}

}
