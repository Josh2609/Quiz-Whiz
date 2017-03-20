<%-- 
    Document   : viewresults
    Created on : 18-Mar-2017, 22:14:11
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
        <title>Results List</title>
        <script src="js/createQuiz.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.tablesorter.js"></script> 

        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <link rel="stylesheet" content= "text/css" href="${pageContext.request.contextPath}/style.css">
        <link rel="stylesheet" content= "text/css" href="${pageContext.request.contextPath}/themes/blue/style.css">        
    </head>
    <body>
    <%@ include file="header.jsp" %>
       <div class="container">
            <div class="panel panel-default" style='opacity: 0.85'>
                <div class="panel-body">
        <%
            ArrayList<String[]> quizList = (ArrayList<String[]>) request.getAttribute("quizList");
            if (quizList.isEmpty())
            {
                %>
                <h2 style="text-align:center">No Results found</h2>
                <%
            } else {%>
            <table id="myTable" class="table table-hover tablesorter"> 
            <thead>
                <tr>
                    <th>Quiz Name</th>
                    <th>Description</th>
                    <th>Module Code</th>
                    <th>Module Name</th>
                    <th>Score</th>
                    <th>Time Submitted</th>
                </tr>
            </thead>
                <tbody>
                    <%
            int currentPage = (int) request.getAttribute("currentPage");;
            Iterator<String[]> iterator = quizList.iterator();
            while (iterator.hasNext())
            {
                String[] tempArr = iterator.next();
                %>
                <tr class='clickable-row' data-href="${pageContext.request.contextPath}/QuizResults?cquizid=<%=tempArr[0]%>&quizid=<%=tempArr[1]%>&score=<%=tempArr[2]%>">
                    <td>&nbsp; <%=tempArr[4]%>  &nbsp;</td>
                    <td>&nbsp; <%=tempArr[7]%>  &nbsp;</td>
                    <td>&nbsp; <%=tempArr[5]%>  &nbsp;</td>
                    <td>&nbsp; <%=tempArr[6]%>  &nbsp;</td>
                    <td>&nbsp; <%=tempArr[2]%>  &nbsp;</td>
                    <td>&nbsp; <%=tempArr[8]%>  &nbsp;</td>
           <%}%>
                </tr>
            </tbody>
        </table>
            <%
            if (currentPage != 1)
            {%>
                <button class="btn btn-success" onclick="location.href = '${pageContext.request.contextPath}/ViewResults?page=<%=currentPage-1%>'" type="button">Previous Page</button>
            <%}
            if (quizList.size() > 24)
            {%>
                <button class="btn btn-success" onclick="location.href = '${pageContext.request.contextPath}/ViewResults?page=<%=currentPage+1%>'" type="button">Next Page</button>
            <%}}%>
        </div>
            </div>
        </div>
    </body>
</html>

<script>
jQuery(document).ready(function($) {
    $(".clickable-row").click(function() {
        window.location = $(this).data("href");
    });
});
$(document).ready(function() { 
    // call the tablesorter plugin 
    $("table").tablesorter(); 
}); 
</script>