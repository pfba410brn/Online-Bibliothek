<!-- BENUTZERREGISTRIERUNG -->
	<h2 style="margin-top:15px; margin-left:15px;">Neuen Benutzer anlegen</h2><table class="registrierung"><tr>
			<td>Vorname*:</td>
			<td><input type="text" name="vname" /></td>
		</tr><tr>
			<td>Nachname*:</td>
			<td><input type="text" name="nname" /></td>
		</tr><tr>
			<td>E-Mailadresse*:</td>
			<td><input type="email" name="email" /></td>
		</tr><tr>
			<td>Straﬂe*:</td>
			<td><input type="text" name="strasse" /></td>
		</tr><tr>
			<td>PLZ*:</td>
			<td><input type="text" name="plz" /></td>
		</tr><tr>
			<td>Ort*:</td>
			<td><input type="text" name="ort" /></td>
		</tr><tr>
			<td>Telefon:</td>
			<td><input type="text" name="telefon" /></td>
		</tr><tr>
			<td>Berechtigung*:</td>
			<td><select name="rechte">
				<option value="0">Bitte w‰hlen</option>
				<option value="3">Kunde</option>
					<option value="1">Administrator</option>
					<option value="2">Mitarbeiter</option>
				</select></td>
		</tr><tr><td>
		<div style="margin-top:20px;">
			<button class="button" id="insert_benutzer">Speichern</button>
			<button class="button close">Abbrechen</button>
		</div></td></tr></table>
<!-- /BENUTZERREGISTRIERUNG -->