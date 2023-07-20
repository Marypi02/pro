<%@ page language="java" contentType="text/html; utf-8"
    pageEncoding="utf-8" import="java.util.*"%>
<!DOCTYPE html>
<html>

	<head>
 		<meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <link rel="stylesheet" href="css/error500.css">
	    <link rel="shortcut icon" type="image/png" href="logo.png">
		<title>|Error500</title>
	</head>
	
	<body>
		<jsp:include page="header.jsp"/>
		
			<div class="grazie">
				<span> Attenzione! C'è stato un problema col server! Riprova!</span>
			</div>

			<div class="acquisto">
				<img src="images/500.jpeg" alt="err505" class="responsive">
			</div>
			
			<div class="torna">
				<a href="Homepage.jsp"><input class="procedi" type="button" value="Torna all'Homepage"> </a>
			</div>
		

			
		<jsp:include page="footer.jsp"/>
	</body>
	
</html>