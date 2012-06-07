/**
 * Die init.js kümmert sich um das Eventhandling. 
 * Alle Mausevents werden hier definiert.
 * 
 * Um welchen Button es sich handelt lässt sich an dem Selektor des
 * jQuery-Objetes "$("")" erkennen. Im großteil der Eventhandler wird
 * eine bestimmte Id angesprochen $("#ID") an die das Event gebunden wird.
 * 
 * Sowie die Ausgabe der Ajax-Abfrage "success: " erfolgt zumeist in ein
 * element mit einer bestimmten ID. success: function(data) { $("#IDAusgabe").html(data)} 
 * data entspricht hier dem Rückgabewert des Servlets bzw. der JSP
 * 
 * @version 1.1
 * @author philipp.renerig
 */

var bib = bib || {};

$.extend(bib, {
	init: function() {
		bib.rechteSetzen();
		bib.addAusleiheClick();
		bib.addAnmeldenClick();
		bib.addAbmeldenClick();
		bib.addKundeEintragen();
		bib.addRegistrierenClick();
		bib.addAusleihManagerClick();
		bib.addBenutzerManagerClick();
		bib.addExemplarManagerClick();
		},
	
	addAusleihManagerClick: function() {
		$("#ausleihManager").click(function(e) {
			window.location.href="/Online-Bibliothek/bib/Controller?do=buecherListe";
		});
	},
	
	addBenutzerManagerClick: function() {
		$("#benutzerManager").click(function(e) {
			window.location.href="/Online-Bibliothek/bib/Controller?do=benutzerListe";
		});	
	},
	
	addExemplarManagerClick: function() {
		$("#exemplarManager").click(function () {
			window.location.href="/Online-Bibliothek/bib/Controller?do=exemplarListe";
		});
	},
	
	rechteSetzen: function() {
		$.ajax({
			url: "AjaxController?do=getRecht",
			type: "GET",
			success: function(data) {
				$(".1,.2,.3").css("visibility","visible");
				var i;
				var cString = "";
				for(i=1; i<=parseInt(data)-1; i++) {
					cString += "." + i + ", ";
				}
				$(cString).css("visibility","hidden");
			}
		});
	},
	
	addCloseClick: function() {
		$(".close").each(function() {
			$(this).click(function(e) {
				$.unblockUI();
			});
		});
	},
	
	addBenutzerAendernClick: function() {
		$(".aendern").each(function() {
			$(this).click(function() {
				var bid = $(this).attr("name");
				$.ajax({
					url: "AjaxController?do=benutzerAendern",
					type: "GET",
					data: "bid=" + bid,
					success: function(data) {
						 $.blockUI({message: data });
					}
				});
			});
		});
	},
	
	addBenutzerLoeschenClick: function () {
		$(".loeschen").each(function() {
			$(this).click(function() {
				var bid = $(this).attr("name");
				$.ajax({
					url: "AjaxController?do=benutzerLoeschen",
					type: "GET",
					data: "bid=" + bid,
					success: function(data) {
							$(".growlUI h1").text("Löschen erfolgreich");
						  $(".growlUI h2").text("Benutzer erfolgreich gelöscht.");
						  $.blockUI({ 
					            message: $(".growlUI"), 
					            fadeIn: 700, 
					            fadeOut: 700, 
					            timeout: 2000, 
					            showOverlay: false, 
					            css: { 
					                width: '350px', 
					                top: '10px', 
					                left: '', 
					                right: '10px', 
					                border: 'none', 
					                padding: '5px', 
					                backgroundColor: '#000', 
					                '-webkit-border-radius': '10px', 
					                '-moz-border-radius': '10px', 
					                opacity: .6, 
					                color: '#fff' 
					            },
					            onBlock: function() {
					            	benutzerTable.fnReloadAjax();
					            }
					       }); 
					}
				});
			});
		});
	},
	
	addIsbnRueckgaengigClick: function() {
		$(".rueckgaengig").each(function() {
			$(this).unbind('click');
			$(this).click(function() {
				var pDiv = $(this).parent("div").parent("div");
				var isbn = $(this).attr("name");
				$.ajax({
					url: "AjaxController?do=isbnRueckgaengig",
					type: "GET",
					data: "isbn=" + isbn,
					success: function(data) {
						pDiv.remove();
						bib.addWarenkorbClick();
						bib.addDetailClick();
						buecherTable.fnReloadAjax();
					}
				});
			});
		});
	},
	
	addIsbnRueckgabeClick: function() {
		$(".warenkorbRueckgabe").each(function () {
			$(this).click(function() {
				var pDiv = $(this).parent("div").parent("div");
				var isbn = $(this).attr("name");
				$.ajax({
					url: "AjaxController?do=isbnRueckgabe",
					type: "GET",
					data: "isbn=" + isbn,
					success: function(data) {
						pDiv.remove();
					}
				});
			});
		});
	},
	
	addBenutzerSpeichernClick: function() {
		$("#insert_benutzer").unbind("click");
		$("#insert_benutzer").click(function(e) {
			var daten = "";
			$(".registrierung input, .registrierung select").each(function() {
				daten += $(this).attr("name")+"="+$(this).val()+"&";
			});
			$.ajax({
				url: "AjaxController?do=benutzerEintragen",
				type: "GET",
				data: 	daten,
				success: function(data) {
					$.unblockUI();
					if(data == "true") {
						
						$(".growlUI h1").text("Speichern erfolgreich");
						$(".growlUI h2").text("Benutzer wurde eingetragen.");
						$.blockUI({ 
							message: $(".growlUI"), 
					        fadeIn: 700, 
					        fadeOut: 700, 
					        timeout: 2000, 
					        showOverlay: false, 
					        centerY: false, 
					        css: { 
					        	width: '350px', 
					            top: '10px', 
					            left: '', 
					            right: '10px', 
					            border: 'none', 
					            padding: '5px', 
					            backgroundColor: '#000', 
					            '-webkit-border-radius': '10px', 
					            '-moz-border-radius': '10px', 
					            opacity: .6, 
					            color: '#fff',
					         }
						});
					} else {
						$('#registrieren').trigger("click", [data]);
					}
				}
			});
		});
	},
	
	addRegistrierenClick: function() {
		$('#registrieren').bind("click", function(e, daten) {
			if(!daten) {
				$.blockUI({ 
					message: $('#inc_benutzer'),
					onBlock:  function() {
						bib.addBenutzerSpeichernClick();
						bib.addCloseClick();
					}
				});
				} else {
					$.blockUI({ 
						message: daten,
						onBlock:  function() {
							bib.addBenutzerSpeichernClick();
							bib.addCloseClick();
						}
					});
				}
				
		});
	},
	
	addAnmeldenClick: function() {
		$("#anmelden").click(function() {
			$.ajax({
				url:"AjaxController?do=loginCheck",
				type:"GET",
				data: "login_benutzeremail="+$("#login_benutzeremail").val() + "&login_passwort=" + $("#login_passwort").val(),
				success:function(data) {
					$("#log").html(data);
					bib.addAnmeldenClick();
					bib.addAbmeldenClick();
					bib.addRegistrierenClick();
					bib.rechteSetzen();
				}
			});
		});
	},

	addAbmeldenClick: function() {
		$("#abmelden").click(function() {
			$.ajax({
				url:"AjaxController?do=ausloggen",
				type:"GET",
				success:function(data) {
					$("#login").html(data);
					bib.addAnmeldenClick();
					bib.addAusleiheClick();
					bib.rechteSetzen();
				}
			});
		});
	},
	
	addWarenkorbClick: function() {
		$(".warenkorb").unbind("click");
		$(".warenkorb").each(function() {
			$(this).click(function() {
				if($("#KundenNr").text() != "") {
				var isbn = $(this).attr("name");
				var link = $(this);
				var status = Number(link.parent().parent().children().eq(3).text());
				if(status == "0") {
					$(".growlUI h1").text("Fehler!");
					  $(".growlUI h2").text("Kein Exemplar mehr vorhanden!");
					  $.blockUI({ 
				            message: $(".growlUI"), 
				            fadeIn: 700, 
				            fadeOut: 700, 
				            timeout: 2000, 
				            showOverlay: false, 
				            centerY: false, 
				            css: { 
				                width: '350px', 
				                top: '10px', 
				                left: '', 
				                right: '10px', 
				                border: 'none', 
				                padding: '5px', 
				                backgroundColor: '#000', 
				                '-webkit-border-radius': '10px', 
				                '-moz-border-radius': '10px', 
				                opacity: .6, 
				                color: '#fff' 
				            },
				            onBlock: function() {
				            	$("#auswerfen").trigger("click");
				            }
				       }); 
				}else {
				$.ajax({
					url: "AjaxController?do=mediumHinzufuegen",
					type: "GET",
					data: "isbn=" + isbn + "&kundennr=" + $("#KundenNr").text(),
					success: function(data) {
						$("#WarenkorbBereich").html(data);
						bib.addIsbnRueckgaengigClick();
						status -= 1;
						link.parent().parent().children().eq(3).text(status);
					}
				});
				}
				} else {
					alert("Bitte zunächst einen Kunden eintragen!");
				}
			});
		});
	},
	
	addDetailClick: function () {
		$(".detail").unbind("click");
		$(".detail").each(function() {
			$(this).click(function(e){
				var isbn = $(this).attr("name");
				$.ajax({
					url: "AjaxController?do=mediumDetail",
					type: "GET",
					data: "isbn=" + isbn,
					success: function(data) {
						$.blockUI({ message: data });
						bib.addCloseClick();
					}
				});
			});
		});
	},
	
	
	addKundeEintragen: function() {
		$('#kundeEintragen').click(function() {
			$.ajax({
				  url: "AjaxController?do=kundenCheck",
				  type: "GET",
				  data: "kundennummer=" + $("#kundenID").val(),
				  success : function(data) {
					  $("#KundenBereich").html(data);
					  bib.addKundeEintragen();
					  bib.addWarenkorbAufloesen();
					  	$.ajax({
						  url: "AjaxController?do=warenkorbAusleihe",
						  	type: "GET",
						  	data: "kundennummer=" + $("#kundenID").val(),
						  	success : function(data) {
							  $("#RueckgabeBereich").html(data);
							  bib.addIsbnRueckgabeClick();
					  }
				  	});
				  }
			});
			bib.addRegistrierenClick();
			
		});
	},
	
	addWarenkorbAufloesen: function() {
		$('#auswerfen').click(function() {
			$.ajax({
				  url: "AjaxController?do=kundenAuswerfen",
				  type: "GET",
				  data: "kundennummer=" + $("#kundenID").val(),
				  success : function(data) {
					  $("#KundenBereich").html(data);
					  bib.addKundeEintragen();
					  $("#WarenkorbBereich, #RueckgabeBereich").html("");
					    buecherTable.fnReloadAjax();	  
				  }
			});
			
			$.ajax({
				url: "AjaxController?do=mediumHinzufuegen",
				type: "GET",
				success: function(data) {
					$("#WarenkorbBereich").html(data);
				}
			});
		});
	},
	
	
	
	addAusleiheClick: function() {
		$("#ausleihe").click(function() {
			$.ajax({
				  url: "AjaxController?do=ausleihe",
				  type: "GET",
				  success : function() {
					  $(".growlUI h1").text("Ausleihe erfolgreich");
					  $(".growlUI h2").text("Bücher wurden ausgeliehen.");
					  $.blockUI({ 
				            message: $(".growlUI"), 
				            fadeIn: 700, 
				            fadeOut: 700, 
				            timeout: 2000, 
				            showOverlay: false, 
				            centerY: false, 
				            css: { 
				                width: '350px', 
				                top: '10px', 
				                left: '', 
				                right: '10px', 
				                border: 'none', 
				                padding: '5px', 
				                backgroundColor: '#000', 
				                '-webkit-border-radius': '10px', 
				                '-moz-border-radius': '10px', 
				                opacity: .6, 
				                color: '#fff' 
				            },
				            onBlock: function() {
				            	$("#auswerfen").trigger("click");
				            }	
				       }); 
				  }
			});
		});
	}
});
$(bib.init);

