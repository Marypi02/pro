<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
    import="java.util.*,it.unisa.model.ProductBean,it.unisa.model.cart"%>
    
<%
    // Ottiene l'attributo "product" dalla richiesta
    ProductBean product = (ProductBean) request.getAttribute("product");
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
    
    <form action="product" method="post" onsubmit="return validateForm()">
        <input type="hidden" name="action" value="updateProduct">
        <input type="hidden" name="id" value="${product.getCode()}">

        <label for="name">Name:</label>
        <input type="text" name="name" id="name" maxlength="20" value="${product.getName()}" placeholder="Name" class="form-field">
        <div id="nameError" class="error_message"></div>
        <br>

        <label for="description">Description:</label>
        <input type="text" name="description" id="description" maxlength="100" rows="3" value="${product.getDescription()}" placeholder="Description" class="form-field">
        <div id="descriptionError" class="error_message"></div>
        <br>

        <label for="price">Price:</label>
        <input type="text" name="price" id="price" min="0" value="${product.getPrice()}" placeholder="Price" class="form-field">
        <div id="priceError" class="error_message"></div>
        <br>

        <label for="quantity">Quantity:</label>
        <input type="text" name="quantity" id="quantity" maxlength="20" value="${product.getQuantity()}" placeholder="Quantity" class="form-field">
        <div id="quantityError" class="error_message"></div>
        <br>

        <label for="nameImg">Image Name:</label>
        <input type="text" name="nameImg" id="nameImg" min="1" value="${product.getNomeImg()}" placeholder="Image Name" class="form-field">
        <div id="nameImgError" class="error_message"></div>
        <br>

        <input type="submit" value="Save Changes">
    </form>

    <script>
        function validateForm() {
            var nameField = document.getElementById("name");
            var descriptionField = document.getElementById("description");
            var priceField = document.getElementById("price");
            var quantityField = document.getElementById("quantity");
            var nameImgField = document.getElementById("nameImg");

            // Espressioni regolari per la validazione dei campi
            var nameRegex = /^[a-zA-Z\s]{1,50}$/;
            var descriptionRegex = /^.{1,100}$/;
            var priceRegex = /^\d+(\.\d{1,3})?$/;
            var quantityRegex = /^[1-9]\d*$/;
            var nameImgRegex = /^.{1,}$/;

            // Ripulisci i messaggi di errore precedenti
            var errorMessages = document.getElementsByClassName("error_message");
            for (var i = 0; i < errorMessages.length; i++) {
                errorMessages[i].textContent = "";
            }

            // Validazione del campo Name
            if (!nameRegex.test(nameField.value)) {
                document.getElementById("nameError").textContent = "Please enter a valid name (up to 50 characters, letters and spaces only).";
                return false;
            }

            // Validazione del campo Description
            if (!descriptionRegex.test(descriptionField.value)) {
                document.getElementById("descriptionError").textContent = "Please enter a valid description (up to 100 characters).";
                return false;
            }

            // Validazione del campo Price
            if (!priceRegex.test(priceField.value)) {
                document.getElementById("priceError").textContent = "Please enter a valid price (numeric value, e.g. 10.99).";
                return false;
            }

            // Validazione del campo Quantity
            if (!quantityRegex.test(quantityField.value)) {
                document.getElementById("quantityError").textContent = "Please enter a valid quantity (positive integer).";
                return false;
            }

            // Validazione del campo Image Name
            if (!nameImgRegex.test(nameImgField.value)) {
                document.getElementById("nameImgError").textContent = "Please enter a valid image name.";
                return false;
            }

            return true;
        }
    </script>
</body>
</html>
