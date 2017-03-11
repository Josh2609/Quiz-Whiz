<%-- 
    Document   : index
    Created on : 14-Feb-2017, 22:18:16
    Author     : Josh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>QuizWhiz</title>
        <script src="js/createQuiz.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <link rel="stylesheet" content= "text/css" href="${pageContext.request.contextPath}/style.css">
    </head>
    
    <body>
        
    <%@ include file="header.jsp" %>
        
         <div>
            <div  style="text-align:center">
            <div style="text-align:center">
                <h1> Welcome to <div style="font-family: 'Faster One', cursive;">QuizWhiz</div></h1>
         <br><br>
         </div>
            
            <div class="container">
               <div style="text-align: center;">
               <div style="display: inline-block; text-align: left">
                   
               <p>Quizwhiz is a website designed to help students pass their exams. 
               Here you’ll find quizzes created to test you on the content of all the modules you study at the University of Dundee. 
               Based around the topics you’ll study in class, your lecturer has created these quizzes to test you on what you’ve learned.<p><br>
         <br>
             <p>You’re free to take each quiz as many times as you like but be careful, your lecturer can see all your scores and how many times you’ve completed a quiz. 
                 To create an account, you’ll need your matriculation number. Good Luck!<p><br>
         <br>
         <br>
         </div></div>
        <div style="text-align:center">
		<input type="submit" class="btn btn-success" value="Log In" onclick=window.location="/AgileQuiz/login.jsp";>
		<input type="submit" class="btn btn-success" value="Sign Up"onclick=window.location="/AgileQuiz/signup.jsp";>
		</div>
	</div>
     </body>
 </html>
