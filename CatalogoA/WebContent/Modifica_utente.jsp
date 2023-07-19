<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, it.unisa.model.*"%>
    
<%
    Utente var = (Utente) request.getSession().getAttribute("utente");
    if (var == null) {
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

    <form method="post" action="modUtente" onsubmit="return validateForm()">
        <div class="areaUtente" id="prova">
            <div class="datiPersonali"> 
                <h1><strong>Modifica dati personali</strong></h1><hr>
                <div class="nicknameArea">
                    <div class="nickSX">
                        <div class="areaNick">
                            <p><strong>Email</strong></p>
                            <input class="campi" type="hidden" name="code" value="<%= var.getIdutente()%>">
                            <input class="campi" type="text" name="email" id="email" value="<%= var.getEmail()%>" placeholder="Email">
                            <div id="emailError" class="error_message"></div>
                        </div>
                    </div>    
                </div>

                <div class="nicknameArea">               
                    <div class="nickSX">
                        <div class="areaNick">
                            <p><strong>Password</strong></p>    
                            <input class="campi" type="password" name="password" id="password" value="<%= var.getPassword()%>" placeholder="Password">
                            <div id="passwordError" class="error_message"></div>
                        </div>
                    </div>
                </div>

                <div class="nicknameArea">               
                    <div class="nickSX">
                        <div class="areaNick">
                            <p><strong>Nome</strong></p>
                            <input class="campi" type="text" name="nome" id="nome" value="<%= var.getNome()%>" placeholder="Nome">
                            <div id="nomeError" class="error_message"></div>
                        </div>
                    </div>
                </div> 

                <div class="nicknameArea">               
                    <div class="nickSX">
                        <div class="areaNick">
                            <p><strong>Cognome</strong></p>
                            <input class="campi" type="text" name="cognome" id="cognome" value="<%= var.getCognome()%>" placeholder="Cognome">
                            <div id="cognomeError" class="error_message"></div>
                        </div>
                    </div>
                </div>

                <div class="nicknameArea">               
                    <div class="nickSX">
                        <div class="areaNick">
                            <p><strong>Indirizzo</strong></p>
                            <input class="campi" type="text" name="indirizzo" id="indirizzo" value="<%= var.getIndirizzo()%>" placeholder="Indirizzo">
                            <div id="indirizzoError" class="error_message"></div>
                        </div>
                    </div>
                </div>

                <div class="nicknameArea">               
                    <div class="nickSX">
                        <div class="areaNick">
                            <p><strong>Città</strong></p>
                            <input class="campi" type="text" name="citta" id="citta" value="<%= var.getCitta()%>" placeholder="Città">
                            <div id="cittaError" class="error_message"></div>
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

    <script>
        function validateForm() {
            var emailField = document.getElementById("email");
            var passwordField = document.getElementById("password");
            var nomeField = document.getElementById("nome");
            var cognomeField = document.getElementById("cognome");
            var indirizzoField = document.getElementById("indirizzo");
            var cittaField = document.getElementById("citta");

            var emailRegex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
            var passwordRegex = /^.{3,}$/;
            var nomeRegex = /^[a-zA-Z\s]{3,}$/;
            var cognomeRegex = /^[a-zA-Z\s]{3,}$/;
            var indirizzoRegex = /^.{3,}$/;
            var cittaRegex = /^[a-zA-Z\s]{3,}$/;

            var errorMessages = document.getElementsByClassName("error_message");
            for (var i = 0; i < errorMessages.length; i++) {
                errorMessages[i].textContent = "";
            }

            if (!emailRegex.test(emailField.value)) {
                document.getElementById("emailError").textContent = "Please enter a valid email.";
                return false;
            }

            if (!passwordRegex.test(passwordField.value)) {
                document.getElementById("passwordError").textContent = "Please enter a valid password.";
                return false;
            }

            if (!nomeRegex.test(nomeField.value)) {
                document.getElementById("nomeError").textContent = "Please enter a valid name.";
                return false;
            }

            if (!cognomeRegex.test(cognomeField.value)) {
                document.getElementById("cognomeError").textContent = "Please enter a valid surname.";
                return false;
            }

            if (!indirizzoRegex.test(indirizzoField.value)) {
                document.getElementById("indirizzoError").textContent = "Please enter a valid address.";
                return false;
            }

            if (!cittaRegex.test(cittaField.value)) {
                document.getElementById("cittaError").textContent = "Please enter a valid city.";
                return false;
            }

            return true;
        }

        var formFields = document.getElementsByClassName("campi");
        for (var i = 0; i < formFields.length; i++) {
          // Salva il placeholder originale in una variabile
          var originalPlaceholder = formFields[i].getAttribute("placeholder");

          // Rimuovi il placeholder quando il campo di input ottiene il focus
          formFields[i].addEventListener("focus", function() {
            this.setAttribute("data-original-placeholder", this.getAttribute("placeholder"));
            this.setAttribute("placeholder", "");
          });

          // Ripristina il placeholder originale quando il campo di input perde il focus e il suo valore è vuoto
          formFields[i].addEventListener("blur", function() {
            if (this.value === "") {
              this.setAttribute("placeholder", this.getAttribute("data-original-placeholder"));
            }
          });
        }



    </script>
</body>
</html>
