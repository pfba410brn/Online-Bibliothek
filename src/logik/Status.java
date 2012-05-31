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


/**
 * Die Klasse Status bündelt die Business-Logik für den Ausleih- und Rückgabevorgang.
 * 
 * @version 1.6
 * @author sina.rest
 *
 */
@WebServlet("/bib/Status")
public class Status extends HttpServlet {

/**
*
*/
private static final long serialVersionUID = 1L;

private Benutzer benutzer;
private List<Exemplar> warenkorbListe = new ArrayList<Exemplar>();;
private List<Exemplar> rueckgabeListe = new ArrayList<Exemplar>();;


/**
 * Die Methode doGet wird über den AjaxController aufgerufen und bekommt dabei Requestparameter 
 * angehangen, die in dieser Methode je nach Verwendungszweck abgearbeitet werden.
 * 
 * @param request Der Requestparameter übergibt den Request
 * @param response Der Responseparameter übergibt den Response
 * @retrun void
 */
public void doGet(HttpServletRequest request,
            HttpServletResponse response)
             throws ServletException, IOException {

	response.setContentType("text/html");
	DbVerwaltung db = new DbVerwaltung();
	PrintWriter out = response.getWriter();
	
	/*
	 * Aufruf beim Parameter "kundenCheck"
	 * --> Nach der Eingabe und Bestätigung der Nummer des ausleihenden Kunden im Kundenbereich des
	 * Warenkorbs
	 * */
	if(request.getParameter("do").equals("kundenCheck")) {
		long benutzernr = Long.valueOf(request.getParameter("kundennummer")).longValue();
		Boolean gefunden = false;
		this.warenkorbListe = new ArrayList<Exemplar>();
		this.rueckgabeListe = new ArrayList<Exemplar>();
		
		/* Prüfung, ob der Kunde in der Datenbank vorhanden ist */
	    List<Benutzer>resultList = db.selectAll_Benutzer();
	    for(Benutzer b:resultList)
	    {
	    	if(b.getBenutzerId() == benutzernr)
	    	{
	    		gefunden = true;
	    		this.benutzer = b;
	    		break;	
	    	}
	    }
	    
	    /* Rückgabe, wenn der Kunde in der Datenbank vorhanden ist */
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
	    } 
	    /* Rückgabe, wenn der Kunde nicht in der Datenbank vorhanden ist */
	    else {
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
	/*  Aufruf beim Parameter "warenkorbAusleihe"
	 * --> Alle noch ausgeliehenen Medien des ausgewählten Benutzers werden aufgelistet
	 */
	if(request.getParameter("do").equals("warenkorbAusleihe")) {
		List<ExemplarBenutzer> ausleihVorgaenge = db.selectAll_ExemplarBenutzer();
		this.rueckgabeListe = new ArrayList<Exemplar>();
		if (ausleihVorgaenge != null)
			/* Selektion aller Ausleihvorgänge für den ausgewählten Benutzer */
			for (ExemplarBenutzer exBe : ausleihVorgaenge)
			{
				if (exBe.getBenutzer().getBenutzerId() == this.benutzer.getBenutzerId())
				{
					/* Alle Bücher, die der Benutezr noch ausgeliehen hat, werden der Liste "rueckgabeListe"
					 * hinzugefügt!*/
					this.rueckgabeListe.add(exBe.getExemplar());
				}
			}
		/* Alle Medien-Exemplare, die in der Liste rueckgabeListe sind, werden als div-container zurück gegeben*/
		for (Exemplar exemplar : this.rueckgabeListe)
		{
			out.print("<div>");
			out.print("<div style=\"width:190px; float:right;\">");
			out.print("<table width=\"190px\">");
			out.print("<tr><td><b>" + exemplar.getBuch().getTitel() + "</b></td></tr>");	
			out.print("<tr><td>" + exemplar.getBuch().getAutor() + "</td></tr>");
			out.print("<tr><td>" +exemplar.getBuch().getIsbn() + "</td></tr>");
			out.print("</table>");
			out.print("</div>");
			out.print("<div style=\"width:45px; margin-top:20px;\"><input type=\"image\" name=\""+ exemplar.getBuch().getIsbn() +"\" src=\"../images/icons/rueckgabe.png\" class=\"warenkorbRueckgabe\"></div>");
			out.print("<div style=\"clear:both;\"></div>");
			out.print("<hr />");
			out.print("</div>");
		}		
	}
	/*  Aufruf beim Parameter "mediumHinzufuegen"
	 * --> Ein Medium wird ausgewählt und über das Warenkorb-Sysmbol in den Warenkorb befördert
	 */
	if(request.getParameter("do").equals("mediumHinzufuegen")) {
		if(request.getParameter("isbn") != null) {
			String isbn = request.getParameter("isbn");

			Buch buch = db.select_BuchUeberISBN(isbn);
			List<Exemplar> exemplarListe = db.selectAll_Exemplar();
			for (Exemplar exemplar : exemplarListe)
			{
				if (exemplar.getBuch().getIsbn().equals(buch.getIsbn()))
				{
					if (!this.istExemplarVerliehen(exemplar) && !this.istExemplarSchonImWarenkorb(exemplar))
					{
						/* Ein Exemplar des Buchs (welches nicht ausgeliehen ist oder schon im Warenkorb vorhanden ist)
						 * mit der übergebenen ISBN wird dem Warenkorb beigefügt*/
						this.warenkorbListe.add(exemplar);
						break;
					}
				}
			}
		}

		for (Exemplar exemplar : this.warenkorbListe){
			/*
			 * Für jedes Medium im Warenkorb wird ein Div-Container erzeugt 
			 */
			out.print("<div>");
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
			out.print("</div>");
		}
	}
	/*  Aufruf beim Parameter "ausleihe"
	 * --> Der Ausleihvorgang wird angestoßen
	 */
	if (this.warenkorbListe.size() >= 0 && request.getParameter("do").equals("ausleihe"))
	{
		long mitarbeiterID = 0;
		Benutzer mitarbeiter = null;
		// TODO: Replace
		//HttpSession session = request.getSession(true);
		//mitarbeiterID = (Long) session.getAttribute("Benutzerid");
		mitarbeiter = db.select_BenutzerUeberID(new Long("3009"));
		out.print(this.medienAusleihen(mitarbeiter));
	}
	/*  Aufruf beim Parameter "kundenAuswerfen"
	 * --> Die bereits eingegebene Kundennummer wird wieder ausgeworfen
	 */
	if (request.getParameter("do").equals("kundenAuswerfen"))
	{
		this.benutzer = null;
		this.warenkorbListe = new ArrayList<Exemplar>();
		this.rueckgabeListe = new ArrayList<Exemplar>();
		/*
		 * Rückgabe der Kundentablelle für den Kundenbereich in der warenkorb_inc.jsp
		 */
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
	/*  Aufruf beim Parameter "isbnRueckgaengig"
	 * --> Ein Medium, das bereits in den Warenkorb gelegt wurde, wird wieder aus dieser Liste entfernt
	 */
	if (request.getParameter("do").equals("isbnRueckgaengig"))
	{
		String isbn = request.getParameter("isbn");
		this.exemplarAusListeEntfernen(isbn);
	}
	/*  Aufruf beim Parameter "isbnRueckgabe"
	 * --> Ein ausgeliehenes Medium wird von dem Kunden zurückgegeben
	 */
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

/**
 * Die Methode medienAusleihe realisiert den Ausleihvorgang. Alle im Warenkorb befindlichen Medien werden
 * für den angegebenen Kunden ausgeliehen, damit wird in der Datenbanktabelle "ExemplarBenutzer" eine neue 
 * Tupel angelegt.
 * 
 * @param verliehenVon Der Parameter enthält ein Benutzerobjekt, welches den Mitarbeiter repräsentiert, der das Buch an den Kunden verliehen hat
 * @retrun String Rückgabestring, der beeinhaltet, ob das Speichern erfolgreich war
 */
private String medienAusleihen(Benutzer verliehenVon){
	// TODO: Anpassen
	String tata = "Start: ";
	DbVerwaltung db = new DbVerwaltung();
	
	for (Exemplar exemplar : this.warenkorbListe)
	{
		ExemplarBenutzerPK exemplarBenutzerPK = new ExemplarBenutzerPK();
		ExemplarBenutzer exemplarBenutzer = new ExemplarBenutzer();

		exemplarBenutzerPK.setBenutzerId(this.benutzer.getBenutzerId());
		exemplarBenutzerPK.setInventarnr(exemplar.getInventarnr());
		
		tata += this.benutzer.getBenutzerId() + " - " + exemplar.getInventarnr() + "<br/>";
		
		exemplarBenutzer.setBenutzer(this.benutzer);
		exemplarBenutzer.setDatum(new Date());
		exemplarBenutzer.setDauer(new BigDecimal (14.0));
		exemplarBenutzer.setExemplar(exemplar);
		exemplarBenutzer.setId(exemplarBenutzerPK);
		exemplarBenutzer.setVerliehenVon(verliehenVon.getBenutzerId() + "");
		
		System.out.println("Vor insert");
		if(db.insertExemplarBenutzer(exemplarBenutzer)) {
			tata += " TRUE";
		} else {
			tata += " FALSE";
		}
	}
	return tata;
}
/**
 * Die Methode mediumZurueckgeben repäsentiert die Medienrcükgabe.
 * 
 * @param exemplarBenutzer Zurückzugebendes ExemplarBenutzer-Objekt 
 * @retrun void
 */
private void mediumZurueckgeben(ExemplarBenutzer exemplarBenutzer){
	DbVerwaltung db = new DbVerwaltung();
	db.deleteExemplarBenutzer(exemplarBenutzer);
}
/**
 * Die Methode exemplarAusListeEntfernen entfernt ein Objekt der Klasse Exemplar mit der 
 * übergebenen ISBN aus der Warenkorb-Liste
 * 
 * @param isbn ISBN Nummer des zu entfernenden Exemplar-Objektes
 * @retrun void
 */
private void exemplarAusListeEntfernen(String isbn)
{
	Exemplar exemplar = null;
	System.out.println("Anzahl elemente" + this.warenkorbListe.size());
	for (int i = 0; i < this.warenkorbListe.size(); i++)
	{
		exemplar = this.warenkorbListe.get(i);
		if (exemplar.getBuch().getIsbn().equals(isbn))
			break;	
	}
	this.warenkorbListe.remove(exemplar);
	System.out.println("Anzahl elemente" + this.warenkorbListe.size());
}

/**
 * Die Methode istExemplarVerliehen gibt zurück, ob das übergebene Exemplar bereits verliehen wurde
 * 
 * @param exemplar Das zu überprüfende Exemplar
 * @retrun boolean Gibt an, ob das Exemplar schon verliehen wurde oder nicht
 */
private boolean istExemplarVerliehen(Exemplar exemplar){
	DbVerwaltung db = new DbVerwaltung();
	boolean exemplarVerliehen = false;
	List<ExemplarBenutzer> exemplarBenutzer = db.selectAll_ExemplarBenutzer();
	for (ExemplarBenutzer exBe : exemplarBenutzer)
	{
		if (exBe.getExemplar().getInventarnr().equals(exemplar.getInventarnr()))
		{
			exemplarVerliehen = true;
			break;
		}
		
	}
	
	return exemplarVerliehen;
}

/**
 * Die Methode istExemplarSchoImWarenkorb gibt an, ob sich das übergebene Exemplar bereits im Warenkorb befindet
 *  
 * @param exempalr Das zu prüfende Exemplar
 * @retrun boolean Gibt an, ob das Exemplar schon im Warenkorb ist oder nicht
 */
private boolean istExemplarSchonImWarenkorb(Exemplar exemplar){
	DbVerwaltung db = new DbVerwaltung();
	boolean exemplarImWarenkorb = false;
	for (Exemplar ex : this.warenkorbListe)
	{
		if (ex.getInventarnr().equals(exemplar.getInventarnr()))
		{
			exemplarImWarenkorb = true;
			break;
		}
	}
	return exemplarImWarenkorb;
}


}

