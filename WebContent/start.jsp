 <!-- START.JSP 
 @Autor: 	Sebastian Schnietz
 @Version:	1.2
 
 Die start.jsp ist das Grundgerüst der Webanwendung.
 Weitere Bereiche werden includiert (inc_xy.jsp). Der Bereich id="mitte" wird abhängig von dem
 Parameter "do" befüllt.
 
 -->
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Online-Bibliothek</title>
		<link rel="stylesheet" type="text/css" href="../css/bibliothek.css" />
		<script type="text/javascript" src="../js/jquery.min.js"></script>
		<script type="text/javascript" src="../js/jquery.dataTables.min.js"></script>
		<script type="text/javascript" src="../js/block.js"></script>
		<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
		<script type="text/javascript" src="../js/init_datatable.js"></script>
		<script type="text/javascript" src="../js/init.js"></script>
	</head>
	<body>
	
		<!-- Modal_Layer_Inhalte -->
		<div style="display:none">
			<div id="inc_benutzer"><jsp:include page="inc_benutzer.jsp"></jsp:include></div>
			<div class="growlUI" style="display:none">
            <h1></h1>
            <h2></h2>
        </div>
		</div>
		<!-- /Modal_Layer_Inhalte -->
	
		<img id="bgImg" src="../images/layout/hintergrund.png" width="100%" height="100%" />
		<div id="bodyDiv">
			<div id="alles">
	    	<div id="banner">
	    	  
	    	<span id="log"><jsp:include page="inc_login.jsp" flush="true"></jsp:include></span>
	    	</div>
				<div id="mitte">
					<%
						if(request.getParameter("do").equals("buecherListe")) {	
					%>
							<jsp:include page="inc_buecherListe.jsp"></jsp:include>
							<jsp:include page="inc_warenkorb.jsp"></jsp:include>
					<%
						} else if(request.getParameter("do").equals("benutzerListe")) {
					%>
						<jsp:include page="inc_benutzerListe.jsp"></jsp:include>
					<%
						} else if(request.getParameter("do").equals("exemplarListe")) {
					 %>
					 	<jsp:include page="inc_exemplarListe.jsp"></jsp:include>
					 <% } %> 
			  	<div style="clear:both;"></div>	
				</div>
				<jsp:include page="inc_footer.jsp"></jsp:include>
			</div>
		</div>
	</body>
</html>
  <!-- START.JSP -->