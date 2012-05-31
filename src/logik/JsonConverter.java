package logik;

import java.util.List;

import db.Benutzer;
import db.Buch;
import db.DbVerwaltung;
import db.ExemplarBenutzer;

public class JsonConverter {

	public static String convertBenutzer(List<Benutzer> benutzerList) {
		String string = "";
		String result = "{\"aaData\":[";
		int counter = 0;
		for (Benutzer benutzer : benutzerList) {
			string = "[\"" +
					benutzer.getBenutzerId() +
					"\",\"" +
					benutzer.getVorname() + " " + benutzer.getNachname() +
					"\",\"" +
					benutzer.getEmail() +
					"\",\"" +
					"<a class='aendern' name='"
					+ benutzer.getBenutzerId() + "'></a><a class='loeschen' name='"
					+ benutzer.getBenutzerId() + "'></a>\"]";
			if(counter != benutzerList.size() -1)
				result += string + ",";
			else
				result += string;
			counter++;
		}
		result += "]}";
		return result;
	}

	

	public static String convertExemplarBenutzer(List<ExemplarBenutzer> exemplarBenutzerList) {
		String string = "";
		String result = "{\"aaData\":[";
		int counter = 0;
		for (ExemplarBenutzer ex : exemplarBenutzerList) {
			string = "[\"" +
					ex.getBenutzer().getBenutzerId()+
					"\",\"" +
					ex.getBenutzer().getVorname() + " " + ex.getBenutzer().getNachname() +
					"\",\"" +
					ex.getExemplar().getInventarnr() +
					"\",\"" +
					ex.getExemplar().getBuch().getTitel() +
					"\",\"" +
					ex.getExemplar().getZustand() +
					"\",\"" +
					ex.getVerliehenVon()+
					"\",\"" +
					ex.getDatum()+
					"\",\"" +
					ex.getDauer()+
					" Tage\"]";
			if(counter != exemplarBenutzerList.size() -1)
				result += string + ",";
			else
				result += string;
			counter++;
		}
		result += "]}";
		return result;
	}
	
	
	public static String convertBuch(List<Buch> buchList) {
		DbVerwaltung db = new DbVerwaltung();
		String string = "";
		String result = "{\"aaData\":[";
		int counter = 0;
		for (Buch buch : buchList) {
			string = "[\""
					+ buch.getIsbn()
					+ "\",\""
					+ buch.getTitel()
					+ "\",\""
					+ buch.getAutor()
					+ "\",\""+db.getBuchStatus(buch)
					+ "\",\"<a class='warenkorb' name='"
					+ buch.getIsbn() + "'></a><a class='detail' name='"
					+ buch.getIsbn() + "'></a>\"]";
			if (counter != buchList.size() - 1) {
				result += string + ",";
			} else {
				result += string;
			}
			counter++;
		}
		result += "]}";
		return result;
	}
}
