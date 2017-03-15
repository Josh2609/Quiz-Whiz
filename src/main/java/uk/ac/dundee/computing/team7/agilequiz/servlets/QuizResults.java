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
    private String quizID = "1";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {     
        
        System.out.println("get");
        String args[] = Converters.SplitRequestPath(request);

        int completedQuizID = Integer.parseInt(args[2]);
        
        quizID = "1";
        
        Quiz quiz = new Quiz();
        
        ArrayList<QuestionBean> questions = quiz.getQuestions(Integer.parseInt(quizID));
        ArrayList<AnswerBean> answers = quiz.getAnswers2();
        ArrayList<Integer> studentAnswers = quiz.getStudentAnswers(completedQuizID); //TODO
        Collections.sort(studentAnswers);
        
        RequestDispatcher rd = request.getRequestDispatcher("/quizresults.jsp");
        request.setAttribute("completedQuizID", args[2]);
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
        int correctAnswers = 0;
        
        AnswerList answerList = new AnswerList(answerRadio);
        correctAnswerList = answerList.getCorrectAnswerList();
        incorrectAnswerList = answerList.getIncorrectAnswerList();
//        for (int i = 0; i < answerRadio.size(); i++)
//        {
//            if(quiz.compareAnswer(answerRadio.get(i)))
//            {
//                correctAnswerList.add(Integer.parseInt(answerRadio.get(i)));
//                System.out.println("cAL.length = " + correctAnswerList.size());
//                correctAnswers++;
//            } else {
//                incorrectAnswerList.add(Integer.parseInt(answerRadio.get(i)));
//                System.out.println("iAL.length = " + incorrectAnswerList.size());
//            }   
//        }
        HttpSession session = request.getSession();
        int studentID = (Integer) session.getAttribute("StudentID");
        int completedQuizID = quiz.addCompletedQuiz(correctAnswers, 1, quizID, studentID);
        quiz.addCompletedAnswers(correctAnswerList, incorrectAnswerList, completedQuizID);
        
        ArrayList<Integer> studentAnswers = new ArrayList<>();
        studentAnswers.addAll(correctAnswerList);
        studentAnswers.addAll(incorrectAnswerList);
        Collections.sort(studentAnswers);
        
        
        RequestDispatcher rd = request.getRequestDispatcher("/quizresults.jsp");
        request.setAttribute("questions", questions);
        request.setAttribute("answers", answers);
        request.setAttribute("studentAnswers", studentAnswers);
        request.setAttribute("completedQuizID", completedQuizID);
        rd.forward(request, response);   
    }
}
