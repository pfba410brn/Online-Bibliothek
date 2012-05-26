<!-- WARENKORB -->
<div id="warenkorb">
 	
<!-- if(Kunde nicht eingeloggt)-->
	<form action="kunden_check.jsp">
    	<table>
        	<tr>
            	<td>KundenNr:</td>
				<td><input type="text" name="ID" size="17" maxlength="30"/></td>
				<td><input type="image" name="uebernehmen" src="../images/icons/ok_haken.png"></td>
				<!-- <input type="button" style="background-color: transparent; background-image: url(img/icons/ok_haken.png); background-repeat: no-repeat; width: 20px; height: 19px;"> -->
			</tr>
		</table>	
<!-- else-->
		<table>
        	<tr>
				<td>KundenNr:</td>
				<td><div id="KundenNr">312312321</div></td>
				<td><input type="image" name="auswerfen" src="../images/icons/cancel.png"></td>
			</tr> 
		</table>
	</form>
<!--end if-->
	<hr />	
	<h3>Warenkorb</h3>  
    <hr />	
		
   	<div style="width:190px; float:right;">
		<table width="190px">
			<tr><td><b>Faust</b></td></tr>
			<tr><td>Goethe</td></tr>
			<tr><td>31232132</td></tr>
			<tr><td>bis 27.05.2013</td></tr>
		</table>
	</div>
	<div style="width:45px; margin-top:20px;"><input type="image" name="absenden" src="../images/icons/pfeil.png"></div>
	<div style="clear:both;"></div>
    <hr />
    
	 <div style="width:190px; float:right;">
		<table width="190px">
			<tr><td><b>Irrungen Wirrungen</b></td></tr>
			<tr><td>Fontane</td></tr>
			<tr><td>31232132</td></tr>
			<tr><td>bis 02.01.2013</td></tr>
		</table>
	</div>
	<div style="width:45px; margin-top:20px;"><input type="image" name="absenden" src="../images/icons/pfeil.png"></div>
	<div style="clear:both;"></div>
    <hr />
       
	<div style="width:190px; float:right;">
		<table width="190px">
			<tr><td><b>**Titel**</b></td></tr>
			<tr><td>**Autor**</td></tr>
			<tr><td>**BuchNr**</td></tr>
			<tr><td>**Abgabefrist**</td></tr>
		</table>
	</div>
	<div style="width:45px; margin-top:20px;"><input type="image" name="absenden" src="../images/icons/pfeil.png"></div>
	<div style="clear:both;"></div>
    <hr />
    
	<!-- Wenn nichts ausgeliehen Wurde
	<div style="text-align:center; height:40px; margin-top:20px;">Kein Buch ausgeliehen</div>
       <hr />
	-->
 </div>
<!-- /WARENKORB -->