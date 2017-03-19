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
import uk.ac.dundee.computing.team7.agilequiz.models.Bookmark;

/**
 *
 * @author joshcorps
 */
@WebServlet(urlPatterns = {
    "/AddBookmark",
})
public class AddToBookmarks extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        int quizID = Integer.parseInt(request.getParameter("quizID"));
        int studentID = Integer.parseInt(request.getParameter("studentID"));
        
        System.out.println("QuizID = " + quizID + " StudentID = " + studentID);
        Bookmark bookmark = new Bookmark();
        bookmark.addBookmark(quizID, studentID);
    }
}
