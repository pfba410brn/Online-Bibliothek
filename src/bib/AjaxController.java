package bib;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logik.JsonConverter;

import db.Buch;
import db.DbVerwaltung;

/**
 * 
 * Der Ajax-Controller nimmt alle Ajax-HTTP-Requests entgegen und leitet
 * diese an die entsprechende Logik-Klassen weiter.
 * 
 * 
 * @version 1.0
 * @author philipp.renerig
 *
 */
@WebServlet("/bib/AjaxController")
public class AjaxController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	static DbVerwaltung db = new DbVerwaltung();
	static List<Buch> buchListe = db.selectAll_Buecher();
	
	static String tata = JsonConverter.convertBuch(buchListe);

	static String codes[][] = {
		new String[] { "buecherListe", "/bib/fetchListe" },
		new String[] { "kundenCheck", "/bib/Status" },
		new String[] { "mediumDetail", "/bib/fetchSomething" },
		new String[] { "mediumHinzufuegen", "/bib/Status"} ,
		new String[] { "benutzerListe", "/bib/fetchListe" },
		new String[] { "exemplarListe", "/bib/fetchListe" },
		new String[] { "loginCheck", "/inc_login.jsp"},
		new String[] { "ausloggen", "/bib/Ausloggen"},
		new String[] { "ausleihe", "/bib/Status" },
		new String[] { "benutzerLoeschen", "/bib/BenutzerManager" },
		new String[] { "benutzerEintragen", "/bib/BenutzerManager" },
		new String[] { "benutzerAendern", "/bib/BenutzerManager" },
		new String[] { "kundenAuswerfen", "/bib/Status"},
		new String[] { "getRecht", "/bib/fetchSomething" },
		new String[] { "isbnRueckgabe", "/bib/Status" },
		new String[] { "isbnRueckgaengig", "/bib/Status" },
		new String[] { "warenkorbAusleihe", "/bib/Status" }
	};
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doPost(req, resp);
	}

	public void doGet(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		String fcode = rq.getParameter("do");
		fcode = fcode == null ? codes[0][0] : fcode;
		String target = null;
		boolean gefunden = false;
		for (int i = 0; i < codes.length; i++) {
			if (fcode.equals(codes[i][0])) {
				rq.setAttribute(fcode, codes[i]);
				target = codes[i][1];
				gefunden = true;
				break;
			}
		}
		
		PrintWriter out = rs.getWriter();
		if (gefunden) {
			rq.getRequestDispatcher(target).forward(rq, rs);
			out.print(rq);			
		} 
		else
			out.print("<H1>Kein Ziel f�r diese Anforderung!</H1>");
	}

}

