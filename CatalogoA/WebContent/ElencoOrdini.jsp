<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"
    import="it.unisa.model.ProductOrder, java.util.*" %>
    
<%
Collection<?> orderList = (Collection<?>) request.getAttribute("OrderList");
%>
    
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="ISO-8859-1" http-equiv="Content-Type" content="text/html">
<link rel="stylesheet" href="css/protected.css" type="text/css">
<title>Order List</title>
</head>
<body>
<h1>Order List</h1>
  
   <table border="1">
        <tr>
            <th>ID Order</th>
            <th>Customer ID</th>
            <th>Order Date</th>
            <th>Order Total</th>
        </tr>
      
    <% if (orderList != null && !orderList.isEmpty()) {
            for (Object obj : orderList) {
                ProductOrder user = (ProductOrder) obj;
        %>
        <tr>
            <td><%= user.getIdOrdine() %></td>
            <td><%= user.getCodUtente() %></td>
            <td><%= user.getData_ordine() %></td>
            <td><%= user.getTotalCost2() %></td>
        </tr>
        <% }
        } else {
        %>
        <tr>
            <td colspan="5">No orders available</td>
        </tr>
        <% } %>
    </table>
    <br>
    <form action="OrderList" method="get">
        <input type="hidden" name="action" value="comeBack">
        <input type="submit" value="Torna alla pagina protetta">
    </form>
   
</body>
</html>