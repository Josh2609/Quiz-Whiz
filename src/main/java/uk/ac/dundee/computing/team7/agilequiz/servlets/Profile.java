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
import uk.ac.dundee.computing.team7.agilequiz.models.Student;
import uk.ac.dundee.computing.team7.agilequiz.models.Staff;
import uk.ac.dundee.computing.team7.agilequiz.stores.LoggedIn;
import uk.ac.dundee.computing.team7.agilequiz.stores.ProfileBean;

/**
 *
 * @author Rory Raeper
 */
@WebServlet(name = "Profile", urlPatterns = {"/Profile"})
public class Profile extends HttpServlet {
    
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
       HttpSession session = request.getSession();
       ProfileBean profile = new ProfileBean();
       LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
       if(lg.getStaff() == false){ //If the user is a student
            Student stud = new Student();
            String matric = lg.getMatric();
            profile = stud.getStudentProfile(matric);
       }
       else{ //If the user is staff
           Staff staff = new Staff();
           String staffid = lg.getStaffID();
           profile = staff.getStaffProfile(staffid);
       }
       
       if(profile != null){
            session.setAttribute("ProfileBean", profile);
            }
       else{
            System.out.println("SOMETHINGS FUCKY");
       }
       RequestDispatcher rd = request.getRequestDispatcher("profile.jsp");
       rd.forward(request, response);
    }   
}
