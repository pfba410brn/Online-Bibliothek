package logik;

import java.util.List;

import db.Benutzer;
import db.Buch;
import db.DbVerwaltung;

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
					"<a class='delete' href='/Online-Bibliothek/bib/AjaxController?do=benutzerLoeschen&benutzerID=" +
					benutzer.getBenutzerId() + "'></a>\"]";
			if(counter != benutzerList.size() -1)
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
					+ "\",\"<a class='warenkorb' href='/Online-Bibliothek/bib/AjaxController?do=buchWaehlen&isbn="
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
