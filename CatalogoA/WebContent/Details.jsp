<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="it.unisa.model.ProductBean"%>



<jsp:include page="header.jsp"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/details.css">
    <title>Details Page</title>
</head>
<body>
<div class= "details-body"> 

     
    <% 
        // Ottiene l'attributo "product" dalla richiesta
        ProductBean product = (ProductBean) request.getAttribute("product");
        
    %>

 <div class="product-wrapper">
 
  <table class="details-table">
    <tr>
    <th scope="col">Product Image</th> <!-- Intestazione per la colonna delle immagini del prodotto -->
      <td>
        <div class="product-images">
          <img src="images/<%=product.getNomeImg()%>" alt="Not available!">
        </div>
      </td>
      <td>
      <div class="details">
          <div class="titolo-pagina"> <h1><%=product.getName()%></h1> </div>
          <br><p class="product-short-des"><%=product.getDescription()%></p>
          <br><span class="product-price"><%=product.getPrice()%>&euro;</span>
          </div>
      </td>
    </tr>
    <tr>
      <td>
        <form action="./cart" method="post">
          <input type="hidden" name="action" value="comeBack">        
          <button type="submit" class="btn return-btn" type="button"  value="Come Back to Catalog">Come Back to Catalog</button>
        </form>
      </td>

      <td>
      <form action="./cart" method="post">
            <input type="hidden" name="action" value="addC">
            <input type="hidden" name="productCode" value="<%= product.getCode() %>">
            <button type="submit" class="btn cart-btn" type="button" value="Aggiungi">Add to cart</button>
          </form>
          </td>
    </tr>
  </table>

 
</div>
</div>
    
    <div class="footer">
    <jsp:include page="footer.jsp"/>
</div>
</body>
</html>
