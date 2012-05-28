package logik;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.eclipse.persistence.internal.oxm.schema.model.Include;

import java.util.*;


import db.Benutzer;
import db.DbVerwaltung;
@WebServlet("/bib/LoginCheck")
public class LoginCheck extends HttpServlet{


	protected void doGet (
		      HttpServletRequest request, HttpServletResponse response
		    ) throws ServletException, java.io.IOException 
		{
		boolean korrekt = false;
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String benutzerEmail = request.getParameter("login_benutzeremail");
		String pw = request.getParameter("login_passwort");
		long benutzerGruppe = 3;
		String benutzerVorname = "";
		String benutzerNachname = "";
		
		DbVerwaltung db = new DbVerwaltung();
	    List<Benutzer>resultList = db.selectAll_Benutzer();
	    
	    for(Benutzer b:resultList)
	    {    	
	    	if(b.getEmail().equals(benutzerEmail) && b.getPasswort().equals(pw))
	    	{
	    		benutzerGruppe = b.getBenutzergruppe().getGruppenId();
	    		benutzerVorname = b.getVorname().toString();
	    		benutzerNachname = b.getNachname().toString();
	    		korrekt = true;
	    		/* TEST
	    		out.println("korrekt \n");
	    		out.println(b.getEmail() + " " + b.getPasswort());
	    		out.println("\n");
		    	out.println(benutzerEmail + " " + pw + " " + benutzerGruppe);
		    	*/
	    	}

	    }
	    
	    if(korrekt)
	    {
	    	HttpSession session = request.getSession(true);
			if (session.isNew()) 
			{
				session.setAttribute("Email", benutzerEmail);
				session.setAttribute("Benutzergruppe", benutzerGruppe);
				session.setMaxInactiveInterval(3600); // Sekunden
			}
			/*
	    	out.print("korrekt");
	    	out.print("\n");
	    	Date now = new Date();
	    	String timestamp = now.toString();
	    	Cookie cookie = new Cookie ("Email", benutzerEmail);
	    	cookie.setMaxAge(365 * 24 * 60 * 60);
	    	response.addCookie(cookie); 
	    	//include('start.jsp');
	    	
	    	out.println("<PRE>"); // Preformatierter Text: Typewriter Schrift 
			out.println("Valid Id: " + request.isRequestedSessionIdValid());
			out.println("Cookie: " + request.isRequestedSessionIdFromCookie());
			out.println("URL-Rewrite: " + request.isRequestedSessionIdFromURL());
			if (session != null) 
			{
				out.println("Session-Id: " + session.getId());
				out.println("Timeout: " + session.getMaxInactiveInterval());
				out.println("Erzeugt am: " + new Date(session.getCreationTime()));
				out
						.println("Letzter Zugriff: "
								+ new Date(session.getLastAccessedTime()));
				Enumeration n =  session.getAttributeNames();
				while (n.hasMoreElements()) 
				{
					String s = (String) n.nextElement();
					out.println("Attribut: " + s + "=" + session.getAttribute(s));
				}
			}*/
			out.println(benutzerEmail + " " + pw + " " + benutzerGruppe);
			out.println("<table style='visibility:hidden;' class='benutzereingabe'>");
			out.println("<tr>");
			out.println("<td>Benutzeremail:</td>");
			out.println("<td><input type='text' id='login_benutzeremail' size='20' maxlength='50'/></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>Passwort:</td>");
			out.println("<td><input type='password' id='login_passwort' size='20' maxlength='50'/></td>");
			out.println("</tr>");
			out.println("</table>"); 
			out.println("<div style='color:green' id='login_fehler'>Hallo " + benutzerVorname + " " + benutzerNachname + "</div>");  
			out.println("<div id='login_btn'>");
			out.println("<b>");
			out.println("<input type='submit' id='abmelden' value='Abmelden'> I");
			out.println("<button id='registrieren'>Registrieren</button>");
			out.println("</b>");
			out.println("</div>");
			
	    }
	    else
	    {
	    	
	        HttpSession session = request.getSession(false);
	        if (session != null) 
	        {
	          //out.println("Sitzung Nr. " + session.getId()  + " wird nun geschlossen!");
	          session.invalidate();
	        }
	        else
	        {
	          //out.println("Sitzung ist bereits geschlossen!");
	        }
	        /*
	    	out.println("<PRE>"); // Preformatierter Text: Typewriter Schrift 
			out.println("Valid Id: " + request.isRequestedSessionIdValid());
			out.println("Cookie: " + request.isRequestedSessionIdFromCookie());
			out.println("URL-Rewrite: " + request.isRequestedSessionIdFromURL());
			session = request.getSession(false);
			if (session != null) 
			{
				out.println("Session-Id: " + session.getId());
				out.println("Timeout: " + session.getMaxInactiveInterval());
				out.println("Erzeugt am: " + new Date(session.getCreationTime()));
				out.println("Letzter Zugriff: " + new Date(session.getLastAccessedTime()));
				Enumeration n =  session.getAttributeNames();
				while (n.hasMoreElements()) 
				{
					String s = (String) n.nextElement();
					out.println("Attribut: " + s + "=" + session.getAttribute(s));
				}
			}	
			*/
	        out.println(benutzerEmail + " " + pw + " " + benutzerGruppe);
			out.println("<table class='benutzereingabe'>");
			out.println("<tr>");
			out.println("<td>Benutzeremail:</td>");
			out.println("<td><input type='text' id='login_benutzeremail' size='20' maxlength='50'/></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>Passwort:</td>");
			out.println("<td><input type='password' id='login_passwort' size='20' maxlength='50'/></td>");
			out.println("</tr>");
			out.println("</table>"); 
			out.println("<div id='login_fehler'>Falsche Anmeldedaten</div>");  
			out.println("<div id='login_btn'>");
			out.println("<b>");
			out.println("<input type='submit' id='anmelden' value='Anmelden'> I");
			out.println("<button id='registrieren'>Registrieren</button>");
			out.println("</b>");
			out.println("</div>");
			
	    }
	}

}
