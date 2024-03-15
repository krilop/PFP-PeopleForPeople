<%@page import="org.example.crud.CRUDAuthorization" %>
<%@page import="org.example.data.Authorization" %>
<%@page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>PFP</title>
    <link rel="stylesheet" href="stylesheets/common.css">
    <link rel="stylesheet" href="stylesheets/userlist.css">

</head>
<body>
<h1><%= "List of available users:"%></h1>
<%
    List<Authorization> users = CRUDAuthorization.dbGetAllUsers();
    for (Authorization user: users) {
%>
<div class="user-info">
    <p>Login: <%= user.getLogin() %></p>
    <p>Email: <%= user.getEmail() %></p>
    <a href="profile.jsp?id=<%=user.getId()%>">Подробнее</a>
</div>
<% } %>
</body>
</html>