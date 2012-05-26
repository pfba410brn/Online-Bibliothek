package logik;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jsonij.json.JSON;
import jsonij.json.JSONMarshaler;

import db.Benutzer;
import db.Buch;

public class JsonConverter {

	 
	public static String convertBenutzer(List<Benutzer> benutzerList){

	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	
		for (Benutzer benutzer : benutzerList) {

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("benutzerId",benutzer.getBenutzerId());
			map.put("vorname",benutzer.getVorname());
			map.put("nachname",benutzer.getNachname());
			map.put("email",benutzer.getEmail());
			map.put("telefonNr",benutzer.getTelefonnr());
			map.put("straﬂe",benutzer.getSTRAﬂE());
			map.put("plz",benutzer.getPlz());
			map.put("ort",benutzer.getOrt());
		//	map.put("passwort",benutzer.getPasswort());
			list.add(map);
		}	
		JSON result = JSONMarshaler.marshalObject(list);
		String jsonString = result.getRoot().toJSON();

	    return jsonString;
	}	
	
	public static String convertBuch(List<Buch> buchList){

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
			for (Buch buch : buchList) {

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("isbn",buch.getIsbn());
				map.put("autor",buch.getAutor());
				map.put("titel",buch.getTitel());
				map.put("mediumId",buch.getMediumId().toString());
				map.put("verlag",buch.getVerlag());

				list.add(map);
			}	
			JSON result = JSONMarshaler.marshalObject(list);
			String jsonString = result.getRoot().toJSON();

		    return jsonString;
		}	
}
