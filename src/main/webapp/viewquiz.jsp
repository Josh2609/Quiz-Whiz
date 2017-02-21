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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Quiz</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        
        <%
            java.util.ArrayList<QuestionBean> questions = (java.util.ArrayList<QuestionBean>) request.getAttribute("questions");
            java.util.ArrayList<AnswerBean> answers = (java.util.ArrayList<AnswerBean>) request.getAttribute("answers");
            if (questions == null) {
        %>
        <p>No Questions found</p>
        <%
        } else {
            // seems to not print the first question
            Iterator<QuestionBean> iterator;
            iterator = questions.iterator();
            while (iterator.hasNext()) 
            {
                QuestionBean qb = (QuestionBean) iterator.next();
                System.out.println(qb.getQuestionText());


        %>
        <p><%=qb.getQuestionText()%></p>
        <%
                Iterator<AnswerBean> iterator2;
                iterator2 = answers.iterator();
                while (iterator2.hasNext()) 
                {
                    
                    AnswerBean ab = (AnswerBean) iterator2.next();
                    //System.out.println("qqID=" +qb.getQuestionID() );
                    //System.out.println("aqID=" +ab.getQuestionID() );
                    if (ab.getQuestionID() == qb.getQuestionID() )
                    {
        %>
        <p><%=ab.getAnswerText()%></p>
        <%
                    }
                }  
            }
        }
        %>
    </body>
</html>
