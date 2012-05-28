var bib = bib || {};

$.extend(bib, {
	init: function() {
		bib.addDataTable();
		bib.addEventHandler();
		bib.addAnmeldenClick();
		bib.addAbmeldenClick();
		bib.addKundeEintragen();
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
						
					}
				});
			});
		});
	},
	
	
	addBenutzerSpeichernClick: function() {
		$("#insert_benutzer").click(function() {
			$("#reg_Benutzer").validate({
				success: function(label) {
					$.ajax({
						url: "AjaxController?do=benutzerEintragen",
					});
				}
			});
			
		});
	},
	
	addAnmeldenClick: function() {
		$("#anmelden").click(function() {
			alert("tata anmelden!");
			$.ajax({
				url:"AjaxController?do=loginCheck",
				type:"GET",
				data: "login_benutzeremail="+$("#login_benutzeremail").val() + "&login_passwort=" + $("#login_passwort").val(),
				success:function(data) {
					$("#login").html(data);
					bib.addAnmeldenClick();
					bib.addAbmeldenClick();
				}
			});
		});
	},
	
	addAbmeldenClick: function() {
		$("#abmelden").click(function() {
			alert("abmelden geklickt!");
			$.ajax({
				url:"AjaxController?do=ausloggen",
				type:"GET",
				success:function(data) {
					$("#login").html(data);
					bib.addAnmeldenClick();
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
				  }
			});
		});
	},
	
	addWarenkorbAufloesen: function() {
		$('#auswerfen').click(function() {
			alert("Kunde Auswerfen geklickt!: " + $("#kundenID").val());
			$.ajax({
				  url: "AjaxController?do=kundenAuswerfen",
				  type: "GET",
				  data: "kundennummer=" + $("#kundenID").val(),
				  success : function(data) {
					  $("#KundenBereich").html(data);
					  bib.addKundeEintragen();
				  }
			});
		});
	},
	
	addEventHandler: function() {
		$("#ausleihe").click(function() {
			$.ajax({
				  url: "AjaxController?do=ausleihe",
				  type: "GET",
				  success : function(data) {
					  $.blockUI({ message: data });
				  }
			});
		});
		
		$('#registrieren').click(function() {
			$.blockUI({ message: $('#inc_benutzer') });
			bib.addBenutzerSpeichernClick();
		});
		
		$('#close').click(function() {
			$.unblockUI();
		});
	}
});
$(bib.init);

