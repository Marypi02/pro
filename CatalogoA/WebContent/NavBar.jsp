<%@ page import="java.sql.*" %>
<%
    String name = request.getParameter("val");
    if (name == null || name.trim().equals("")) {
    } else {
        try {
            String ip = "localhost";
            String port = "3306";
            String db = "storage2";
            String username = "root";
            String password = "password";

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/" + db + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", username, password);

            //String sqlQuery = "SELECT * FROM product WHERE name LIKE ?";
            //PreparedStatement ps = con.prepareStatement(sqlQuery);
            ////ps.setString(1, name + "%"); // Imposta il parametro posizionale con il valore
            PreparedStatement ps=con.prepareStatement("select * from product where name like '"+name+"%'");  
			ResultSet rs=ps.executeQuery();  


            if (!rs.isBeforeFirst()) {
                // Nessun risultato trovato
            } else {
                while (rs.next()) {
                    out.print("<a href='product?action=read&id=<%= bean.getCode()&>'> " + rs.getString(4) + "</a>");
                }
            }

            con.close();
        } catch (Exception e) {
            out.print(e);
        }
    }
%>
