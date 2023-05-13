<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*,it.unisa.model.ProductBean,it.unisa.model.cart"%>
    
    <%
    //Check your credentials
    Boolean adminRoles = (Boolean) session.getAttribute("adminRoles");
    if((adminRoles == null) || (!adminRoles.booleanValue())){
      response.sendRedirect("login.html");
      return;
    }
    
    Collection<?> products = (Collection<?>) request.getAttribute("products");
	if(products == null) {
		response.sendRedirect("./product");	
		return;
	}
	
	ProductBean product = (ProductBean) request.getAttribute("product");
    %>
    
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1" http-equiv="Content-Type" content="text/html">
<link href="ProductStyle.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="css/protected.css" type="text/css">
<title>Protected Page</title>
</head>
<body>
<h1>Welcome to the Protected Page</h1>
<p align="center">Congratulations. You have accessed a protected document.</p>
<br><br>

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
			<td><a href="product?action=delete&id=<%=bean.getCode()%>&isAdmin=true">Delete</a><br></td>
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

	<h2>Insert</h2>
	<form action="product" method="post">
		<input type="hidden" name="action" value="insert"> 
		
		<label for="name">Name:</label><br> 
		<input name="name" type="text" maxlength="20" required placeholder="enter name"><br> 
		
		<label for="description">Description:</label><br>
		<textarea name="description" maxlength="100" rows="3" required placeholder="enter description"></textarea><br>
		
		<label for="price">Price:</label><br> 
		<input name="price" type="number" min="0" value="0" required><br>
		
		<label for="nameImg">NameImg:</label><br> 
		<input name="nameImg" type="text" maxlength="20" required placeholder="enter nameImg"><br> 
		
		<label for="quantity">Quantity:</label><br> 
		<input name="quantity" type="number" min="1" value="1" required><br>
		<br>
		<input type="submit" value="Add"> <input type="reset" value="Reset">
	</form>
	<br><br>
<form action="logout" method="get">
<input type="submit" value="Logout">
</form>

</body>
</html>