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
			
			out.print("<h1>Hey das geht </h1>" + buch.getIsbn() + " mit dieser ISBN!!!");
			
		} else if(request.getParameter("do").equals("benutzerListe")) {
			List<Benutzer> benutzerListe = db.selectAll_Benutzer();
			out.println(JsonConverter.convertBenutzer(benutzerListe));
		}
	  }


}




