<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>PFP</title>
  <link rel="stylesheet" href="stylesheets/common.css">
</head>
<body>
<h1><%= "Welcome to our new platform for acquaintances!"%></h1>
<% if(request.getParameter("id")!=null)
{%>
<a href="${e:forHtml(pageContext.request.contextPath)}/userlist.jsp?id=<%= request.getParameter("id") %>">Список доступных пользователей</a>

<%}else{
%>
<a href="${e:forHtml(pageContext.request.contextPath)}/sign_in.jsp">Авторизация</a>
<a href="${e:forHtml(pageContext.request.contextPath)}/registration.jsp">Регистрация</a>

<%}%>
</body>
</html>