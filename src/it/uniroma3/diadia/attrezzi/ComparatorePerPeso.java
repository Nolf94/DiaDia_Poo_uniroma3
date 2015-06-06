package it.uniroma3.diadia.attrezzi;

import java.util.Comparator;
/**
  @author Marco
  @version 4.0
 **/
public class ComparatorePerPeso implements Comparator<Attrezzo>{

	@Override
	public int compare(Attrezzo a1, Attrezzo a2) {
		if(a1.getPeso() == a2.getPeso())
			return  a1.getNome().compareTo(a2.getNome());
		else
			return  a1.getPeso()-a2.getPeso();
	}
}
