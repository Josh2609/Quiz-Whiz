/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.team7.agilequiz.servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uk.ac.dundee.computing.team7.agilequiz.lib.Converters;

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
        
        String args[] = Converters.SplitRequestPath(request);
        System.out.println(args[2]);
        int correctAnswers = Integer.parseInt(args[2]);
        RequestDispatcher rd = request.getRequestDispatcher("/quizresults.jsp");
        request.setAttribute("correctAnswers", correctAnswers);
        rd.forward(request, response);
    }
}
