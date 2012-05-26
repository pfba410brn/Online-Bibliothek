<%@page import="logik.JsonConverter"%>
<%@page import="db.Buch"%>
<%@page import="java.util.List"%>
<%@page import="db.DbVerwaltung"%>
<%@ page language="java" contentType="text/json; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	
<%!
	DbVerwaltung db = new DbVerwaltung();
  List<Buch> buchListe = db.selectAll_Buecher();
%>
<%= 
JsonConverter.convertBuch(buchListe)
%>

	