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
import uk.ac.dundee.computing.team7.agilequiz.models.Student;

/**
 *
 * @author joshcorps
 */
@WebServlet(urlPatterns = {
    "/StudentSignup", 
})
public class StudentSignup extends HttpServlet 
{  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {  
        
        RequestDispatcher rd = request.getRequestDispatcher("/studentsignup.jsp");
        rd.forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // refactored some of login into Student.java
        String matric = request.getParameter("matric");
        String fname = request.getParameter("fname");
        String sname = request.getParameter("sname");
        String password = request.getParameter("password");
        String repeatPassword = request.getParameter("repeatPassword");
        
        if(!(matric.equals("") || fname.equals("") || sname.equals("") || password.equals("") || repeatPassword.equals(""))){        
            if (!(password.equals(repeatPassword)))
            {
                RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
                request.setAttribute("Message", "Passwords do not match");
                request.setAttribute("matric", matric);
                rd.forward(request, response);
                return;
            }
            Student user = new Student();
            user.createStudent(matric, fname, sname, password);
        
            RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
            rd.forward(request, response);
        }
        else{
            RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
            request.setAttribute("Message", "One or more fields were missing");
            rd.forward(request, response);
        }
    }
}
