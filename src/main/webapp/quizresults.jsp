<%-- 
    Document   : result
    Created on : 08-Mar-2017, 20:39:52
    Author     : joshcorps
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quiz Results</title>
    </head>
    <body>
        <%
        int correctAnswers = (Integer) request.getAttribute("correctAnswers");
            
        if (correctAnswers == 1)
        {%>
        <h1>You got <%=correctAnswers%> answer right</h1>
        <%} else {%>
        <h1>You got <%=correctAnswers%> answers right</h1>
        <%}%>
    </body>
</html>
