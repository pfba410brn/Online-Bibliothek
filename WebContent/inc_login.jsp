<!-- Login -->
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
			<input type="submit" id="anmelden" value="Anmelden"> I
			<button id="registrieren">Registrieren</button>
		</b>
	</div>
</div>
<div style="clear:both;"></div>
<!-- /Login -->