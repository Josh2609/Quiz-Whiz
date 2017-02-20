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
        
        <form class="questions" method="POST"  action="CreateQuiz">
            <input type="text" id="numQuestions" name="numQuestions" value="0" hidden>
            <input type="submit" value="Create"> 
        </form>
        
  
        <input type="button" onclick="addQuestion()" value="Add Question">
            <script>
                var numQuestions = 0;
                var numAnswers = new Array();
                function addQuestion()
                {
                    numQuestions++;
                    numAnswers[numQuestions] = 1;
                    document.getElementById("numQuestions").value = numQuestions;
                    var html;
                    html = [
                    '<div class="question'+numQuestions+'">',
                    '<textarea placeholder="Question '+numQuestions+'" name ="question_'+numQuestions+'"></textarea>',
                    '<input type="button" value="Add Answer" onclick=addAnswer('+numQuestions+')>',
                    '<input type="text" name="'+numQuestions+'_answer_'+1+'" placeholder="Enter Correct Answer">',
                    '<input type="text" id="'+numQuestions+'_numAnswers" name="'+numQuestions+'_numAnswers" value="1" hidden></div><br><br>'
                    ].join("\n");
                    $(".questions").append(html);
                }
               
            </script>
        </select>
        <br>    
        
        <script>
        function addAnswer(questionNum)
        {
            var ansNum = numAnswers[questionNum]+1;
            numAnswers[questionNum]++;
            document.getElementById(questionNum+"_numAnswers").value = ansNum;
            var html;
            html = [
                '<input type="text" name="'+questionNum+'_answer_'+ansNum+'" placeholder="Enter incorrect Answer">'
            ].join("\n");
            $(".question"+questionNum).append(html);
        }       
        </script>
    </body>
</html>
