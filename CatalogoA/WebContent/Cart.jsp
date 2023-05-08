<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*,it.unisa.model.ProductBean,it.unisa.model.cart,it.unisa.model.ProductOrder"%>

<% 
    //ProductBean product = (ProductBean) request.getAttribute("product");
	cart cart = (cart) request.getAttribute("cart");
	%>
	
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="ProductStyle.css" rel="stylesheet" type="text/css">
<title>Cart</title>
</head>
<body>
<% if(cart != null) { %>
		<h2>Cart</h2>
		<table border="1">
		<tr>
		    <th>Id</th>
			<th>Name</th>
			<th>Description</th>
			<th>Unit Cost</th>
			<th>Number</th>
			<th>Total Cost</th>
			<th>Action</th>
		</tr>
		<% //List<ProductBean> prodcart = cart.getProducts();
		   //for(ProductBean beancart: prodcart) {
			   Map<ProductBean, ProductOrder> prodcart = cart.getProducts();
			   for(Map.Entry<ProductBean, ProductOrder> beancart : prodcart.entrySet()){
		%>
		<tr>
			<td><%=beancart.getKey().getCode()%></td>
			<td><%=beancart.getKey().getName()%></td>
			<td><%=beancart.getKey().getDescription()%></td>
			<td><%=beancart.getValue().getUnitCost()%></td>
			<td>
			<form action="./cart" method="post">
                <input type="hidden" name="action" value="setNumOrder">
			    <input type="number" name="quantity" value=<%=beancart.getValue().getNumItems() %>>
                <input type="hidden" name="productCode" value="<%= beancart.getKey().getCode() %>">
                <input type="submit" value="Update Order">
                </form>
			</td>
			<td><%=beancart.getValue().getTotalCost() %></td>
			<td><form action="./cart" method="post">
                <input type="hidden" name="action" value="deleteC">
                <input type="hidden" name="productCode" value="<%= beancart.getKey().getCode() %>">
                <input type="submit" value="Delete from cart">
                </form></td>
		</tr>
		
		<%} %>
	</table>	
	
	
	            <form action="./cart" method="post">
                <input type="hidden" name="action" value="comeBack">        
                <input type="submit" value="Come Back to Catalog">
                </form>
	<% } %>	
</body>
</html>