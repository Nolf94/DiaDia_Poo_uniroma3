package it.uniroma3.diadia.ui;

import java.util.Scanner;

/**
@author Marco
@version 4.0
 **/

public class InterfacciaUtenteConsole implements InterfacciaUtente {
	private Scanner scanner;

	public InterfacciaUtenteConsole(){
		this.scanner = new Scanner(System.in);
	}

	public void mostraMessaggio(String messaggio){
		System.out.println(messaggio);
	}

	public String prendiIstruzione(){
		String myLine = scanner.nextLine();
		return myLine;

	}
}
