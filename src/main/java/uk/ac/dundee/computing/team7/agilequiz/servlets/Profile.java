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
import java.util.ArrayList;
import uk.ac.dundee.computing.team7.agilequiz.models.Student;
import uk.ac.dundee.computing.team7.agilequiz.models.Staff;
import uk.ac.dundee.computing.team7.agilequiz.models.Bookmark;
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
       Bookmark bk = new Bookmark();
       if(lg.getStaff() == false){ //If the user is a student
            Student stud = new Student();
            String matric = lg.getMatric();
            profile = stud.getStudentProfile(matric);
            ArrayList<String[]> bookmarkList = bk.getBookmarkedQuizzes(Integer.parseInt(matric));
            request.setAttribute("bookmarkList", bookmarkList);
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
            //If for some reason it is null
       }
       RequestDispatcher rd = request.getRequestDispatcher("profile.jsp");
       rd.forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        String oldpass = request.getParameter("currPass");
        String newpass = request.getParameter("newPass");
        String confpass = request.getParameter("confPass");
        
        LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
        if(!(oldpass.equals("") || newpass.equals("") || confpass.equals("")))
        {
            RequestDispatcher rd = request.getRequestDispatcher("/profile.jsp");
            request.setAttribute("Message", "One or more fields were empty.");
            rd.forward(request, response);
        }
        else{
            if (!(newpass.equals(confpass)))
            {
                RequestDispatcher rd = request.getRequestDispatcher("/profile.jsp");
                request.setAttribute("Message", "Passwords do not match");
                rd.forward(request, response);
                return;
            }
                if(lg.getStaff()){
                Staff user = new Staff();
                String staffid = lg.getStaffID();
                if (user.checkDetails(staffid, oldpass))
                {
                    user.changePassword(staffid, newpass);
                    request.setAttribute("Message", "Password has been changed");
                }
                else{
                    RequestDispatcher rd = request.getRequestDispatcher("/profile.jsp");
                    request.setAttribute("Message", "Your Current Password was Incorrect");
                    rd.forward(request, response);
                    return;
                }
            }
            else{
                Student user = new Student();
                String matric = lg.getMatric();
                if (user.checkDetails(matric, oldpass))
                {
                    user.changePassword(matric, newpass);
                    request.setAttribute("Message", "Password has been changed");
                }
                else{
                    RequestDispatcher rd = request.getRequestDispatcher("/profile.jsp");
                    request.setAttribute("Message", "Your Current Password was Incorrect");
                    rd.forward(request, response);
                    return;
                }
            }
            RequestDispatcher rd = request.getRequestDispatcher("/profile.jsp");
            rd.forward(request, response);
        }
    }
}
