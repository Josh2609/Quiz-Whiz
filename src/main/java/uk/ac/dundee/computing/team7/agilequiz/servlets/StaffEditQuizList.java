/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.team7.agilequiz.servlets;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uk.ac.dundee.computing.team7.agilequiz.models.Quiz;
import uk.ac.dundee.computing.team7.agilequiz.models.Results;
import uk.ac.dundee.computing.team7.agilequiz.stores.LoggedIn;

/**
 *
 * @author joshcorps
 */
@WebServlet(urlPatterns = {
    "/StaffEditQuizList", 
})
public class StaffEditQuizList extends HttpServlet 
{  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException { 
        System.out.println("get");
        HttpSession session = request.getSession();
        LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
        if (lg == null) 
        {    
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/"));
        } else {
        
            int cPage = 1;
            if (request.getParameter("page") != null)
            {
                cPage = Integer.parseInt(request.getParameter("page"));
            }
            System.out.println(session.getAttribute("UserID"));
            int staffID = (int) session.getAttribute("UserID");
            Quiz quiz = new Quiz();
            ArrayList<String[]> quizList = quiz.getQuizListCreatedByStaff(cPage, staffID);

            RequestDispatcher rd = request.getRequestDispatcher("/staffeditquizlist.jsp");
            request.setAttribute("quizList", quizList);
            request.setAttribute("currentPage", cPage);
            rd.forward(request, response);  
        }
    }
}
