package logik;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.http.HttpServlet;
@WebServlet("/bib/Ausloggen")
public class Ausloggen extends HttpServlet
{
	protected void doGet (
		      HttpServletRequest request, HttpServletResponse response
		    ) throws ServletException, java.io.IOException 
		{
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();	
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
        
    	//"Benutzername oder Passwort falsch."
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
		out.println("<div id='login_fehler'>Erfolgreich Abgemeldet</div>");  
		out.println("<div id='login_btn'>");
		out.println("<b>");
		out.println("<input type='submit' id='anmelden' value='Anmelden'> I");
		out.println("<button id='registrieren'>Registrieren</button>");
		out.println("</b>");
		out.println("</div>");  
	}
}
