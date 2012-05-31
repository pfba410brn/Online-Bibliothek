package logik;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import db.Buch;
import db.DbVerwaltung;
/**
 * 
 * Die Klasse FetchSomething uebergibt alle Informationen zur Buchdetailseite
 * 
 * @version 1.2
 * @author philipp.renerig
 *
 */
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
			out.print("<div id='buchinfo'><table cellpadding='0' cellspacing='15px'>");
			out.print("<tr><td><b>Titel:</b></td><td>"+ buch.getTitel() +"</td></tr>");
			out.print("<tr><td><b>Autor:</b></td><td>"+ buch.getAutor() +"</td></tr>");
			out.print("<tr><td><b>ISBN:</b></td><td>"+ buch.getIsbn() +"</td></tr>");
			out.print("<tr><td><b>Verlag:</b></td><td>"+ buch.getVerlag() +"</td></tr>");
			out.print("</table></div><div style='clear: both;'></div><hr>");
			out.print("<div id='buchbeschreibung'><h3>Buchbeschreibung:</h3>");
			if(buch.getKurzbeschreibung() == null)
			{
				out.print("<p>Keine Beschreibung</p></div>");
			}
			else
			{
				out.print("<p>" + buch.getKurzbeschreibung()+"</p></div>");
			}
			out.print("<button class='button close' id='close2'>Schlieﬂen</button>");
			
		} else if (request.getParameter("do").equals("getRecht")) {
			HttpSession session = request.getSession(true);
			if (session.getAttribute("Benutzergruppe") == null) {
				out.print("3");
			} else {
				out.print(session.getAttribute("Benutzergruppe"));
			}
		}
	  }


}




