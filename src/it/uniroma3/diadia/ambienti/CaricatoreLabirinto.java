package it.uniroma3.diadia.ambienti;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

import java.io.*;
import java.util.*;
/**@author Marco
   @version 4.0
 **/

public class CaricatoreLabirinto {
	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze */
	private static final String STANZE_MARKER = "Stanze: ";             

	/* prefisso di una singola riga contenente il nome della stanza iniziale */
	private static final String STANZA_INIZIALE_MARKER = "Inizio: ";    

	/* prefisso della riga contenente il nome stanza vincente */
	private static final String STANZA_VINCENTE_MARKER = "Vincente: ";  

	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeAttrezzo> <peso> <nomeStanza> */
	private static final String ATTREZZI_MARKER = "Attrezzi: ";

	/* prefisso della riga contenente le specifiche dei collegamenti tra stanza nel formato <nomeStanzaDa> <direzione> <nomeStanzaA> */
	private static final String USCITE_MARKER = "Uscite: ";

	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze magiche */
	private static final String STANZE_MAGICHE_MARKER = "StanzeMagiche: ";

	/* prefisso della riga contenente le specifiche delle stazie buie nel formato <nomeStanza> <attrezzo> */
	private static final String STANZE_BUIE_MARKER = "StanzeBuie: ";

	/* prefisso della riga contenente le specifiche delle stanze bloccate nel formato <nomeStanza> <direzione> <attrezzo> */
	private static final String STANZE_BLOCCATE_MARKER = "StanzeBloccate: ";

	/* prefisso della riga contenente le specifiche dei personaggi nel formato 
	 * <tipoPersonaggio> <nomePersonaggio> <nomeStanza> <presentazione> <nomeRegalo> <pesoRegalo> <ciboPreferito> 
	 * Se uno dei parametri non è presenti specificare come segue: 
	 * nomeRegalo non presente => null, pesoRegalo non presente => 0, ciboPreferito non presente => null */
	private static final String PERSONAGGI_MARKER = "Personaggi: ";

	/*
	 *  Esempio di un possibile file di specifica di un labirinto (vedi POO-26-eccezioni-file.pdf)

        Stanze: N11, Biblioteca, N10, Bar
        StanzeMagiche: N2
        StanzeBuie: Campus lanterna
        StanzeBloccate: N12 nord chiave
        Inizio: Biblioteca
        Vincente: Bar
        Attrezzi: lanterna 3 N10, osso 1 N11, chiave 1 Campus
        Uscite: N11 nord Biblioteca, N11 est N11, N11 sud N10, N11 ovest Campus, N11 est N12, N11 ovest N11, N11 sud Campus, N10 nord N11, N10 est Campus, N10 ovest N12, N12 est N10, N12 ovest N11, N12 sud Campus, N12 nord Bar, Campus est N11, Campus ovest N11, Campus nord N12, Biblioteca sud N11
        Personaggi: Mago Merlino N12 "Sei un gran burlone!" chiave 1 null, Cane Cerbero N11 "" pallina 1 osso, Strega Morgana N11 "Sono una strega molto permalosa." null 0 null



	 */
	private LineNumberReader reader;
	private Map<String, Stanza> nome2stanza;
	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;


	public CaricatoreLabirinto(Reader reader) {
		this.nome2stanza = new HashMap<>();
		this.reader = new LineNumberReader(reader);
	}

	public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException {
		this(new FileReader(nomeFile));
	}

	public void carica() throws FormatoFileNonValidoException {
		try {
			this.leggiECreaStanze();
			this.leggiECreaStanzeMagiche();
			this.leggiECreaStanzeBuie();
			this.leggiECreaStanzeBloccate();
			this.leggiInizialeEvincente();
			this.leggiECollocaAttrezzi();
			this.leggiEImpostaUscite();
			this.leggiECollocaPersonaggi();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

	}

	private String leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
		try { 
			String riga = this.reader.readLine();
			check(riga.startsWith(marker),"era attesa una riga che cominciasse per "+marker);
			return riga.substring(marker.length());
		} catch (IOException e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}
	}

	private void leggiECreaStanze() throws FormatoFileNonValidoException  {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MARKER);
		for(String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
			String tmp = nomeStanza.trim();
			Stanza stanza = new Stanza(tmp);
			this.nome2stanza.put(tmp, stanza);
		}
	}

	private List<String> separaStringheAlleVirgole(String string) {
		List<String> result = new LinkedList<>();
		Scanner scanner = new Scanner(string);
		scanner.useDelimiter(", ");
		try (Scanner scannerDiParole = scanner) {
			while(scannerDiParole.hasNext()){
				result.add(scannerDiParole.next());
			}
		}
		return result;
	}


	private void leggiInizialeEvincente() throws FormatoFileNonValidoException {
		String nomeStanzaIniziale = null;
		nomeStanzaIniziale = this.leggiRigaCheCominciaPer(STANZA_INIZIALE_MARKER);
		check(this.isStanzaValida(nomeStanzaIniziale), nomeStanzaIniziale +" non definita");
		String nomeStanzaVincente = this.leggiRigaCheCominciaPer(STANZA_VINCENTE_MARKER);
		check(this.isStanzaValida(nomeStanzaVincente), nomeStanzaVincente + " non definita");
		this.stanzaIniziale = this.nome2stanza.get(nomeStanzaIniziale);
		this.stanzaVincente = this.nome2stanza.get(nomeStanzaVincente);
	}

	private void leggiECollocaAttrezzi() throws FormatoFileNonValidoException {
		String specificheAttrezzi = this.leggiRigaCheCominciaPer(ATTREZZI_MARKER);

		for(String specificaAttrezzo : separaStringheAlleVirgole(specificheAttrezzi)) {
			String nomeAttrezzo = null;
			String pesoAttrezzo = null;
			String nomeStanza = null; 
			try (Scanner scannerLinea = new Scanner(specificaAttrezzo)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un attrezzo."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo "+nomeAttrezzo+"."));
				pesoAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare l'attrezzo "+nomeAttrezzo+"."));
				nomeStanza = scannerLinea.next();
			}				
			posaAttrezzo(nomeAttrezzo, pesoAttrezzo, nomeStanza);
		}
	}

	private void posaAttrezzo(String nomeAttrezzo, String pesoAttrezzo, String nomeStanza) throws FormatoFileNonValidoException {
		int peso;
		try {
			peso = Integer.parseInt(pesoAttrezzo);
			Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
			check(isStanzaValida(nomeStanza),"Attrezzo "+ nomeAttrezzo+" non collocabile: stanza " +nomeStanza+" inesistente");
			this.nome2stanza.get(nomeStanza).addAttrezzo(attrezzo);
		}
		catch (NumberFormatException e) {
			check(false, "Peso attrezzo "+nomeAttrezzo+" non valido");
		}
	}


	private boolean isStanzaValida(String nomeStanza) {
		return this.nome2stanza.containsKey(nomeStanza);
	}

	private void leggiEImpostaUscite() throws FormatoFileNonValidoException {
		String specificheUscite = this.leggiRigaCheCominciaPer(USCITE_MARKER);
		for(String stringaUscita : separaStringheAlleVirgole(specificheUscite)) {
			try (Scanner scannerDiLinea = new Scanner(stringaUscita)) {			
				while (scannerDiLinea.hasNext()) {
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("le uscite di una stanza."));
					String stanzaPartenza = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la direzione di una uscita della stanza "+stanzaPartenza));
					String dir = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la destinazione di una uscita della stanza "+stanzaPartenza+" nella direzione "+dir));
					String stanzaDestinazione = scannerDiLinea.next();

					impostaUscita(stanzaPartenza, dir, stanzaDestinazione);
				}
			} 
		}
	}

	private String msgTerminazionePrecoce(String msg) {
		return "Terminazione precoce del file prima di leggere "+msg;
	}

	private void impostaUscita(String stanzaDa, String dir, String nomeA) throws FormatoFileNonValidoException {
		check(isStanzaValida(stanzaDa),"Stanza di partenza sconosciuta "+dir);
		check(isStanzaValida(nomeA),"Stanza di destinazione sconosciuta " + nomeA + " " + dir);
		Stanza partenzaDa = this.nome2stanza.get(stanzaDa);
		Stanza arrivoA = this.nome2stanza.get(nomeA);
		partenzaDa.impostaStanzaAdiacente(dir, arrivoA);
	}

	private void leggiECreaStanzeMagiche() throws FormatoFileNonValidoException {
		String nomiStanzeMagiche = this.leggiRigaCheCominciaPer(STANZE_MAGICHE_MARKER);
		for(String nomeStanza : separaStringheAlleVirgole(nomiStanzeMagiche)) {
			Stanza stanzaMagica = new StanzaMagica(nomeStanza);
			this.nome2stanza.put(nomeStanza, stanzaMagica);
		}
	}

	private void leggiECreaStanzeBuie() throws FormatoFileNonValidoException {
		String specificheStanzeBuie = this.leggiRigaCheCominciaPer(STANZE_BUIE_MARKER);
		for(String specificaStanzaBuia : separaStringheAlleVirgole(specificheStanzeBuie)) {
			String nomeStanza = null;
			String nomeAttrezzo = null;
			try (Scanner scannerLinea = new Scanner(specificaStanzaBuia)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di una stanza."));
				nomeStanza = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un attrezzo."));
				nomeAttrezzo = scannerLinea.next();
			}			
			Stanza stanzaBuia = new StanzaBuia(nomeStanza, nomeAttrezzo);
			this.nome2stanza.put(nomeStanza, stanzaBuia);
		}
	}

	private void leggiECreaStanzeBloccate() throws FormatoFileNonValidoException {
		String specificheStanzeBloccate = this.leggiRigaCheCominciaPer(STANZE_BLOCCATE_MARKER);
		for(String specificaStanzaBloccata : separaStringheAlleVirgole(specificheStanzeBloccate)) {
			String nomeStanza = null;
			String direzione = null;
			String nomeAttrezzo = null;
			try (Scanner scannerLinea = new Scanner(specificaStanzaBloccata)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di una stanza."));
				nomeStanza = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di una direzione."));
				direzione = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un attrezzo."));
				nomeAttrezzo = scannerLinea.next();
			}			
			Stanza stanzaBloccata = new StanzaBloccata(nomeStanza, direzione, nomeAttrezzo);
			this.nome2stanza.put(nomeStanza, stanzaBloccata);
		}
	}

	private void leggiECollocaPersonaggi() throws FormatoFileNonValidoException {
		String specifichePersonaggi = this.leggiRigaCheCominciaPer(PERSONAGGI_MARKER);

		for(String specificaPersonaggio : separaStringheAlleVirgole(specifichePersonaggi)) {
			String tipoPersonaggio = null;
			String nomePersonaggio = null;
			String nomeStanza = null;
			String presentazione = null;
			String nomeRegalo = null;
			int pesoRegalo = 0;
			String ciboPreferito = null;

			try (Scanner scannerLinea = new Scanner(specificaPersonaggio)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il tipo del personaggio."));
				tipoPersonaggio = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome del personaggio."));
				nomePersonaggio = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare "+nomePersonaggio+"."));
				nomeStanza = scannerLinea.next();

				check(scannerLinea.hasNext(),msgTerminazionePrecoce("presentazione del personaggio."));
				presentazione = scannerLinea.next().substring(1) + " ";
				while(!presentazione.contains("\"")) {
					presentazione += scannerLinea.next() + " ";
				}
				presentazione = presentazione.substring(0, presentazione.length() - 2);

				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome del regalo."));
				nomeRegalo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso del regalo "+nomeRegalo+"."));
				pesoRegalo = Integer.parseInt(scannerLinea.next());
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il cibo preferito."));
				ciboPreferito = scannerLinea.next();
			}

			String nomeClasse = "it.uniroma3.diadia.personaggi.";
			nomeClasse += tipoPersonaggio;
			AbstractPersonaggio personaggio = null;
			try {
				personaggio = (AbstractPersonaggio) Class.forName(nomeClasse).newInstance();
				personaggio.setNome(nomePersonaggio);
				personaggio.setPresentazione(presentazione);
				personaggio.setRegalo(new Attrezzo(nomeRegalo, pesoRegalo));
				personaggio.setCiboPreferito(ciboPreferito);
			} catch(Exception e) {
				e.printStackTrace();
			}
			collocaPersonaggio(nomeStanza, personaggio);
		}
	}

	private void collocaPersonaggio(String nomeStanza, AbstractPersonaggio personaggio) throws FormatoFileNonValidoException {
		check(isStanzaValida(nomeStanza),"Stanza sconosciuta.");
		this.nome2stanza.get(nomeStanza).setPersonaggio(personaggio);
	}



	final private void check(boolean condizioneCheDeveEsseraVera, String messaggioErrore) throws FormatoFileNonValidoException {
		if (!condizioneCheDeveEsseraVera)
			throw new FormatoFileNonValidoException("Formato file non valido [" + this.reader.getLineNumber() + "] "+messaggioErrore);		
	}

	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}

	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}
	public Map<String, Stanza> getNome2Stanza() {
		return this.nome2stanza;
	}

}
