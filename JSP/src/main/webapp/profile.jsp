<%@page import="org.example.crud.CRUDContact" %>
<%@page import="org.example.data.Contact" %>
<%@page import="org.example.crud.CRUDAuthorization" %>
<%@page import="org.example.data.Authorization" %>
<%@page import="java.util.List" %>
<%@ page import="org.example.data.ContactType" %>
<%@ page import="org.example.DBFunctions" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.ResultSet" %>
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
    Authorization user = CRUDAuthorization.dbGetUserByID(Long.parseLong(request.getParameter("check")));
%>
<h1><%= "List of available contact for user:"+user.getLogin()%></h1>
<%
    List<Contact> contacts = CRUDContact.dbGetAllContacts();
    int cnt=0;
    for (Contact co: contacts) {
        if(user.getId()==co.getUserId()){
            cnt++;
            String info=null;
            DBFunctions db = new DBFunctions();
            Connection conn = null;
            ResultSet rs = null;
            PreparedStatement ps = null;
            try {
                conn = db.connectToDB("PFP", "krimlad", "krilop");
                ps = conn.prepareStatement("SELECT contact_title FROM contact_type WHERE id = ?");
                ps.setLong(1, co.getContactType());
                rs = ps.executeQuery();
                while (rs.next()) {
                     info= rs.getString("contact_title");
                }
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException e) {
                    }
                }
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException e) {
                    }
                }
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                    }

                }
            }
%>
<div class="user-info">
    <p>Contact: <%= info %></p>
    <p>Info: <%= co.getInfo() %></p>
</div>
<% }}
    if(cnt==0){%>
<h1>Sorry, but for this user we don't have any additional information :(</h1>
<%}%>
</body>
</html>