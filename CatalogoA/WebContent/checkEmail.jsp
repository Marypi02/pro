<%@ page import="java.sql.*"%>
<%
	String name = request.getParameter("email").toString();
	System.out.println(name);
	String data = "";
	try {
		String ip = "localhost";
		String port = "3306";
		String db = "storage2";
		String username = "root";
		String password = "Paolinoerra01!";
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://"+ ip+":"+ port+"/"+db+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", username, password);
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from utente where email='" + name + "'");
		int count = 0;
		while (rs.next()) {

			count++;
		}

		if (count > 0) {
			data = "Email già eistente!";
		} else {
			//data = "You can register now!!!!";
		}
		out.println(data);
		System.out.println(data);
	} catch (Exception e) {
		System.out.println(e);
	}
%>
