/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.team7.agilequiz.models;

import com.mysql.cj.api.jdbc.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import uk.ac.dundee.computing.team7.agilequiz.lib.dbconnect;
import uk.ac.dundee.computing.team7.agilequiz.stores.AnswerBean;
import uk.ac.dundee.computing.team7.agilequiz.stores.QuestionBean;
import uk.ac.dundee.computing.team7.agilequiz.stores.QuizBean;

/**
 *
 * @author joshcorps
 */
public class Quiz {
    
    private ArrayList<AnswerBean> answerListNew;

    public ArrayList<QuestionBean> getQuestions(int quizID)
    {
        ArrayList<QuestionBean> questionList = new ArrayList<>();
        ArrayList<AnswerBean> answerList = new ArrayList<>();

        
        dbconnect dbCon = new dbconnect();
	Connection con = dbCon.mysqlConnect();
	PreparedStatement stmt;
	try {
	    String sql = "SELECT Question_ID, Question_Text,Question_Explanation, Answer_ID, Answer_Text, Correct_Answer_Flag"
                    + " FROM questionanswer WHERE Quiz_ID=?";
	    stmt = con.prepareStatement(sql);
	    stmt.setString(1, Integer.toString(quizID));
	    ResultSet rs=stmt.executeQuery();  
	    if (rs.isBeforeFirst())
            {
                int qIDnew = -1;
                int qIDold = -1;
                //results exist
                //now randomly misses second question TODO
                boolean firstTime = true;
                while(rs.next())
                {
                    if (qIDnew == -1)
                    {
                        qIDold = Integer.parseInt(rs.getString("Question_ID"));
                    } else {
                        qIDold = qIDnew;
                    }
                    
                    if (!firstTime)
                    {
                        qIDnew = Integer.parseInt(rs.getString("Question_ID"));
                    }
                    QuestionBean qb = new QuestionBean();
                    AnswerBean ab = new AnswerBean();
                    
                    if (qIDnew == qIDold)
                    {
                        
                    } else {
                        qb.setQuestionID(Integer.parseInt(rs.getString("Question_ID")));
                        qb.setQuestionText(rs.getString("Question_Text"));

                       // if (rs.getString("Question_Explanation") != null)
                       // {
                            qb.setQuestionExplanation(rs.getString("Question_Explanation"));
                       //}
                        questionList.add(qb);
                    }
                    firstTime = false;
                    ab.setAnswerID(Integer.parseInt(rs.getString("Answer_ID")));
                    ab.setAnswerText(rs.getString("Answer_Text"));
                    ab.setQuestionID(rs.getInt("Question_ID"));
                    if(rs.getInt("Correct_Answer_Flag") == 0)
                    {
                        ab.setCorrectAnswer(false);
                    }
                    else
                        ab.setCorrectAnswer(true);
                    answerList.add(ab);
                }
            } else {
                //no results for this quiz id
            }
            

	} catch (SQLException e)
	{
            e.printStackTrace();
	    	System.out.println("SQLException1");
	}
        answerListNew = answerList;
        return questionList;
    }
    
    public ArrayList<AnswerBean> getAnswers2()
    {
        return answerListNew;
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
    
    public QuizBean getQuiz(int quizID)
    {
        QuizBean qb = new QuizBean();
        dbconnect dbCon = new dbconnect();
	Connection con = dbCon.mysqlConnect();
	PreparedStatement stmt;
	try { //TODO
	    String sql = "SELECT Quiz_ID, Quiz_Name, Available_Flag, Quiz_Version"
                    + " FROM quiz WHERE Quiz_ID=?";
	    stmt = con.prepareStatement(sql);
	    stmt.setString(1, Integer.toString(quizID));
	    ResultSet rs=stmt.executeQuery();  
	    if (rs.isBeforeFirst())
            {
                //results exist
                while(rs.next())
                {
                   qb.setQuizID(rs.getInt("Quiz_ID"));
                   qb.setQuizVersion(rs.getInt("Quiz_Version"));
                }
            } else {
                //no results for this question id, shouldn't happend really
            }
        } catch (SQLException e)
	{
	    	System.out.println("SQLException3");
                e.printStackTrace();
	}
        
        return qb;
    }
    
    // refactored from CreateQuiz post
    public boolean createQuiz(int numQuestions, String[] questionArray, ArrayList<ArrayList<String>> QandAlist2d)
    {
        boolean success;
        dbconnect dbCon = new dbconnect();
	Connection con = dbCon.mysqlConnect();
	PreparedStatement stmt;
	try {
            String sql = "INSERT INTO question (Question_ID, Question_Text, Quiz_ID) VALUES (NULL, ?, 1)";
            stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            for (int x = 1; x <= numQuestions; x++)
            {
                stmt.setString(1, questionArray[x]);
                stmt.addBatch();
            }
            int [] questionID = new int[numQuestions+1];
            stmt.executeBatch();
            ResultSet rs = stmt.getGeneratedKeys();
            int c = 0;
            while (rs.next()) {
                int id = rs.getInt(1);
                questionID[c] = id;
                c++;
            }
            
            sql = "INSERT INTO answer (Answer_ID, Answer_Text, Correct_Answer_Flag, Question_ID) VALUES (NULL, ?, 0, ?)";
            stmt = con.prepareStatement(sql);
            for (int y = 1; y <= numQuestions; y++)
            {
                ArrayList<String> testList;
                testList = QandAlist2d.get(y-1);
                System.out.println("testList ayyitem " + 0 + " = " + testList.get(0));
                for (int i = 0; i < testList.size(); i++)
                {
                    System.out.println("testList item " + i + " = " + testList.get(i));
                }
                for (int z = 1; z <= testList.size(); z++)
                {                  
                    stmt.setString(1, testList.get(z-1));
                    stmt.setString(2, Integer.toString(questionID[y-1]));
                    stmt.addBatch();
                }
            }
	    stmt.executeBatch(); 
            success = true;
	} catch (SQLException e)
	{
            success = false;
            System.out.println("Yo1, SQLException thrown");
            e.printStackTrace();
        }
        return success;
    }
    
    
    public int getNumQuestions()
    {
        return 0;
    }
    
    public int getQuizScore(ArrayList<String> answerList)
    {
        int correctAnswers = 0;
        for (int i = 0; i < answerList.size(); i++)
        {
            if(compareAnswer(answerList.get(i)))
            {
                correctAnswers++;
            }
        }
        return 0;
    }
    
    public boolean compareAnswer(String answerID)
    {
        dbconnect dbCon = new dbconnect();
	Connection con = dbCon.mysqlConnect();
	PreparedStatement stmt;
        String sql = "SELECT Correct_Answer_Flag From Answer WHERE Answer_ID=?";
        System.out.println("lakjFALSK " + answerID);
        
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
    
}
