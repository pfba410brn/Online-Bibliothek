package logik;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/bib/Status")
public class Status extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

   
	
	public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            			throws ServletException, IOException {
		
		if(request.getParameter("isbn") != null) {
			
			/* NEUES BUCH HINZUFÜGEN */
			PrintWriter out = response.getWriter();
			out.println(request.getParameter("isbn"));
		}
		
		if(request.getParameter("kundennr") != null) {
			/* NEUEN KUNDEN HINZUFÜGEN */
			
		}
	}
	
}
