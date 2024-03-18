<%@page import="org.example.data.Relation"%>
<%@page import="org.example.crud.CRUDRelation"%>
<%@page import="org.example.crud.CRUDAuthorization"%>
<%@page import="org.example.data.Authorization"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>PFP</title>
    <link rel="stylesheet" href="stylesheets/common.css">
    <link rel="stylesheet" href="stylesheets/userlist.css">
</head>
<body>
<%
    long userId = Long.parseLong(request.getParameter("id"));
    Authorization user = CRUDAuthorization.dbGetUserByID(userId);
    if (user != null) {
%>
<h1><%= "List of available contacts for user: " + user.getLogin() %></h1>
<%
    // Получение отношений из таблицы relation
    List<Relation> relations = CRUDRelation.dbGetAllRelation();
    boolean hasContacts = false;
    for (Relation rel : relations) {
        if ((rel.getUserId() == userId || rel.getFriendId() == userId) && rel.getRelationType() == 2) {
            hasContacts = true;
%>
<h1><%= (rel.getUserId() == userId) ? CRUDAuthorization.dbGetUserByID(rel.getFriendId()).getLogin() : CRUDAuthorization.dbGetUserByID(rel.getUserId()).getLogin() %></h1>
<%
        }
    }
    if (!hasContacts) {
%>
<h1>Sorry, but for this user we don't have any additional information :(</h1>
<%
    }
} else {
%>
<h1>User not found</h1>
<%
    }
%>
</body>
</html>
