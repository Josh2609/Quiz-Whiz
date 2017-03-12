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
import uk.ac.dundee.computing.team7.agilequiz.models.Staff;

/**
 *
 * @author joshcorps
 */
@WebServlet(urlPatterns = {
    "/StaffSignup", 
})
public class StaffSignup extends HttpServlet 
{  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {  
        
        RequestDispatcher rd = request.getRequestDispatcher("/staffsignup.jsp");
        rd.forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String staffNumber = request.getParameter("staffNumber");
        String password = request.getParameter("password");
        String repeatPassword = request.getParameter("repeatPassword");
        
        if (!(password.equals(repeatPassword)))
        {
            RequestDispatcher rd = request.getRequestDispatcher("/staffsignup.jsp");
            request.setAttribute("Message", "Passwords do not match");
            request.setAttribute("staffNumber", staffNumber);
            rd.forward(request, response);
            return;
        }
        Staff staff = new Staff();
        staff.createStaff(staffNumber, password);
        
    }
}

