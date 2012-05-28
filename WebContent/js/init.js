var bib = bib || {};

$.extend(bib, {
	init: function() {
		bib.addDataTable();
		bib.addEventHandler();
		//bib.addTableHandler();
	},
	
	/*addTableHandler: function () {
		var color = "";
		$("#buecher tbody").delegate("tr", "hover", function() {
			color = $(this).css("background-color");
			$(this).css("background-color","#999");
		},
		function() {
			$(this).css("background-color",color);
		});
		
		$("#buecher tbody").delegate("tr", "click", function() {
			var firstCellText = $("td:first", this).text();
			alert("First Cell: " + firstCellText);
			$.ajax({
				  url: "AjaxController?do=mediumDetail",
				  type: "GET",
				  data: "isbn="+firstCellText,
				  success: function(data) {
					  $.blockUI({ message: data });
				  }
			});
		});
	},*/
	
	
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
	        }
	    });
		
		$('#benutzer').dataTable({
			"bProcessing": true,
			"sAjaxSource": "AjaxController?do=benutzerListe",
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
	        }
	    });
	},
	
	addEventHandler: function() {
		
		$('#kundeEintragen').click(function() {
			$.ajax({
				  url: "AjaxController?do=kundeEintragen",
				  type: "POST",
				  data: "kundennummer="+$('#kundenummer').val(),
				  success : function() {
					  
				  }
			});
		});
		
		$('#registrieren').click(function() {
			$.blockUI({ message: $('#inc_benutzer') });
		});
		
		$('#close').click(function() {
			$.unblockUI();
		});
		
		
		$("#anmelden").click(function() {
			alert("geklickt!");
			$.ajax({
				url:"AjaxController?do=loginCheck",
				type:"POST",
				data: "login_benutzeremail="+$("#login_benutzeremail").val()+"&login_passwort="+$("#login_benutzeremail").val(),
				success:function(data) {
					$("#login").html(data);
				}
			});
		});
		
		
		
		$(".warenkorb").each(function() {
			$(this).click(function(e){
				e.preventDefault();
				var isbn = $(this).attr("name");
				alert(isbn);
				$.ajax({
					url: "AjaxController?do=mediumHinzufuegen",
					type: "GET",
					data: "isbn=" + isbn,
					sucess: function(data) {
						$("#WarenkorbBereich").html(data);
					}
				});
			});
		});
		
		$(".detail").each(function() {
			$(this).click(function(e){
				e.preventDefault();
				var isbn = $(this).attr("name");
				alert(isbn);
				$.ajax({
					url: "AjaxController?do=mediumDetail",
					type: "GET",
					data: "isbn=" + isbn,
					sucess: function(data) {
						$.blockUI({ message: data });
					}
				});
			});
		});
	}
});
$(bib.init);
