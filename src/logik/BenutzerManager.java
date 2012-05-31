package logik;
import java.util.regex.Pattern;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import db.Benutzer;
import db.Benutzergruppe;
import db.DbVerwaltung;

@WebServlet("/bib/BenutzerManager")
public class BenutzerManager extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Benutzer aktuellerBenutzer;
	private DbVerwaltung db;
	
	public void doGet(HttpServletRequest request,
	                    HttpServletResponse response)
	      throws ServletException, IOException {
		response.setContentType("text/html");
		db = new DbVerwaltung();
		PrintWriter out = response.getWriter();
		aktuellerBenutzer = new Benutzer();
		
		if(request.getParameter("do").equals("benutzerAendern")) 
		{
			// ÄNDERN
			out.print("<div id='buchbild'><!-- Bild --></div>");
			
		} 
		else if(request.getParameter("do").equals("benutzerEintragen")) 
		{
			String[] printOut = this.validateBenutzer(request); 
			
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

String[] validateBenutzer(HttpServletRequest request) {
	String[] print = {"",""};
	String check = "checked='checked'";
	String regMail = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	Pattern pat = Pattern.compile(regMail);
	
	print[0] += "<tr>";
	print[0] += "<td>Vorname*:</td>";
	if(!request.getParameter("vname").equals("")) {
		aktuellerBenutzer.setVorname(request.getParameter("vname"));
		print[0] += "<td><input type=\"text\" name=\"vname\" value="+request.getParameter("vname")+" /></td>";
	} else {
		print[1] += "Bitte Vorname eingeben<br/>";
		print[0] += "<td><input type=\"text\" name=\"vname\"/></td>";
	}
	print[0] += "</tr><tr>";
	print[0] += "<td>Nachname*:</td>";
	if(!request.getParameter("nname").equals("")) {
		aktuellerBenutzer.setNachname(request.getParameter("nname"));
		print[0] += "<td><input type=\"text\" name=\"nname\" value="+request.getParameter("nname")+" /></td>";
	} else {
		print[1] += "Bitte Nachname eingeben<br/>";
		print[0] += "<td><input type=\"text\" name=\"nname\" /></td>";
	}
	print[0] += "</tr><tr>";
	print[0] += "<td>E-Mailadresse*:</td>";
	if(!request.getParameter("email").equals("")) {
		if(pat.matcher(request.getParameter("email")).matches()) {
			aktuellerBenutzer.setEmail(request.getParameter("email"));
			print[0] += "<td><input type=\"email\" name=\"email\" value="+request.getParameter("email")+" /></td>";
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
	print[0] += "<td>Straï¿½e*:</td>";
	if(!request.getParameter("strasse").equals("")) {
		aktuellerBenutzer.setSTRAÃŸE(request.getParameter("strasse"));
		print[0] += "<td><input type=\"text\" name=\"strasse\" value="+request.getParameter("strasse")+" /></td>";
	} else {
		print[1] += "Bitte Strasse eingeben<br/>";
		print[0] += "<td><input type=\"text\" name=\"strasse\" /></td>";
	}
	print[0] += "</tr><tr>";
	print[0] += "<td>PLZ*:</td>";
	if(!request.getParameter("plz").equals("")) {
		aktuellerBenutzer.setPlz(request.getParameter("plz"));
		print[0] += "<td><input type=\"text\" name=\"plz\" value="+request.getParameter("plz")+" /></td>";
	} else {
		print[1] += "Bitte PLZ eingeben<br/>";
		print[0] += "<td><input type=\"text\" name=\"plz\" /></td>";
	}
	print[0] += "</tr><tr>";
	print[0] += "<td>Ort*:</td>";
	if(!request.getParameter("ort").equals("")) {
		aktuellerBenutzer.setOrt(request.getParameter("ort"));
		print[0] += "<td><input type=\"text\" name=\"ort\" value="+request.getParameter("ort")+" /></td>";
	} else {
		print[1] += "Bitte Ort eingeben<br/>";
		print[0] += "<td><input type=\"text\" name=\"ort\" /></td>";
	}
	print[0] += "</tr><tr>";
	print[0] += "<td>Telefon:</td>";
	if(!request.getParameter("telefon").equals("")) {
		aktuellerBenutzer.setTelefonnr(request.getParameter("telefon"));
		print[0] += "<td><input type=\"text\" name=\"telefon\" value="+request.getParameter("telefon")+" /></td>";
	} else {
		print[0] += "<td><input type=\"text\" name=\"telefon\" /></td>";
	}
	print[0] += "</tr><tr>";
	if(!request.getParameter("rechte").equals("0")) {
		
		Benutzergruppe bg = this.db.selectBenutzerGruppeUeberID(new Long(request.getParameter("rechte")));
		this.aktuellerBenutzer.setBenutzergruppe(bg);		
		
		print[0] += "<td>Berechtigung*:</td><td><select name='rechte'><option value='0'>Bitte wï¿½hlen</option><option value='3'";
		if(request.getParameter("rechte").equals("3")){
			print[0] += check;
		}
		print[0] += ">Kunde</option><option value='1'";
		if(request.getParameter("rechte").equals("1")){
			print[0] += check;
		}
		print[0] += ">Administrator</option><option value='2'";
		if(request.getParameter("rechte").equals("2")) {
			print[0] += check;
		}
		print[0] += ">Mitarbeiter</option></select></td>";
	} else {
		print[0] += "<td>Berechtigung*:</td><td><select name='rechte'><option value='0'>Bitte wï¿½hlen</option><option value='3'>Kunde</option><option value='1'>Administrator</option><option value='2'>Mitarbeiter</option></select></td>";
		print[1] += "Bitte Berechtigung auswï¿½hlen<br/>";
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




