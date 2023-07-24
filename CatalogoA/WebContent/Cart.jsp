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
<div class="cart-body">
<% if(cart != null) { %>

  <h1 class="cart-h1">Carrello</h1>
 
  <table class="cart-table">
    <thead>
      <tr class="cart-tr">
        <th class="cart-th">Immagine</th>
        <th class="cart-th">Nome</th>
        <th class="cart-th">Descrizione</th>
        <th class="cart-th">Costo unico</th>
        <th class="cart-th">Numero</th>
        <th class="cart-th">Costo totale</th>
        <th class="cart-th">Azione</th>
      </tr>
    </thead>
    <tbody class="cart.tbody">
     <%      // Itera sui prodotti nel carrello

			   Map<ProductBean, ProductOrder> prodcart = cart.getProducts();
			   for(Map.Entry<ProductBean, ProductOrder> beancart : prodcart.entrySet()){
		%>

      <tr class="cart-tr">
        <td class="cart-td"><img class="cart-img" src=images/<%=beancart.getKey().getNomeImg()%> class="imgProdotto"></td>
	    <td class="cart-td"><%=beancart.getKey().getName()%></td>
		<td class="cart-td"><%=beancart.getKey().getDescription()%></td>

		<td class="cart-td"><%=beancart.getValue().getUnitCost()%>&euro;</td>

        <td class="cart-td"><form action="./cart" method="post"> 
        <input type="hidden" name="action" value="setNumOrder">
			    <input class="cart-input" type="number" name="quantity" value=<%=beancart.getValue().getNumItems() %>>
                <input class="cart-input" type="hidden" name="productCode" value="<%= beancart.getKey().getCode() %>">
             <button type="submit" class="cart-button" type="button" value="Update Order">Update Order</button>
                 </div>
                </form>
        </td>

        <td class="cart-td"><%=beancart.getValue().getTotalCost()%>&euro;</td>

        
        <td class="cart-td"> <form action="./cart" method="post">
                <input type="hidden" name="action" value="deleteC">
                <input type="hidden" name="productCode" value="<%= beancart.getKey().getCode() %>">
                <button type="submit" class="cart-button" type="button" value="Delete from cart">Delete from cart</button>
                
                
                </form></td>
        <td class="cart-td"> <form action="./cart" method="post">
      			<input type="hidden" name="action" value="addReview">
    			<input type="hidden" name="productCode" value="<%= beancart.getKey().getCode() %>">
    
    			<label for="reviewText">Recensione:</label>
    			<textarea id="reviewText" name="reviewText" rows="4" cols="50"></textarea><br>
    
    			<label for="rating">Voto:</label>
                <input type="number" id="rating" name="rating" min="1" max="5" required><br>
    
                  <button name = "submit" type="submit">Invia Recensione</button>
        
			</form></td>
			</tr>
    </tbody>
    <%} %>
  </table>

   <span class="cart-total">Total: <%= cart.getTotalCost() %>&euro;</span>
  <div class="actions">
     <form action="./cart" method="post">

                <input type="hidden" name="action" value="comeBack">        
                <button type="submit" class="cart-button" type="button"  value="Come Back to Catalog">Come Back to Catalog</button>
                </form>
                
<form action="./mostra_ordini_utente" method="post">

                <input type="hidden" name="action" value="procediOrdine"> 
    <button type="submit" class="cart-button" type="button"  value="Procedi all'ordine">Aquista</button>
</form>


                </div>
	<% }else { %>	
		
		<h1>Carrello vuoto</h1>
		
		<%} %>
		</div>
<div class="footer"><jsp:include page="footer.jsp"/></div>

<script>
function updateOrder() {
	  var quantityField = document.getElementById("quantity");
	  var quantityValue = quantityField.value;

	  // Espressione regolare per la quantit� (deve essere un numero intero positivo)
	  var quantityRegex = /^[1-9]\d*$/;

	  // Ripulisci i messaggi di errore precedenti
	  document.querySelector(".error_message_quantity").textContent = "";
	  document.querySelector(".error_message_quantity").style.display = "none";
	  quantityField.classList.remove("error");

	  if (!quantityRegex.test(quantityValue)) {
	    document.querySelector(".error_message_quantity").textContent = "La quantit� deve essere un numero intero positivo.";
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