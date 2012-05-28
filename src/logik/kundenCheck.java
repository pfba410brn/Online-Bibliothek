package logik;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.Benutzer;
import db.DbVerwaltung;

@WebServlet("/bib/kundenCheck")
public class kundenCheck extends HttpServlet{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			   throws ServletException,IOException
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
	
		long benutzernr = Long.valueOf(request.getParameter("kundennummer")).longValue();
		
		DbVerwaltung db = new DbVerwaltung();
	    List<Benutzer>resultList = db.selectAll_Benutzer();
	    
	    Boolean gefunden = false;
	    
	    for(Benutzer b:resultList)
	    {
	    	if(b.getBenutzerId() == benutzernr)
	    	{
	    		gefunden = true;
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
			out.print("<td><input type=\"image\" id=\"uebernehmen\" name=\"uebernehmen\" src=\"../images/icons/ok_haken.png\"></td>");
			out.print("</tr>");
			out.print("<tr>");
			out.print("<td></td>");
			out.print("<td>Fehler: Kunde konnte nicht gefudnden werden! <a href=\"\">Registrieren</a></td>");
			out.print("<td><input type=\"image\" id=\"auswerfen\" src=\"../images/icons/cancel.png\"></td>");
			out.print("</tr>");
			out.print("</table>");
	    }
	}
}
