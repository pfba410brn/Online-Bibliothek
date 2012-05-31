<!-- FOOTER -->
<div id="footer">
	<% if(request.getParameter("do").equals("buecherListe")) { %>
	<button class="1" id="benutzerManager" style="text-align:left; float:left;">Benutzerverwaltung</button>
	<% } else { %>
		<button class="1" id="ausleihManager" style="text-align:left; float:left;">Ausleihverwaltung</button>
	<% } %>
	<button class="2" id="exemplarManager" style="text-align:left; float:left;">Ausgeliehene Exemplare</button>
	<div id="buttons">
		<button class="2" id="ausleihe">Ausleihen</button>
		<button class="2" id="rueckgabe">Zurückgeben</button>
	</div>
</div>
<!-- /FOOTER -->