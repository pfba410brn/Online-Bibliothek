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
		out.println("<div style='color:#32CD32' id='login_fehler'><b>Erfolgreich Abgemeldet</b></div>");  
		out.println("<div id='login_btn'>");
		out.println("<b>");
		out.println("<a style='cursor:pointer;' id='anmelden'>Anmelden</a> I");
		out.println("<a style='cursor:pointer;' id='registrieren'>Registrieren</a>");
		out.println("</b>");
		out.println("</div>");  
	}
}
