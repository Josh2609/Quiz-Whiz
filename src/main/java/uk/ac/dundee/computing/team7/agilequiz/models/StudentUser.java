/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.team7.agilequiz.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import uk.ac.dundee.computing.team7.agilequiz.lib.dbconnect;

/**
 *
 * @author Josh Corps
 */
public class StudentUser 
{
 
    public boolean checkDetails(String matric, String password)
    {
	dbconnect dbCon = new dbconnect();
	Connection con = dbCon.mysqlConnect();
	PreparedStatement stmt;
	try {
	    String sql = "SELECT Matric_Number, User_Password FROM student_user WHERE Matric_Number=? AND User_Password=?";
	    stmt = con.prepareStatement(sql);
	    stmt.setString(1, matric);
	    stmt.setString(2, password);
	    ResultSet rs=stmt.executeQuery();  
	    return rs.isBeforeFirst();

	} catch (SQLException e)
	{
	    	System.out.println("TODO");
	}
	return false;
    }
    
    // TODO
    private String hashPassword(String password)
    {
	String passwordHash = null;
	
	
	return passwordHash;
    }
    
    public boolean createStudentUser(String matric, String password)
    {
        int numAffectedRows = 0;
        dbconnect dbCon = new dbconnect();
	Connection con = dbCon.mysqlConnect();
	PreparedStatement stmt;
	try {
	    String sql = "INSERT INTO student_user (User_ID, Matric_Number, User_Password) VALUES (NULL, ?, ?)";
	    stmt = con.prepareStatement(sql);
	    stmt.setString(1, matric);
	    stmt.setString(2, password);
	    numAffectedRows = stmt.executeUpdate();
        } 
        catch (SQLException e)
	{
            e.printStackTrace();
        }
        return numAffectedRows > 0;
    }
}
