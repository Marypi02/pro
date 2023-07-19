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
  			<div class="update-button">
    			<input type="hidden" name="action" value="setNumOrder">
    			<input type="number" name="quantity" id="quantity" value="<%=beancart.getValue().getNumItems() %>">
    			<input type="hidden" name="productCode" value="<%= beancart.getKey().getCode() %>">
    			<button type="button" onclick="updateOrder()" class="pulsanteUPDATE">Update Order</button>
    			<div class="error_message_quantity" style="display: none; color: red; font-size: 12px; margin-top: 5px;"></div>
  			</div>
		</form>

		<td><%=beancart.getValue().getTotalCost()%> </td>

	<td>
 		 <form action="./cart" method="post">
    		<input type="hidden" name="action" value="deleteC">
   			 <input type="hidden" name="productCode" value="<%= beancart.getKey().getCode() %>">
    		<button type="button" onclick="deleteFromCart()" class="pulsanteDELETE">Delete from cart</button>
  		</form>
	</td>
		</tr>
		<%} %>
		
	</table>	
	
	
  <span class="cart-total">Total: <%= cart.getTotalCost() %> </span>


	</div>
	</div>
	<div class=bottoni>
	
	
				<form action="./mostra_ordini_utente" method="post">
                <input type="hidden" name="action" value="procediOrdine">        
                <input type="submit" value="Procedi all'ordine">
                </form>
                
                
	            <form action="./cart" method="post">
                <input type="hidden" name="action" value="comeBack">        
                <button type="submit" class="pulsanteRETURN" type="button"  value="Come Back to Catalog">Come Back to Catalog</button>
                </form>
                
                       
                
                </div>
	<% }else { %>	
		<caption>Cart</caption>
		<h1>Carrello vuoto</h1>
		
		<%} %>
<div class="footer"><jsp:include page="footer.jsp"/></div>

<script>
function updateOrder() {
	  var quantityField = document.getElementById("quantity");
	  var quantityValue = quantityField.value;

	  // Espressione regolare per la quantità (deve essere un numero intero positivo)
	  var quantityRegex = /^[1-9]\d*$/;

	  // Ripulisci i messaggi di errore precedenti
	  document.querySelector(".error_message_quantity").textContent = "";
	  document.querySelector(".error_message_quantity").style.display = "none";
	  quantityField.classList.remove("error");

	  if (!quantityRegex.test(quantityValue)) {
	    document.querySelector(".error_message_quantity").textContent = "La quantità deve essere un numero intero positivo.";
	    document.querySelector(".error_message_quantity").style.display = "block";
	    quantityField.classList.add("error");
	    return;
	  }

	  // Se la validazione ha successo, invia il modulo
	  quantityField.parentNode.submit();
	}


  function deleteFromCart() {
    // Conferma con l'utente prima di eliminare dal carrello
    if (confirm("Sei sicuro di voler eliminare questo prodotto dal carrello?")) {
      event.target.parentNode.submit();
    }
  }
  
  </script>
</body>
</html>