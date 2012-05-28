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


	protected void doPost (
		      HttpServletRequest request, HttpServletResponse response
		    ) throws ServletException, java.io.IOException 
		{
		boolean korrekt = false;
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String benutzerEmail = request.getParameter("login_benutzeremail");
		String pw = request.getParameter("login_passwort");
		String benutzerGruppe = "";
		
		DbVerwaltung db = new DbVerwaltung();
	    List<Benutzer>resultList = db.selectAll_Benutzer();
	    
	    for(Benutzer b:resultList)
	    {
	    	
	    	if(b.getEmail().equals(benutzerEmail) && b.getPasswort().equals(pw))
	    	{
	    		benutzerGruppe = b.getBenutzergruppe().toString();
	    		korrekt = true;
//	    		out.print("korrekt");
//	    		out.print(b.getEmail() + " " + b.getPasswort());
//		    	out.print(benutzerEmail + " " + pw);
	    	}
	    	else
	    	{
	    		korrekt = false;
	    		
	    	}
	    }
	    
	    if(korrekt)
	    {
	    	out.print("korrekt");
	    	HttpSession session = request.getSession(true);
			if (session.isNew()) 
			{
				session.setAttribute("Email", benutzerEmail);
				session.setAttribute("Benutzergruppe", benutzerGruppe);
				session.setMaxInactiveInterval(3600); // Sekunden
			}
			/*
	    	Date now = new Date();
	    	String timestamp = now.toString();
	    	Cookie cookie = new Cookie ("Email", benutzerEmail);
	    	cookie.setMaxAge(365 * 24 * 60 * 60);
	    	response.addCookie(cookie);
	    	include('start.jsp'); */   	
	    }
	    else
	    {
	    	//out.print("NICHT korrekt");
	    	//"Benutzername oder Passwort falsch."
	    }
	}

}
