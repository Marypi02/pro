<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, it.unisa.model.*"%>
    
<%
	Utente var = (Utente)request.getSession().getAttribute("utente");
	if(var == null){
		System.out.println("cose");
		response.sendRedirect("./login.html");
		return;
	}
	request.setAttribute("utente", null);
%>   
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
 		<meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <link rel="stylesheet" href="./css/modifica_utente.css">
	    <link rel="shortcut icon" type="image/png" href="logo.png">
	    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css">
	    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
		<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />	
	    <script src="https://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
	    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
		<title>andreea|Modifica dati utente</title>
	</head>
	
	<body>
		<jsp:include page="header.jsp" />
			
		<form method="post" action="Modifica_utente">
			<div class="areaUtente" id="prova">
				<div class="datiPersonali"> 
					<h1><strong>Modifica dati personali</strong></h1><hr>
					<div class="nicknameArea">
						<div class="nickSX">
							<div class="areaNick">
							 <p><strong>Email</strong></p>
							 <input class="campi" type="hidden" name="code" value="<%= var.getIdutente()%>">
                             <input class="campi" type="text" name="email" value="<%= var.getEmail()%>"> 
						    </div>
						</div>	
					</div>
				
					<div class="nicknameArea">				
	            		<div class="nickSX">
	                    	<div class="areaNick">
		                        <p><strong>Password</strong></p>	
		                        <input class="campi" type="password" name="password" value="<%= var.getPassword()%>">
	                    	</div>
	            		</div>
	        		</div>
	        		
	        		
				
					<div class="nicknameArea">				
	            		<div class="nickSX">
	                    	<div class="areaNick">
		                        <p><strong>Nome</strong></p>
		                        <input class="campi" type="text" name="nome" value="<%= var.getNome()%>">
	                    	</div>
	            		</div>
	        		</div> 
					
					<div class="nicknameArea">				
	            		<div class="nickSX">
	                    	<div class="areaNick">
		                        <p><strong>Cognome</strong></p>
		                        <input class="campi" type="text" name="cognome" value="<%= var.getCognome()%>">
	                    	</div>
	            		</div>
	        		</div>
	
					
	        		
	        		<div class="nicknameArea">				
	            		<div class="nickSX">
	                    	<div class="areaNick">
		                        <p><strong>Indirizzo</strong></p>
		                        <input class="campi" type="text" name="indirizzo" value="<%= var.getIndirizzo()%>">
	                    	</div>
	            		</div>
	        		</div>
	        		
	        		<div class="nicknameArea">				
	            		<div class="nickSX">
	                    	<div class="areaNick">
		                        <p><strong>Città</strong></p>
		                        <input class="campi" type="text" name="citta" value="<%= var.getCitta()%>">
	                    	</div>
	            		</div>
	        		</div>
	        		
	        		<div class="nicknameArea">				
	            		<div class="nickSX">
	                    	<div class="areaNick">
		                        <input class="Modifica" type="submit" name="action" value="conferma">
	                    	</div>
	            		</div>
	        		</div> 
        		</div>   		
			</div>
		</form>
		<jsp:include page="footer.jsp" />
	
	</body>
	
</html>
