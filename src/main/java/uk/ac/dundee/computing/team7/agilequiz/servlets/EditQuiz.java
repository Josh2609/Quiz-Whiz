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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uk.ac.dundee.computing.team7.agilequiz.lib.Converters;
import uk.ac.dundee.computing.team7.agilequiz.lib.dbconnect;
import uk.ac.dundee.computing.team7.agilequiz.models.Quiz;
import uk.ac.dundee.computing.team7.agilequiz.stores.AnswerBean;
import uk.ac.dundee.computing.team7.agilequiz.stores.QuestionBean;
import uk.ac.dundee.computing.team7.agilequiz.stores.QuizBean;

/**
 *
 * @author Josh Corps
 */
@WebServlet(urlPatterns = {
    "/EditQuiz", 
    "/EditQuiz/*",
})
public class EditQuiz extends HttpServlet 
{  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {     
        
        String args[] = Converters.SplitRequestPath(request);
        String quizID = args[2];
        request.setAttribute("quizID", args[2]);
        System.out.println(args[2]);
        
        Quiz quiz = new Quiz();
        
        ArrayList<QuestionBean> questions = quiz.getQuestions(Integer.parseInt(quizID));
        ArrayList<AnswerBean> answers = quiz.getAnswers2();
        
        
        Iterator<QuestionBean> iterator;
        iterator = questions.iterator();
        while (iterator.hasNext()) 
        {
           QuestionBean qb = (QuestionBean) iterator.next();
           int questionID = qb.getQuestionID();
           //answers.add(quiz.getAnswers(questionID));
        }
        
        RequestDispatcher rd = request.getRequestDispatcher("/editquiz.jsp");
        request.setAttribute("questions", questions);
        request.setAttribute("answers", answers);
        request.setAttribute("quizID", quizID);
        rd.forward(request, response);
       
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Quiz quiz = new Quiz();
        int oldQuizID = Integer.parseInt(request.getParameter("oldQuizID"));
        int oldQuizVersion;
        
        QuizBean qb = quiz.getQuiz(oldQuizID);
        oldQuizVersion = qb.getQuizVersion();
        
        int numQuestions =  Integer.parseInt(request.getParameter("numQuestions"));

        String[] questionArray = new String[numQuestions+1];
        ArrayList<ArrayList<String>> QandAlist2d = new ArrayList<ArrayList<String>>();
        
        for (int i = 1; i <= numQuestions; i++)
        {
            ArrayList<String> QandAlist1d = new ArrayList<>();
            String qName = i + "_numAnswers";
            int numAnswers = Integer.parseInt(request.getParameter(qName));
            questionArray[i] = request.getParameter("question_" + i);
            //QandAlist.add(new ArrayList<String>());
            for (int j = 1; j <= numAnswers; j++)
            {
                QandAlist1d.add(request.getParameter(i + "_answer_" + j));
            }
            QandAlist2d.add(QandAlist1d);
        }
        
        dbconnect dbCon = new dbconnect();
	Connection con = dbCon.mysqlConnect();
	PreparedStatement stmt;
        
        int newQuizID = 0;
        
        try { //TODO
            String sql = "INSERT INTO quiz (Quiz_ID, Quiz_Name, Available_Flag, Quiz_Version, Module_ID,"
                    + "Quiz_Creator_ID) VALUES (NULL, ?, 1, ?, 7357, 1)";
            stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, "Test");
            stmt.setInt(2, oldQuizVersion+1);
            stmt.execute();
            ResultSet rs = stmt.getGeneratedKeys();
            while (rs.next()) {
                newQuizID = rs.getInt(1);
            }

        } catch (SQLException e)
	{
            System.out.println("Yo, SQLException thrown");
            e.printStackTrace();
        }
        
	try {
            String sql = "INSERT INTO question (Question_ID, Question_Text, Quiz_ID) VALUES (NULL, ?, ?)";
            stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            for (int x = 1; x <= numQuestions; x++)
            {
                stmt.setString(1, questionArray[x]);
                stmt.setInt(2, newQuizID);
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
	} catch (SQLException e)
	{
            System.out.println("Yo, SQLException thrown");
            e.printStackTrace();
        }
        
    }
    
}
