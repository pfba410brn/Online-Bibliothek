package logik;
import java.util.List;



import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import db.Buch;
import db.DbVerwaltung;

import logik.JsonConverter;

@WebServlet("/bib/fetchBuecherListe")
public class FetchBuecherListe extends HttpServlet {

	private static final long serialVersionUID = 1L;

public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
	
	response.setContentType("text/json");
	DbVerwaltung db = new DbVerwaltung();
	List<Buch> buchListe = db.selectAll_Buecher();
	
    PrintWriter out = response.getWriter();
    out.println(JsonConverter.convertBuch(buchListe));
    //String ta = "{\"aaData\":[[\"rororo\",\"<a class='warenkorb' href='xx.html'>&nbsp;</a>\",\"Karin Slaughter\",\"Vergiss mein Nicht\",\"9783499232435\"]]}";
    //out.println(ta);
  }
}




