package logik;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.Benutzer;
import db.Buch;
import db.DbVerwaltung;
import db.Exemplar;
import db.ExemplarBenutzer;
import db.ExemplarBenutzerPK;



@WebServlet("/bib/Status")
public class Status extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Benutzer benutzer;
	private List<Exemplar> exemlarListe = new ArrayList<Exemplar>();
   
	
	public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            			throws ServletException, IOException {
		
		response.setContentType("text/html");
		DbVerwaltung db = new DbVerwaltung();
		PrintWriter out = response.getWriter();
		
		if(request.getParameter("isbn") != null) {
			
			/* NEUES BUCH HINZUFÜGEN */
			String isbn = request.getParameter("isbn");
			//out.println(isbn);
			
			Buch buch = db.select_BuchUeberISBN(isbn);
			List<Exemplar> exemplarListe =  db.selectAll_Exemplar();
			for (Exemplar exemplar : exemplarListe)
			{
				if (exemplar.getBuch().getIsbn().equals(buch.getIsbn()))
				{
					this.exemlarListe.add(exemplar);
					break;
				}
			}
		}
		
		if(request.getParameter("kundennr") != null && this.benutzer == null) {
			/* NEUEN KUNDEN HINZUFÜGEN ????*/
			String kundennr = request.getParameter("kundennr");
			this.benutzer = db.select_BenutzerUeberID(Long.parseLong(kundennr));
		}
		
		if(request.getParameter("do").equals("mediumHinzufuegen")) {
			//out.print("<h1>Hey das geht </h1>" + buch.getIsbn() + " mit dieser ISBN!!!");
			for (Exemplar exemplar : this.exemlarListe){
			
				out.print("<div style=\"width:190px; float:right;\">");
				out.print("<table width=\"190px\">");
				out.print("<tr><td><b>" + exemplar.getBuch().getTitel() + "</b></td></tr>");
				out.print("<tr><td>" + exemplar.getBuch().getAutor() + "</td></tr>");
				out.print("<tr><td>" +exemplar.getBuch().getIsbn() + "</td></tr>");
				out.print("</table>");
				out.print("</div>");
				out.print("<div style=\"width:45px; margin-top:20px;\"><input type=\"image\" name=\"absenden\" src=\"../images/icons/pfeil.png\" id=\"isbnZurueck\"></div>");
				out.print("<div style=\"clear:both;\"></div>");
				out.print("<hr />");
			}
			
			if (this.exemlarListe.size() > 0 && request.getParameter("do").equals("ausleihe"))
			{
				this.medienAusleihen();
			}
			
		}
	}



	private List<Exemplar> getExemlarListe() {
		return exemlarListe;
	}


	private void setExemlarListe(List<Exemplar> exemlarListe) {
		this.exemlarListe = exemlarListe;
	}


	private Benutzer getBenutzer() {
		return benutzer;
	}


	private void setBenutzer(Benutzer benutzer) {
		this.benutzer = benutzer;
	}
	
	private void medienAusleihen(){
		// TODO: Anpassen
		DbVerwaltung db = new DbVerwaltung();
		for (Exemplar exemplar : this.exemlarListe)
		{
			ExemplarBenutzerPK exemplarBenutzerPK = new ExemplarBenutzerPK();
			ExemplarBenutzer exemplarBenutzer = new ExemplarBenutzer();
			
			exemplarBenutzerPK .setBenutzerId(this.benutzer.getBenutzerId());
			exemplarBenutzerPK .setInventarnr(exemplar.getInventarnr());
			
			exemplarBenutzer.setBenutzer(this.benutzer);
			exemplarBenutzer.setDatum(new Date());
			exemplarBenutzer.setDauer(new BigDecimal (14.0));
			exemplarBenutzer.setExemplar(exemplar);
			exemplarBenutzer.setId(exemplarBenutzerPK);
			
			
			db.insertExemplarBenutzer(exemplarBenutzer);
		}
	}
	
	private void mediumZurueckgeben(){
		
	}
	
	
}
