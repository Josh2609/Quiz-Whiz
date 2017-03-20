<%-- 
    Document   : result
    Created on : 08-Mar-2017, 20:39:52
    Author     : joshcorps
--%>
<%LoggedIn lg2 = (LoggedIn) session.getAttribute("LoggedIn");
   if (lg2 == null) 
   {    response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/")); }
%>
<%@page import="uk.ac.dundee.computing.team7.agilequiz.stores.AnswerBean"%>
<%@page import="java.util.Iterator"%>
<%@page import="uk.ac.dundee.computing.team7.agilequiz.stores.QuestionBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quiz Results</title>
        <script src="${pageContext.request.contextPath}/js/createQuiz.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <link rel="stylesheet" content= "text/css" href="${pageContext.request.contextPath}/style.css">
    </head>
    <body>

    <%@ include file="header.jsp" %>
    <% 
        int corAns = (int)request.getAttribute("numCorrect");
        int numQue = (int)request.getAttribute("numQuestions");
        
        System.out.println("corAns = " + corAns);
        System.out.println("numQue = " + numQue);
        
        double percRight = (corAns*100)/numQue;
        System.out.println("percRight = " + percRight);
        /*int corAns=0;
        int numQue=0;
        
        int percRight=0;*/
    %>
    
    <div class="container"><!-- style="text-align:center">-->
        <%
            java.util.ArrayList<QuestionBean> questions = (java.util.ArrayList<QuestionBean>) request.getAttribute("questions");
            java.util.ArrayList<AnswerBean> answers = (java.util.ArrayList<AnswerBean>) request.getAttribute("answers");
            java.util.ArrayList<Integer> studentAnswers = (java.util.ArrayList<Integer>) request.getAttribute("studentAnswers");
            QuizBean quizBean = (QuizBean) request.getAttribute("quizBean");
        %>
        <div style="text-align: center;color:#eeeeee;">
            <h1><%=quizBean.getQuizName()%></h1>
            <p><%=quizBean.getQuizDescription()%></p>
            <h3>Date Added: <%=quizBean.getDateAdded()%></h3>
            <h3><%=quizBean.getModuleCode()%> - <%=quizBean.getModuleName()%></h3>
            <h3>Created By: <%=quizBean.getQuizCreator()%></h3><br>
            
            <h1>Percentage Correct: <%=percRight%></h1>
            <h1>Score: <%=corAns%>/<%=numQue%></h1>
        </div>  
        <%if (questions.isEmpty()) {
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

                    if (ab.getQuestionID() == qb.getQuestionID() )
                    {
                        j++;
        %>
        <div class="col-sm-3">
            <div class="radio disabled">
                <%
                int pos = 0;
                if (studentAnswers.contains(ab.getAnswerID()))
                {
                    pos = studentAnswers.indexOf(ab.getAnswerID());
                    if(studentAnswers.get(pos) == ab.getAnswerID())
                    {
                        if(ab.getCorrectAnswer())
                        {%>
                        <label style="color:green;"><input style="text-align:left" type="radio" name="optradio<%=i%>" checked="checked" disabled><%=ab.getAnswerText()%></label>
                        <%} else {%>
                            <label style="color:red;"><input style="text-align:left" type="radio" name="optradio<%=i%>" checked="checked" disabled><%=ab.getAnswerText()%></label>
                        <%}
                    }
                } else {
                    if (ab.getCorrectAnswer()){%>
                        <label style="color:green;"><input style="text-align:left" type="radio" name="optradio<%=i%>" disabled><%=ab.getAnswerText()%></label>
                    <%} else {%>
                        <label style="color:red;"><input style="text-align:left" type="radio" name="optradio<%=i%>" disabled><%=ab.getAnswerText()%></label>
                    <%}
                }%>
                
               
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
