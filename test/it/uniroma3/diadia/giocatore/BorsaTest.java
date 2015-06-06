package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

import org.junit.Before;
import org.junit.Test;

public class BorsaTest {
	private Borsa borsa;
	private Borsa borsa2;
	private Attrezzo attrezzoPesoZero;
	private Attrezzo attrezzoPesoEccessivo;
	private Attrezzo piombo;
	private Attrezzo piuma;
	private Attrezzo ps;
	private Attrezzo libro;

	@Before
	public void setUp() throws Exception {
		borsa = new Borsa();
		borsa2= new Borsa(40);
		attrezzoPesoZero = new Attrezzo("Zero", 0);
		attrezzoPesoEccessivo = new Attrezzo("Troppo", 100);
		piombo = new Attrezzo("piombo",10);
		ps = new Attrezzo("ps",5);
		piuma = new Attrezzo("piuma",1);
		libro = new Attrezzo ("libro",5);
		borsa2.addAttrezzo(piombo);
		borsa2.addAttrezzo(ps);
		borsa2.addAttrezzo(libro);
		borsa2.addAttrezzo(piuma);

	}

	@Test
	public void testAddAttrezzo_AttrezzoNull() {
		assertFalse(borsa.addAttrezzo(null));
	}

	@Test
	public void testAddAttrezzo_AttrezzoPesoZero() {
		assertTrue(borsa.addAttrezzo(attrezzoPesoZero));
	}

	@Test
	public void testAddAttrezzo_AttrezzoPesoEccessivo() {
		assertFalse(borsa.addAttrezzo(attrezzoPesoEccessivo));
	}

	@Test
	public void testGetNumeroAttrezzi() {
		borsa.addAttrezzo(attrezzoPesoZero);
		assertEquals(1,this.borsa.getNumeroAttrezzi());
	}

	@Test
	public void testIsEmpty_BorsaVuota() {
		assertTrue(borsa.isEmpty());
	}

	@Test
	public void testIsEmpty_BorsaNonVuota() {
		this.borsa.addAttrezzo(attrezzoPesoZero);
		assertFalse(this.borsa.isEmpty());
	}

	@Test
	public void testHasAttrezzo_BorsaConAttrezzo() {
		borsa.addAttrezzo(attrezzoPesoZero);
		assertTrue(borsa.hasAttrezzo("Zero"));
	}

	@Test
	public void testHasAttrezzo_BorsaSenzaAttrezzo() {
		assertFalse(borsa.hasAttrezzo("Zero"));
	}

	@Test
	public void testRemoveAttrezzo_BorsaSenzaAttrezzi() {
		assertNull(borsa.removeAttrezzo("Zero"));
	}

	@Test
	public void testRemoveAttrezzo_BorsaConUnAttrezzo() {
		borsa.addAttrezzo(attrezzoPesoZero);
		assertEquals(attrezzoPesoZero, borsa.removeAttrezzo("Zero"));
	}

	@Test
	public void testRemoveAttrezzo_BorsaConUnAttrezzoNull() {
		borsa.addAttrezzo(attrezzoPesoZero);
		borsa.addAttrezzo(null);
		assertNull(borsa.removeAttrezzo(null));
	}	

	@Test
	public void  testgetContenutoOrdinatoPerPeso_NessunOggetto(){
		List<Attrezzo> listaAttrezzi = new ArrayList<>();
		assertEquals(listaAttrezzi,this.borsa.getContenutoOrdinatoPerPeso());
	}

	@Test
	public void  testgetContenutoOrdinatoPerPeso_ConUnOggetto(){
		List<Attrezzo> listaAttrezzi = new ArrayList<>();
		listaAttrezzi.add(piuma);
		this.borsa.addAttrezzo(piuma);
		assertEquals(listaAttrezzi,this.borsa.getContenutoOrdinatoPerPeso());
	}

	@Test
	public void  testgetContenutoOrdinatoPerPeso_ConVariOggetti(){
		List<Attrezzo> listaAttrezzi = new ArrayList<>();
		listaAttrezzi.add(piuma);
		listaAttrezzi.add(libro);
		listaAttrezzi.add(ps);
		listaAttrezzi.add(piombo);
		assertEquals(listaAttrezzi,this.borsa2.getContenutoOrdinatoPerPeso());
	}

	@Test
	public void  testgetContenutoOrdinatoPerPeso_ConListaOrdinataMale(){
		List<Attrezzo> listaAttrezzi = new ArrayList<>();
		listaAttrezzi.add(libro);
		listaAttrezzi.add(piuma);
		listaAttrezzi.add(piombo);
		listaAttrezzi.add(ps);
		assertNotEquals(listaAttrezzi,this.borsa2.getContenutoOrdinatoPerPeso());
	}

	@Test
	public void testGetContenutoOrdinatoPerNome_NessunOggetto(){
		List<Attrezzo> listaAttrezzi = new ArrayList<>();
		assertEquals(listaAttrezzi,this.borsa.getContenutoOrdinatoPerNome());
	}

	@Test
	public void testGetContenutoOrdinatoPerNome_ConUnOggetto(){
		List<Attrezzo> listaAttrezzi = new ArrayList<>();
		listaAttrezzi.add(piuma);
		this.borsa.addAttrezzo(piuma);
		assertEquals(listaAttrezzi,this.borsa.getContenutoOrdinatoPerNome());
	}

	@Test
	public void testGetContenutoOrdinatoPerNome_ConVariOggetti(){
		List<Attrezzo> listaAttrezzi = new LinkedList<>();
		listaAttrezzi.add(libro);
		listaAttrezzi.add(piombo);
		listaAttrezzi.add(piuma);
		listaAttrezzi.add(ps);
		assertEquals(listaAttrezzi,this.borsa2.getContenutoOrdinatoPerNome());
	}

	@Test
	public void  testgetContenutoOrdinatoPerNome_ConListaOrdinataMale(){
		List<Attrezzo> listaAttrezzi = new ArrayList<>();
		listaAttrezzi.add(libro);
		listaAttrezzi.add(piuma);
		listaAttrezzi.add(piombo);
		listaAttrezzi.add(ps);
		assertNotEquals(listaAttrezzi,this.borsa2.getContenutoOrdinatoPerNome());
	}

	@Test
	public void testgetContenutoRaggruppatoPerPeso_NessunOggetto(){
		Map<Integer,Set<Attrezzo>> mappaAttrezzi = new HashMap<>();
		assertEquals(mappaAttrezzi ,this.borsa.getContenutoRaggruppatoPerPeso());
	}

	@Test
	public void testgetContenutoRaggruppatoPerPeso_ConUnOggetto(){
		Map<Integer,Set<Attrezzo>> mappaAttrezzi = new HashMap<>();
		Set<Attrezzo> piuma1 = new HashSet<>();
		piuma1.add(piuma);
		mappaAttrezzi.put(1,piuma1);
		this.borsa.addAttrezzo(piuma);
		assertEquals(mappaAttrezzi ,this.borsa.getContenutoRaggruppatoPerPeso());
	}

	@Test
	public void testgetContenutoRaggruppatoPerPeso_ConVariOggetti(){
		Map<Integer,Set<Attrezzo>> mappaAttrezzi = new HashMap<>();
		Set<Attrezzo> piombo10 = new HashSet<>();
		piombo10.add(piombo);
		Set<Attrezzo> libro_ps5 = new HashSet<>();
		libro_ps5.add(libro);
		libro_ps5.add(ps);
		Set<Attrezzo> piuma1 = new HashSet<>();
		piuma1.add(piuma);
		mappaAttrezzi.put(10,piombo10);
		mappaAttrezzi.put(1, piuma1);
		mappaAttrezzi.put(5,libro_ps5);
		assertEquals(mappaAttrezzi ,this.borsa2.getContenutoRaggruppatoPerPeso());
	}

}
