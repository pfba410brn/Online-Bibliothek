package logik;
import java.util.List;



import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import db.Benutzer;
import db.Buch;
import db.DbVerwaltung;
import db.ExemplarBenutzer;

import logik.JsonConverter;
/**
 * 
 * FetchListe holt den einen Bestand aus der Datenbank und gibt
 * ihn als JSON convertiert an die init.js zurück.
 * 
 * @version 1.2
 * @author philipp.renerig
 *
 */
@WebServlet("/bib/fetchListe")
public class FetchListe extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request,
	                    HttpServletResponse response)
	      throws ServletException, IOException {
		response.setContentType("text/json");
		DbVerwaltung db = new DbVerwaltung();
		PrintWriter out = response.getWriter();
		if(request.getParameter("do").equals("buecherListe")) {
			List<Buch> buchListe = db.selectAll_Buecher();
			out.println(JsonConverter.convertBuch(buchListe));
		} else if(request.getParameter("do").equals("benutzerListe")) {
			List<Benutzer> benutzerListe = db.selectAll_Benutzer();
			out.println(JsonConverter.convertBenutzer(benutzerListe));
		}  else if(request.getParameter("do").equals("exemplarListe")) {
			List<ExemplarBenutzer> exemplarBenutzerListe = db.selectAll_ExemplarBenutzer();
			out.print(JsonConverter.convertExemplarBenutzer(exemplarBenutzerListe));
		}
	}
}




