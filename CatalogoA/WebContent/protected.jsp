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
<link rel="stylesheet" href="css/protected.css" type="text/css">
<title>Protected Page</title>
</head>
<body>
<div class ="page">
<h1 >Welcome to the Protected Page</h1>
<p align="center">Congratulations. You have accessed a protected document.</p>
<br>


<h1>Products</h1>
	<table border="1">
		<tr>
			<th>Code <a href="product?sort=code">Sort</a></th>
			<th>Name <a href="product?sort=name">Sort</a></th>
			<th>Description <a href="product?sort=description">Sort</a></th>
			<th>Cost <a href="product?sort=cost"></a></th>
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
			<td><%=bean.getPrice()%></td>
			<td><a href="product?action=delete&id=<%=bean.getCode()%>&isAdmin=true">Delete</a><br>
			    <a href="product?action=edit&id=<%=bean.getCode()%>&isAdmin=true">Edit</a><br></td>
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
<form action="product" method="post" onsubmit="return validateProductForm()">
  <input type="hidden" name="action" value="insert">

  <label for="categoria">Categoria:</label><br>
  <input name="categoria" type="text" maxlength="20" required placeholder="Enter categoria"><br>
  <div class="error_message" id="categoriaError"></div>

  <label for="specie">Specie:</label><br>
  <input name="specie" type="text" maxlength="20" required placeholder="Enter specie"><br>
  <div class="error_message" id="specieError"></div>

  <label for="name">Name:</label><br>
  <input name="name" type="text" maxlength="20" required placeholder="Enter name"><br>
  <div class="error_message" id="nameError"></div>

  <label for="description">Description:</label><br>
  <textarea name="description" maxlength="100" rows="3" required placeholder="Enter description"></textarea><br>
  <div class="error_message" id="descriptionError"></div>

  <label for="price">Price:</label><br>
  <input name="price" type="text" placeholder="Enter price"><br>
  <div class="error_message" id="priceError"></div>

  <label for="nameImg">NameImg:</label><br>
  <input name="nameImg" type="text" maxlength="20" required placeholder="Enter nameImg"><br>
  <div class="error_message" id="nameImgError"></div>

  <label for="quantity">Quantity:</label><br>
  <input name="quantity" type="number" min="1" value="1" placeholder="Enter quantity"><br>
  <div class="error_message" id="quantityError"></div>
  <br>
  <input type="submit" value="Add"> <input type="reset" value="Reset">
</form>


	<br><br>

<h2>Users</h2>
<form action="userList" method="get">
    <input type="hidden" name="action" value="elencoUtenti">
    <input type="submit" value="Elenco Utenti">
</form>

<h2>Ordini</h2>

<form method="get" action="orderList">
    <input type="hidden" name="action" value="allOrders">
    <input type="submit" value="All Orders">
</form><br>


<form method="get" action="orderList" onsubmit="return validateEmailFilter()">
    <input type="hidden" name="action" value="customerOrders">
    <input type="text" name="customerEmail" id="customerEmail" placeholder="Customer Email">
    <div class="error_message" id="emailError"></div>
    <input type="submit" value="Filter by Customer">
</form><br>

<form method="get" action="orderList" onsubmit="return validateDateFilter()">
    <input type="hidden" name="action" value="filteredOrders">
    <input type="text" name="fromDate" id="fromDate" placeholder="From Date (yyyy-MM-dd)">
    <input type="text" name="toDate" id="toDate" placeholder="To Date (yyyy-MM-dd)">
    <div class="error_message" id="dateError"></div>
    <input type="submit" value="Filter by Date">
</form><br>


<form action="logout" method="get">
<input type="submit" value="Logout">
</form>
</div>

<script>

function validateProductForm() {
    var categoriaField = document.getElementsByName("categoria")[0];
    var specieField = document.getElementsByName("specie")[0];
    var nameField = document.getElementsByName("name")[0];
    var descriptionField = document.getElementsByName("description")[0];
    var priceField = document.getElementsByName("price")[0];
    var nameImgField = document.getElementsByName("nameImg")[0];
    var quantityField = document.getElementsByName("quantity")[0];

    var categoriaRegex = /^[\s\S]{3,20}$/;
    var specieRegex = /^[\s\S]{3,20}$/;
    var nameRegex = /^[\s\S]{3,20}$/;
    var priceRegex = /^[0-9]+(\.[0-9]{1,2})?$/;
    var quantityRegex = /^[1-9][0-9]*$/;

    var categoriaError = document.getElementById("categoriaError");
    var specieError = document.getElementById("specieError");
    var nameError = document.getElementById("nameError");
    var descriptionError = document.getElementById("descriptionError");
    var priceError = document.getElementById("priceError");
    var nameImgError = document.getElementById("nameImgError");
    var quantityError = document.getElementById("quantityError");

    categoriaError.textContent = "";
    specieError.textContent = "";
    nameError.textContent = "";
    descriptionError.textContent = "";
    priceError.textContent = "";
    nameImgError.textContent = "";
    quantityError.textContent = "";

    var validForm = true;

    if (!categoriaRegex.test(categoriaField.value)) {
      categoriaError.textContent = "Please enter a valid categoria (up to 20 characters).";
      validForm = false;
    }

    if (!specieRegex.test(specieField.value)) {
      specieError.textContent = "Please enter a valid specie (up to 20 characters).";
      validForm = false;
    }

    if (!nameRegex.test(nameField.value)) {
      nameError.textContent = "Please enter a valid name (minimum 3 characters).";
      validForm = false;
    }

    if (descriptionField.value.trim() === "") {
      descriptionError.textContent = "Please enter a description.";
      validForm = false;
    }

    if (!priceRegex.test(priceField.value)) {
      priceError.textContent = "Please enter a valid price.";
      validForm = false;
    }

    if (!nameRegex.test(nameImgField.value)) {
      nameImgError.textContent = "Please enter a valid nameImg (up to 20 characters).";
      validForm = false;
    }

    if (!quantityRegex.test(quantityField.value)) {
      quantityError.textContent = "Please enter a valid quantity.";
      validForm = false;
    }

    return validForm;
  }
  
  
function validateEmailFilter() {
    var emailInput = document.getElementById("customerEmail");
    var emailPattern = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
    var emailError = document.getElementById("emailError");

    emailError.textContent = "";

    if (!emailPattern.test(emailInput.value)) {
        emailError.textContent = "Invalid email format.";
        return false;
    }

    return true;
}

function validateDateFilter() {
    var fromDateInput = document.getElementById("fromDate");
    var toDateInput = document.getElementById("toDate");
    var datePattern = /^\d{4}-\d{2}-\d{2}$/;
    var dateError = document.getElementById("dateError");

    dateError.textContent = "";

    if (!datePattern.test(fromDateInput.value) || !datePattern.test(toDateInput.value)) {
        dateError.textContent = "Invalid date format. Use yyyy-MM-dd.";
        return false;
    }

    return true;
}

var formFields = document.querySelectorAll("form input:not([type=hidden]), form textarea");
for (var i = 0; i < formFields.length; i++) {
    formFields[i].addEventListener("focus", function() {
        this.setAttribute("data-original-placeholder", this.getAttribute("placeholder"));
        this.setAttribute("placeholder", "");
    });

    formFields[i].addEventListener("blur", function() {
        if (this.value === "") {
            this.setAttribute("placeholder", this.getAttribute("data-original-placeholder"));
        }
    });
}
</script>

</body>
</html>