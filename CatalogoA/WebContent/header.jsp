<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 
<!DOCTYPE html>
<html>

<script type="text/javascript" src="caption.js"></script>
<head>
<link rel="stylesheet" href="css/header.css">

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    	<script src="https://kit.fontawesome.com/0a07c70885.js" crossorigin="anonymous"></script>
    	
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body class"header-body">

<nav class="header-navbar">
<div class="header-nav">
    <a href="Homepage.jsp">
  <img src="images/imgprofilo.jpg" class="header-brand-logo" alt="Logo">
</a>
    <div class="header-nav-items">
        <div class="header-search">
            <input type="text" class="header-search-box" placeholder="search brand, product">
            <button class="header-search-btn">search</button>
        </div>
        <a href="utente.jsp"><i class="fa-solid fa-circle-user fa-xl"></i></a>
        <a href="Cart.jsp"><i class="fa fa-shopping-cart "></i></a>
    </div>
</div>
</nav>

<ul class="header-links-container">
    
    <li class="header-link-item">
   <div class="header-dropdown"><a href="#" class="header-link"> <i class="fa-sharp fa-solid fa-dog"></i><p>Cani</p></a>
    <div class="header-dropdown-content">
			<a href="product?action=cibo_cane"> Cibo </a>
			<a href="product?action=accessori_cane"> Accessori </a>
			<a href="product?action=giocattoli_cane"> Giocattoli </a>
			<a href="product?action=igiene_cane"> Igiene </a>
		</div>
		</div></li>
		
		
    <li class="header-link-item">
   <div class="header-dropdown"><a href="#" class="header-link"><i class="fa-sharp fa-solid fa-cat"></i><p>Gatti</p></a>
    <div class="header-dropdown-content">
			<a href="product?action=cibo_gatto"> Cibo </a>
			<a href="product?action=accessori_gatto"> Accessori </a>
			<a href="product?action=giocattoli_gatto"> Giocattoli </a>
			<a href="product?action=igiene_gatto"> Igiene </a>
		</div>
		</div></li>
		
		
    <li class="header-link-item">
  <div class="header-dropdown"><a href="#" class="header-link"><i class="fa-solid fa-fish"></i> <p>Pesci</p></a>
    <div class="header-dropdown-content">
			<a href="product?action=cibo_pesci"> Cibo </a>
			<a href="product?action=accessori_pesci"> Accessori </a>
			
		</div>
		</div></li>
		
		
    <li class="header-link-item">
   <div class="header-dropdown"><a href="#" class="header-link"> <i class="fa-solid fa-crow"></i> <p>Uccelli</p></a>
    <div class="header-dropdown-content">
			<a href="product?action=cibo_uccelli"> Cibo </a>
			<a href="product?action=accessori_uccelli"> Accessori </a>
			<a href="product?action=gabbie_uccelli"> Gabbie </a>
			<a href="product?action=igiene_uccelli"> Igiene </a>
		</div>
		</div></li>
		
		
    <li class="header-link-item"><a href="product?action=all_products" class="header-link"><i class='fa-solid fa-bars'></i><p>Catalogo</p></a></li>
</ul>





</body>


</html>