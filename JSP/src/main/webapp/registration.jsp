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
    <%
        if(request.getParameter("id")!=null)
        {
            response.sendRedirect("/index.jsp?id="+request.getParameter("id"));
        }
    %>
<a href="${e:forHtml(pageContext.request.contextPath)}/sign_in.jsp">Авторизация</a>
<form action="${e:forHtml(pageContext.request.contextPath)}/registration.jsp" method="post">
    <label for="login">Login:</label>
    <input type="text" id="login" name="login"><br><br>


    <label for="email">Email:</label>
    <input type="text" id="email" name="email"><br><br>

    <label for="password">Пароль:</label>
    <input type="password" id="password" name="password"><br><br>

    <input type="submit" value="Зарегистрироваться">

    <%
            // Получаем параметры из запроса
            String email = request.getParameter("email");
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            List <Authorization> authorizations = CRUDAuthorization.dbGetAllUsers();
            int source = 1;
        for (Authorization auth:authorizations) {
            if(source == 1 && (auth.getLogin()==login||auth.getEmail()==email))
            {
                source = 0;
                out.println("Registration failed!");
            }
        }
        if(source==1&&email!=null&&login!=null&&password!=null)
        {
            CRUDAuthorization.createUser(login, password, email);
            out.println("Authentication successful!");
            Authorization auth = CRUDAuthorization.dbGetUserByName(login);
            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + "/index.jsp?id=" + auth.getId());
        }
    %>
</form>
</body>

</html>
