package logik;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Pattern;



import java.io.*;
import java.math.BigDecimal;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import db.Benutzer;
import db.Benutzergruppe;
import db.DbVerwaltung;

import logik.JsonConverter;

@WebServlet("/bib/BenutzerManager")
public class BenutzerManager extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Benutzer aktuellerBenutzer;
	
	public void doGet(HttpServletRequest request,
	                    HttpServletResponse response)
	      throws ServletException, IOException {
		response.setContentType("text/html");
		DbVerwaltung db = new DbVerwaltung();
		PrintWriter out = response.getWriter();
		aktuellerBenutzer = new Benutzer();
		
		if(request.getParameter("do").equals("benutzerAendern")) 
		{
			// ÄNDERN
			out.print("<div id='buchbild'><!-- Bild --></div>");
			
		} 
		else if(request.getParameter("do").equals("benutzerEintragen")) 
		{
			if(this.validateBenutzer(request).equals("true")) {
				out.print("true");
			} else {
				out.print(this.validateBenutzer(request));
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

String validateBenutzer(HttpServletRequest request) {
	String fehler = "";
	String print = "";
	String regMail = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	Pattern pat = Pattern.compile(regMail);
	
	print += "<h2 style='margin-top:15px; margin-left:15px;'>Neuen Benutzer anlegen</h2><table class='registrierung'><tr>";
	print += "<td>Vorname*:</td>";
	if(!request.getParameter("vname").equals("")) {
		aktuellerBenutzer.setVorname(request.getParameter("vname"));
		print += "<td><input type=\"text\" name=\"vname\" value="+request.getParameter("vname")+" /></td>";
	} else {
		fehler += "Bitte Vorname eingeben<br/>";
		print += "<td><input type=\"text\" name=\"vname\"/></td>";
	}
	print += "</tr><tr>";
	print += "<td>Nachname*:</td>";
	if(!request.getParameter("nname").equals("")) {
		aktuellerBenutzer.setNachname(request.getParameter("nname"));
		print += "<td><input type=\"text\" name=\"nname\" value="+request.getParameter("nname")+" /></td>";
	} else {
		fehler += "Bitte Nachname eingeben<br/>";
		print += "<td><input type=\"text\" name=\"nname\" /></td>";
	}
	print += "</tr><tr>";
	print += "<td>E-Mailadresse*:</td>";
	if(!request.getParameter("email").equals("")) {
		if(pat.matcher(request.getParameter("email")).matches()) {
			aktuellerBenutzer.setEmail(request.getParameter("email"));
			print += "<td><input type=\"email\" name=\"email\" value="+request.getParameter("email")+" /></td>";
		}
		else {
			fehler += "Keine valide E-Mailadresse<br/>";
			print += "<td><input type=\"email\" name=\"email\" /></td>";
		}
		
	} else {
		fehler += "Bitte E-Mailadresse eingeben<br/>";
		print += "<td><input type=\"email\" name=\"email\" /></td>";
	}
	
	print += "</tr><tr>";
	print += "<td>Straße*:</td>";
	if(!request.getParameter("strasse").equals("")) {
		aktuellerBenutzer.setSTRAßE(request.getParameter("strasse"));
		print += "<td><input type=\"text\" name=\"strasse\" value="+request.getParameter("strasse")+" /></td>";
	} else {
		fehler += "Bitte Strasse eingeben<br/>";
		print += "<td><input type=\"text\" name=\"strasse\" /></td>";
	}
	print += "</tr><tr>";
	print += "<td>PLZ*:</td>";
	if(!request.getParameter("plz").equals("")) {
		aktuellerBenutzer.setPlz(request.getParameter("plz"));
		print += "<td><input type=\"text\" name=\"plz\" value="+request.getParameter("plz")+" /></td>";
	} else {
		fehler += "Bitte PLZ eingeben<br/>";
		print += "<td><input type=\"text\" name=\"plz\" /></td>";
	}
	print += "</tr><tr>";
	print += "<td>Ort*:</td>";
	if(!request.getParameter("ort").equals("")) {
		aktuellerBenutzer.setOrt(request.getParameter("ort"));
		print += "<td><input type=\"text\" name=\"ort\" value="+request.getParameter("ort")+" /></td>";
	} else {
		fehler += "Bitte Ort eingeben<br/>";
		print += "<td><input type=\"text\" name=\"ort\" /></td>";
	}
	print += "</tr><tr>";
	print += "<td>Telefon:</td>";
	if(!request.getParameter("telefon").equals("")) {
		print += "<td><input type=\"text\" name=\"telefon\" value="+request.getParameter("telefon")+" /></td>";
	} else {
		print += "<td><input type=\"text\" name=\"telefon\" /></td>";
	}
	print += "</tr>";
	print += "<tr><td>";
	print += "<div style=\"margin-top:20px;\">";
	print += "<button class=\"button\" id=\"insert_benutzer\">Speichern</button>";
	print += "<button class=\"button close\">Abbrechen</button>";
	print += "</div></td></tr></table>";
	
	if(fehler.equals(""))
		return "true";
	else
		return print;
}
}




