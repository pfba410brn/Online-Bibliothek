package logik;
import java.util.List;



import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.eclipse.persistence.internal.sessions.remote.Transporter;

import db.Benutzer;
import db.Buch;
import db.DbVerwaltung;

import logik.JsonConverter;

@WebServlet("/bib/fetchSomething")
public class FetchSomething extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request,
	                    HttpServletResponse response)
	      throws ServletException, IOException {
		response.setContentType("text/html");
		DbVerwaltung db = new DbVerwaltung();
		PrintWriter out = response.getWriter();
		
		if(request.getParameter("do").equals("mediumDetail")) {
			Buch buch = db.select_BuchUeberISBN(request.getParameter("isbn"));
			out.print("<div id='buchbild'><!-- Bild --></div>");
			out.print("<div id='buchinfo'><table cellpadding='0' cellspacing='15px'>");
			out.print("<tr><td><b>Titel:</b></td><td>"+ buch.getTitel() +"</td></tr>");
			out.print("<tr><td><b>Autor:</b></td><td>"+ buch.getAutor() +"</td></tr>");
			out.print("<tr><td><b>ISBN:</b></td><td>"+ buch.getIsbn() +"</td></tr>");
			out.print("<tr><td><b>Verlag:</b></td><td>"+ buch.getVerlag() +"</td></tr>");
			out.print("</table></div><div style='clear: both;'></div><hr>");
			out.print("<div id='buchbeschreibung'><h3>Buchbeschreibung:</h3><p>"+buch.getKurzbeschreibung()+"</p></div>");
			
		} else if(request.getParameter("do").equals("benutzerListe")) {
			List<Benutzer> benutzerListe = db.selectAll_Benutzer();
			out.println(JsonConverter.convertBenutzer(benutzerListe));
		}
	  }


}




