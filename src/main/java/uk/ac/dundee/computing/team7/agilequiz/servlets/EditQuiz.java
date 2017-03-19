/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.team7.agilequiz.servlets;

import com.mysql.cj.api.jdbc.Statement;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import uk.ac.dundee.computing.team7.agilequiz.lib.dbconnect;
import uk.ac.dundee.computing.team7.agilequiz.models.Quiz;
import uk.ac.dundee.computing.team7.agilequiz.stores.AnswerBean;
import uk.ac.dundee.computing.team7.agilequiz.stores.QuestionBean;
import uk.ac.dundee.computing.team7.agilequiz.stores.QuizBean;

/**
 *
 * @author Josh Corps
 */
@WebServlet(urlPatterns = {
    "/EditQuiz", 
    "/EditQuiz/*",
})
public class EditQuiz extends HttpServlet 
{  
    ArrayList<Integer> questionIDList = new ArrayList<>();
    ArrayList<Integer> answerIDList = new ArrayList<>();
    ArrayList<String> QandAlistAnsOri = new ArrayList<String>();
    ArrayList<String> QandAlistQueOri = new ArrayList<String>();
    String quizID = null;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        QandAlistAnsOri.clear();
        QandAlistQueOri.clear();
        String args[] = Converters.SplitRequestPath(request);
        quizID = args[2];
        request.setAttribute("quizID", args[2]);
        System.out.println(args[2]);
        
        Quiz quiz = new Quiz();
        
        ArrayList<QuestionBean> questions = quiz.getQuestions(Integer.parseInt(quizID));
        ArrayList<AnswerBean> answers = quiz.getAnswers2();
        
        for(int i=0; i < answers.size(); i++){
            String AnswerText = answers.get(i).getAnswerText();
            QandAlistAnsOri.add(AnswerText);
        }
        for(int i=0; i < questions.size(); i++){
            String QuestionText = questions.get(i).getQuestionText();
            QandAlistQueOri.add(QuestionText);
        }
        
        RequestDispatcher rd = request.getRequestDispatcher("/editquiz.jsp");
        request.setAttribute("questions", questions);
        request.setAttribute("answers", answers);
        request.setAttribute("quizID", quizID);
        rd.forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int numQuestions = Integer.parseInt(request.getParameter("numQuestions"));
        Quiz quiz = new Quiz();
        int Availability = 0;
        ArrayList<String[]> QandAlistAns = new ArrayList<String[]>();
        ArrayList<String[]> QandAlistQue = new ArrayList<String[]>();
        QandAlistAns.clear();
        QandAlistQue.clear();
        String checked = request.getParameter("radioAv");
        
        System.out.println("CHECKED1: " + checked);
        if(checked.equals("Av")){
            Availability = 1;
        }
        System.out.println("QUIZ ID: " + quizID);
        quiz.updateAvailability(quizID, Availability);
   
        System.out.println("SIZE: " + QandAlistQueOri.size());
        for (int i = 1; i <= QandAlistQueOri.size(); i++)
        {
            String questionText = request.getParameter("question-" + i);
            String questionID = request.getParameter("question_" + i);
            System.out.println("Current: " + i);
            if(!questionText.equals(QandAlistAnsOri.get(i-1))){
                QandAlistQue.add(new String[]{questionID, questionText});
            }
        }
            
        for (int j = 1; j < QandAlistAnsOri.size(); j++)
        {
            String answerID = request.getParameter(j + "-answer");
            String answerText = request.getParameter(j + "_answer");
            if(!answerText.equals(QandAlistAnsOri.get(j-1))){
                QandAlistAns.add(new String[]{answerID, answerText});
            }
        }
        quiz.editQuestions(QandAlistQue);
        quiz.editAnswers(QandAlistAns);
        
        RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
        rd.forward(request, response);
    }
}