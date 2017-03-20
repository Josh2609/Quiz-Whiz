<%-- 
    Document   : addmodule
    Created on : 21-Feb-2017, 18:00:09
    Author     : team7
--%>
<%LoggedIn lg2 = (LoggedIn) session.getAttribute("LoggedIn");
   if (lg2 == null) 
   {    response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/")); }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add a module</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <link rel="stylesheet" type= "text/css" href="style.css"> 
        <link rel="shortcut icon" href="https://s-media-cache-ak0.pinimg.com/originals/04/38/35/0438350175391a71727f8dac6e7be433.png">
    </head>
    <body>
        <%@ include file="header.jsp" %>
        <div class="container">
            <div class="panel panel-default">
            <div class="panel-body">
            <div class="col-sm-12" style="text-align:center">
            <form method="POST"  action="AddModule">
                <div class="input-group">
                    <span class="input-group-addon">Module Code</span>
                    <input type="text" class="form-control" name="moduleCode" required="true">
                </div><br>
                <div class="input-group">
                    <span class="input-group-addon">Module Name</span>
                    <input type="text" class="form-control" name="moduleName" required="true">
                </div>
                <br>
                <input class="btn btn-success" type="submit" value="Add Module"> 
            </form>
            </div>
            </div></div>
        </div>
    </body>
</html>