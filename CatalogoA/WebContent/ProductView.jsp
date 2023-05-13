<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	Collection<?> products = (Collection<?>) request.getAttribute("products");
	if(products == null) {
		response.sendRedirect("./product");	
		return;
	}
	
	ProductBean product = (ProductBean) request.getAttribute("product");
%>

<!DOCTYPE html>
<html lang="en">
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,it.unisa.model.ProductBean,it.unisa.model.cart"%>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="ProductStyle.css" rel="stylesheet" type="text/css">
	<title>Storage DS/BF</title>
</head>

<body>
	<h1>Products</h1>
	<a href="product">List</a>
	<table border="1">
		<tr>
			<th>Code <a href="product?sort=code">Sort</a></th>
			<th>Name <a href="product?sort=name">Sort</a></th>
			<th>Description <a href="product?sort=description">Sort</a></th>
			<th>Action</th>
		</tr>
		<%
			if (products != null && products.size() != 0) {
				Iterator<?> it = products.iterator();
				while (it.hasNext()) {
					ProductBean bean = (ProductBean) it.next();
		%>
		<tr>
			<td><%=bean.getCode()%></td>
			<td><%=bean.getName()%></td>
			<td><%=bean.getDescription()%></td>
			<td><a href="product?action=read&id=<%= bean.getCode() %>" target="_blank" rel="noopener">Details</a><br>
				<!-- noopener per evitare che, quando viene caricata la nuova pagina, venga caricata in realtà una pagina malevola che es. può chiedere dei dati sensibili -->
				<form action="./cart" method="post">
                <input type="hidden" name="action" value="addC">
                <input type="hidden" name="productCode" value="<%= bean.getCode() %>">
                <input type="submit" value="Add to cart">
                </form>
				
				</td>
		</tr>
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
	</table>
	
	<form action="logout" method="get">
	<input type="submit" value="Logout">
	</form>
	
</body>
</html>