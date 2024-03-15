<%@ page import="java.util.List" %>
<%@ page import="org.example.data.Authorization" %>
<%@ page import="org.example.crud.CRUDAuthorization" %>
<%@ page import="com.google.common.hash.Hashing" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>PFP</title>
    <link rel="stylesheet" href="stylesheets/common.css">
    <link rel="stylesheet" href="stylesheets/userlist.css">
</head>
<body>

<a href="${pageContext.request.contextPath}/registration.jsp">Авторизация</a>
<form action="${pageContext.request.contextPath}/sign_in.jsp" method="post">
    <label for="emailOrLogin">Email или логин:</label>
    <input type="text" id="emailOrLogin" name="emailOrLogin"><br><br>

    <label for="password">Пароль:</label>
    <input type="password" id="password" name="password"><br><br>

    <input type="submit" value="Войти">

    <%
        // Получаем параметры из запроса
        String emailOrLogin = request.getParameter("emailOrLogin");
        String password = request.getParameter("password");
        List <Authorization> authorizations = CRUDAuthorization.dbGetAllUsers();
        for (Authorization auth:authorizations) {
            if(auth.getLogin()==emailOrLogin||auth.getEmail()==emailOrLogin)
            {
                if(Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString().equals(auth.getHashOfPass())){
                    out.println("Authentication successful!");
                    response.sendRedirect("${pageContext.request.contextPath}/index.jsp?id="+auth.getId());}
            }
        }
        out.println("Authentication failed!");
    %>
</form>
</body>

</html>
