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
<body>

<nav class="header-navbar">
<div class="header-nav">

  <a href="Homepage.jsp" > <img src="images/logo.jpg" class="header-brand-logo" alt=""></a> 
    
    
    
    
    <div class="header-nav-items">
    

        <a href="#"><i class="fa-solid fa-circle-user fa-xl"></i></a>
        <a href="Cart.jsp"><i class="fa fa-shopping-cart "></i></a>
    </div>
</div>
</nav>

<ul class="header-links-container">
<i class="fa-sharp fa-solid fa-dog"></i>
   <div class="dropdown"> <li class="header-link-item"><a href="#" class="header-link">Cani</a></li>
    <div class="dropdown-content">
			<a href="cibo_cane.jsp"> Cibo </a>
			<a href="accessori_cane.jsp"> Accessori </a>
			<a href="giocattoli_cani.jsp"> Giocattoli </a>
			<a href="igiene_cani.jsp"> Igiene </a>
		</div>
		</div>
		<i class="fa-sharp fa-solid fa-cat"></i>
   <div class="dropdown"> <li class="header-link-item"><a href="#" class="header-link">Gatti</a></li>
    <div class="dropdown-content">
			<a href="cibo_gatto.jsp"> Cibo </a>
			<a href="accessori_gatti.jsp"> Accessori </a>
			<a href="giocattoli_gatti.jsp"> Giocattoli </a>
			<a href="igiene_gatto.jsp"> Igiene </a>
		</div>
		</div>
		<i class="fa-solid fa-fish"></i>
  <div class="dropdown">  <li class="header-link-item"><a href="#" class="header-link">Pesci</a></li>
    <div class="dropdown-content">
			<a href="cibo_pesci.jsp"> Cibo </a>
			<a href="accessori_pesci.jsp"> Accessori </a>
			
		</div>
		</div>
		<i class="fa-solid fa-crow"></i>
   <div class="dropdown"> <li class="header-link-item"><a href="#" class="header-link">Uccelli</a></li>
    <div class="dropdown-content">
			<a href="cibo_uccelli.jsp"> Cibo </a>
			<a href="accessori_uccelli.jsp"> Accessori </a>
			<a href="gabbie_uccelli.jsp"> Gabbie </a>
			<a href="igiene_uccelli.jsp"> Igiene </a>
		</div>
		</div>
    <li class="header-link-item"><a href="ProductView.jsp" class="header-link">Catalogo</a></li>
</ul>
</body>


</html>