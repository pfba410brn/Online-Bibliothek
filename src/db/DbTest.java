package db;

import java.util.List;

import jsonij.json.JSON;

import logik.JsonConverter;

public class DbTest {

	/**
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

		
		if(db.selectVerlieheneExemplareProBenutzer(1001)!=null){
			System.out.println("");
		}
		System.out.println(" "+db.select_NächsteBenutzerID());

	}

}
