$.fn.dataTableExt.oApi.fnReloadAjax = function ( oSettings ) {


	    this.fnClearTable( this );
	    this.oApi._fnProcessingDisplay( oSettings, true );
	    var that = this;
	     
	    $.getJSON( oSettings.sAjaxSource, null, function(json) {
	    /* Got the data - add it to the table */
	    for ( var i=0 ; i<json.aaData.length ; i++ ) {
	    that.oApi._fnAddData( oSettings, json.aaData[i] );
	    }
	     
	    oSettings.aiDisplay = oSettings.aiDisplayMaster.slice();
	    that.fnDraw( that );
	    that.oApi._fnProcessingDisplay( oSettings, false );
	    });
	}

	$(document).ready(function() {
	    buecherTable = $('#buecher').dataTable({
			"bProcessing": true,
			"sAjaxSource": "AjaxController?do=buecherListe",
			"bRetrieve": true,
			
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
	        "fnDrawCallback": function(){
	        	bib.addDetailClick();
				bib.addWarenkorbClick();
	        },
	    });
	    
	    exemplarTable = $('#exemplar').dataTable({
			"bProcessing": true,
			"sAjaxSource": "AjaxController?do=exemplarListe",
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
	    });
	    
	    benutzerTable = $('#benutzer').dataTable({
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
	     
	        
	});