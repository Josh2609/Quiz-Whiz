<%-- 
    Document   : createquiz
    Created on : 14-Feb-2017, 22:30:39
    Author     : Josh Corps
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create a Quiz</title>
        <script src="js/createQuiz.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    </head>
    <body>
        <div id=questions></div>        
        <form class="questions" method="POST"  action="CreateQuiz">
            <input type="text" name="quizName" placeholder="Quiz Name">
            <input type="text" name="quizModule" placeholder="Quiz Module">
            <br>
            <input type="text" id="numQuestions" name="numQuestions" value="0" hidden>
            <input type="submit" value="Create"> 
        </form>       
        <input type="button" onclick="addQuestion()" value="Add Question">
        <br>          
    </body>
</html>
