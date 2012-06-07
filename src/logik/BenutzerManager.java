package logik;
import java.util.regex.Pattern;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import db.Benutzer;
import db.Benutzergruppe;
import db.DbVerwaltung;
/**
 * 
 * Kümmert sich um die Verwaltung des Benutzer.
 * 
 * @version 1.0
 * @author philipp.renerig
 *
*/
@WebServlet("/bib/BenutzerManager")
public class BenutzerManager extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Benutzer aktuellerBenutzer;
	private DbVerwaltung db;
	
	/**
	 * Die Methode verwaltet die Interaktionen 
	 * auf Benutzer: löschen, ändern, hinzufügen
	 * 
	 * @author philipp.renerig
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 */
	public void doGet(HttpServletRequest request,
	                    HttpServletResponse response)
	      throws ServletException, IOException {
		response.setContentType("text/html");
		db = new DbVerwaltung();
		PrintWriter out = response.getWriter();
		aktuellerBenutzer = new Benutzer();
		
		if(request.getParameter("do").equals("benutzerAendern")) 
		{
			String[] requestArray = {};
			this.aktuellerBenutzer = db.select_BenutzerUeberID(new Long(request.getParameter("bid")));
			
			if(request != null) {
				
				requestArray[0] = this.aktuellerBenutzer.getVorname();
				requestArray[1] = this.aktuellerBenutzer.getNachname();
				requestArray[2] = this.aktuellerBenutzer.getEmail();
				requestArray[3] = this.aktuellerBenutzer.getSTRAßE();
				requestArray[4] = this.aktuellerBenutzer.getPlz();
				requestArray[5] = this.aktuellerBenutzer.getOrt();
				requestArray[6] = this.aktuellerBenutzer.getTelefonnr();
				requestArray[7] = this.aktuellerBenutzer.getBenutzergruppe().getBerechtigung().toString();
			}
			
			String[] printOut = this.validateBenutzer(requestArray); 
			
			if(printOut[1].equals("true")) {
				out.print("true");
				db.updateBenutzer(this.aktuellerBenutzer);
			} else {
				out.print("<h2 style='margin-top:15px; margin-left:15px;'>Benutzer ändern</h2><table class='registrierung'>");
				out.print("<tr><td colspan='2' style='color:red;'>" + printOut[1] + "</td></tr>");
				out.print(printOut[0]);
			}
			
		} 
		else if(request.getParameter("do").equals("benutzerEintragen")) 
		{
			String[] requestArray = {"","","","","","","","","",""};
			if(request != null) {
				
				requestArray[0] = request.getParameter("vname");
				requestArray[1] = request.getParameter("nname");
				requestArray[2] = request.getParameter("email");
				requestArray[3] = request.getParameter("strasse");
				requestArray[4] = request.getParameter("plz");
				requestArray[5] = request.getParameter("ort");
				requestArray[6] = request.getParameter("telefon");
				requestArray[7] = request.getParameter("rechte");
			}
			String[] printOut = this.validateBenutzer(requestArray); 
			
			if(printOut[1].equals("true")) {
				out.print("true");
				db.insertBenutzer(this.aktuellerBenutzer);
			} else {
				out.print("<h2 style='margin-top:15px; margin-left:15px;'>Neuen Benutzer anlegen</h2><table class='registrierung'>");
				out.print("<tr><td colspan='2' style='color:red;'>" + printOut[1] + "</td></tr>");
				out.print(printOut[0]);
			}
		} 
		else if(request.getParameter("do").equals("benutzerLoeschen")) 
		{
			// LOESCHEN
			String benutzerid = request.getParameter("bid");
			long bid = Long.parseLong(benutzerid);
			Benutzer benutzer = db.select_BenutzerUeberID(bid);
			db.deleteBenutzer(benutzer);
		}
	  }
	
	DbVerwaltung getDb() {
		return this.db;
	}

/**
 * 
 * Validiert die Benutzereingaben
 * 
 * @version 0.01
 * @author philipp.renerig
 * 
 * @param String[] input Array der Eingaben
 * @return print[0] beinhaltet das Formular, mit den bereits eingegebenen Values
 * @return print[1] beinhaltet die Fehlermeldungen in Form eines Strings bzw. true wenn korrekt war
 *
 */
String[] validateBenutzer(String[] input) {
	
	
	String[] print = {"",""};
	String check = "checked='checked'";
	String regMail = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	Pattern pat = Pattern.compile(regMail);
	
	print[0] += "<tr>";
	print[0] += "<td>Vorname*:</td>";
	if(!input[0].equals("")) {
		aktuellerBenutzer.setVorname(input[0]);
		print[0] += "<td><input type=\"text\" name=\"vname\" value="+input[0]+" /></td>";
	} else {
		print[1] += "Bitte Vorname eingeben<br/>";
		print[0] += "<td><input type=\"text\" name=\"vname\"/></td>";
	}
	print[0] += "</tr><tr>";
	print[0] += "<td>Nachname*:</td>";
	if(!input[1].equals("")) {
		aktuellerBenutzer.setNachname(input[1]);
		print[0] += "<td><input type=\"text\" name=\"nname\" value="+input[1]+" /></td>";
	} else {
		print[1] += "Bitte Nachname eingeben<br/>";
		print[0] += "<td><input type=\"text\" name=\"nname\" /></td>";
	}
	print[0] += "</tr><tr>";
	print[0] += "<td>E-Mailadresse*:</td>";
	if(!input[2].equals("")) {
		if(pat.matcher(input[2]).matches()) {
			aktuellerBenutzer.setEmail(input[2]);
			print[0] += "<td><input type=\"email\" name=\"email\" value="+input[2]+" /></td>";
		}
		else {
			print[1] += "Keine valide E-Mailadresse<br/>";
			print[0] += "<td><input type=\"email\" name=\"email\" /></td>";
		}
		
	} else {
		print[1] += "Bitte E-Mailadresse eingeben<br/>";
		print[0] += "<td><input type=\"email\" name=\"email\" /></td>";
	}
	
	print[0] += "</tr><tr>";
	print[0] += "<td>Straße*:</td>";
	if(!input[3].equals("")) {
		aktuellerBenutzer.setSTRAßE(input[3]);
		print[0] += "<td><input type=\"text\" name=\"strasse\" value="+input[3]+" /></td>";
	} else {
		print[1] += "Bitte Strasse eingeben<br/>";
		print[0] += "<td><input type=\"text\" name=\"strasse\" /></td>";
	}
	print[0] += "</tr><tr>";
	print[0] += "<td>PLZ*:</td>";
	if(!input[4].equals("")) {
		aktuellerBenutzer.setPlz(input[4]);
		print[0] += "<td><input type=\"text\" name=\"plz\" value="+input[4]+" /></td>";
	} else {
		print[1] += "Bitte PLZ eingeben<br/>";
		print[0] += "<td><input type=\"text\" name=\"plz\" /></td>";
	}
	print[0] += "</tr><tr>";
	print[0] += "<td>Ort*:</td>";
	if(!input[5].equals("")) {
		aktuellerBenutzer.setOrt(input[5]);
		print[0] += "<td><input type=\"text\" name=\"ort\" value="+input[5]+" /></td>";
	} else {
		print[1] += "Bitte Ort eingeben<br/>";
		print[0] += "<td><input type=\"text\" name=\"ort\" /></td>";
	}
	print[0] += "</tr><tr>";
	print[0] += "<td>Telefon:</td>";
	if(!input[6].equals("")) {
		aktuellerBenutzer.setTelefonnr(input[6]);
		print[0] += "<td><input type=\"text\" name=\"telefon\" value="+input[6]+" /></td>";
	} else {
		print[0] += "<td><input type=\"text\" name=\"telefon\" /></td>";
	}
	print[0] += "</tr><tr>";
	if(!input[7].equals("0")) {
		
		Benutzergruppe bg = this.db.selectBenutzerGruppeUeberID(new Long(input[7]));
		this.aktuellerBenutzer.setBenutzergruppe(bg);		
		
		print[0] += "<td>Berechtigung*:</td><td><select name='rechte'><option value='0'>Bitte wählen</option><option value='3'";
		if(input[7].equals("3")){
			print[0] += check;
		}
		print[0] += ">Kunde</option><option value='1'";
		if(input[7].equals("1")){
			print[0] += check;
		}
		print[0] += ">Administrator</option><option value='2'";
		if(input[7].equals("2")) {
			print[0] += check;
		}
		print[0] += ">Mitarbeiter</option></select></td>";
	} else {
		print[0] += "<td>Berechtigung*:</td><td><select name='rechte'><option value='0'>Bitte wählen</option><option value='3'>Kunde</option><option value='1'>Administrator</option><option value='2'>Mitarbeiter</option></select></td>";
		print[1] += "Bitte Berechtigung auswählen<br/>";
	}
	
	
	print[0] += "<tr><td>";
	print[0] += "<div style=\"margin-top:20px;\">";
	print[0] += "<button class=\"button\" id=\"insert_benutzer\">Speichern</button>";
	print[0] += "<button class=\"button close\">Abbrechen</button>";
	print[0] += "</div></td></tr></table>";
	
	if(print[1].equals(""))
		print[1] = "true";
	return print;
	
	}
}




