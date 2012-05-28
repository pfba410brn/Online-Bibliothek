<!-- Login -->
<%@ page language="java" %>
<%
String cookieName = "Email";
Cookie cookies [] = request.getCookies ();
Cookie myCookie = null;
if (cookies != null)
{
	for (int i = 0; i < cookies.length; i++) 
	{
		if (cookies [i].getName().equals (cookieName))
		{
		myCookie = cookies[i];
		break;
		}
	}
}
%>
<div id="login">
	<!-- Noch nicht eingeloggt -->
	<form action="LoginCheck" name="anmeldung" method="post">
	 	<table class="benutzereingabe">
	   		<tr>
	   			<td>Benutzeremail:</td>
	   			<td><input type="text" name="login_benutzeremail" size="20" maxlength="50"/></td>
	   		</tr>
            <tr>
            	<td>Passwort:</td>
            	<td><input type="password" name="login_passwort" size="20" maxlength="50"/></td>
            </tr>
	   </table> 
	<!-- Benutzernamen in login_fehler anzeigen wenn eingeloggt -->   
	<div id="login_fehler"></div> 
	<div id="login_btn">
		<b>
			<%
			if (myCookie == null) {
			%>
			No Cookie found with the name <%=cookieName%>
			<%
			} else {
			%> 
			Welcome: <%=myCookie.getValue()%>.
			<%
			}
			%>
			<input type="submit" id="anmelden" value="Anmelden"> I <!-- <button id="abmelden">Abmelden</button> -->
			<button id="registrieren">Registrieren</button>
		</b>
	</div>
	</form>
</div>
<div style="clear:both;"></div>
<!-- /Login -->