/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.team7.agilequiz.servlets;


import java.io.IOException;
import java.util.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uk.ac.dundee.computing.team7.agilequiz.models.Quiz;


@WebServlet(name = "CreateQuiz", urlPatterns = {"/CreateQuiz"})
public class CreateQuiz extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
       RequestDispatcher rd = request.getRequestDispatcher("createquiz.jsp");
       rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String quizName = request.getParameter("quizName");
        String quizDescription = request.getParameter("quizDescription");
        String moduleID = request.getParameter("moduleSelect");
        System.out.println(moduleID);
        String checked = request.getParameter("radioAv");
        
        int available = 1;
        
        System.out.println("CHECKED1: " + checked);
        if(!checked.equals("Un")){
            available = 0;
        }
        HttpSession session=request.getSession();
        int creatorID = (int) session.getAttribute("UserID");
        
        int numQuestions = Integer.parseInt(request.getParameter("numQuestions"));

        String[] questionArray = new String[numQuestions+1];
        String[] explanationArray = new String[numQuestions+1];
        ArrayList<ArrayList<String>> QandAlist2d = new ArrayList<>();
        ArrayList<String> correctAnswers = new ArrayList<>();
        
        for (int i = 1; i <= numQuestions; i++)
        {
            ArrayList<String> QandAlist1d = new ArrayList<>();
            String qName = i + "_numAnswers";
            int numAnswers = Integer.parseInt(request.getParameter(qName));
            questionArray[i] = request.getParameter("question_" + i);
            explanationArray[i] = request.getParameter(i+"_explanation");
            
            //QandAlist.add(new ArrayList<String>());
            for (int j = 1; j <= numAnswers; j++)
            {
                QandAlist1d.add(request.getParameter(i + "_answer_" + j));
            }
            QandAlist2d.add(QandAlist1d);
        }
        Quiz quiz = new Quiz();
        // refactored create code into quiz model class
        int quizID = quiz.createQuiz(quizName, quizDescription, moduleID, available, 
                creatorID, numQuestions, questionArray, QandAlist2d, explanationArray);
        
        response.sendRedirect("ViewQuiz/"+quizID);
//        RequestDispatcher rd = request.getRequestDispatcher("/SitQuiz/"+quizID);
//        rd.forward(request, response);
    }
}
