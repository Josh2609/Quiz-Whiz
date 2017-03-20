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
        <title>View Results</title>
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
        <div class="container"><!-- style="text-align:center">-->
            <div class="panel panel-default" style='opacity: 0.85'>
                <div class="panel-body">
        <h1>Quiz List!</h1>
        
        <%
            int numQuestions = (int) request.getAttribute("numQuestions");
            ArrayList<String[]> quizList = (ArrayList<String[]>) request.getAttribute("quizList");
            int currentPage = (int) request.getAttribute("currentPage");
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
                    <th>Module</th>
                    <th>Score</th>
                    <th>Student Name</th>
                    <th>Matric Number</th>
                    <th>Time Submitted</th>
                </tr>
            </thead>
                <tbody>
            <%
            Iterator<String[]> iterator = quizList.iterator();
            while (iterator.hasNext())
            {
                String[] tempArr = iterator.next();
                int scorePerc = (Integer.parseInt(tempArr[2])*100)/numQuestions;
                %>
                <tr class='clickable-row' data-href="${pageContext.request.contextPath}/QuizResults?cquizid=<%=tempArr[0]%>&quizid=<%=tempArr[1]%>&score=<%=tempArr[2]%>">
                    <td>&nbsp; <%=tempArr[4]%>  &nbsp;</td>
                    <td>&nbsp; <%=tempArr[5]%>  &nbsp;</td>
                    <td>&nbsp; <%=scorePerc%>%  &nbsp;</td>
                    <td>&nbsp; <%=tempArr[7]%>  <%=tempArr[8]%>&nbsp;</td>
                    <td>&nbsp; <%=tempArr[9]%>  &nbsp;</td>
                    <td>&nbsp; <%=tempArr[10]%>  &nbsp;</td>
           <%}%>
                </tr>
            </tbody>
        </table>
                <%
            
            String sortBy = "Quiz_ID";
                int quizID = Integer.parseInt(request.getParameter("quizid"));
                if (request.getParameter("sortby") != null)
                {
                    sortBy = request.getParameter("sortby");
                }

            if (currentPage != 1)
            {%>
                <button class="btn btn-success" onclick="location.href = '${pageContext.request.contextPath}/StaffResultsList?page=<%=currentPage-1%>&sortby=<%=sortBy%>&quizid=<%=quizID%>'" type="button">Previous Page</button>
            <%}
            if (quizList.size() > 24)
            {%>
                <button class="btn btn-success" onclick="location.href = '${pageContext.request.contextPath}/StaffResultsList?page=<%=currentPage+1%>&sortby=<%=sortBy%>&quizid=<%=quizID%>'" type="button">Next Page</button>
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
