<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*,it.unisa.model.ProductBean,it.unisa.model.cart,it.unisa.model.ProductOrder"%>

<% 
    //Recupera il carrello dalla sessione
	cart cart = (cart) session.getAttribute("cart");
	
	%>
	
<jsp:include page="header.jsp"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="css/cart.css" rel="stylesheet" type="text/css">
<title>Cart</title>
</head>
<body>
<div class=cart-page>
<% if(cart != null) { %>
<div class=cart-table>
		<table border="1">
		<div class=titoloCarrello> <h4>CART</h4> </div>
		<tr> 
		    <th>Product</th>
			<th>Name</th>
			<th>Description</th>
			<th>Unit Cost</th>
			<th>Number</th>
			<th>Total Cost</th>
			<th>Action</th>
		</tr>
		<%      // Itera sui prodotti nel carrello
			   Map<ProductBean, ProductOrder> prodcart = cart.getProducts();
			   for(Map.Entry<ProductBean, ProductOrder> beancart : prodcart.entrySet()){
		%>
		<tr>
			<td><img src=images/<%=beancart.getKey().getNomeImg()%> class="imgProdotto"></td>
			<td><%=beancart.getKey().getName()%></td>
			<td><%=beancart.getKey().getDescription()%></td>
			<td><%=beancart.getValue().getUnitCost()%></td>
			<td>
			
			<form action="./cart" method="post">
			<div class=update-button>
                <input type="hidden" name="action" value="setNumOrder">
			    <input type="number" name="quantity" value=<%=beancart.getValue().getNumItems() %>>
                <input type="hidden" name="productCode" value="<%= beancart.getKey().getCode() %>">
             <button type="submit" class="pulsanteUPDATE" type="button" value="Update Order">Update Order</button>
                 </div>
                </form>
			</td>
			<td><%=beancart.getValue().getTotalCost()%> </td>
			<td>
			    <form action="./cart" method="post">
                <input type="hidden" name="action" value="deleteC">
                <input type="hidden" name="productCode" value="<%= beancart.getKey().getCode() %>">
                <button type="submit" class="pulsanteDELETE" type="button" value="Delete from cart">Delete from cart</button>
                </form></td> 
		</tr>
		<%} %>
		
	</table>	
	
	
  <span class="cart-total">Total: <%= cart.getTotalPrice() %> </span>


	</div>
	</div>
	<div class=bottoni>
	            <form action="./cart" method="post">
                <input type="hidden" name="action" value="comeBack">        
                <button type="submit" class="pulsanteRETURN" type="button"  value="Come Back to Catalog">Come Back to Catalog</button>
                </form>
                
                       
                <button type="submit" class="checkout-button" type="button"  value="Aquista">Aquista</button>
                </div>
	<% }else { %>	
		<caption>Cart</caption>
		<h1>Carrello vuoto</h1>
		
		<%} %>
<div class="footer"><jsp:include page="footer.jsp"/></div>
</body>
</html>