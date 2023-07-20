<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, it.unisa.model.*"%>
<%@ page import="java.sql.*"%>
	
<%
// Verifica se l'attributo "products" è presente nella richiesta
Collection<?> products = (Collection<?>) request.getAttribute("products");
if(products == null) {
    // Se l'attributo "products" non è presente, reindirizza alla pagina di visualizzazione dei prodotti
    response.sendRedirect("./product");	
    return;
}

// Ottiene l'attributo "product" dalla richiesta
ProductBean product = (ProductBean) request.getAttribute("product");
%>


<jsp:include page="header.jsp"/>

<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
    <link rel="stylesheet" href="css/categorie.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    
    <title>Products</title>
    
    <script>
        $(document).ready(function() {
            $('.imgProdotto').hover(
                function() {
                    $(this).animate({ 'margin-top': '-20px' }, 200);
                },
                function() {
                    $(this).animate({ 'margin-top': '0' }, 200);
                }
            );
        });
    </script>
    
</head>

<body >
<div class="cat-body">
<%
// Verifica se la collezione di prodotti non è vuota
if (products != null && products.size() != 0) {
    Iterator<?> it = products.iterator();
    while (it.hasNext()) {
        ProductBean bean = (ProductBean) it.next();
%>
    <!-- Codice per visualizzare i prodotti -->
    <div class="cat-prodotto">
        <a href="product?action=read&id=<%= bean.getCode() %>" target="_blank" >
            <img src="images/<%= bean.getNomeImg() %>" class="cat-imgProdotto">
        </a>
        <p><%= bean.getName() %></p><br>
        <p><%= bean.getPrice() %> &euro;</p>

        <form action="./cart" method="post">
            <input type="hidden" name="action" value="addC">
            <input type="hidden" name="productCode" value="<%= bean.getCode() %>">
            <button type="submit" class="cat-pulsante" type="button" value="Aggiungi">Add to cart</button>
        </form>
    </div>
    <!-- Fine codice per visualizzare i prodotti -->
<%
    }
} else {
%>
    <tr>
        <td colspan="6">No products available</td>
    </tr>
<%
}
%>
</div>
<div class="footer">
    <jsp:include page="footer.jsp"/>
</div>

</body>
</html>
