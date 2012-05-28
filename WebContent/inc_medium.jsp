<!-- MEDIUM-DETAIL -->
<%@page import="db.Buch"%>
<%@page import="org.eclipse.persistence.internal.sessions.remote.Transporter"%>
<div id="buchbild">
	<img src="img/faust.jpg" width="220px" height="200px" alt="Faust Cover" />
</div>
<div id="buchinfo">
<%
	Transporter transporter = (Transporter)request.getAttribute("buch");
	if(transporter == null) {
		System.out.println("Transporter leer!");
	} else {
		Buch buch = (Buch) transporter.getObject();
		
		buch.getAutor();
		buch.getIsbn();
		buch.getExemplars();
		buch.getVerlag();
%>
	<table cellpadding="0" cellspacing="15px">
		<tr>
			<td><b>Titel:</b></td>
			<td><%= buch.getTitel() %></td>
		</tr>
		<tr>
			<td><b>Autor:</b></td>
			<td><%= buch.getTitel() %></td>
		</tr>
		<tr>
			<td><b>Status:</b></td>
			<td>Verfügbar</td>
		</tr>
		<tr>
			<td><b>ISBN:</b></td>
			<td><%= buch.getIsbn() %></td>
		</tr>
	</table>
</div>
<div style="clear: both;"></div>
<hr>
<div id="buchbeschreibung">
	<h3>Buchbeschreibung:</h3>
	<p><%= buch.getKurzbeschreibung() %></p>
</div>
<% } %>
<!-- /MEDIUM-DETAIL -->