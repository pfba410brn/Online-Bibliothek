<!-- Login -->
<div id="login">
	<!-- Noch nicht eingeloggt -->
	<form action="LoginCheck.java" name="anmeldung" method="post">
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
	<div id="login_fehler">Benutzername oder Passwort falsch.</div> 
	<div id="login_btn">
		<b>
			<input type="submit" value="Absenden"><button id="anmelden">Anmelden</button> I <!-- <button id="abmelden">Abmelden</button> -->
			<button id="registrieren">Registrieren</button>
		</b>
	</div>
	</form>
</div>
<div style="clear:both;"></div>
<!-- /Login -->