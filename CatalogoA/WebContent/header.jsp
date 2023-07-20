<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang = "en">

<script type="text/javascript" src="caption.js"></script>
<head>
<link rel="stylesheet" href="css/header.css">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
<script src="https://kit.fontawesome.com/0a07c70885.js"
	crossorigin="anonymous"></script>
<script>
	var request = new XMLHttpRequest();
	function searchInfo() {
		var name = document.vinform.name.value;
		var url = "NavBar.jsp?val=" + name;

		try {
			request.onreadystatechange = function() {
				if (request.readyState == 4) {
					var val = request.responseText;
					document.getElementById('mylocation').innerHTML = val;
				}
			}//end of function  
			request.open("GET", url, true);
			request.send();
		} catch (e) {
			alert("Impossibile connettersi al server!");
		}
	}
</script>

<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
</head>
<body>
	<nav class="header-navbar">
		<div class="header-nav">

			<a href="Homepage.jsp"> <img src="images/logo.jpg"
				class="header-brand-logo" alt=""></a>
				
			<form name="vinform">
				<input type="text" id="ricercabar" name="name" onkeyup="searchInfo()" placeholder="Cerca su PetParadise">
				<div id="mylocation"></div>
			</form>




			<div class="header-nav-items">


				<a href="utente.jsp"><i class="fa-solid fa-circle-user fa-xl"></i></a>
				<a href="Cart.jsp"><i class="fa fa-shopping-cart "></i></a>
			</div>
		</div>
	</nav>

	<ul class="header-links-container">
		<i class="fa-sharp fa-solid fa-dog"></i>
		<div class="dropdown">
			<li class="header-link-item"><a href="#" class="header-link">Cani</a></li>
			<div class="dropdown-content">
				<a href="product?action=cibo_cane"> Cibo </a> <a
					href="product?action=accessori_cane"> Accessori </a> <a
					href="product?action=giocattoli_cane"> Giocattoli </a> <a
					href="product?action=igiene_cane"> Igiene </a>
			</div>
		</div>
		<i class="fa-sharp fa-solid fa-cat"></i>
		<div class="dropdown">
			<li class="header-link-item"><a href="#" class="header-link">Gatti</a></li>
			<div class="dropdown-content">
				<a href="product?action=cibo_gatto"> Cibo </a> <a
					href="product?action=accessori_gatto"> Accessori </a> <a
					href="product?action=giocattoli_gatto"> Giocattoli </a> <a
					href="product?action=igiene_gatto"> Igiene </a>
			</div>
		</div>
		<i class="fa-solid fa-fish"></i>
		<div class="dropdown">
			<li class="header-link-item"><a href="#" class="header-link">Pesci</a></li>
			<div class="dropdown-content">
				<a href="product?action=cibo_pesci"> Cibo </a> <a
					href="product?action=accessori_pesci"> Accessori </a>

			</div>
		</div>
		<i class="fa-solid fa-crow"></i>
		<div class="dropdown">
			<li class="header-link-item"><a href="#" class="header-link">Uccelli</a></li>
			<div class="dropdown-content">
				<a href="product?action=cibo_uccelli"> Cibo </a> <a
					href="product?action=accessori_uccelli"> Accessori </a> <a
					href="product?action=gabbie_uccelli"> Gabbie </a> <a
					href="product?action=igiene_uccelli"> Igiene </a>
			</div>
		</div>
		<li class="header-link-item"><a
			href="product?action=all_products" class="header-link">Catalogo</a></li>
	</ul>
</body>


</html>
