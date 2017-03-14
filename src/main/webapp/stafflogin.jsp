<%-- 
    Document   : stafflogin
    Created on : 14-Mar-2017, 19:15:14
    Author     : The King
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uk.ac.dundee.computing.team7.agilequiz.lib.*" %>
<%@page import="uk.ac.dundee.computing.team7.agilequiz.servlets.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <script src="js/createQuiz.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <link rel="stylesheet" content= "text/css" href="${pageContext.request.contextPath}/style.css">
        <title>Staff Login</title>
    </head>
	
	
    <body>
        <%@ include file="header.jsp" %>
	<div class="container" style="text-align:center">
            <h1>Staff Login</h1><br>
                <div class="col-sm-12">
        
	<% String msg = (String)request.getAttribute("Message");
               if(msg != null) { %>
            <p id="flash_message"><%= msg %></p>
            <% } %>
	<form method="POST"  action="StaffLogin">	
                <ul>
                    <div class="input-group">
                        <span class="input-group-addon">Staff ID</span>
                        <input id="staffid" type="text" class="form-control" name="staffid" placeholder="Enter your Staff ID here">
                    </div>
                    <div class="input-group">
                        <span class="input-group-addon">Password</span>
                        <input id="password" type="password" class="form-control" name="password" placeholder="Enter your password here">
                    </div>
                </ul>
                <br/>
                <div class="span12" style="text-align:center">
                    <div class="STYLE">
                        <input type="submit" class="btn btn-success" value="Login">
                    </div>
                </div> 
		
	</form>
	</div>
	</div>
    </body>
</html>
