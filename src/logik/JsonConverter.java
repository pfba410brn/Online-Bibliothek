package logik;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.sun.image.codec.jpeg.TruncatedFileException;

import db.Benutzer;
import db.Buch;
import db.DbVerwaltung;
import db.ExemplarBenutzer;
/**
 * Converter Klasse die verschiedene Objektlisten in Json Strings konvertiert
 * letzte Aenderung: 30.05.2012
 * @author Christian.Kauf
 * @version 0.01
 */
public class JsonConverter {

	/**
	 * Konvertiert eine Benutzerliste in einen Json String
	 * @param benutzerList Liste von Benutzern die konvertiert werden
	 * @return String Json String
	 */
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

	

	/**
	 * Konvertiert eine ExemplarBenutzerliste in einen Json String
	 * @param exemplarBenutzerList Liste von ExemplarBenutzer die konvertiert werden
	 * @return String Json String
	 */
	@SuppressWarnings("deprecation")
	public static String convertExemplarBenutzer(List<ExemplarBenutzer> exemplarBenutzerList) {
		String string = "";
		String result = "{\"aaData\":[";
		int counter = 0;
		for (ExemplarBenutzer ex : exemplarBenutzerList) {
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(ex.getDatum());
			
			cal2.add(Calendar.DAY_OF_WEEK, ex.getDauer().intValue());
			Date date=cal2.getTime();
			
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
					date.toGMTString().substring(0, 12)+
					"\"]";
			if(counter != exemplarBenutzerList.size() -1)
				result += string + ",";
			else
				result += string;
			counter++;
		}
		result += "]}";
		return result;
	}
	
	
	/**
	 * Konvertiert eine Buchliste in einen Json String
	 * @param buchList Liste von Buchern die konvertiert werden
	 * @return String Json String
	 */
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
		result += "]}";
		return result;
	}
}
