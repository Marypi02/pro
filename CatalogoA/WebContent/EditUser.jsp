<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
    import="java.util.*,it.unisa.model.Utente"%>
    
    <%
    // Ottiene l'attributo "utente" dalla richiesta
    Utente bean = (Utente) request.getAttribute("utente");

	%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <link rel="stylesheet" href="css/protected.css" type="text/css">
<title>Edit user</title>
</head>
<body>
    <h1>Edit User</h1>
    
    <form action="modUtente" method="post">
        <input type="hidden" name="action" value="updateUser">
        <input type="hidden" name="id" value="${bean.getIdutente()}">
        
        <label for="email">Email:</label>
        <input type="text" name="email" maxlength="40" value="${bean.getEmail()}">
        <br>
        
        <label for="pwd">Password:</label>
        <input type="text" name="pwd" maxlength="100" value="${bean.getPassword()}">
        <br>
        
        <label for="name">Nome:</label>
        <input type="text" name="name" maxlength="40" value="${bean.getNome()}">
        <br>
        
        <label for="cognome">Cognome:</label>
        <input type="text" name="cognome" maxlength="40" value="${bean.getCognome()}">
        <br>
        
        <label for="address">Indirizzo:</label>
        <input type="text" name="address" maxlength="100" value="${bean.getIndirizzo()}">
        <br>
        
        <label for="citta">Città:</label>
        <input type="text" name="citta" maxlength="40" value="${bean.getCitta()}">
        <br>
        
        <input type="submit" value="Save Changes">
    </form>
</body>
</html>