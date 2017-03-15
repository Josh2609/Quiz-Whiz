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
import org.mindrot.jbcrypt.BCrypt;
import uk.ac.dundee.computing.team7.agilequiz.lib.dbconnect;
import uk.ac.dundee.computing.team7.agilequiz.stores.ProfileBean;

/**
 *
 * @author joshcorps
 */
public class Staff 
{
 
    public boolean checkDetails(String staffNumber, String password)
    {
	dbconnect dbCon = new dbconnect();
	Connection con = dbCon.mysqlConnect();
        boolean passwordMatch = false;
	PreparedStatement stmt;
	try {
	    String sql = "SELECT Staff_Number, User_Password FROM staff WHERE Staff_Number=?";
	    stmt = con.prepareStatement(sql);
	    stmt.setString(1, staffNumber);

	    ResultSet rs=stmt.executeQuery();  
           
            while(rs.next())
            {
                passwordMatch = BCrypt.checkpw(password, rs.getString("User_Password"));
            }
	    return passwordMatch;
            
	} catch (SQLException e)
	{
	    	e.printStackTrace();
	}
	return false;
    }
    
    // TODO
    private String hashPassword(String password)
    {
	String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
	return hashedPassword;
    }
    
    public boolean createStaff(String staffNumber, String fname, String sname, String password)
    {
        int numAffectedRows = 0;
        dbconnect dbCon = new dbconnect();
	Connection con = dbCon.mysqlConnect();
	PreparedStatement stmt;                
        String hashedPassword = hashPassword(password);
	try {
	    String sql = "INSERT INTO staff (User_ID, Staff_Number, User_Password, First_Name, Surname) VALUES (NULL, ?, ?, ?, ?)";
	    stmt = con.prepareStatement(sql);
	    stmt.setString(1, staffNumber);
	    stmt.setString(2, hashedPassword);
            stmt.setString(3, fname);
            stmt.setString(4, sname);
	    numAffectedRows = stmt.executeUpdate();
            con.close();
        } 
        catch (SQLException e)
	{
            e.printStackTrace();
        }
        return numAffectedRows > 0;
    }
    
    public boolean removeStaff(String staffNumber)
    {
        int numAffectedRows = 0;
        dbconnect dbCon = new dbconnect();
	Connection con = dbCon.mysqlConnect();
	PreparedStatement stmt;
	try {
            String sql = "DELETE FROM staff WHERE Staff_Number=?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, staffNumber);
            numAffectedRows = stmt.executeUpdate();
            con.close();
        } 
        catch (SQLException e)
	{
            e.printStackTrace();
        }
        return numAffectedRows > 0;
    }
    
    public ProfileBean getStaffProfile(String staffNumber){
        dbconnect dbCon = new dbconnect();
	Connection con = dbCon.mysqlConnect();
	PreparedStatement stmt;

        ProfileBean profile = new ProfileBean();

	try {
	    String sql = "SELECT * FROM staff WHERE Staff_Number=?";
	    stmt = con.prepareStatement(sql);
	    stmt.setString(1, staffNumber);
	    ResultSet rs=stmt.executeQuery();    
            
            while(rs.next()){
                profile.setFirstName(rs.getString("First_Name"));
                profile.setSurname(rs.getString("Surname"));
            }
            profile.setMatric(staffNumber);

	} catch (SQLException e)
	{
	    	e.printStackTrace();
	}
        return profile;
    }
    
    
    public boolean changePassword(String staffNumber, String newPass)
    {
        int numAffectedRows = 0;
        dbconnect dbCon = new dbconnect();
	Connection con = dbCon.mysqlConnect();
	PreparedStatement stmt;                
        String hashedPassword = hashPassword(newPass);
	try {
	    String sql = "UPDATE staff SET User_Password = ? WHERE Staff_Number = ?";
	    stmt = con.prepareStatement(sql);
	    stmt.setString(1, hashedPassword);
	    stmt.setString(2, staffNumber);
	    numAffectedRows = stmt.executeUpdate();
        } 
        catch (SQLException e)
	{
            e.printStackTrace();
        }
        return numAffectedRows > 0;
    }
}
