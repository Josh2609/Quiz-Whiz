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
            
            con.close();
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
            
            con.close();
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
            con.close();
        } catch (SQLException e)
	{
	    	System.out.println("SQLException3");
                e.printStackTrace();
	}
        
        return qb;
    }
    
    // refactored from CreateQuiz post
    public boolean createQuiz(String quizName, String quizDescription, String moduleID, 
            int available, int creatorID, int numQuestions, String[] questionArray, 
            ArrayList<ArrayList<String>> QandAlist2d)
    {
        boolean success;
        dbconnect dbCon = new dbconnect();
	Connection con = dbCon.mysqlConnect();
	PreparedStatement stmt;
	try {
            String sql = "INSERT INTO quiz (Quiz_ID, Quiz_Name, Available_Flag, Quiz_Version,"
                    + " Module_ID, Quiz_Creator_ID, Quiz_Description)"
                    + " VALUES (NULL, ?, ?, 1, ?, ?, ?)";
            stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, quizName);
            stmt.setInt(2, available);
            stmt.setString(3, moduleID);
            stmt.setInt(4, creatorID);
            stmt.setString(5, quizDescription);
            stmt.execute();
            
            int quizID = -1;
            ResultSet rs = stmt.getGeneratedKeys();
            while (rs.next()) {
                quizID = rs.getInt(1);
            }
                    
                    
            sql = "INSERT INTO question (Question_ID, Question_Text, Quiz_ID) VALUES (NULL, ?, ?)";
            stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            for (int x = 1; x <= numQuestions; x++)
            {
                stmt.setString(1, questionArray[x]);
                stmt.setInt(2, quizID);
                stmt.addBatch();
            }
            int [] questionID = new int[numQuestions+1];
            stmt.executeBatch();
            rs = stmt.getGeneratedKeys();
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
            con.close();
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
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(answerID));
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
    
    public ArrayList<String[]> getAvailableQuizList(int currentPage)
    {
        ArrayList<String[]> quizList = new ArrayList<>();
        
        dbconnect dbCon = new dbconnect();
	Connection con = dbCon.mysqlConnect();
	PreparedStatement stmt;
        String sql = "SELECT Quiz_ID, Quiz_Name, Quiz_Version, Module_ID,"
                + " Quiz_Creator_ID, Quiz_Description From quiz WHERE Available_Flag=1"
                + " LIMIT ? OFFSET ?";
        
        int limit = currentPage*10;
        int offset = (currentPage*10)-9;
        if (currentPage == 1)
        {
            offset = 0;
        }
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, limit);
            stmt.setInt(2, offset);
	    ResultSet rs=stmt.executeQuery();       

            while(rs.next())
            {
                String[] tempArr = new String[6];
                tempArr[0] = Integer.toString(rs.getInt("Quiz_ID"));
                tempArr[1] = rs.getString("Quiz_Name");
                tempArr[2] = Integer.toString(rs.getInt("Quiz_Version"));
                tempArr[3] = rs.getString("Module_ID");
                tempArr[4] = Integer.toString(rs.getInt("Quiz_Creator_ID"));
                tempArr[5] = rs.getString("Quiz_Description");
                quizList.add(tempArr);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Quiz.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return quizList;
    }
    
    
    public int addCompletedQuiz(int score, int attempt, int quizID, int studentID)
    {
        int numAffectedRows = 0;
        dbconnect dbCon = new dbconnect();
	Connection con = dbCon.mysqlConnect();
	PreparedStatement stmt;
        int completedQuizID = -1;

	try {
            String sql = "INSERT INTO completed_quiz (Completed_Quiz_ID, Score, Attempt,"
                    + " Quiz_ID, User_ID) VALUES (NULL, ?, ?, ?, ?)";
            stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, score);
            stmt.setInt(2, attempt);
            stmt.setInt(3, quizID);
            stmt.setInt(4, studentID);
            stmt.executeUpdate();
            
            completedQuizID = -1;
            ResultSet rs = stmt.getGeneratedKeys();
            while (rs.next()) {
                completedQuizID = rs.getInt(1);
            }
            con.close();
        } 
        catch (SQLException e)
	{
            e.printStackTrace();
        }
        return completedQuizID;
    }
    
    public boolean addCompletedAnswers(ArrayList<Integer> correctAnswerList, ArrayList<Integer> incorrectAnswerList, int completedQuizID)
    {
        dbconnect dbCon = new dbconnect();
	Connection con = dbCon.mysqlConnect();
	PreparedStatement stmt;
	try {       
            
            String sql = "INSERT INTO completed_answer (Completed_Answer_ID, Correct_Answer_Flag,"
                    + " Answer_ID, Completed_Quiz_ID) VALUES (NULL, ?, ?, ?)";
            stmt = con.prepareStatement(sql);
            for (int i = 0; i < correctAnswerList.size(); i++)
            {
                stmt.setInt(1, 1);
                stmt.setInt(2, correctAnswerList.get(i));
                stmt.setInt(3, completedQuizID);
                stmt.addBatch();
            }
            for (int i = 0; i < incorrectAnswerList.size(); i++)
            {
                stmt.setInt(1, 0);
                stmt.setInt(2, incorrectAnswerList.get(i));
                stmt.setInt(3, completedQuizID);
                stmt.addBatch();
            }
            stmt.executeBatch();
            con.close();
            return true;
        } 
        catch (SQLException e)
	{
            e.printStackTrace();
            return false;
        }
    }
    
    public ArrayList<Integer> getStudentAnswers(int completedQuizID)
    {
        ArrayList<Integer> studentAnswers = new ArrayList<>();
        
        dbconnect dbCon = new dbconnect();
	Connection con = dbCon.mysqlConnect();
	PreparedStatement stmt;
	String sql = "SELECT Answer_ID From completed_answer WHERE Completed_Quiz_ID=?";
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, completedQuizID);
	    ResultSet rs=stmt.executeQuery(); 
            while(rs.next())
            {
                int answerID;
                answerID = rs.getInt("Answer_ID");
                studentAnswers.add(answerID);
            }    
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Quiz.class.getName()).log(Level.SEVERE, null, ex);
        }  
        
        return studentAnswers;
    }
    
    public int getQuizIDFromCompletedID(int completedQuizID)
    {
        int quizID = 0;
        
        dbconnect dbCon = new dbconnect();
	Connection con = dbCon.mysqlConnect();
	PreparedStatement stmt;
	String sql = "SELECT Quiz_ID From completed_quiz WHERE Completed_Quiz_ID=?";
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, completedQuizID);
	    ResultSet rs=stmt.executeQuery(); 
            while(rs.next())
            {
                quizID = rs.getInt("Answer_ID");
            }    
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Quiz.class.getName()).log(Level.SEVERE, null, ex);
        }  
        
        return quizID;
    }
}
