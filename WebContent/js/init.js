var bib = bib || {};

$.extend(bib, {
	init: function() {
		bib.rechteSetzen();
		bib.addDataTable();
		bib.addAusleiheClick();
		bib.addAnmeldenClick();
		bib.addAbmeldenClick();
		bib.addKundeEintragen();
		bib.addRegistrierenClick();
		bib.addAusleihManagerClick();
		bib.addBenutzerManagerClick();
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
	
	rechteSetzen: function() {
		$.ajax({
			url: "AjaxController?do=getRecht",
			type: "GET",
			success: function(data) {
				$(".1,.2,.3").css("visibility","hidden");
				var i;
				var cString = "";
				for(i=1; i<=parseInt(data); i++) {
					cString += "." + i + ", ";
				}
				$(cString).css("visibility","visible");
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
	
	addDataTable: function() {
		$('#buecher').dataTable({
			"bProcessing": true,
			"sAjaxSource": "AjaxController?do=buecherListe",
	        "oLanguage": {
	            "sProcessing":   "Bitte warten...",
	            "sLengthMenu":   "_MENU_ Einträge anzeigen",
	            "sZeroRecords":  "Keine Bücher vorhanden.",
	            "sInfo":         "_START_ bis _END_ von _TOTAL_ Einträgen",
	            "sInfoEmpty":    "0 bis 0 von 0 Einträgen",
	            "sInfoFiltered": "(gefiltert von _MAX_  Einträgen)",
	            "sInfoPostFix":  "",
	            "sSearch":       "Suchen",
	            "sUrl":          "",
	            "oPaginate": {
	                "sFirst":    "Erster",
	                "sPrevious": "Zurück",
	                "sNext":     "Nächster",
	                "sLast":     "Letzter"
	            }
	        },
			"fnDrawCallback": function() {
				bib.addDetailClick();
				bib.addWarenkorbClick();
			}
	    });
		
		$('#benutzer').dataTable({
			"bProcessing": true,
			"sAjaxSource": "AjaxController?do=benutzerListe",
	        "oLanguage": {
	            "sProcessing":   "Bitte warten...",
	            "sLengthMenu":   "_MENU_ Einträge anzeigen",
	            "sZeroRecords":  "Keine Benutzer vorhanden.",
	            "sInfo":         "_START_ bis _END_ von _TOTAL_ Einträgen",
	            "sInfoEmpty":    "0 bis 0 von 0 Einträgen",
	            "sInfoFiltered": "(gefiltert von _MAX_  Einträgen)",
	            "sInfoPostFix":  "",
	            "sSearch":       "Suchen",
	            "sUrl":          "",
	            "oPaginate": {
	                "sFirst":    "Erster",
	                "sPrevious": "Zurück",
	                "sNext":     "Nächster",
	                "sLast":     "Letzter"
	            }
	        },
	        "fnDrawCallback": function() {
	        	bib.addBenutzerAendernClick();
	        	bib.addBenutzerLoeschenClick();
	        }
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
					            	window.location.href="/Online-Bibliothek/bib/Controller?do=benutzerListe";
					            }
					       }); 
					}
				});
			});
		});
	},
	
	addIsbnRueckgaengigClick: function() {
		$(".rueckgaengig").each(function() {
			
			$(this).click(function() {
				var pDiv = $(this).parent("div").parent("div");
				var isbn = $(this).attr("name");
				$.ajax({
					url: "AjaxController?do=isbnRueckgaengig",
					type: "GET",
					data: "isbn=" + isbn,
					success: function(data) {
						pDiv.remove();
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
						$.blockUI({ 
							message: data,
							onBlock: function() {
								bib.addaddBenutzerSpeichernClick;
								bib.addCloseClick();
							}
						});
					}
				}
			});
		});
	},
	
	addRegistrierenClicks: function() {
		
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
		$(".warenkorb").each(function() {
			$(this).click(function() {
				var isbn = $(this).attr("name");
				$.ajax({
					url: "AjaxController?do=mediumHinzufuegen",
					type: "GET",
					data: "isbn=" + isbn + "&kundennr=" + $("#KundenNr").text(),
					success: function(data) {
						$("#WarenkorbBereich").html(data);
						bib.addIsbnRueckgaengigClick();
						alert("steht drinne: " + $(this).parent().parent().html());
						//var status = $(this).parent().parent("td:eq(3)").text();
						//status -= 1;
						//$(this).parent().parent("td:eq(3)").text(status);
					}
				});
			});
		});
	},
	
	addDetailClick: function () {
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
	
	addRegistrierenClick: function() {
		$('#registrieren').click(function() {
			$.blockUI({ 
				message: $('#inc_benutzer'),
				onBlock:  function() {
					bib.addBenutzerSpeichernClick();
					bib.addCloseClick();
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

