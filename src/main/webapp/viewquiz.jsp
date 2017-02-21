<%-- 
    Document   : viewquiz
    Created on : 20-Feb-2017, 21:28:52
    Author     : joshcorps
--%>

<%@page import="java.util.Iterator"%>
<%@page import="uk.ac.dundee.computing.team7.agilequiz.stores.QuestionBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Quiz</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        
        <%
            java.util.ArrayList<QuestionBean> questions = (java.util.ArrayList<QuestionBean>) request.getAttribute("questions");
            if (questions == null) {
        %>
        <p>No Questions found</p>
        <%
        } else {
            Iterator<QuestionBean> iterator;
            iterator = questions.iterator();
            while (iterator.hasNext()) 
            {
                QuestionBean qb = (QuestionBean) iterator.next();

        %>
        <p><%=qb.getQuestionText()%></p>
        <%

            }
        }
        %>
    </body>
</html>
