<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*,it.unisa.model.ProductBean,it.unisa.model.cart"%>

<%
    ProductBean bean = (ProductBean) request.getAttribute("product");

	%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="ProductStyle.css" rel="stylesheet" type="text/css">
    <title>Product Details</title>
</head>
<body>
    <h2>Product Details</h2>
    <c:set var="id" value="${param.id}" />
    <c:set var="product" value="${model.doRetrieveByKey(id)}" />
	
	<table border="1">
		<tr>
			<th>Code</th>
			<th>Name</th>
			<th>Description</th>
			<th>Price</th>
			<th>Quantity</th>
		</tr>
		<tr>
			<td>${product.getCode()}</td>
			<td>${product.getName()}</td>
			<td>${product.getDescription()}</td>
			<td>${product.getPrice()}</td>
			<td>${product.getQuantity()}</td>
		</tr>
	</table>
	<table>
	    <tr><th>Image:</th></tr>
	    <tr>
	        <td><img src="${pageContext.request.contextPath}/images/${product.getNomeImg()}" alt="Cannot load..."></td>
	    </tr>
	</table>
	
	<form action="./cart" method="post">
	<input type="hidden" name="action" value="addC">
	<input type="hidden" name="productCode" value="<%= bean.getCode() %>">
	<input type="submit" value="Add to cart">
	</form>
	
	<form action="./product" method="post">
    <input type="hidden" name="action" value="comeBack">        
    <input type="submit" value="Come Back to Catalog">
    </form>
                
</body>
</html>

