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
        <script src="js/createQuiz.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <link rel="stylesheet" content= "text/css" href="${pageContext.request.contextPath}/style.css">
        <title>Login</title>
    </head>
	
	
    <body>
        <%@ include file="header.jsp" %>
	<div class="container">
    <div class="col-sm-9">
	<h1 style="text-align:center">Login</h1><br>
        
	<% String msg = (String)request.getAttribute("Message");
               if(msg != null) { %>
            <p id="flash_message"><%= msg %></p>
            <% } %>
	<form method="POST"  action="Login">	
                <ul>
                    <div class="input-group">
                        <span class="input-group-addon">Matriculation Number</span>
                        <input id="msg" type="text" class="form-control" name="matric" placeholder="Enter your matriculation number here">
                    </div>
                    <div class="input-group">
                        <span class="input-group-addon">&emsp;&emsp;&emsp;&emsp;&emsp;&nbsp;Password</span>
                        <input id="msg" type="password" class="form-control" name="password" placeholder="Enter your password here">
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