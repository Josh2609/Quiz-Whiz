<%-- 
    Document   : login
    Created on : 14-Feb-2017, 15:08:51
    Author     : Josh Corps
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uk.ac.dundee.computing.team7.agilequiz.lib.*" %>
<%@page import="uk.ac.dundee.computing.team7.agilequiz.servlets.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>Login</h1>
	<% String msg = (String)request.getAttribute("Message");
               if(msg != null) { %>
            <p id="flash_message"><%= msg %></p>
            <% } %>
            <form method="POST"  action="Login">
                <ul>
                    <li>Matric Number <input type="text" name="matric" required="true"></li>
                    <li>Password <input type="password" name="password" required="true"></li>
                </ul>
                <br/>
                <input type="submit" value="Login"> 
            </form>
    </body>
</html>
