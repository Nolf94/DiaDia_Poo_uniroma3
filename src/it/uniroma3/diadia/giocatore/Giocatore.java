package it.uniroma3.diadia.giocatore;

/**
 * Questa classe modella il giocatore che dovr� muoversi all'interno del labirinto.
 * @author Marco
 * @see Borsa
 * @version 4.0
 */

public class Giocatore {
	private int cfu;
	private static int CFU_INIZIALI = 20;
	private Borsa borsa;

	public Giocatore() {
		this.cfu = CFU_INIZIALI;
		this.borsa = new Borsa();
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;
	}

	public int getCfu() {
		return this.cfu;
	}

	public Borsa getBorsa() {
		return this.borsa;
	}

	public int decrementaCfu() {
		return this.cfu--;
	}
	@Override
	public String toString() {
		return "CFU: " + this.cfu + "\n" + borsa.toString();
	}
}
