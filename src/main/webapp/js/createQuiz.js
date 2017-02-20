/*
 * 
 * Document   : createQuiz.js
 * Created on : 20-Feb-2017, 18:54:27
 * Author     : Josh Corps
 */

var numQuestions = 0;
var numAnswers = new Array();
function addQuestion()
{
    numQuestions++;
    numAnswers[numQuestions] = 1;
    document.getElementById("numQuestions").value = numQuestions;
    var html;
    html = [
        '<div class="question' + numQuestions + '">',
        '<textarea placeholder="Question ' + numQuestions + '" name ="question_' + numQuestions + '"></textarea>',
        '<input type="button" value="Add Answer" onclick=addAnswer(' + numQuestions + ')>',
        '<input type="text" name="' + numQuestions + '_answer_' + 1 + '" placeholder="Enter Correct Answer">',
        '<input type="text" id="' + numQuestions + '_numAnswers" name="' + numQuestions + '_numAnswers" value="1" hidden></div><br><br>'
    ].join("\n");
    $(".questions").append(html);
}

function addAnswer(questionNum)
{
    var ansNum = numAnswers[questionNum] + 1;
    numAnswers[questionNum]++;
    document.getElementById(questionNum + "_numAnswers").value = ansNum;
    var html;
    html = [
        '<input type="text" name="' + questionNum + '_answer_' + ansNum + '" placeholder="Enter incorrect Answer">'
    ].join("\n");
    $(".question" + questionNum).append(html);
}
