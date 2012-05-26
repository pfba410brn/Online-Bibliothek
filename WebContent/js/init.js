var bib = bib || {};

$.extend(bib, {
	init: function() {
		bib.addDataTable();
		bib.addEventHandler();
	},
	
	addDataTable: function() {
		$('#buecher').dataTable({
			"bProcessing": true,
			"sAjaxSource": "/bib/AjaxController?do=buecherListe",
	        "oLanguage": {
	            "sProcessing":   "Bitte warten...",
	            "sLengthMenu":   "_MENU_ Eintr�ge anzeigen",
	            "sZeroRecords":  "Keine B�cher vorhanden.",
	            "sInfo":         "_START_ bis _END_ von _TOTAL_ Eintr�gen",
	            "sInfoEmpty":    "0 bis 0 von 0 Eintr�gen",
	            "sInfoFiltered": "(gefiltert von _MAX_  Eintr�gen)",
	            "sInfoPostFix":  "",
	            "sSearch":       "Suchen",
	            "sUrl":          "",
	            "oPaginate": {
	                "sFirst":    "Erster",
	                "sPrevious": "Zur�ck",
	                "sNext":     "N�chster",
	                "sLast":     "Letzter"
	            }
	        }
	    });
	},
	
	addEventHandler: function() {
		$('#registrieren').click(function() {
			$.blockUI({ message: $('#inc_benutzer') });
		});
		
		$('#close').click(function() {
			$.unblockUI();
		});
		$('#anmelden').click(function() {
			//anmeldeskript abrufen
		});
	}
});
$(bib.init);
