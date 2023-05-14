<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
    import="java.util.*,it.unisa.model.ProductBean,it.unisa.model.cart"%>
    
    <%
   
    ProductBean bean = (ProductBean) request.getAttribute("product");

	%>
    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/protected.css" type="text/css">
    <title>Edit Product</title>
</head>
<body>
    <h1>Edit Product</h1>
    
    <form action="product" method="post">
        <input type="hidden" name="action" value="updateProduct">
        <input type="hidden" name="id" value="${product.getCode()}">
        
        <label for="name">Name:</label>
        <input type="text" name="name" maxlength="20" value="${product.getName()}">
        <br>
        
        <label for="description">Description:</label>
        <input type="text" name="description" maxlength="100" rows="3" value="${product.getDescription()}">
        <br>
        
        <label for="price">Price:</label>
        <input type="text" name="price" min="0" value="${product.getPrice()}">
        <br>
        
        <label for="quantity">Quantity:</label>
        <input type="text" name="quantity" maxlength="20" value="${product.getQuantity()}">
        <br>
        
        <label for="nameImg">Image Name:</label>
        <input type="text" name="nameImg" min="1" value="${product.getNomeImg()}">
        <br>
        
        <input type="submit" value="Save Changes">
    </form>
</body>
</html>