<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, it.unisa.model.*"%>
    
   <%Utente obj = (Utente)request.getSession().getAttribute("utente");
    	if(obj == null){%>
    		
    		<jsp:forward page="login.html"/>
    	<%}
    	
    	 if (obj.isAdmin()){ %>
    		<jsp:forward page="protected.jsp" />
    		
    	<% }  
 
 		
    	 ArrayList<PagamentoBean> pagamenti =   (ArrayList<PagamentoBean>) request.getAttribute("pagamenti");
	
    	ArrayList<ProductOrder> ordini = (ArrayList<ProductOrder>) request.getAttribute("ordini");	
    	if(ordini != null){
    		response.sendRedirect("Mostra_ordini_utente?action=mostra");
    		return;
    	}
    	%>
    
    
    
<!DOCTYPE html>
<html>
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
	    <script>
			function visualizzaCatalogo() {
   				 window.location.href = "ProductView.jsp";
					}
			</script>
	    
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
		
		<button onclick="visualizzaCatalogo()">Visualizza catalogo prodotti</button>
		
		
		
		
		
		
		
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
				
			
				
			 <!--  	<form method="get" action="./salva">
        			<input type="hidden" name="action" value="viewPagamento">
       	 			<input type="submit" value="vedi dati pagamento">
    		</form><br> -->
    		
    
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
				<p class="error_message_cvv" style="display:none;"></p>
				<p class="error_message_carta" style="display:none;"></p>
				<p class="error_message_via" style="display:none;"></p>
				<p class="error_message_prov" style="display:none;"></p>
			</div>

		
    <br>
		<div class="ciclo">
    <p>I tuoi dati di pagamento</p>
</div>

<table>
    <tr>
        <th>Nominativo</th>
        <th>CVV</th>
        <th>Mese Scadenza</th>
        <th>Codice Carta</th>
        <th>Anno Scadenza</th>
    </tr>

    <% if (pagamenti != null && !pagamenti.isEmpty()) {
        for (PagamentoBean pagamento : pagamenti) {
    %>
    <tr>
        <td><%= pagamento.getNominativo() %></td>
        <td><%= pagamento.getCVV() %></td>
        <td><%= pagamento.getMeseScadenza() %></td>
        <td><%= pagamento.getCodice_carta() %></td>
        <td><%= pagamento.getAnnoScadenza() %></td>
    </tr>
    <% }
    } else {
    %>
    <tr>
        <td colspan="5">No payment methods available</td>
    </tr>
    <% } %>
</table>

			<div class="ciclo">
             		<p>I tuoi dati di consegna</p>
          </div>

<%
    for (ConsegnaBean pbean : obj.getConsegna()){ %>
    <div class="dunno">
        <p><strong>Via:</strong> <%=pbean.getVia() %></p>
        <p><strong>CAP:</strong> <%=pbean.getCap() %></p>
        <p><strong>Numero:</strong> <%=pbean.getNumero() %></p>
        <p><strong>Città:</strong> <%=pbean.getCitta() %></p>
    </div>
<% } %>

<div class="tabella">
    <table class="tab">
    
    	 <div class="ciclo">
             		<p>Dati ordine</p>
          </div>
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
		
		
		<!--  <script> //SCRIPT SUI CONTROLLI
 			
			$("#pay").click(function(e){
				e.preventDefault();
				var flag = true; 
				//controllo sul codice carta
					if ($.isNumeric($("#cod_carta").val())){
						$(".error_message_carta").css("display","none");
					}else{
						$(".error_message_carta").html("Il formato del codice carta da te inserito non è valido");
						$(".error_message_carta").css("display","flex");
						$(".error_message_carta").css("color", "red");
					 	flag = false; 
					}	
				//controllo sul cvv
					if ($.isNumeric($("#cod_cvv").val())){
						$(".error_message_cvv").css("display","none");
					}else{
						$(".error_message_cvv").html("Il formato del codice CVV da te inserito non è valido");
						$(".error_message_cvv").css("display","flex");
						$(".error_message_cvv").css("color", "red");
					 	flag = false; 
					}
					//controllo sull'intestatario
					if ($("#intestatario").val() != ""){
						$(".error_message_via").css("display","none");
					}else{
						$(".error_message_via").html("Il formato dell'intestatario da te inserito non è valido");
						$(".error_message_via").css("display","flex");
						$(".error_message_via").css("color", "red");
					 	flag = false; 
					}
					//controllo sul mese
					if ($("#month").val() != ""){
						$(".error_message_prov").css("display","none");
					}else{
						$(".error_message_prov").html("Il formato della data da te inserito non è valido");
						$(".error_message_prov").css("display","flex");
						$(".error_message_prov").css("color", "red");
					 	flag = false; 
					}	
 				if (flag == true){
					$("#met_pag").submit();
				} 
			}) 
		
		

 			$("#address").click(function(e){
				e.preventDefault();
				var flag = true;
				//controllo sul numero civico
					if ($.isNumeric($("#number").val())){
						$(".error_message_cvv").css("display","none");
					}else{
						$(".error_message_cvv").html("Il formato del numero civico da te inserito non è valido");
						$(".error_message_cvv").css("display","flex");
						$(".error_message_cvv").css("color", "red");
						flag = false;
					}		
				//controllo sul CAP
					if ($.isNumeric($("#cap").val())){
						$(".error_message_carta").css("display","none");
					}else{
						$(".error_message_carta").html("Il formato del codice CAP da te inserito non è valido");
						$(".error_message_carta").css("display","flex");
						$(".error_message_carta").css("color", "red");
						flag = false;
					}
					//controllo sulla VIA
					if ($("#via").val() != ""){
						$(".error_message_via").css("display","none");
					}else{
						$(".error_message_via").html("Il formato della via da te inserito non è valido");
						$(".error_message_via").css("display","flex");
						$(".error_message_via").css("color", "red");
					 	flag = false; 
					}
					//controllo sulla PROVINCIA
					if ($("#provincia").val() != ""){
						$(".error_message_prov").css("display","none");
					}else{
						$(".error_message_prov").html("Il formato della provincia da te inserito non è valido");
						$(".error_message_prov").css("display","flex");
						$(".error_message_prov").css("color", "red");
					 	flag = false; 
					}
				if (flag == true){
					$("#met_send").submit();
				}
			}) 
		</script> -->
		
	</body>
</html>