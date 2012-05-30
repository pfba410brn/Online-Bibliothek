<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="javax.servlet.*"%>
<%@ page import="javax.servlet.annotation.WebServlet"%>
<%@ page import="javax.servlet.http.*"%>
<!-- Login -->
<%
	String message = (String) request.getAttribute("Email");
	String bg = (String) request.getAttribute("Benutzergruppe");
	out.println(message);
	out.println(bg);
%>
<div id="login">
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
	<div id="login_fehler"></div> 
	<div id="login_btn">
		<b>
			<a style="cursor:pointer;" id="anmelden">Anmelden</a> I
			<a style="cursor:pointer;" id="registrieren">Registrieren</a>
		</b>
	</div>
</div>
<div style="clear:both;"></div>
<!-- /Login -->