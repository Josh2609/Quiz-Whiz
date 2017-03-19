/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.team7.agilequiz.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uk.ac.dundee.computing.team7.agilequiz.lib.Converters;
import uk.ac.dundee.computing.team7.agilequiz.models.Quiz;
import uk.ac.dundee.computing.team7.agilequiz.stores.AnswerBean;
import uk.ac.dundee.computing.team7.agilequiz.stores.QuestionBean;
import uk.ac.dundee.computing.team7.agilequiz.stores.QuizBean;

/**
 *
 * @author joshcorps
 */
@WebServlet(urlPatterns = {
    "/SitQuiz", 
    "/SitQuiz/*",
})
public class SitQuiz extends HttpServlet 
{  
    private String quizID;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {     
        
        String args[] = Converters.SplitRequestPath(request);
        quizID = args[2];
        request.setAttribute("quizID", args[2]);
        System.out.println(args[2]);
        
        Quiz quiz = new Quiz();
        
        ArrayList<QuestionBean> questions = quiz.getQuestions(Integer.parseInt(quizID));
        ArrayList<AnswerBean> answers = quiz.getAnswers2();
        QuizBean qb = new QuizBean();
        qb = quiz.getQuizDetails(Integer.parseInt(quizID));
        
        long seed = System.nanoTime();
        Collections.shuffle(answers, new Random(seed));
        seed = System.nanoTime();
        Collections.shuffle(questions, new Random(seed));
        
        RequestDispatcher rd = request.getRequestDispatcher("/sitquiz.jsp");
        request.setAttribute("quizBean", qb);
        request.setAttribute("questions", questions);
        request.setAttribute("answers", answers);
        rd.forward(request, response);
       
    }
    
   
}