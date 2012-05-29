package logik;
import java.util.List;



import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import db.Benutzer;
import db.DbVerwaltung;

import logik.JsonConverter;

@WebServlet("/bib/BenutzerManager")
public class BenutzerManager extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request,
	                    HttpServletResponse response)
	      throws ServletException, IOException {
		response.setContentType("text/html");
		DbVerwaltung db = new DbVerwaltung();
		PrintWriter out = response.getWriter();
		
		if(request.getParameter("do").equals("benutzerAendern")) 
		{
			// ÄNDERN
			out.print("<div id='buchbild'><!-- Bild --></div>");
		} 
		else if(request.getParameter("do").equals("benutzerEintragen")) 
		{
			out.print("vname: "+request.getParameter("vname"));
			out.print("<a class=\"button\" id=\"close\">Abbrechen</a>");
			List<Benutzer> benutzerListe = db.selectAll_Benutzer();
			// meine Aenderungen
			String benutzerEmail = request.getParameter("geaenderte_benutzeremail");
			
			for(Benutzer b:benutzerListe) 
			{	
				if(b.getEmail().equals(benutzerEmail))
		    	{
					// FEHLER Mail schon vorhanden

		    	}
				else
				{	
					// Benutzer eintragen!
				}
				// ENDE
			}
			
			
		} 
		else if(request.getParameter("do").equals("benutzerLoeschen")) 
		{
			// LOESCHEN
			String benutzerid = ""; // benuzter wird uebergeben
			long bid = Long.parseLong(benutzerid);
			Benutzer benutzer = db.select_BenutzerUeberID(bid);

			db.deleteBenutzer(benutzer);
		}
	  }


}




