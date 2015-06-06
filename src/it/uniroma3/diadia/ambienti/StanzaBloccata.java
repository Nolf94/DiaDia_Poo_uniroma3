package it.uniroma3.diadia.ambienti;
/**@author Marco
   @version 4.0
 **/
public class StanzaBloccata extends Stanza {
	private String direzioneBloccata;
	private String oggettoChiave;

	public StanzaBloccata(String nome, String direzioneBloccata, String oggettoChiave) {
		super(nome);
		this.direzioneBloccata = direzioneBloccata;
		this.oggettoChiave = oggettoChiave;
	}

	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		if (direzione.equals(this.direzioneBloccata) && !this.hasAttrezzo(oggettoChiave))
			return this;
		else 
			return super.getStanzaAdiacente(direzione);

	}

	@Override
	public String getDescrizione() {
		return super.toString() + "\nLa porta che conduce a " + this.direzioneBloccata + " sembrerebbe essere chiusa a chiave,"
				+ " trova qualcosa per sbloccarla.";
	}

}
