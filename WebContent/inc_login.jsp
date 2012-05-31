<%@page import="db.Benutzer"%>
<%@page import="db.DbVerwaltung"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="javax.servlet.*"%>
<%@ page import="javax.servlet.annotation.WebServlet"%>
<%@ page import="javax.servlet.http.*"%>
<!-- Login 
	HttpSession session_neu = request.getSession(false);
	if(session_neu != null) {
		String a =  session_neu.getId();
		out.println("session_id: " +  a);
		Enumeration n = session_neu.getAttributeNames();
		while(n.hasMoreElements()){
			String s = (String) n.nextElement();
			out.println(s + " = " + session_neu.getAttribute(s));
		}
	}
  -->
  <%
  HttpSession session_neu = request.getSession(false);
	Boolean korrekt = false;
	
	DbVerwaltung db = new DbVerwaltung();
  List<Benutzer>resultList = db.selectAll_Benutzer();
  	for(Benutzer b:resultList)
    {    	
    	if(b.getEmail().equals(request.getAttribute("login_benutzeremail")) && b.getPasswort().equals(request.getAttribute("login_passwort")))
    	{
				korrekt = true;
    	}
    }
  	
%>
<div id="login">
<% if(session_neu.getAttribute("Vorname") == null || korrekt) { %>
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
	<div id="login_fehler"><% if(!korrekt) {%><b>Falsche Anmeldedaten</b> <% } %> </div> 
	<div id="login_btn">
		<b>
			<a style="cursor:pointer;" id="anmelden">Anmelden</a> I
			<a style="cursor:pointer;" id="registrieren">Registrieren</a>
		</b>
	</div>
</div>
<div style="clear:both;"></div>
<% } else { %>
	<img style='float:left;' src="../images/silhouette_"+ <%= session_neu.getAttribute("Benutzergruppe") %> +".png">
	<div style='color:#32CD32; text-align:left; margin-left:15px;' id='login_fehler'><b> <%= session_neu.getAttribute("Vorname") %> <%= session_neu.getAttribute("Nachname") %></b></div>
	<div style='margin-top:59px;' id='login_btn'>
	<b>
	<a style='cursor:pointer;' id='abmelden'>Abmelden</a> I
	<a style='cursor:pointer;' id='registrieren'>Registrieren</a>
	</b>
	</div>
<% } %>
<!-- /Login -->