package it.uniroma3.diadia.ambienti;
import java.util.Comparator;

/**@author Marco
   @version 4.0
 **/

public class ComparatorePerAttrezzi implements Comparator<Stanza> {


	@Override
	public int compare(Stanza stanza1,Stanza stanza2){
		return  stanza1.getAttrezzi().size()-((stanza2.getAttrezzi().size()));
	}

}
