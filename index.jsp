<%-- 
    Document   : index
    Created on : 14-Feb-2017, 22:18:16
    Author     : Josh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" type= "text/css" href="style.css">        
		<%@ include file="header.jsp" %>
        <title>Qu¡zWh¡z</title>
        
        
    </head>
    
    <body>
        
        <div>
            <div  style="text-align:center">
        <h1> Welcome to Qu¡zWh¡z!</h1>
        <br>
        </div>
            
              <div style="text-align: center;">
              <div style="display: inline-block; text-align: left">
                  
              Quizwhiz is a website designed to help students pass their exams. 
              Here you’ll find quizzes created to test you on the content of all the modules you study at the University of Dundee. 
              Based around the topics you’ll study in class, your lecturer has created these quizzes to test you on what you’ve learned.<br>
        <br>
            You’re free to take each quiz as many times as you like but be careful, your lecturer can see all your scores and how many times you’ve completed a quiz. 
            To create an account, you’ll need your matriculation number. Good Luck! <br>
        <br>
        - Team 1<br>
        </div></div>
    </body>
    
    <input type="submit" value="Log In" onclick=window.location="/AgileQuiz/login.jsp";>
    <input type="submit" value="Sign Up"onclick=window.location="/AgileQuiz/signup.jsp";>
</html>
