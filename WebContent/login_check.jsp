<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=iso-8859-1">
</head>
<body>
<%@ page import="java.sql.*"%>
<%
	String user, password;
	String benutzerEmail = request.getParameter("login_benutzeremail");
	String pw = request.getParameter("login_passwort");
	try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost/my_db", "root", "");
		Statement stmt = conn.createStatement();
		String sql = "select * from email where user like '" + benutzerEmail
				+ "'";
		ResultSet res = stmt.executeQuery(sql);
		res.first();
		user = res.getString(1);
		if (benutzerEmail.equals(user)) {
			password = res.getString(2);
			if (pw.equals(password)) 
			{
				out.println("<p>Login okay....</p>"); // Umsetzung mit InnerHTML oder JavaScript?
			} 
			else 
			{
				out.println("<b>Passwort falsch</b><br>");
			}
		} else {
			out.println("<b>Benutzeremail falsch</b><br>");
		}
	} catch (ClassNotFoundException err) {
		out.println("DB-Driver nicht gefunden!"); // ERROR Testen
		out.println(err);
	} catch (SQLException err) {
		out.println("Connect nicht möglich");
		out.println(err);
	}
%>
</body>
</html>
