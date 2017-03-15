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
public class AnswerList 
{
    private ArrayList<Integer> correctAnswerList = new ArrayList<>();
    private ArrayList<Integer> incorrectAnswerList = new ArrayList<>();
    private dbconnect dbCon = new dbconnect();
    private Connection con = dbCon.mysqlConnect();
    private PreparedStatement stmt;
    private String sql = "SELECT Correct_Answer_Flag From Answer WHERE Answer_ID=?";
   
    public AnswerList(ArrayList<String> answerRadio)
    {
        for (int i = 0; i < answerRadio.size(); i++)
        {
            if(compareAnswer(answerRadio.get(i)))
            {
                correctAnswerList.add(Integer.parseInt(answerRadio.get(i)));
                System.out.println("cAL.length = " + correctAnswerList.size());
            } else {
                incorrectAnswerList.add(Integer.parseInt(answerRadio.get(i)));
                System.out.println("iAL.length = " + incorrectAnswerList.size());
            }   
        }  
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(AnswerList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Integer> getCorrectAnswerList()
    {     
        return correctAnswerList;
    }
    
    public ArrayList<Integer> getIncorrectAnswerList()
    {
        return incorrectAnswerList;
    }
    
    private boolean compareAnswer(String answerID)
    {
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(answerID));
	    ResultSet rs=stmt.executeQuery(); 
            
            while(rs.next())
            {
                return rs.getInt("Correct_Answer_Flag") == 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Quiz.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    
    private boolean compareAnswers(ArrayList<String> answerRadio)
    {
        dbconnect dbCon = new dbconnect();
	Connection con = dbCon.mysqlConnect();
	PreparedStatement stmt = null;
        String sql = "SELECT Correct_Answer_Flag From Answer WHERE Answer_ID=?";
       
            try {
                int counter = 1;
                for (int i = 1; i < answerRadio.size(); i++)
                {
                    sql += " OR Answer_ID=?";
                    stmt = con.prepareStatement(sql);
                    stmt.setInt(i, Integer.parseInt(answerRadio.get(i-1)));
                    counter++;
                }
                //stmt.setInt(counter+1, Integer.parseInt(answerRadio.get(i-1)));
                ResultSet rs=stmt.executeQuery(); 
                while(rs.next())
                {
                    return rs.getInt("Correct_Answer_Flag") == 1;
                }
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Quiz.class.getName()).log(Level.SEVERE, null, ex);
            }
        return true;
    }
}
