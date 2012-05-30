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
import javax.servlet.http.HttpSession;

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
private List<Exemplar> warenkorbListe = new ArrayList<Exemplar>();;
private List<Exemplar> rueckgabeListe = new ArrayList<Exemplar>();;

  

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
		List<Exemplar> exemplarListe = db.selectAll_Exemplar();
		for (Exemplar exemplar : exemplarListe)
		{
			if (exemplar.getBuch().getIsbn().equals(buch.getIsbn()))
			{
				this.warenkorbListe.add(exemplar);
				break;
			}
		}
	}

	/*if(request.getParameter("kundennr") != null && this.benutzer == null) {
		 NEUEN KUNDEN HINZUFÜGEN ????
		String kundennr = request.getParameter("kundennr");
		this.benutzer = db.select_BenutzerUeberID(Long.parseLong(kundennr));
	}*/
	if(request.getParameter("do").equals("kundenCheck")) {
		long benutzernr = Long.valueOf(request.getParameter("kundennummer")).longValue();
		
	    List<Benutzer>resultList = db.selectAll_Benutzer();
	    
	    Boolean gefunden = false;
	    
	    for(Benutzer b:resultList)
	    {
	    	if(b.getBenutzerId() == benutzernr)
	    	{
	    		gefunden = true;
	    		this.benutzer = b;
	    		break;	
	    	}
	    }
	    
	    if(gefunden) {
		    out.print("<table>");
			out.print("<tr>");
			out.print("<td>KundenNr:</td>");
			out.print("<td><div id=\"KundenNr\">" +  benutzernr + "</div></td>");
			out.print("<td><input type=\"image\" id=\"auswerfen\" src=\"../images/icons/cancel.png\"></td>");
			out.print("</tr>");
			out.print("<tr>");
			out.print("<td></td>");
			out.print("<td></td>");
			out.print("<td></td>");
			out.print("</tr>");
			out.print("</table>");
	    } else {
	    	out.print("<table>");
    		out.print("<tr>");
			out.print("<td>KundenNr:</td>");
			out.print("<td><input type=\"text\" id=\"kundenID\" size=\"17\" maxlength=\"30\"/></td>");
			out.print("<td><input type=\"image\" id=\"kundeEintragen\" name=\"uebernehmen\" src=\"../images/icons/ok_haken.png\"></td>");
			out.print("</tr>");
			out.print("<tr>");
			out.print("<td colspan=\"3\">Fehler: Kunde konnte nicht gefunden werden! <a id=\"registrieren\">Registrieren</a></td>");
			out.print("</tr>");
			out.print("</table>");
	    }
	}
	if(request.getParameter("do").equals("warenkorbAusleihe")) {
		List<ExemplarBenutzer> ausleihVorgaenge = db.selectAll_ExemplarBenutzer();
		this.rueckgabeListe = new ArrayList<Exemplar>();
		if (ausleihVorgaenge != null)
			for (ExemplarBenutzer exBe : ausleihVorgaenge)
			{
				if (exBe.getBenutzer().getBenutzerId() == this.benutzer.getBenutzerId())
				{
					this.rueckgabeListe.add(exBe.getExemplar());
				}
			}
		
		for (Exemplar exemplar : this.rueckgabeListe)
		{
			out.print("<div style=\"width:190px; float:right;\">");
			out.print("<table width=\"190px\">");
			out.print("<tr><td><b>" + exemplar.getBuch().getTitel() + "</b></td></tr>");	
			out.print("<tr><td>" + exemplar.getBuch().getAutor() + "</td></tr>");
			out.print("<tr><td>" +exemplar.getBuch().getIsbn() + "</td></tr>");
			out.print("</table>");
			out.print("</div>");
			out.print("<div style=\"width:45px; margin-top:20px;\"><input type=\"image\" name=\"warenkorbRueckgabe\" src=\"../images/icons/rueckgabe.png\" id=\"warenkorbRueckgabe\"></div>");
			out.print("<div style=\"clear:both;\"></div>");
			out.print("<hr />");
		}		
	}
	if(request.getParameter("do").equals("mediumHinzufuegen")) {
		//out.print("<h1>Hey das geht </h1>" + buch.getIsbn() + " mit dieser ISBN!!!");
		for (Exemplar exemplar : this.warenkorbListe){

			out.print("<div style=\"width:190px; float:right;\">");
			out.print("<table width=\"190px\">");
			out.print("<tr><td><b>" + exemplar.getBuch().getTitel() + "</b></td></tr>");	
			out.print("<tr><td>" + exemplar.getBuch().getAutor() + "</td></tr>");
			out.print("<tr><td>" +exemplar.getBuch().getIsbn() + "</td></tr>");
			out.print("</table>");
			out.print("</div>");
			out.print("<div style=\"width:45px; margin-top:20px;\"><input type=\"image\" name=\""+exemplar.getBuch().getIsbn() +"\" src=\"../images/icons/rueckgaengig.png\" class=\"rueckgaengig\"></div>");
			out.print("<div style=\"clear:both;\"></div>");
			out.print("<hr />");
		}

		if (this.warenkorbListe.size() > 0 && request.getParameter("do").equals("ausleihe"))
		{
			long mitarbeiterID = 0;
			Benutzer mitarbeiter = null;
			HttpSession session = request.getSession(true);
			mitarbeiterID = (Long) session.getAttribute("Benutzerid");
			mitarbeiter = db.select_BenutzerUeberID(mitarbeiterID);
			this.medienAusleihen(mitarbeiter);
		}

	}
	if (request.getParameter("do").equals("kundenAuswerfen"))
	{
		this.benutzer = null;
		this.warenkorbListe = new ArrayList<Exemplar>();
		this.rueckgabeListe = new ArrayList<Exemplar>();
		
		out.print("<table>");
		out.print("<tr>");
		out.print("<td>KundenNr:</td>");
		out.print("<td><input type=\"text\" id=\"kundenID\" size=\"17\" maxlength=\"30\"/></td>");
		out.print("<td><input type=\"image\" id=\"kundeEintragen\" name=\"uebernehmen\" src=\"../images/icons/ok_haken.png\"></td>");
		out.print("</tr>");
		out.print("<tr>");
		out.print("<td></td>");
		out.print("<td></td>");
		out.print("<td></td>");
		out.print("</tr>");
		out.print("</table>");		
	}
	if (request.getParameter("do").equals("isbnRueckgaengig"))
	{
		String isbn = request.getParameter("isbn");
		this.exemplarAusListeEntfernen(isbn);
		
	}
	/* Zurückgeben der noch ausgeliehenen Medien*/
	if (request.getParameter("do").equals("isbnRueckgabe"))
	{
		String isbn = request.getParameter("isbn");
		ExemplarBenutzer exemplarBenutzer = null;
		this.exemplarAusListeEntfernen(isbn);
		
		List<ExemplarBenutzer> exemplarBenutzerList = db.selectAll_ExemplarBenutzer();
		for (ExemplarBenutzer exBe : exemplarBenutzerList)
		{
			if (exBe.getExemplar().getBuch().getIsbn().equals(isbn) && exBe.getBenutzer().getBenutzerId() == this.benutzer.getBenutzerId())
					{
						exemplarBenutzer = exBe;
						break;
					}
		}
		
		this.mediumZurueckgeben(exemplarBenutzer);
	}
}


private Benutzer getBenutzer() {
	return benutzer;
}


private void setBenutzer(Benutzer benutzer) {
	this.benutzer = benutzer;
}

private void medienAusleihen(Benutzer verliehenVon){
	// TODO: Anpassen
	DbVerwaltung db = new DbVerwaltung();
	
	for (Exemplar exemplar : this.warenkorbListe)
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
		exemplarBenutzer.setVerliehenVon(verliehenVon.getBenutzerId() + "");
		System.out.println("Vor insert");
		db.insertExemplarBenutzer(exemplarBenutzer);
		System.out.println("Geinserted");
	}
}

private void mediumZurueckgeben(ExemplarBenutzer exemplarBenutzer){
	DbVerwaltung db = new DbVerwaltung();
	db.deleteExemplarBenutzer(exemplarBenutzer);
}

private void exemplarAusListeEntfernen(String isbn){
	for (Exemplar exemplar : this.warenkorbListe)
	{
		if (exemplar.getBuch().getIsbn().equals(isbn))
			this.warenkorbListe.remove(exemplar);
	}
}


}

