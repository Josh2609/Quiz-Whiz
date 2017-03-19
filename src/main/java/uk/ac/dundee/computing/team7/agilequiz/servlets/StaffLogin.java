/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.team7.agilequiz.servlets;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import uk.ac.dundee.computing.team7.agilequiz.models.Staff;
import uk.ac.dundee.computing.team7.agilequiz.stores.LoggedIn;

/**
 *
 * @author Josh Corps
 */
@WebServlet(name = "StaffLogin", urlPatterns = {"/StaffLogin"})
public class StaffLogin extends HttpServlet {
    
    @Override
    public void init(ServletConfig config) throws ServletException 
    {
        // TODO Auto-generated method stub
    }
    
    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
       RequestDispatcher rd = request.getRequestDispatcher("stafflogin.jsp");
       rd.forward(request, response);
    }
    
     /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // refactored some of login into Student.java
        String staffid = request.getParameter("staffid");
        String password = request.getParameter("password");
	
	Staff user = new Staff();
	HttpSession session=request.getSession();

	
	if (user.checkDetails(staffid, password))
	{
	    LoggedIn lg = new LoggedIn();
	    lg.setLoggedIn();
	    lg.setStaffID(staffid);
            lg.setStaff();
            session.setAttribute("UserID", user.getStaffIDFromNumber(staffid));
	    session.setAttribute("StaffID", staffid);
            session.setAttribute("Staff", true);
	    session.setAttribute("LoggedIn", lg);
	    System.out.println("Session in servlet "+session);
            RequestDispatcher rd=request.getRequestDispatcher("/index.jsp");
	    rd.forward(request,response);           
	} else {
	    RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
	    request.setAttribute("Message", "Your Staff ID or Password entered is incorrect.");
	    rd.forward(request, response);
	}
    }
   
}
