<%@ page import="java.sql.*"%>
<%
	String name = request.getParameter("val");
	if (name == null || name.trim().equals("")) {
	} else {
		try {
			String ip = "localhost";
			String port = "3306";
			String db = "storage2";
			String username = "root";
			String password = "Paolinoerra01!";

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://"+ ip+":"+ port+"/"+db+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", username, password);
			PreparedStatement ps = con.prepareStatement("select * from product where name like '" + name + "%'");
			ResultSet rs = ps.executeQuery();

			if (!rs.isBeforeFirst()) {
				//out.println("<p>Nessun risultato!</p>");
			} else {
				//out.print("<table border='1' cellpadding='2' width='100%'>");
				while (rs.next()) {
					out.print("<a href='#'> " + rs.getString(2) + "</a>");
				}
				//out.print("</table>");
			} //end of else for rs.isBeforeFirst  
			con.close();
		} catch (Exception e) {
			out.print(e);
		}
	} //end of else
%>
