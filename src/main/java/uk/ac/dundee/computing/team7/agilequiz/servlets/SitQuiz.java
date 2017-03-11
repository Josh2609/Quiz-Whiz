/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.team7.agilequiz.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
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
        
        
        Iterator<QuestionBean> iterator;
        iterator = questions.iterator();
        while (iterator.hasNext()) 
        {
           QuestionBean qb = (QuestionBean) iterator.next();
           int questionID = qb.getQuestionID();
           //answers.add(quiz.getAnswers(questionID));
        }
        
        RequestDispatcher rd = request.getRequestDispatcher("/sitquiz.jsp");
        request.setAttribute("questions", questions);
        request.setAttribute("answers", answers);
        rd.forward(request, response);
       
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ArrayList<String> answerRadio = new ArrayList<>();
        Map<String, String[]> parameters = request.getParameterMap();
        
        for(String parameter : parameters.keySet()) 
        { 
            if(parameter.toLowerCase().startsWith("optradio")) 
            {
                String[] temp = parameters.get(parameter);
                answerRadio.add(temp[0]);
            }
        }
        Quiz quiz = new Quiz();

        int correctAnswers = 0;
        for (int i = 0; i < answerRadio.size(); i++)
        {
            if(quiz.compareAnswer(answerRadio.get(i)))
            {
                correctAnswers++;
            } else {
            
            }   
        }     
        RequestDispatcher rd = request.getRequestDispatcher("/quizresults.jsp");
        request.setAttribute("correctAnswers", correctAnswers);
        rd.forward(request, response);   
    }
}