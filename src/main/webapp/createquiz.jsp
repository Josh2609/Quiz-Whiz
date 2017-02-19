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
    </head>
    <body>
        <h1>Hello World!</h1>
        <label for='createQuestionTB'>Number of questions to create:</label>
        <select id='createQuestionTB'>
            <option>0</option>
            <option>1</option>
            <option>2</option>
            <option>3</option>
            <option>4</option>
        </select>
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
            
            <input type="text" name="numQuestions" value="2" hidden>
            <input type="submit" value="Create"> 
        </form>
        
        <script>   
            //idk lol
        function createAnswerTB(j)
        {
            
        }
        
        function createAnswerDrop(k)
        {
//            var newList = document.createElement("numAnswerDrop");
//            var newListData = new Option("my label", "my value");
//            newList.appendChild(newListData);
//            questions.appendChild(newList);
            var newPanel = document.createElement("p");
            var newList = document.createElement("select");
            var newListData = new Option("my label", "my value");
            newList.appendChild(newListData);
            newPanel.appendChild(newList);
            document.getElementById("questions").appendChild(newPanel);
        }
        
        function createQuestionTB(i)
        {
            var k, target = document.getElementById('questions');
            var y = document.createElement("INPUT");
            y.setAttribute("type", "text");
            y.setAttribute("Placeholder", "Question " + i);
            y.setAttribute("Name", "question" + i);
            document.getElementById("questions").appendChild(y);
            createAnswerDrop(k);
            var br = document.createElement("br");
            questions.appendChild(br);
            var br = document.createElement("br");
            questions.appendChild(br);
        }
        
        function create(param) {
            'use strict';

            var i, target = document.getElementById('questions');
            target.innerHTML = '';

            for (i = 0; i < param; i += 1) {
                createQuestionTB(i);
                //target.innerHTML += '<input type="text" name="Fname">';
            }
        }

        document.getElementById('createQuestionTB').addEventListener('change', function () {
            create(this.value);
        }, false);
            
        </script>
    </body>
</html>
