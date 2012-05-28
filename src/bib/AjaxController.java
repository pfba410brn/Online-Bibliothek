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


@WebServlet("/bib/AjaxController")
public class AjaxController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	static DbVerwaltung db = new DbVerwaltung();
	static List<Buch> buchListe = db.selectAll_Buecher();
	static String tata = JsonConverter.convertBuch(buchListe);

	static String codes[][] = {
		new String[] { "buecherListe", "/bib/fetchListe" },
		new String[] { "kundeEintragen", "/bib/Status" },
		new String[] { "mediumDetail", "/bib/fetchSomething" },
		new String[] { "benutzerListe", "/bib/fetchListe" },
		new String[] { "loginCheck", "/bib/LoginCheck"},
		new String[] { "benutzerLoeschen", "/" },
		new String[] { "benutzerEintragen", "/" },
		new String[] { "benutzerAendern", "/" } };
	
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
			out.print("<H1>Kein Ziel für diese Anforderung!</H1>");
	}

}
