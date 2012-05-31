<!-- LOGIN
 
	 @Autor: 	Sebastian Schnietz
	 @Version:	1.1
	 
	 Die inc_login ueberprueft und sichert die Eingaben des Benutzers in den Anmeldebereich.
	 Nach Betaetigung des Anmelde-Buttons werden die Eingaben Email und Passwort ueberprueft,
	 anschliessend werden die Daten des Benutzers aus der Datenbank ausgelesen und in eine Session gespeichert.
	 
  -->
<%@page import="db.Benutzer"%>
<%@page import="db.DbVerwaltung"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="javax.servlet.*"%>
<%@ page import="javax.servlet.annotation.WebServlet"%>
<%@ page import="javax.servlet.http.*"%>

  <%
  	HttpSession session_neu = request.getSession(true);
	boolean korrekt = false;
	int eingabe = 0;
	String benutzerEmail="";
	String pw ="";
	long benutzerGruppe = 3;
	long benutzerId = 0;
	String benutzerVorname = "";
	String benutzerNachname = "";
	DbVerwaltung db = new DbVerwaltung();
  	List<Benutzer>resultList = db.selectAll_Benutzer();
	
	if(session_neu.getAttribute("Email") == null)
	{
		benutzerEmail = request.getParameter("login_benutzeremail");
	}
	else
	{
		benutzerEmail = (String) session_neu.getAttribute("Email");
	}
	
	if(session_neu.getAttribute("Email") == null)
	{
		pw = request.getParameter("login_passwort");
	}
	else
	{
		pw = (String) session_neu.getAttribute("Email");
	}
		
  	for(Benutzer b:resultList)
    {    	
    	if(b.getEmail().equals(benutzerEmail) && b.getPasswort().equals(pw))
    	{
				korrekt = true;
				benutzerGruppe = b.getBenutzergruppe().getGruppenId();
	    		benutzerVorname = b.getVorname().toString();
	    		benutzerNachname = b.getNachname().toString();
	    		benutzerId = b.getBenutzerId();
    	}
    }
  	if(korrekt)
  	{	
  		session_neu.setAttribute("Pw", pw);
		session_neu.setAttribute("Email", benutzerEmail);
		session_neu.setAttribute("Benutzergruppe", benutzerGruppe);
		session_neu.setAttribute("Benutzerid", benutzerId);
		session_neu.setAttribute("Vorname", benutzerVorname);
		session_neu.setAttribute("Nachname", benutzerNachname);
		session_neu.setMaxInactiveInterval(3600);
		response.setIntHeader("Refresh", 0);
  	}
  	
  	Enumeration n = session_neu.getAttributeNames();
	while(n.hasMoreElements()){
		String s = (String) n.nextElement();
		System.out.println(s + " = " + session_neu.getAttribute(s));}
  	
%>
<div id="login">
<% if(session_neu.getAttribute("Vorname") == null) { %>
	<!-- Noch nicht eingeloggt -->
	 	<table class="benutzereingabe">
	   		<tr>
	   			<td>Benutzeremail:</td>
	   			<td><input type="text" id="login_benutzeremail" size="20" maxlength="50"/></td>
	   		</tr>
            <tr>
            	<td>Passwort:</td>
            	<td><input type="password" id="login_passwort" size="20" maxlength="50"/></td>
            </tr>
	   </table> 
	<!-- Benutzernamen in login_fehler anzeigen wenn eingeloggt -->   
	<div id="login_fehler"><% if(request.getParameter("do").equals("loginCheck")) { %><b>Falsche Anmeldedaten</b> <% } else { %><b> </b> <% } %></div> 
	<div id="login_btn">
		<b>
			<a style="cursor:pointer;" id="anmelden">Anmelden</a> I
			<a style="cursor:pointer;" id="registrieren">Registrieren</a>
		</b>
	</div>
<% } else { %>
	<img style='float:left;' src="../images/silhouette_<%= session_neu.getAttribute("Benutzergruppe") %>.png">
	<div style='color:#32CD32; text-align:left; margin-left:35px;' id='login_fehler'><b> <%= session_neu.getAttribute("Vorname") %> <%= session_neu.getAttribute("Nachname") %></b></div>
	<div style='margin-top:59px;' id='login_btn'>
	<b>
	<a style='cursor:pointer;' id='abmelden'>Abmelden</a> I
	<a style='cursor:pointer;' id='registrieren'>Registrieren</a>
	</b>
	</div>
<% } %>
</div>
<div style="clear:both;"></div>
<!-- /Login -->