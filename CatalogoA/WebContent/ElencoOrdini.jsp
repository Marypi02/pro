<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"
    import="it.unisa.model.ProductOrder, java.util.*" %>
    
<%
Collection<?> orderList = (Collection<?>) request.getAttribute("OrderList");
%>
    
<!DOCTYPE html>
<html>
<head>
<<meta charset="ISO-8859-1" http-equiv="Content-Type" content="text/html">
<link href="ProductStyle.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="css/protected.css" type="text/css">
<title>Order List</title>
</head>
<body>
<h1>Order List</h1>

    <form method="get" action="orderList">
        <input type="hidden" name="action" value="allOrders">
        <input type="submit" value="All Orders">
    </form>

    <form method="get" action="orderList">
        <input type="hidden" name="action" value="customerOrders">
        <input type="text" name="customerEmail" placeholder="Customer Email">
        <input type="submit" value="Filter by Customer">
    </form>
    
    <form method="get" action="orderList">
        <input type="hidden" name="action" value="filteredOrders">
        <input type="date" name="fromDate" placeholder="From Date (yyyy-mm-dd)">
        <input type="date" name="toDate" placeholder="From Date (yyyy-mm-dd)">
        <input type="submit" value="Filter by Date">
   </form>
    

    <table>
        <tr>
            <th>Order ID</th>
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
            <td><%= user.getTotalCost() %></td>
        </tr>
        <% }
        } else {
        %>
        <tr>
            <td colspan="5">No orders available</td>
        </tr>
        <% } %>
    </table>

    <!-- Aggiungi qui il resto del tuo contenuto -->
</body>
</html>