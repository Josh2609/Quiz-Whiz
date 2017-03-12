/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.team7.agilequiz.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uk.ac.dundee.computing.team7.agilequiz.models.Quiz;

/**
 *
 * @author joshcorps
 */
@WebServlet(urlPatterns = {
    "/ViewQuiz",
})
public class ViewQuizzes extends HttpServlet 
{  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {   
        
        Quiz quiz = new Quiz();
        ArrayList<String[]> quizList = quiz.getAvailableQuizList();

        RequestDispatcher rd = request.getRequestDispatcher("/viewquizzes.jsp");
        request.setAttribute("quizList", quizList);
        rd.forward(request, response);  
    }
}
