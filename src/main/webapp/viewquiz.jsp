<%-- 
    Document   : viewquiz
    Created on : 20-Feb-2017, 21:28:52
    Author     : joshcorps
--%>

<%@page import="uk.ac.dundee.computing.team7.agilequiz.stores.AnswerBean"%>
<%@page import="java.util.Iterator"%>
<%@page import="uk.ac.dundee.computing.team7.agilequiz.stores.QuestionBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
	<%@ include file="header.jsp" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Quiz</title>
        <script src="js/createQuiz.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    </head>
    <body>
    
    <div class="container"><!-- style="text-align:center">-->
        <div style="text-align: center">
            <h1>Quiz Name Here</h1>
            <p>Description displayed here.</p>
            <h3>Date Added: 01/01/0001</h3>
            <h3>Module Code - Module Name</h3>
            <h3>Quiz Author</h3><br>
        </div>
        <%
            java.util.ArrayList<QuestionBean> questions = (java.util.ArrayList<QuestionBean>) request.getAttribute("questions");
            java.util.ArrayList<AnswerBean> answers = (java.util.ArrayList<AnswerBean>) request.getAttribute("answers");
            if (questions.isEmpty()) {
        %>
        <h2 style="text-align:center">No Questions found</h2>
        <%
        } else {
            // seems to not print the first question
            Iterator<QuestionBean> iterator;
            iterator = questions.iterator();
            int i = 0;
            while (iterator.hasNext()) 
            {
                i++;
                QuestionBean qb = (QuestionBean) iterator.next();
                System.out.println(qb.getQuestionText());

                
        %>
        <div class="panel panel-success" name="panel<%=qb.getQuestionID()%>">
            <div class="panel-heading"><h4>#<%=i%> <%=qb.getQuestionText()%></h4></div>
                <div class="panel-body">
        <%
                Iterator<AnswerBean> iterator2;
                iterator2 = answers.iterator();
                int j = 0;
                while (iterator2.hasNext()) 
                {
                    AnswerBean ab = (AnswerBean) iterator2.next();
                    //System.out.println("qqID=" +qb.getQuestionID() );
                    //System.out.println("aqID=" +ab.getQuestionID() );
                    if (ab.getQuestionID() == qb.getQuestionID() )
                    {
                        j++;
        %>
        <div class="col-sm-3">
            <div class="radio disabled">
                <label><input style="text-align:left" type="radio" name="optradio<%=i%>"<% if(j==1){%> checked="checked" <%}else{%> disabled <%}%>><%=ab.getAnswerText()%></label>
            </div>
        </div>
        <%
                    }
                }
            %>
                </div>
            </div>
        <%
            }
        }
        %>
    </div>
    </body>
</html>
