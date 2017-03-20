<%-- 
    Document   : quiz
    Created on : 23-Feb-2017, 16:18:16
    Author     : joshcorps
--%>
<%LoggedIn lg2 = (LoggedIn) session.getAttribute("LoggedIn");
   if (lg2 == null) 
   {    response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/")); }
%>
<%@page import="java.util.Iterator"%>
<%@page import="uk.ac.dundee.computing.team7.agilequiz.stores.AnswerBean"%>
<%@page import="uk.ac.dundee.computing.team7.agilequiz.stores.QuestionBean"%>
<%@page import="uk.ac.dundee.computing.team7.agilequiz.stores.QuizBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
     <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sit Quiz</title>
        <script src="${pageContext.request.contextPath}/js/bookmark.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <link rel="stylesheet" type= "text/css" href="${pageContext.request.contextPath}/style.css">
        <link rel="shortcut icon" href="https://s-media-cache-ak0.pinimg.com/originals/04/38/35/0438350175391a71727f8dac6e7be433.png">
    </head>
    
    <body>
	<%@ include file="header.jsp" %>
    <form method="POST"  action="${pageContext.request.contextPath}/QuizResults?quizid=<%=request.getAttribute("quizID")%>">	
    <div class="container"><!-- style="text-align:center">-->
        <%
            java.util.ArrayList<QuestionBean> questions = (java.util.ArrayList<QuestionBean>) request.getAttribute("questions");
            java.util.ArrayList<AnswerBean> answers = (java.util.ArrayList<AnswerBean>) request.getAttribute("answers");
            QuizBean quizBean = (QuizBean) request.getAttribute("quizBean");
            %>
            <div class="panel panel-default" name="panelINFO">
                <div class="panel-body">
                
        <div style="text-align: center">
            <h1><%=quizBean.getQuizName()%></h1>
            <p><%=quizBean.getQuizDescription()%></p>
            <h3>Date Added: <%=quizBean.getDateAdded()%></h3>
            <h3><%=quizBean.getModuleCode()%> - <%=quizBean.getModuleName()%></h3>
            <h3>Created By: <%=quizBean.getQuizCreator()%></h3><br>
            <%if ((boolean) request.getAttribute("bookmarked") && !(boolean) session.getAttribute("Staff"))
            {%>
                <button class="btn btn-primary" id="bookmark" type="button" onclick="removeBookmark(<%=request.getAttribute("quizID")%>, <%=session.getAttribute("StudentID")%>)">Remove Bookmark</button>
            <% } else if (!(boolean) request.getAttribute("bookmarked") && !(boolean) session.getAttribute("Staff")){ %>
                <button class="btn btn-primary" id="bookmark" type="button" onclick="addToBookmarks(<%=request.getAttribute("quizID")%>, <%=session.getAttribute("StudentID")%>)">Bookmark Quiz</button>
            <%}%>
        </div>    
        <br>
        <div id="bookmarkResult"><b></b></div>
        
            <%if (questions.isEmpty()) {
        %>
        <div class="alert alert-warning"><strong>No Questions found</strong></div>
         </div>
            </div>
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
        <div class="panel panel-default" name="panel<%=qb.getQuestionID()%>">
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
                        j++;%>
                        <div class="col-sm-3">
                            <div class="radio">
                                <label><input style="text-align:left" type="radio" name="optradio<%=ab.getQuestionID()%>" value="<%=ab.getAnswerID()%>"><%=ab.getAnswerText()%></label>
                            </div>
                        </div>
                    <%}
                }
            %>            
                </div>       
            </div>
        <%
            }%>
            
           <input type="text" value="<%=i%>" hidden > 
        <%}
        %>
    </div>
    <div style="text-align:center">
        <%if (!(boolean) session.getAttribute("Staff"))
        {%>
            <input type="submit" class="btn btn-success" value="Submit">
        <%}%>
    </div>
    <br>
    </form>
    </body>
</html>
