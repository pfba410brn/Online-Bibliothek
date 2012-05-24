var bib = bib || {};

$.extend(bib, {
	init: function() {
		bib.addDataTable();
		bib.addEventHandler();
	},
	
	addDataTable: function() {
		$('#buecher').dataTable({
			"bProcessing": true,
			"sAjaxSource": "<<ALLEBUECHER!!!>>.json",
	        "oLanguage": {
	            "sLengthMenu": "Zeige _MENU_ Eintr�ge pro Seite",
	            "sZeroRecords": "Leider kein Buch vorhanden",
	            "sInfo": "Zeigt _START_ bis _END_ von _TOTAL_ Eintr�gen",
	            "sInfoEmpty": "Zeigt 0 bis 0 von 0 Eintr�gen",
	            "sInfoFiltered": "(filtered from _MAX_ total records)"
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
	}
});
$(bib.init);