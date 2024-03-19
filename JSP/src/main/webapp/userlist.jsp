<%@page import="org.example.crud.CRUDAuthorization" %>
<%@page import="org.example.data.Authorization" %>
<%@page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ru,en" xml:lang="ru,en">

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
        if(user.getId()!= Long.parseLong(request.getParameter("id"))){
%>
<div class="user-info">
    <p>Login: <%= user.getLogin() %></p>
    <p>Email: <%= user.getEmail() %></p>
    <a href="profile.jsp?check=<%=user.getId()%>&id=<%= request.getParameter("id") %>">Подробнее</a>
    <a href="friends.jsp?check=<%=user.getId()%>&id=<%= request.getParameter("id") %>">Просмотреть друзей</a>
    <a href="addfriend.jsp?id=<%= request.getParameter("id") %>&friendId=<%= user.getId() %>">Добавить в друзья</a>


</div>
<% }} %>
</body>
</html>