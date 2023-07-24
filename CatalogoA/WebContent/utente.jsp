<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, it.unisa.model.*"%>
   
    
   <%Utente obj = (Utente)request.getSession().getAttribute("utente");
    	if(obj == null){%>
    		
    		<jsp:forward page="login.html"/>
    	<%}
    	
    	 if (obj.isAdmin()){ %>
    		<jsp:forward page="protected.jsp" />
    		
    	<% }  
 
    	
    	
 		
    	
    	
	
    	ArrayList<ProductOrder> ordini = (ArrayList<ProductOrder>) request.getAttribute("ordini");	
    	if(ordini == null){
    		response.sendRedirect("Mostra_ordini_utente?action=mostra");
    		return;
    	}
    	%>
    
    
    
<!DOCTYPE html>
<html lang = "en">
	<head>
	
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta charset="ISO-8859-1">
 		<meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <link rel="stylesheet" href="css/profilo_utente.css">
	    <link rel="shortcut icon" type="image/png" href="logo.png">
	    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css">
	    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
		<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />	
		<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
	    
	    <script src="https://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
	    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	    
		<title>andreea |Area utente</title>
	</head>
	
	<body>
		 <jsp:include page="header.jsp" /> 
	
	
	
		<div class="areaUtente" id="prova">
		    <div class="datiPersonali">
		        <h1><strong>Dati Personali</strong></h1><hr>
		        <div class="nicknameArea">				
		            <div class="nickSX">
							<span class="material-symbols-outlined"> mail </span>
		 					<div class="areaNick">
			                    <p><strong>Email</strong></p>
			                    <p><%=obj.getEmail() %></p>
		                	</div>
		            </div>
		        </div> 
		        
		        <div class="nicknameArea">				
		            <div class="nickSX">
		 					<span class="material-symbols-outlined"> person </span>
		 					<div class="areaNick">
			                    <p><strong>Nome</strong></p>
			                    <p><%=obj.getNome() %></p>
		                	</div>
		            </div>
		        </div> 
		             
		        <div class="nicknameArea">				
		            <div class="nickSX">
		            		<span class="material-symbols-outlined"> person </span>
		 					<div class="areaNick">
			                    <p><strong>Cognome</strong></p>
			                    <p><%=obj.getCognome() %></p>
		                	</div>
		            </div>
		        </div> 
		        
		        <div class="nicknameArea">				
		            <div class="nickSX">
		            		<span class="material-symbols-outlined"> vpn_key </span>
		 					<div class="areaNick">
			                    <p><strong>Password</strong></p>
			                    <p>*********</p>
		                	</div>
		            </div>
		        </div> 
		       
				
				 <form action ="modifica" method="get">
					<input type="submit" class="Modifica" value="Modifica dati">
				</form>	 
				
		
			
			
				
				<form action ="logout" method="get" >
					<input type="submit" class="Modifica2" value="Logout">
				</form>	
					
					<br><br>
						
						
					

					
					
												
		    </div>
		</div>		
		
		<div class="metodi">
			<div class="met">
				<h2><strong>Metodi di pagamento</strong></h2>
				<form id="met_pag" method="post" action="./salva">
				    <input type="hidden" name="action" value="salvaPagamento">
					<input class="campi" type="text" placeholder="Intestatario" id="intestatario" name="intestatario" >
					<input class="campi" type="text" placeholder="CVV" id="cod_cvv" name="cod_cvv" maxlength="3"  >
					<input class="campi" type="month" placeholder="Mese scadenza" id="month" name="month" >
					<input class="campi" type="text" placeholder="Codice carta" id="cod_carta" name="cod_carta" maxlength="12" >
					
					
					<br>
				 <input type="submit" id="pay" class="save" value="Salva nuovo metodo di pagamento"> 

				</form>
				
		
				<br>   
    		
    
			</div>

			<div class="met">
				<h2><strong>Indirizzi di spedizione</strong></h2>
				<form id="met_send" method="post" action="./consegna">
					<input class="campi" type="text" placeholder="Via" id="via" name="via" >
					<input class="campi" type="text" placeholder="CAP" id="cap" name="cap" maxlength="5" >
					<input class="campi" type="text" placeholder="Numero civico" id="number" name="number" maxlength="6" >
					<input class="campi" type="text" placeholder="Citta" id="provincia" name="citta" >
					
					<br>
					<input type="submit" id="address" class="save"  value="Salva nuovo indirizzo di spedizione">

				</form>
			</div>	    
		</div>
		
				<div class="error_box">
  					<p class="error_message error_message_cvv"></p>
  					<p class="error_message error_message_carta"></p>
 				    <p class="error_message error_message_via"></p>
  					<p class="error_message error_message_prov"></p>
				</div>


		
    <br>
		
<div class="tabella">
    <table class="tab">
    
    	<tr>
            <th colspan="4" class="ciclo">Dati ordine</th>
        </tr>
        <tr>
            <th>ID ordine</th>
            <th>Data ordine</th>
            <th>Stato ordine</th>
            <th>Totale</th>
        </tr>

        <% if (ordini != null && !ordini.isEmpty()) {
            for (ProductOrder bean : ordini) { %>
                <tr>
                    <td><%= bean.getIdOrdine() %></td>
                    <td><%= bean.getData_ordine() %></td>
                    <td><%= bean.getStato_ordine() %></td>
                    <td><%= String.format("%.02f", bean.getTotalCost2()) %> &euro;</td>
                </tr>
            <% }
        } else {
        %>
        <tr>
            <td colspan="4">No orders available</td>
        </tr>
        <% } %>
    </table>
</div>





			
	
		<br><br>
		<jsp:include page="footer.jsp" /> 
		
		
		    <script>
    // Funzione per la validazione del campo CVV
    function validateCVV() {
        var cvvField = document.getElementById("cod_cvv");
        var cvvValue = cvvField.value;

        // Espressione regolare per il CVV a 3 cifre
        var cvvRegex = /^[0-9]{3}$/;

        if (!cvvRegex.test(cvvValue)) {
            document.querySelector(".error_message_cvv").textContent = "Il CVV deve essere un numero di 3 cifre.";
            document.querySelector(".error_message_cvv").style.display = "block";
            cvvField.classList.add("error");
            return false;
        }

        document.querySelector(".error_message_cvv").style.display = "none";
        cvvField.classList.remove("error");
        return true;
    }

    // Funzione per la validazione del campo Codice carta
    function validateCodiceCarta() {
        var codiceCartaField = document.getElementById("cod_carta");
        var codiceCartaValue = codiceCartaField.value;

        // Espressione regolare per il codice carta di 12 cifre
        var codiceCartaRegex = /^[0-9]{12}$/;

        if (!codiceCartaRegex.test(codiceCartaValue)) {
            document.querySelector(".error_message_carta").textContent = "Il codice carta deve essere un numero di 12 cifre.";
            document.querySelector(".error_message_carta").style.display = "block";
            codiceCartaField.classList.add("error");
            return false;
        }

        document.querySelector(".error_message_carta").style.display = "none";
        codiceCartaField.classList.remove("error");
        return true;
    }

    // Funzione per la validazione del campo Via
    function validateVia() {
        var viaField = document.getElementById("via");
        var viaValue = viaField.value;

        // Espressione regolare per la via (almeno 2 caratteri)
        var viaRegex = /^.{2,}$/;

        if (!viaRegex.test(viaValue)) {
            document.querySelector(".error_message_via").textContent = "Inserisci una via valida.";
            document.querySelector(".error_message_via").style.display = "block";
            viaField.classList.add("error");
            return false;
        }

        document.querySelector(".error_message_via").style.display = "none";
        viaField.classList.remove("error");
        return true;
    }

    // Funzione per la validazione del campo Città
    function validateCitta() {
        var cittaField = document.getElementById("provincia");
        var cittaValue = cittaField.value;

        // Espressione regolare per la città (almeno 2 caratteri)
        var cittaRegex = /^.{2,}$/;

        if (!cittaRegex.test(cittaValue)) {
            document.querySelector(".error_message_prov").textContent = "Inserisci una città valida.";
            document.querySelector(".error_message_prov").style.display = "block";
            cittaField.classList.add("error");
            return false;
        }

        document.querySelector(".error_message_prov").style.display = "none";
        cittaField.classList.remove("error");
        return true;
    }

    // Event listener per la validazione dei campi nel form dei metodi di pagamento
    document.getElementById("met_pag").addEventListener("submit", function(event) {
        if (!validateCVV() || !validateCodiceCarta()) {
            event.preventDefault(); // Blocca l'invio del form se i campi non sono validi
        }
    });

    // Event listener per la validazione dei campi nel form degli indirizzi di spedizione
    document.getElementById("met_send").addEventListener("submit", function(event) {
        if (!validateVia() || !validateCitta()) {
            event.preventDefault(); // Blocca l'invio del form se i campi non sono validi
        }
    });
</script> 
		
	</body>
</html>