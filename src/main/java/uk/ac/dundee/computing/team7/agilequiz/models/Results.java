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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import uk.ac.dundee.computing.team7.agilequiz.lib.dbconnect;

/**
 *
 * @author joshcorps
 */
public class Results {
    
    public ArrayList<String[]> getStudentsQuizResults(int currentPage, int studentID)
    {
        ArrayList<String[]> studentQuizResults = new ArrayList<>();
        
        dbconnect dbCon = new dbconnect();
	Connection con = dbCon.mysqlConnect();
	PreparedStatement stmt;
        String sql = "SELECT *"
                + " FROM quizcompletedquizmodule WHERE User_ID=?"
                + " LIMIT ? OFFSET ?";
        
        int limit = currentPage*10;
        int offset = (currentPage*10)-9;
        if (currentPage == 1)
        {
            offset = 0;
        }
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, studentID);
            stmt.setInt(2, limit);
            stmt.setInt(3, offset);
	    ResultSet rs=stmt.executeQuery();       

            while(rs.next())
            {   //refactor to bean
                String[] tempArr = new String[8];
                tempArr[0] = Integer.toString(rs.getInt("Completed_Quiz_ID"));
                tempArr[1] = Integer.toString(rs.getInt("Quiz_ID"));
                tempArr[2] = rs.getString("Score");
                tempArr[3] = Integer.toString(rs.getInt("Attempt"));
                tempArr[4] = rs.getString("Quiz_Name");
                tempArr[5] = rs.getString("Module_Code");
                tempArr[6] = rs.getString("Module_Name");
                tempArr[7] = rs.getString("Quiz_Description");
                studentQuizResults.add(tempArr);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Quiz.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return studentQuizResults;
    }
    
}
