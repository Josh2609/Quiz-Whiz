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
    
    public QuizBean getQuizDetails(int quizID)
    {
        QuizBean qb = new QuizBean();
        dbconnect dbCon = new dbconnect();
	Connection con = dbCon.mysqlConnect();
	PreparedStatement stmt;
	try { //TODO
	    String sql = "SELECT *"
                    + " FROM quizmodulecreatorview WHERE Quiz_ID=?";
	    stmt = con.prepareStatement(sql);
	    stmt.setString(1, Integer.toString(quizID));
	    ResultSet rs=stmt.executeQuery();  
	    if (rs.isBeforeFirst())
            {
                //results exist
                while(rs.next())
                {
                   qb.setQuizID(rs.getInt("Quiz_ID"));
                   qb.setQuizName(rs.getString("Quiz_Name"));
                   qb.setQuizDescription(rs.getString("Quiz_Description"));
                   qb.setQuizVersion(rs.getInt("Quiz_Version"));
                   qb.setQuizCreator(rs.getString("First_Name") + " " + rs.getString("Surname"));
                   qb.setModuleCode(rs.getString("Module_Code"));
                   qb.setModuleName(rs.getString("Module_Name"));
                   qb.setDateAdded(rs.getDate("Date_Added"));
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
    public int createQuiz(String quizName, String quizDescription, String moduleID, 
            int available, int creatorID, int numQuestions, String[] questionArray, 
            ArrayList<ArrayList<String>> QandAlist2d, String[] explanationArray)
    {
        int quizID = -1;
        dbconnect dbCon = new dbconnect();
	Connection con = dbCon.mysqlConnect();
	PreparedStatement stmt;
	try {
            String sql = "INSERT INTO quiz (Quiz_ID, Quiz_Name, Available_Flag, Quiz_Version,"
                    + " Module_ID, Quiz_Creator_ID, Quiz_Description, Date_Added)"
                    + " VALUES (NULL, ?, ?, 1, ?, ?, ?, CURDATE())";
            stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, quizName);
            stmt.setInt(2, available);
            stmt.setString(3, moduleID);
            stmt.setInt(4, creatorID);
            stmt.setString(5, quizDescription);
            stmt.execute();
    
            ResultSet rs = stmt.getGeneratedKeys();
            while (rs.next()) {
                quizID = rs.getInt(1);
            }
                    
                    
            sql = "INSERT INTO question (Question_ID, Question_Text, Quiz_ID, Question_Explanation) VALUES (NULL, ?, ?, ?)";
            stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            for (int x = 1; x <= numQuestions; x++)
            {
                stmt.setString(1, questionArray[x]);
                stmt.setInt(2, quizID);
                stmt.setString(3, explanationArray[x]);
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
            
            sql = "INSERT INTO answer (Answer_ID, Answer_Text, Correct_Answer_Flag, Question_ID) VALUES (NULL, ?, ?, ?)";
            stmt = con.prepareStatement(sql);
            for (int y = 1; y <= numQuestions; y++)
            {
                ArrayList<String> testList;
                testList = QandAlist2d.get(y-1);
                System.out.println("testList ayyitem " + 0 + " = " + testList.get(0));
                for (int z = 1; z <= testList.size(); z++)
                {                  
                    stmt.setString(1, testList.get(z-1));
                    if (z == 1)
                    {
                        stmt.setInt(2, 1);
                    } else {
                        stmt.setInt(2, 0);
                    }
                    stmt.setString(3, Integer.toString(questionID[y-1]));
                    stmt.addBatch();
                }
            }
	    stmt.executeBatch(); 
            con.close();
	} catch (SQLException e)
	{
            System.out.println("Yo1, SQLException thrown");
            e.printStackTrace();
        }
        return quizID;
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
        String sql = "SELECT Quiz_ID, Quiz_Name, Module_Name,"
                + " First_Name, Surname, Quiz_Description From quizmodulecreatorview WHERE Available_Flag=1"
                + " LIMIT ? OFFSET ?";
        
        int limit = currentPage*25;
        int offset = (currentPage*25)-24;
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
                String[] tempArr = new String[5];
                tempArr[0] = rs.getString("Quiz_ID");
                tempArr[1] = rs.getString("Quiz_Name");
                tempArr[2] = rs.getString("Module_Name");
                tempArr[3] = (rs.getString("First_Name") +  " " + rs.getString("Surname"));
                tempArr[4] = rs.getString("Quiz_Description");
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
        dbconnect dbCon = new dbconnect();
	Connection con = dbCon.mysqlConnect();
	PreparedStatement stmt;
        int completedQuizID = -1;

	try {
            String sql = "INSERT INTO completed_quiz (Completed_Quiz_ID, Score, Attempt,"
                    + " Quiz_ID, User_ID, Time_Submitted) VALUES (NULL, ?, ?, ?, ?, NOW())";
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
                quizID = rs.getInt("Quiz_ID");
            }    
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Quiz.class.getName()).log(Level.SEVERE, null, ex);
        }      
        return quizID;
    }

    
    public ArrayList<String[]> getQuizListCreatedByStaff(int currentPage, int staffID)
    {
        ArrayList<String[]> quizList = new ArrayList<>();
        System.out.println(staffID);
        dbconnect dbCon = new dbconnect();
	Connection con = dbCon.mysqlConnect();
	PreparedStatement stmt;
        String sql = "SELECT Quiz_ID, Quiz_Name, Module_Name, Module_Code,"
                + " Quiz_Creator_ID, Quiz_Description, Available_Flag,"
                + " Date_Added FROM quizmodulecreatorview WHERE Quiz_Creator_ID=?"
                + " LIMIT ? OFFSET ?";
        
        int limit = currentPage*25;
        int offset = (currentPage*25)-24;
        if (currentPage == 1)
        {
            offset = 0;
        }
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, staffID);
            stmt.setInt(2, limit);
            stmt.setInt(3, offset);
	    ResultSet rs=stmt.executeQuery();       

            while(rs.next())
            {
                String[] tempArr = new String[8];
                tempArr[0] = Integer.toString(rs.getInt("Quiz_ID"));
                tempArr[1] = rs.getString("Quiz_Name");
                tempArr[2] = rs.getString("Module_Code");
                tempArr[3] = rs.getString("Module_Name");
                tempArr[4] = Integer.toString(rs.getInt("Quiz_Creator_ID"));
                tempArr[5] = rs.getString("Quiz_Description");
                tempArr[6] = rs.getString("Available_Flag");
                tempArr[7] = rs.getString("Date_Added");
                quizList.add(tempArr);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Quiz.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return quizList;
    }




    public boolean editAnswers(ArrayList<String[]> AnsList){
        boolean success = false;
        int numAffectedRows = 0;
        dbconnect dbCon = new dbconnect();
	Connection con = dbCon.mysqlConnect();
	PreparedStatement stmt;
	try {
	    String sql = "UPDATE answer SET Answer_Text = ? WHERE Answer_ID = ?";
	    stmt = con.prepareStatement(sql);
            for (int z = 0; z < AnsList.size(); z++)
            {
                stmt.setString(2, AnsList.get(z)[0]);
                stmt.setString(1, AnsList.get(z)[1]);
                stmt.addBatch();
            }
            stmt.executeBatch();
            success = true;
        } 
        catch (SQLException e)
	{
            e.printStackTrace();
        }
        return success;
    }
    
    
    public boolean editQuestions(ArrayList<String[]> QueList){
        boolean success = false;
        dbconnect dbCon = new dbconnect();
	Connection con = dbCon.mysqlConnect();
	PreparedStatement stmt;
	try {
	    String sql = "UPDATE question SET Question_Text = ? WHERE Question_ID = ?";
	    stmt = con.prepareStatement(sql);
            for (int z = 0; z < QueList.size(); z++)
            {
                stmt.setString(1, QueList.get(z)[0]);
                stmt.setString(2, QueList.get(z)[1]);
                stmt.addBatch();
            }
            stmt.executeBatch();
            success = true;
        } 
        catch (SQLException e)
	{
            e.printStackTrace();
        }
        return success;
    }
    
    //ThIS IS HOW IT SHOULD BE
    public boolean deleteQuiz(String quizID){
        boolean success = false;
        dbconnect dbCon = new dbconnect();
	Connection con = dbCon.mysqlConnect();
	PreparedStatement stmt;
	try {
	    String sql = "DELETE FROM quiz WHERE Quiz_ID = ?";
	    stmt = con.prepareStatement(sql);
            stmt.setString(1, quizID);
            stmt.executeUpdate();
            success = true;
        } 
        catch (SQLException e)
	{
            e.printStackTrace();
        }
        return success;
    }
    
    //ThIS IS HOW IT SHOULD BE
    public boolean deleteQuiz2(String quizName){
        boolean success = false;
        dbconnect dbCon = new dbconnect();
	Connection con = dbCon.mysqlConnect();
	PreparedStatement stmt;
	try {
	    String sql = "DELETE FROM quiz WHERE Quiz_Name = ?";
	    stmt = con.prepareStatement(sql);
            stmt.setString(1, quizName);
            stmt.executeUpdate();
            success = true;
        } 
        catch (SQLException e)
	{
            e.printStackTrace();
        }
        return success;
    }

    //Method to change the quizzes availability 
    public boolean updateAvailability(String quizID, int quizAv){
        boolean success = false;
        dbconnect dbCon = new dbconnect();
        Connection con = dbCon.mysqlConnect();
        PreparedStatement stmt;
        try{
            String sql = "UPDATE quiz SET Available_Flag = ? WHERE Quiz_ID = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, quizAv);
            stmt.setString(2, quizID);
            stmt.executeUpdate();
            success = true;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return success;
    }
}
