<!-- Login -->
<div id="login">
	<form action="login_check.jsp" name="anmeldung" method="post">
	 	<table class="benutzereingabe">
	   	<tr>
	   		<td>Benutzername:</td>
	   		<td><input type="text" name="Benutzername" size="20" maxlength="50"/></td>
	   	</tr>
	     <tr>
	     	<td>Passwort:</td>
	     	<td><input type="password" name="Passwort" size="20" maxlength="50"/></td>
	     </tr>
	   </table>
	 </form>
	<div id="login_fehler">Benutzername oder Passwort falsch.</div>
	<div id="login_btn">
		<b><button id="anmelden">Anmelden</button> I <button id="registrieren">Registrieren</button></b>
	</div>
</div>
<div style="clear:both;"></div>
<!-- /Login -->