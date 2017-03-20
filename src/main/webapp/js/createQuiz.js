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
        '<div id="section' + numQuestions + '">',
        '<div class="panel panel-primary" name"panel' + numQuestions + '" >',
                '<div class="panel-heading">Question #' + numQuestions + '</div>',
                '<div class="panel-body">',
                '<div class="panel1">',
                    '<div class="input-group">',
                        //'<div class="question' + numQuestions + '">',
                            '<span class="input-group-addon">Question</span>',
                            '<textarea class="form-control" placeholder="Question ' + numQuestions + '" name ="question_' + numQuestions + '" class="form-control"></textarea>',
                        //'</div>',
                    '</div><br>',
                    '<input type="button" class="btn btn-primary" value="Add Answer" onclick=addAnswer(' + numQuestions + ')><br>',
                    '<br><div class="input-group"><span class="input-group-addon">Correct Answer</span>',
                    '<input type="text" name="' + numQuestions + '_answer_' + 1 + '" class="form-control" placeholder="Enter Correct Answer"></div><br>',
                    '<input type="text" id="' + numQuestions + '_numAnswers" name="' + numQuestions + '_numAnswers" value="1" hidden>',
                    '<div class="input-group"><span class="input-group-addon">Explanation</span>',
                    '<textarea type="text" class="form-control" name="' + numQuestions + '_explanation" placeholder="Enter an explanation for the Answer"></textarea>',
                    '</div><br>',
                '</div>',
                '</div>',
            '</div>',
        '<div><br><br>',
        '</div>'
    ].join("\n");
    $("#questions").before(html);
    
}

function addAnswer(questionNum)
{
    var ansNum = numAnswers[questionNum] + 1;
    numAnswers[questionNum]++;
    document.getElementById(questionNum + "_numAnswers").value = ansNum;
    var html;
    html = [
        '<div class="input-group"><span class="input-group-addon">Incorrect Answer</span>',
        '<input type="text" name="' + questionNum + '_answer_' + ansNum + '" class="form-control" placeholder="Enter incorrect Answer"></div><br>'
    ].join("\n");
    $(".panel" + questionNum).append(html);
}
