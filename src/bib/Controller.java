package bib;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/bib/Controller")
public class Controller extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8875458007177265635L;
	
	static String codes[][] = {
		new String[] { "buecherListe", "/start.jsp", "buecher"  },
		new String[] { "benutzerListe", "/start.jsp", "benutzer" },
		};
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doPost(req, resp);
	}

	public void doGet(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		String fcode = rq.getParameter("do");
		fcode = fcode == null ? codes[0][0] : fcode;
		String target = null;
		boolean gefunden = false;
		for (int i = 0; i < codes.length; i++) {
			if (fcode.equals(codes[i][0])) {
				rq.setAttribute(fcode, codes[i]);
				target = codes[i][1];
				gefunden = true;
				break;
			}
		}
		
		PrintWriter out = rs.getWriter();
		if (gefunden) {
			rq.getRequestDispatcher(target).forward(rq, rs);
			out.print(rq);			
		} 
		else
			out.print("<H1>Kein Ziel für diese Anforderung!</H1>");
	}

}
