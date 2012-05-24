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
		<script type="text/javascript" src="../js/init.js"></script>
	</head>
	<body>
	
		<!-- Modal_Layer_Inhalte -->
		<div style="display:none">
			<div id="inc_benutzer"><jsp:include page="inc_benutzer.jsp"></jsp:include></div>
			<div id="inc_rueckgabe"><jsp:include page="inc_rueckgabe.jsp"></jsp:include></div>
			<div id="inc_ausleihe"><jsp:include page="inc_ausleihe.jsp"></jsp:include></div>
		</div>
		<!-- /Modal_Layer_Inhalte -->
	
		<img id="bgImg" src="../images/layout/hintergrund.png" width="100%" height="100%" />
		<div id="bodyDiv">
			<div id="alles">
	    	<div id="banner">
	    	  <!-- TODO: Kann das weg? -->
	    		<!--<div><img style="float:left;" src="img/logo2.png" alt="" /></div> <div id="logo">Onlinebibliothek Mega</div> -->
	    	<jsp:include page="inc_login.jsp"></jsp:include>
	    	</div>
				<div id="mitte">
					
					<jsp:include page="inc_buecherListe.jsp"></jsp:include>
					<jsp:include page="inc_warenkorb.jsp"></jsp:include>
			  	<div style="clear:both;"></div>	
				</div>
				<div id="footer">
					<jsp:include page="inc_footer.jsp"></jsp:include>
				</div>
				<!-- Ist der Überflüssig? -->
				<div></div>
			</div>
		</div>
	</body>
</html>