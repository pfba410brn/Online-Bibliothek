<!-- BENUTZERREGISTRIERUNG -->
<form id="reg_Benutzer" method="post" action="" style="margin:25px;">
	<!-- Wie wird eine ID vergeben? -->
	<h2>Neuen Benutzer anlegen</h2>
	<table class="registrierung">
		<tr>
			<td>Vorname*:</td>
			<td><input type="text" name="vname" class="required" minlength="3" /></td>
		</tr>
			<tr>
			<td>Nachname*:</td>
			<td><input type="text" name="nname" class="required" minlength="3" /></td>
		</tr>
		<tr>
			<td>E-Mailadresse*:</td>
			<td><input type="email" name="email" class="required" /></td>
		</tr>
		<tr>
			<td>Straﬂe*:</td>
			<td><input type="text" name="strasse" class="required"  /></td>
		</tr>
		<tr>
			<td>PLZ*:</td>
			<td><input type="text" name="plz" class="required"  /></td>
		</tr>
		<tr>
			<td>Ort*:</td>
			<td><input type="text" name="ort" class="required"  /></td>
		</tr>
		<tr>
			<td>Telefon:</td>
			<td><input type="text" name="telefon" /></td>
		</tr>
		<tr>
		<td>
			<a class="button" id="insert_benutzer">Speichern</a>
			<a class="button" id="close">Abbrechen</a>
	</td>
	</tr>
	</table>
		
</form>



<!-- /BENUTZERREGISTRIERUNG -->