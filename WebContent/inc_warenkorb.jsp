<!-- WARENKORB -->
<%@page import="java.util.Date"%>
<div id="warenkorb" class="2">
 
 
 <!--  1) URL entnahme ISBN Ajax Controller   -->
 <!--  3) IST DIE KUNDENNUMMER ungleich NULL  -->
 
 
<!-- if(Kunde nicht eingeloggt)-->
	<div id="KundenBereich">
    	<table>
        	<tr>
            	<td>KundenNr:</td>
				<td><input type="text" name="ID" size="17" maxlength="30"/></td>
				<td><input type="image" id="kundeEintragen" name="uebernehmen" src="../images/icons/ok_haken.png"></td>
				<!-- <input type="button" style="background-color: transparent; background-image: url(img/icons/ok_haken.png); background-repeat: no-repeat; width: 20px; height: 19px;"> -->
			</tr>
		<!-- </table>	-->
<!-- else-->
		
			<!-- <table>  -->
	        	<tr>
					<td></td>
					<!-- <td><div id="KundenNr">312312321</div></td> -->
					<td></td>
					<!-- <td><input type="image" id="auswerfen" src="../images/icons/cancel.png"></td> -->
					<td></td>
				</tr> 
			</table>
		</div>
	
	<hr />	
	<h3>Warenkorb</h3>  
    <hr />	
		
    <div id="WarenkorbBereich"></div> 
    <div style="clear:both;"></div>
    <hr />	     
 </div>
<!-- /WARENKORB -->