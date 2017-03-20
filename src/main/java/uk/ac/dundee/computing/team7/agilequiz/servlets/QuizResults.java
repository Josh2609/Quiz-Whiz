/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.team7.agilequiz.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uk.ac.dundee.computing.team7.agilequiz.lib.Converters;
import uk.ac.dundee.computing.team7.agilequiz.models.AnswerList;
import uk.ac.dundee.computing.team7.agilequiz.models.Quiz;
import uk.ac.dundee.computing.team7.agilequiz.stores.AnswerBean;
import uk.ac.dundee.computing.team7.agilequiz.stores.QuestionBean;
import uk.ac.dundee.computing.team7.agilequiz.stores.QuizBean;

/**
 *
 * @author joshcorps
 */
@WebServlet(urlPatterns = {
    "/QuizResults", 
    "/QuizResults/*",
})
public class QuizResults extends HttpServlet 
{  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {     
        
        System.out.println("get");

        int completedQuizID = Integer.parseInt(request.getParameter("cquizid"));//Integer.parseInt(args[2]);
        int quizID = Integer.parseInt(request.getParameter("quizid"));
        int correctAnswers = Integer.parseInt(request.getParameter("score"));
        
         System.out.println(completedQuizID);
         System.out.println(quizID);
         System.out.println(correctAnswers);
        
        Quiz quiz = new Quiz();
        
        ArrayList<QuestionBean> questions = quiz.getQuestions(quizID);
        ArrayList<AnswerBean> answers = quiz.getAnswers2();
        ArrayList<Integer> studentAnswers = quiz.getStudentAnswers(completedQuizID);
        Collections.sort(studentAnswers);
        QuizBean qb = new QuizBean();
        qb = quiz.getQuizDetails(quizID);
        int numQuestions = questions.size();
        
        RequestDispatcher rd = request.getRequestDispatcher("/quizresults.jsp");
        request.setAttribute("numCorrect", correctAnswers);
        request.setAttribute("numQuestions", numQuestions);
        request.setAttribute("completedQuizID", completedQuizID);
        request.setAttribute("quizBean", qb);
        request.setAttribute("questions", questions);
        request.setAttribute("answers", answers);
        request.setAttribute("studentAnswers", studentAnswers);
        rd.forward(request, response);
    }
 
     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String args[] = Converters.SplitRequestPath(request);
        int quizID = Integer.parseInt(request.getParameter("quizid"));
        
        Quiz quiz = new Quiz();
        
        ArrayList<QuestionBean> questions = quiz.getQuestions(quizID);
        ArrayList<AnswerBean> answers = quiz.getAnswers2();
  
        
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
        
        ArrayList<Integer> correctAnswerList = new ArrayList<>();
        ArrayList<Integer> incorrectAnswerList = new ArrayList<>();

        // needs optimised
        
        AnswerList answerList = new AnswerList(answerRadio);
        correctAnswerList = answerList.getCorrectAnswerList();
        incorrectAnswerList = answerList.getIncorrectAnswerList();

        HttpSession session = request.getSession();
        int studentID = (Integer) session.getAttribute("StudentID");
        int numCorrect = answerList.getNumCorrect();
        int completedQuizID = quiz.addCompletedQuiz(numCorrect, 1, quizID, studentID);
        quiz.addCompletedAnswers(correctAnswerList, incorrectAnswerList, completedQuizID);
        
        ArrayList<Integer> studentAnswers = new ArrayList<>();
        studentAnswers.addAll(correctAnswerList);
        studentAnswers.addAll(incorrectAnswerList);
        Collections.sort(studentAnswers);
        
        QuizBean qb = new QuizBean();
        qb = quiz.getQuizDetails(quizID);
        int numQuestions = questions.size();
        
        RequestDispatcher rd = request.getRequestDispatcher("/quizresults.jsp");
        request.setAttribute("numCorrect", numCorrect);
        request.setAttribute("numQuestions", numQuestions);
        request.setAttribute("quizBean", qb);
        request.setAttribute("questions", questions);
        request.setAttribute("answers", answers);
        request.setAttribute("studentAnswers", studentAnswers);
        request.setAttribute("completedQuizID", completedQuizID);
        rd.forward(request, response);   
    }
}
