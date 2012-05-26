package logik;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.List;


import db.Benutzer;
import db.DbVerwaltung;

public class LoginCheck extends HttpServlet{


	public static void login_pruefen(HttpServletRequest request, HttpServletResponse response)
			   throws ServletException,IOException
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String benutzerEmail = request.getParameter("login_benutzeremail");
		String pw = request.getParameter("login_passwort");
		
		
		DbVerwaltung db = new DbVerwaltung();
	    List<Benutzer>resultList = db.selectAll_Benutzer();
	    
	    for(Benutzer b:resultList)
	    {
	    	if(b.getEmail().equals(benutzerEmail) && b.getPasswort().equals(pw))
	    	{
	    		out.print("korrekt");
	    	}
	    	else
	    	{
	    		out.print("NICHT korrekt");
	    	}
	    }
	}

}
