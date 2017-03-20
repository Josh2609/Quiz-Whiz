<%-- 
    Document   : editquiz
    Created on : 21-Feb-2017, 19:24:30
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
        <title>Edit Quiz</title>
        <script src="${pageContext.request.contextPath}/js/createQuiz.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js" charset="utf-8"></script>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <link rel="stylesheet" content= "text/css" href="${pageContext.request.contextPath}/style.css">
        <link rel="shortcut icon" href="https://s-media-cache-ak0.pinimg.com/originals/04/38/35/0438350175391a71727f8dac6e7be433.png">
    </head>
    <body>
        
        <%@ include file="header.jsp" %>
        <!--<div class="container">  -->      
        <%
            java.util.ArrayList<QuestionBean> questions = (java.util.ArrayList<QuestionBean>) request.getAttribute("questions");
            java.util.ArrayList<AnswerBean> answers = (java.util.ArrayList<AnswerBean>) request.getAttribute("answers");
            if (questions.isEmpty()) {
        %>
        <p>No Questions found</p>
        <%
        } else {
        %>
        <form class="questions" method="POST"  action="EditQuiz">
            <!--<input type="text" name="oldQuizID" value="<%=request.getAttribute("quizID")%>">-->
            <div class="container" style="text-align:center">
                <div class="panel panel-default" name="QuizDetails">
                    <div class="panel-body" style="text-align:center">
                <p class="field switch">
                    <input type="radio" id="radio1" name="radioAv" value="Av" checked hidden/>
                    <input type="radio" id="radio2" name="radioAv" value="Un" hidden/>
                    <label for="radio1" class="cb-enable selected"><span>Available</span></label>
                    <label for="radio2" class="cb-disable"><span>Unavailable</span></label>
                </p>
                </div></div>
        <%
            Iterator<QuestionBean> iterator;
            iterator = questions.iterator();
            int questionNum = 1;
            int iterateQue = 0;
            int iterateAns = 0;
            while (iterator.hasNext()) 
            {
                iterateQue++;
                QuestionBean qb = (QuestionBean) iterator.next();
                System.out.println(qb.getQuestionText());


        %>
        <div class="panel panel-default" name="<%=iterateQue%>">
            <div class="panel-heading">
                <h4>Questions<h4>
                <div class="input-group"><span class="input-group-addon">Question <%=questionNum%></span>
                <input class="form-control" type="text" name="question_<%=iterateQue%>" value="<%=qb.getQuestionText()%>">
                </div></div>
                <br>
                <h4>Answers</h4>
            <div class="panel-body">
        <input type="text" name="question-<%=iterateQue%>" value="<%=qb.getQuestionID()%>" hidden>
        <%
                Iterator<AnswerBean> iterator2;
                iterator2 = answers.iterator();
                int answerNum = 1;
                
                while (iterator2.hasNext()) 
                {
                    AnswerBean ab = (AnswerBean) iterator2.next();

                    if (ab.getQuestionID() == qb.getQuestionID() )
                    {   
                        iterateAns++;
                        %>
                        <div class="input-group">
                            <%
                                if(answerNum == 1){
                            %>
                            <span class="input-group-addon">Correct Answer</span>
                            <%
                                }else{
                            %>
                            <span class="input-group-addon">Incorrect Answer <%=(answerNum - 1)%></span>
                            <%
                                }
                            %>
                            <input type="text" class="form-control" name="<%=iterateAns%>_answer" value="<%=ab.getAnswerText()%>">
                        </div>
                        <br>
                        <input type="text" name="<%=iterateAns%>-answer" value="<%=ab.getAnswerID()%>" hidden>
                        <%
                        answerNum++;
                    }
                }  
                %>
                <input type="text" id="<%=questionNum%>_numAnswers" name="<%=questionNum%>_numAnswers" value="<%=answerNum-1%>" hidden>
            </div>
                </div>
            <br>
                <%
                questionNum++;
            }
            %>
            <input type="text" name="numQuestions" value="<%=questionNum%>" hidden>
            <br><br><input class="btn btn-success" type="submit" value="Edit">
            </div>
            <br><br><br>
        </form>
            <%
        }
        %>
        <!--</div>-->
    </body>
</html>


<script type="text/javascript">
$(document).ready( function(){ 
	$(".cb-enable").click(function(){
		var parent = $(this).parents('.switch');
		$('.cb-disable',parent).removeClass('selected');
		$(this).addClass('selected');
		$('.checkbox',parent).attr('checked', true);
	});
	$(".cb-disable").click(function(){
		var parent = $(this).parents('.switch');
		$('.cb-enable',parent).removeClass('selected');
		$(this).addClass('selected');
		$('.checkbox',parent).attr('checked', false);
	});
});
</script>

