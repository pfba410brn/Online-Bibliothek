package logik;

import java.util.List;

import db.Buch;
import db.DbVerwaltung;
/**
 * Testklasse zum Testen des Systems mit hohem Datenvolumen.
 * letzte Aenderung: 04.06.2012
 * @author Christian.Kauf
 * @version 0.01
 */
public class LastTest {

	
	/**
	 * Konvertiert eine Buchliste in einen Json String
	 * @param buchList Liste von Buchern die konvertiert werden
	 * @param faktor Faktor zum begrenzen der Anzahl Stringelemente
	 * @return String Json String 
	 */
	public static String createTestBuchString(List<Buch> buchList,int faktor) {
		DbVerwaltung db = new DbVerwaltung();
		String string = "";
		String result = "{\"aaData\":[";
		for (int i = 0; i < faktor; i++) {
			int counter = 0;
			if(i>0){
				result+=",";
			}
			for (Buch buch : buchList) {
	
				string = "[\""
						+ buch.getIsbn()
						+ "\",\""
						+ buch.getTitel()
						+ "\",\""
						+ buch.getAutor()
						+ "\",\"XYZ"//+db.getBuchStatus(buch)
						+ "\",\"<a class='warenkorb 2' name='"
						+ buch.getIsbn() + "'></a><a class='detail' name='"
						+ buch.getIsbn() + "'></a>\"]";
				if (counter != buchList.size() - 1) {
					result += string + ",";
				} else {
					result += string;
				}
				counter++;
			}
	   }
		result += "]}";
		return result;
	}
	
	
}
