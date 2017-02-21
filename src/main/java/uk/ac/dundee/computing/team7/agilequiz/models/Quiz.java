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
import java.util.LinkedList;
import uk.ac.dundee.computing.team7.agilequiz.lib.dbconnect;
import uk.ac.dundee.computing.team7.agilequiz.stores.AnswerBean;
import uk.ac.dundee.computing.team7.agilequiz.stores.QuestionBean;

/**
 *
 * @author joshcorps
 */
public class Quiz {
    
    public ArrayList<QuestionBean> getQuestions(int quizID)
    {
        ArrayList<QuestionBean> questionList = new ArrayList<>();
        
        dbconnect dbCon = new dbconnect();
	Connection con = dbCon.mysqlConnect();
	PreparedStatement stmt;
	try {
	    String sql = "SELECT Question_ID, Question_Text, Question_Explanation FROM question WHERE Quiz_ID=?";
	    stmt = con.prepareStatement(sql);
	    stmt.setString(1, Integer.toString(quizID));
	    ResultSet rs=stmt.executeQuery();  
	    if (rs.isBeforeFirst())
            {
                //results exist
                while(rs.next())
                {
                    QuestionBean qb = new QuestionBean();
                    qb.setQuestionID(Integer.parseInt(rs.getString("Question_ID")));
                    qb.setQuestionText(rs.getString("Question_Text"));
                    if (rs.getString("Question_Explanation") != null)
                    {
                        qb.setQuestionExplanation(rs.getString("Question_Explanation"));
                    }
                    questionList.add(qb);
                }
            } else {
                //no results for this quiz id
            }
            

	} catch (SQLException e)
	{
            e.printStackTrace();
	    	System.out.println("SQLException1");
	}
        
        return questionList;
    }
    
    public ArrayList<AnswerBean> getAnswers(int questionID)
    {
        ArrayList<AnswerBean> answerList = new ArrayList<>();
        
        dbconnect dbCon = new dbconnect();
	Connection con = dbCon.mysqlConnect();
	PreparedStatement stmt;
	try {
	    String sql = "SELECT Answer_ID, Answer_Text, Correct_Answer_Flag FROM answer WHERE Question_ID=?";
	    stmt = con.prepareStatement(sql);
	    stmt.setString(1, Integer.toString(questionID));
	    ResultSet rs=stmt.executeQuery();  
	    if (rs.isBeforeFirst())
            {
                //results exist
                while(rs.next())
                {
                    AnswerBean ab = new AnswerBean();
                    ab.setAnswerID(Integer.parseInt(rs.getString("Answer_ID")));
                    ab.setAnswerText(rs.getString("Answer_Text"));
                    if(rs.getInt("Correct_Answer_Flag") == 0)
                        ab.setCorrectAnswer(false);
                    else
                        ab.setCorrectAnswer(true);
                    answerList.add(ab);
                }
            } else {
                //no results for this question id, shouldn't happend really
            }
            

	} catch (SQLException e)
	{
	    	System.out.println("SQLException2");
	}
        
        return answerList;
    }
}
