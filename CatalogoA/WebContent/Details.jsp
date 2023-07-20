<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="it.unisa.model.ProductBean"%>



<jsp:include page="header.jsp"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/details.css">
</head>
<body>

     <div class="titolo-pagina"> <h1>Product Details</h1> </div>
    <% 
        // Ottiene l'attributo "product" dalla richiesta
        ProductBean product = (ProductBean) request.getAttribute("product");
        
    %>

 <div class="product-wrapper">
  <table class="image-table">
    <tr>
      <td>
        <div class="product-images">
          <img src="images/<%=product.getNomeImg()%>" alt="Not available!">
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
    </tr>
  </table>

  <table class="description-table">
    <tr>
      <td>
        <div class="details">
          <h2 class="product-brand"><%=product.getName()%></h2>
          <p class="product-short-des"><%=product.getDescription()%></p>
          <span class="product-price"><%=product.getPrice()%></span>

          <form action="./cart" method="post">
            <input type="hidden" name="action" value="addC">
            <input type="hidden" name="productCode" value="<%= product.getCode() %>">
            <button type="submit" class="btn cart-btn" type="button" value="Aggiungi">Add to cart</button>
          </form>
        </div>
      </td>
    </tr>
  </table>
</div>

    
    <div class="footer">
    <jsp:include page="footer.jsp"/>
</div>
</body>
</html>
