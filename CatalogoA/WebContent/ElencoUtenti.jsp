<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"
    import="it.unisa.model.Utente, java.util.*" %>

<%
Collection<?> userList = (Collection<?>) request.getAttribute("UserList");
Utente searchResult = (Utente) request.getAttribute("searchResult");
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1" http-equiv="Content-Type" content="text/html">
<link rel="stylesheet" href="css/protected.css" type="text/css">
<title>Users list</title>
</head>
<body>
    <h1>Utenti registrati nel database</h1>
    <table border="1">
        <tr>
            <th>Email</th>
            <th>First Name</th>
            <th>Surname</th>
            <th>Address</th>
            <th>From</th>
        </tr>
        <% if (userList != null && !userList.isEmpty()) {
            for (Object obj : userList) {
                Utente user = (Utente) obj;
        %>
        <tr>
            <td><%= user.getEmail() %></td>
            <td><%= user.getNome() %></td>
            <td><%= user.getCognome() %></td>
            <td><%= user.getIndirizzo() %></td>
            <td><%= user.getCitta() %></td>
        </tr>
        <% }
        } else {
        %>
        <tr>
            <td colspan="5">No users available</td>
        </tr>
        <% } %>
    </table>
 <h1>Ricerca tramite email</h1>
    <form method="post" action="UserList">
        <input type="hidden" name="action" value="searchUser">
        <input type="text" name="email" placeholder="Inserisci l'email dell'utente">
        <input type="submit" value="Cerca">
    </form>

    <% if (searchResult != null) { %>
    <h2>Risultato della ricerca:</h2>
    <table border="1">
        <tr>
            <th>Email</th>
            <th>First Name</th>
            <th>Surname</th>
            <th>Address</th>
            <th>From</th>
        </tr>
        <tr>
            <td><%= searchResult.getEmail() %></td>
            <td><%= searchResult.getNome() %></td>
            <td><%= searchResult.getCognome() %></td>
            <td><%= searchResult.getIndirizzo() %></td>
            <td><%= searchResult.getCitta() %></td>
        </tr>
    </table>
    <% } %>
    <br>
    <form action="UserList" method="get">
        <input type="hidden" name="action" value="comeBack">
        <input type="submit" value="Torna alla pagina protetta">
    </form>
</body>
</html>
