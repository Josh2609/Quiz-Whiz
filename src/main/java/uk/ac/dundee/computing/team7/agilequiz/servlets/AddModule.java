/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.team7.agilequiz.servlets;

import com.mysql.cj.api.jdbc.Statement;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uk.ac.dundee.computing.team7.agilequiz.lib.dbconnect;

/**
 *
 * @author Josh Corps
 */
@WebServlet(name = "AddModule", urlPatterns = {"/AddModule"})
public class AddModule extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
       RequestDispatcher rd = request.getRequestDispatcher("addmodule.jsp");
       rd.forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String moduleCode = request.getParameter("moduleCode");
        String moduleName = request.getParameter("moduleName");
                
        dbconnect dbCon = new dbconnect();
	Connection con = dbCon.mysqlConnect();
	PreparedStatement stmt;
	try {
            String sql = "INSERT INTO module (Module_ID, Module_Code, Module_Name) VALUES (NULL, ?, ?)";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, moduleCode);
            stmt.setString(2, moduleName);
            stmt.execute();
        } 
        catch (SQLException e)
	{
            System.out.println("Yo, SQLException thrown");
        }
    }
}
