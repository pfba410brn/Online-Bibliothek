 <!-- WARENKORB 
 @Autor: 	Sina Rest
 @Version:	0.9
 
 Die inc_warenkorb JSP-Seite repräsentiert den Warenkorb-Bereich. Dieser wird in die Start JSP inkludiert.
 Umschlossen wird der Warenkorb mit einem div-Bereich "Warenkorb", in dem sich ein Kundenbereich, zur Registrierung
 des Kunden, der Medien leihen möchte, eines Warenkorb-div-Bereichs mit allen auszuleihenden Medien und ein Rückgabe-
 div-Bereich mit den noch vom Kunden ausgeliehenen Medien. 
 
 -->
<%@page import="java.util.Date"%>

<div id="warenkorb" class="2">
	<div id="KundenBereich">
    	<table>
        	<tr>
            	<td>KundenNr:</td>
				<td><input type="text" id="kundenID" size="17" maxlength="30"/></td>
				<td><input type="image" id="kundeEintragen" name="uebernehmen" src="../images/icons/ok_haken.png"></td>
				<!-- <input type="button" style="background-color: transparent; background-image: url(img/icons/ok_haken.png); background-repeat: no-repeat; width: 20px; height: 19px;"> -->
			</tr>
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
    <hr />
    
	<h3>Ausgeliehen</h3>  
    <hr />
    <div id="RueckgabeBereich"></div>
    <div style="clear:both;"></div>
    <hr />	   
      
 </div>
<!-- /WARENKORB -->