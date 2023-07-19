<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 
<!DOCTYPE html>
<html>


<head>
<link rel="stylesheet" href="css/header.css">

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    	<script src="https://kit.fontawesome.com/0a07c70885.js" crossorigin="anonymous"></script>
    	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
		
    	
    	
<meta charset="ISO-8859-1">
<title>Insert title here</title>




</head>
<body>

<nav class="header-navbar">
<div class="header-nav">

  <a href="Homepage.jsp" > <img src="images/logo.jpg" class="header-brand-logo" alt=""></a> 
    
    
    
    
    <div class="header-nav-items">
    

        <a href="utente.jsp"><i class="fa-solid fa-circle-user fa-xl"></i></a>
        <a href="Cart.jsp"><i class="fa fa-shopping-cart "></i></a>
    </div>
    
     <!-- Barra di Ricerca -->
            <div class="header-search">
                <input type="text" id="searchInput" class="header-search-box" placeholder="Cerca prodotto">
                <div id="searchResults"></div>
                <button id="searchButton" class="header-search-btn" type="button"><i class="ion-ios-search"></i></button>
            </div>
</div>
</nav>

<ul class="header-links-container">
<i class="fa-sharp fa-solid fa-dog"></i>
   <div class="dropdown"> <li class="header-link-item"><a href="#" class="header-link">Cani</a></li>
    <div class="dropdown-content">
			<a href="product?action=cibo_cane"> Cibo </a>
			<a href="product?action=accessori_cane"> Accessori </a>
			<a href="product?action=giocattoli_cane"> Giocattoli </a>
			<a href="product?action=igiene_cane"> Igiene </a>
		</div>
		</div>
		<i class="fa-sharp fa-solid fa-cat"></i>
   <div class="dropdown"> <li class="header-link-item"><a href="#" class="header-link">Gatti</a></li>
    <div class="dropdown-content">
			<a href="product?action=cibo_gatto"> Cibo </a>
			<a href="product?action=accessori_gatto"> Accessori </a>
			<a href="product?action=giocattoli_gatto"> Giocattoli </a>
			<a href="product?action=igiene_gatto"> Igiene </a>
		</div>
		</div>
		<i class="fa-solid fa-fish"></i>
  <div class="dropdown">  <li class="header-link-item"><a href="#" class="header-link">Pesci</a></li>
    <div class="dropdown-content">
			<a href="product?action=cibo_pesci"> Cibo </a>
			<a href="product?action=accessori_pesci"> Accessori </a>
			
		</div>
		</div>
		<i class="fa-solid fa-crow"></i>
   <div class="dropdown"> <li class="header-link-item"><a href="#" class="header-link">Uccelli</a></li>
    <div class="dropdown-content">
			<a href="product?action=cibo_uccelli"> Cibo </a>
			<a href="product?action=accessori_uccelli"> Accessori </a>
			<a href="product?action=gabbie_uccelli"> Gabbie </a>
			<a href="product?action=igiene_uccelli"> Igiene </a>
		</div>
		</div>
    <li class="header-link-item"><a href="product?action=all_products" class="header-link">Catalogo</a></li>
</ul>


<script>
  document.getElementById("searchButton").addEventListener("click", function() {
    var searchInput = document.getElementById("searchInput").value;
    var searchResultsDiv = document.getElementById("searchResults");
    
    // Effettua la richiesta AJAX
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
      if (xhr.readyState === 4 && xhr.status === 200) {
        var results = JSON.parse(xhr.responseText);
        displaySearchResults(results);
      }
    };
    xhr.open("GET", "SearchServlet?query=" + encodeURIComponent(searchInput), true);
    xhr.send();
  });
  
  function displaySearchResults(results) {
	  var searchResultsDiv = document.getElementById("searchResults");
	  searchResultsDiv.innerHTML = "";
	  
	  if (results.length === 0) {
	    searchResultsDiv.innerHTML = "Nessun risultato trovato.";
	    return;
	  }
	  
	  for (var i = 0; i < results.length; i++) {
	    var product = results[i];
	    var productLink = document.createElement("a");
	    productLink.href = "product?action=read&id=" + product.code;
	    productLink.target = "_blank";
	    
	    var productName = document.createTextNode(product.name);
	    var productDescription = document.createTextNode(product.description);
	    var productPrice = document.createTextNode(product.price + "€");
	    
	    var productImage = document.createElement("img");
	    productImage.src = "images/" + product.nomeImg;
	    productImage.className = "imgProdotto";
	    
	    productLink.appendChild(productImage);
	    productLink.appendChild(document.createElement("br"));
	    productLink.appendChild(productName);
	    productLink.appendChild(document.createElement("br"));
	    productLink.appendChild(productDescription);
	    productLink.appendChild(document.createElement("br"));
	    productLink.appendChild(productPrice);
	    
	    searchResultsDiv.appendChild(productLink);
	  }
	}

</script>

</body>


</html>