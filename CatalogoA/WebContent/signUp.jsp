<!DOCTYPE html>
<html lang="">
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Registrazione</title>
<link rel="icon" type="image/png" href="loginImage/iconaLogo.png">
<link rel="stylesheet" href="css/signUp.css" type="text/css">
<script>
	function printError(elemId, hintMsg) {
		document.getElementById(elemId).innerHTML = hintMsg;
	}

	function validateForm() {

		var nome = document.regForm.nome.value;
		var cognome = document.regForm.cognome.value;
		var email = document.regForm.email.value;
		var password = document.regForm.password.value;
		var indirizzo = document.regForm.indirizzo.value

		var nameErr = cognomeErr = passwordErr = emailErr = indirizzoErr = true;

		//Nome
		if (nome == "") {
			printError("nameErr", "Inserire un nome");
		} else {
			var regex = /^[a-zA-Z\s]+$/;
			if (regex.test(nome) === false) {
				printError("nameErr", "Inserire un nome valido");
			} else {
				printError("nameErr", "");
				nameErr = false;
			}
		}

		//Cognome
		if (cognome == "") {
			printError("cognomeErr", "Inserire un cognome");
		} else {
			var regex = /^[a-zA-Z\s]+$/;
			if (regex.test(cognome) === false) {
				printError("cognomeErr", "Inserire un cognome valido");
			} else {
				printError("cognomeErr", "");
				cognomeErr = false;
			}
		}

		//E-mail
		if (email == "") {
			printError("emailErr", "Inserire una e-mail");
		} else {
			var regex = /^\S+@\S+\.\S+$/;
			if (regex.test(email) === false) {
				printError("emailErr", "Inserire una e-mail valida");
			} else {
				printError("emailErr", "");
				emailErr = false;
			}
		}

		//Password
		if (password == "") {
			printError("passwordErr", "Inserire una password");
		} else {
			var regex = /^[A-Za-z]\w{7,14}$/;
			if (regex.test(password) === false) {
				printError("passwordErr", "Inserire una password valido");
			} else {
				printError("passwordErr", "");
				passwordErr = false;
			}
		}

		//Indirizzo
		if (indirizzo == "") {
			printError("indirizzoErr", "Inserire un indirizzo");
		} else {
			var regex = /^[0-9a-zA-Z,]\w{8,60}]+$/;
			if (regex.test(indirizzo) === false) {
				printError("indirizzoErr", "Inserire un indirizzo valido");
			} else {
				printError("indirizzoErr", "");
				indirizzoErr = false;
			}
		}

		if ((nameErr = cognomeErr = usernameErr = passwordErr = emailErr) == true) {
			return false;
		}
	};

	function check(value) {
		xmlHttp = GetXmlHttpObject()
		var url = "checkajax.jsp";
		url = url + "?email=" + value;
		xmlHttp.onreadystatechange = stateChanged
		xmlHttp.open("GET", url, true)
		xmlHttp.send(null)
	}
	function stateChanged() {
		if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete") {
			var showdata = xmlHttp.responseText;
			document.getElementById("mydiv").innerHTML = showdata;
		}
	}
	function GetXmlHttpObject() {
		var xmlHttp = null;
		try {
			xmlHttp = new XMLHttpRequest();
		} catch (e) {
			try {
				xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
		}
		return xmlHttp;
	}
</script>
</head>

<body>
	<div class="main">
		<a href="home.html"><img src="loginImage/logo.png" alt="Logo"
			width="100"></a>
		<h1>Creazione account</h1>

		<form name="regForm" action="utente" onsubmit="return validateForm()"
			method="post">
			<input type="email" name="email" id="email" class="testo" placeholder="Email"
				autofocus onkeyup="check(this.value);"><br>
			<div class="error" id="emailErr"></div>

			<input type="password" name="password" class="testo" maxlength="8"
				placeholder="Password"><br>
			<div class="error" id="passwordErr"></div>

			<input type="text" name="nome" class="testo" placeholder="Nome"><br>
			<div class="error" id="nameErr"></div>

			<input type="text" name="cognome" class="testo" placeholder="Cognome"><br>
			<div class="error" id="cognomeErr"></div>

			<input type="text" name="indirizzo" class="testo"
				placeholder="Indirizzo"><br>
			<div class="error" id="indirizzoErr"></div>

			<input type="text" name="citta'" class="testo"
				placeholder="Citta', Provincia, Cap"><br>
			<br> <input type="submit" value="Iscriviti" id="pulsante">
		</form>
		<p>
			Oppure <a href="login.html" id="login">accedi</a>
		</p>
	</div>
</body>
</html>