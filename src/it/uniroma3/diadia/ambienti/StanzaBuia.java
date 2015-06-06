package it.uniroma3.diadia.ambienti;

/**
 * Classe che modella una stanza buia. A meno che all'interno non vi sia un oggetto in grado di emettere luce
 * allora non sar� possibile visualizzarne la descrizione.
 * @author Marco
 * @version 4.0
 */

public class StanzaBuia extends Stanza {
	private String fonteDiLuce;

	public StanzaBuia(String nome, String fonteDiLuce) {
		super(nome);
		this.fonteDiLuce = fonteDiLuce;
	}

	@Override
	public String getDescrizione() {
		if (super.hasAttrezzo(fonteDiLuce))
			return super.getDescrizione();
		else
			return "Qui c'è buio pesto.";
	}


}
