<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="java.sql.*"%>
	
<!DOCTYPE html>

<jsp:include page="header.jsp"/>

<html lang="en">

<head>
 	 <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
    <link rel="stylesheet" href="css/homepagestyle.css">
    <link href="categorie.css" rel="stylesheet" type="text/css">
    
	<meta name="viewport" content="width=device-width, initial-scale=1">
<meta content="text/html; charset=iso-8859-2" http-equiv="Content-Type">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">



	<title>Pet Paradise|Homepage</title>

</head>

<body>
                    
<div class="w3-content w3-section" style="max-width:100%">
  <img class="mySlides" src="images/spot.png" style="width:100%">
  <img class="mySlides" src="images/spot2.png" style="width:100%">
  <img class="mySlides" src="images/spot1.jpg" style="width:100%">
</div>

<script>
var myIndex = 0;
carousel();

function carousel() {
  var i;
  var x = document.getElementsByClassName("mySlides");
  for (i = 0; i < x.length; i++) {
    x[i].style.display = "none";  
  }
  myIndex++;
  if (myIndex > x.length) {myIndex = 1}    
  x[myIndex-1].style.display = "block";  
  setTimeout(carousel, 5000); // Change image every 2 seconds
}
</script>
</head>

 <% 
                // Connessione al database
                String url = "jdbc:mysql://localhost:3306/storage2?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
                String user = "root";
                String password = "Paolinoerra01!";
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection(url, user, password);
                conn.setAutoCommit(false);
        		
                try {
                    // Query per selezionare i prodotti per cani
                    String query = "SELECT * FROM product WHERE code>=27;";
                    PreparedStatement ps = conn.prepareStatement(query);
                    ResultSet rs = ps.executeQuery();

                    // Iterazione sui risultati della query
                    %>
<div class="cat-body">
  <% while (rs.next()) {
    int code = rs.getInt("code");
    String img = rs.getString("nome_immagine");
    String nome = rs.getString("name");
    String descrizione = rs.getString("description");
    String prezzo = rs.getString("price");
  %>
  
<div class="cat-prodotto">
			
				<a href="product?action=read&id=<%= code %>" target="_blank" >
							<img src="images/<%=img%>" class="cat-imgProdotto">
				</a>	
							<%=nome%>
							<br>
							<%= prezzo %> &euro;
				
				
					 <form action="./cart" method="post">
					  <input type="hidden" name="action" value="addC">
					  <input type="hidden" name="productCode" value="<%= code%>">
					  <button type="submit" class="cat-pulsante" type="button" value="Aggiungi">Add to cart</button>
					</form>
						
					
			</div>
  <% } %>


                   
                   
                    <% } catch (SQLException e) {
                                       out.println("Error executing SQL query: " + e.getMessage());
                                   }
                                   %>
 </div>
                     <div class="footer"><jsp:include page="footer.jsp"/></div>
                  
                   </body>
                   </html>