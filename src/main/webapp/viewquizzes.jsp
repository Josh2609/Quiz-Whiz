<%-- 
    Document   : viewquizzes
    Created on : 12-Mar-2017, 16:52:21
    Author     : joshcorps
--%>
<%LoggedIn lg2 = (LoggedIn) session.getAttribute("LoggedIn");
   if (lg2 == null) 
   {    response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/")); }
%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="uk.ac.dundee.computing.team7.agilequiz.models.Quiz"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Quizzes</title>
        <script src="js/createQuiz.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <link rel="stylesheet" content= "text/css" href="${pageContext.request.contextPath}/style.css">
    </head>
    <body>
        <%@ include file="header.jsp" %>
        <div class="container"><!-- style="text-align:center">-->

        <h1>Quiz List!</h1>
        <%
            ArrayList<String[]> quizList = (ArrayList<String[]>) request.getAttribute("quizList");
            int currentPage = (int) request.getAttribute("currentPage");;
            Iterator<String[]> iterator = quizList.iterator();
            while (iterator.hasNext())
            {
                String[] tempArr = iterator.next();
                %>
                <p><a href="${pageContext.request.contextPath}/SitQuiz/<%=tempArr[0]%>">
                <%
                for (int i = 0; i < tempArr.length; i++)
                {
                %>
                    &nbsp; <%=tempArr[i]%>  &nbsp;
                <%}
                %>
                </a></p>
                <%
            }
            
            if (currentPage != 1)
            {%>
                <button class="btn btn-success" onclick="location.href = '${pageContext.request.contextPath}/ViewQuiz?page=<%=currentPage-1%>'" type="button">Previous Page</button>
            <%}
            if (quizList.size() > 9)
            {%>
                <button class="btn btn-success" onclick="location.href = '${pageContext.request.contextPath}/ViewQuiz?page=<%=currentPage+1%>'" type="button">Next Page</button>
            <%}%>
        </div>
    </body>
</html>
