package logik;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jsonij.json.JSON;
import jsonij.json.JSONMarshaler;

import db.Benutzer;
import db.Buch;
import db.DbVerwaltung;

public class JsonConverter {

	public static String convertBenutzer(List<Benutzer> benutzerList) {

		//List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
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
			
			/*Map<String, Object> map = new HashMap<String, Object>();
			map.put("benutzerId", benutzer.getBenutzerId());
			map.put("vorname", benutzer.getVorname());
			map.put("nachname", benutzer.getNachname());
			map.put("email", benutzer.getEmail());
			map.put("telefonNr", benutzer.getTelefonnr());
			map.put("stra�e", benutzer.getSTRA�E());
			map.put("plz", benutzer.getPlz());
			map.put("ort", benutzer.getOrt());
			// map.put("passwort",benutzer.getPasswort());*/
			//list.add(map);
		}
		//JSON result = JSONMarshaler.marshalObject(list);
		//String jsonString = result.getRoot().toJSON();
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
