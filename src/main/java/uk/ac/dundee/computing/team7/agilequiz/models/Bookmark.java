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
 * @author joshcorps
 */
public class Bookmark {
    
    public boolean addBookmark(int quizID, int studentID)
    {
        int numAffectedRows = 0;
        dbconnect dbCon = new dbconnect();
	Connection con = dbCon.mysqlConnect();
	PreparedStatement stmt;
        
        try {
            String sql = "INSERT INTO bookmark_quiz (Bookmarked_Quiz_ID, Quiz_ID, User_ID) VALUES (NULL, ?, ?)";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, quizID);
            stmt.setInt(2, studentID);
            numAffectedRows = stmt.executeUpdate();
            con.close();    
        }
        catch (SQLException e)
	{
            e.printStackTrace();
        }
        
        return numAffectedRows > 0;
    }
    
    public boolean checkBookmarkExists(int quizID, int studentID)
    {
        boolean success = false;
        dbconnect dbCon = new dbconnect();
	Connection con = dbCon.mysqlConnect();
	PreparedStatement stmt;
        ResultSet rs = null;
        System.out.println("QuizID = " + quizID + " StudentID = " + studentID);
        try {
            String sql = "SELECT * FROM bookmark_quiz WHERE Quiz_ID=? AND User_ID=?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, quizID);
            stmt.setInt(2, studentID);
           
            rs = stmt.executeQuery();
            success = rs.isBeforeFirst();
            con.close();
            return success;
        }
        catch (SQLException e)
	{
            e.printStackTrace();
        }
        return success;
    }
    
    public boolean removeBookmark(int quizID, int studentID)
    {
        int numAffectedRows = 0;
        dbconnect dbCon = new dbconnect();
	Connection con = dbCon.mysqlConnect();
	PreparedStatement stmt;
        
        try {
            String sql = "DELETE FROM bookmark_quiz WHERE Quiz_ID=? AND User_ID=?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, quizID);
            stmt.setInt(2, studentID);
            numAffectedRows = stmt.executeUpdate();
            con.close();    
        }
        catch (SQLException e)
	{
            e.printStackTrace();
        }
        
        return numAffectedRows > 0;
    }
}
