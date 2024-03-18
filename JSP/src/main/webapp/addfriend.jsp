<%@ page import="org.example.data.Relation" %>
<%@ page import="java.io.IOException" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="org.example.crud.CRUDRelation" %>

<%
    String userId = request.getParameter("id");
    String friendId = request.getParameter("friendId");
    String relationType = "1";

    Relation rel = new Relation(0L, Long.parseLong(userId), Long.parseLong(friendId), Long.parseLong(relationType));
    int status = CRUDRelation.dbSaveRelation(rel);

    if (status == 1)
        out.println("Relation saved successfully");
    else
        out.println("ERROR while saving relation");
%>