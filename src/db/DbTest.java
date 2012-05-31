package db;

import java.util.List;

import jsonij.json.JSON;

import logik.JsonConverter;
/**
 * Datenbank Test Klasse fuer diverse Tests.
 * letzte Aenderung: 30.05.2012
 * @author Christian.Kauf
 * @version 0.01
 */

public class DbTest {

	/**
	 * Standard Main Methode
	 * @param args
	 */
	public static void main(String[] args) {
		

		
		DbVerwaltung db = new DbVerwaltung();
	    
		
//	    List<Benutzer>resultList = db.selectAll_Benutzer();
//	    List<Buch> buchListe = db.selectAll_Buecher();
//	    
//	    
//	   System.out.println(JsonConverter.convertBenutzer(resultList));
//	   System.out.println(JsonConverter.convertBuch(buchListe));

		System.out.println(JsonConverter.convertExemplarBenutzer(db.selectAll_ExemplarBenutzer()));
		

	}

}
