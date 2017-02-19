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
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    </head>
    <body>
        <div id=questions></div>
        
        <form method="POST"  action="CreateQuiz">
            <textarea placeholder="Question 1" name ="question_1"></textarea>
            <input type="text" name="1_answer_1" placeholder="Answer 1">
            <input type="text" name="1_answer_2" placeholder="Answer 2">
            <input type="text" name="1_answer_3" placeholder="Answer 3">
            <input type="text" name="1_answer_4" placeholder="Answer 4">
            <input type="text" name="1_numAnswers" value="4" hidden>
            
            <textarea placeholder="Question 2" name ="question_2"></textarea>
            <input type="text" name="2_answer_1" placeholder="Answer 1">
            <input type="text" name="2_answer_2" placeholder="Answer 2">
            <input type="text" name="2_numAnswers" value="2" hidden>
            
            <input id="numQuestions" type="text" name="numQuestions" value="2" hidden>
            <input type="submit" value="Create"> 
        </form>
        
        <div class="questions" id="test"></div>
  
        <!--<select id="numQuestionsSelect" onchange="getNumQuestions()" >-->
            <script>
                var html = '<label for="createQuestionTB">Number of questions to create:</label><select id="numQuestionsSelect" onchange="getNumQuestions()" >';
                for (i=0; i<=40 ;i++)
                html += [
                    '<option value="'+ i +'">'+i+''
                ].join("\n");
                $("body").prepend(html);
            </script>
        </select>
        <br>
        
        <script>
        function clearBox(elementID)
        {
            document.getElementById(elementID).innerHTML = "";
        }
            
        function getNumQuestions()
        {
            clearBox("test");
            var numQs = document.getElementById("numQuestionsSelect").value;
            document.getElementById("numQuestions").value = numQs;
            setNumQuestions(numQs);
        }
        </script>
        
        
        <script>
        function addAnswer(questionNum)
        {
            var html;
            html = [
                '<input type="text" placeholder="Enter incorrect Answer">'
            ].join("\n");
            $(".question"+questionNum).append(html);
        }
        
        function setNumQuestions(numQs)
        {
            var html;
            for (i = 1; i <= numQs; i++)
            {
                html = [
                    '<div class="question'+i+'">',
                    '<textarea placeholder="Question '+i+'" name ="question_'+i+'"></textarea>',
                    '<input type="button" value="Add Answer" onclick=addAnswer('+i+')>',
                    '<input type="text" placeholder="Enter Correct Answer"></div><br><br>'
                ].join("\n");
                $(".questions").append(html);
            }
        }
        
        
        </script>
           
    </body>
</html>
