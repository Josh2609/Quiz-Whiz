/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.team7.agilequiz.models;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import uk.ac.dundee.computing.team7.agilequiz.stores.AnswerBean;
import uk.ac.dundee.computing.team7.agilequiz.stores.QuestionBean;
import uk.ac.dundee.computing.team7.agilequiz.stores.QuizBean;

/**
 *
 * @author joshcorps
 */
public class QuizTest {
    
    public QuizTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getQuestions method, of class Quiz.
     */
    @Test
    public void testGetQuestions() {
        System.out.println("getQuestions");
        int quizID = 1;
        Quiz instance = new Quiz();
        ArrayList<QuestionBean> result = instance.getQuestions(quizID);
        Assert.assertNotNull(result);
    }

    /**
     * Test of getAnswers2 method, of class Quiz.
     */
    @Test
    public void testGetAnswers2() {
        System.out.println("getAnswers2");
        Quiz instance = new Quiz();
        instance.getQuiz(1);
        ArrayList<AnswerBean> result = instance.getAnswers2();
        Assert.assertNotNull(result);
    }

    /**
     * Test of getAnswers method, of class Quiz.
     */
    @Test
    public void testGetAnswers() {
        System.out.println("getAnswers");
        int questionID = 1;
        Quiz instance = new Quiz();
        ArrayList<AnswerBean> result = instance.getAnswers(questionID);
        Assert.assertNotNull(result);
    }

    /**
     * Test of getQuiz method, of class Quiz.
     */
    @Test
    public void testGetQuiz() {
        System.out.println("getQuiz");
        int quizID = 1;
        Quiz instance = new Quiz();
        QuizBean result = instance.getQuiz(quizID);
        assertEquals(1, result.getQuizID());
    }

    /**
     * Test of createQuiz method, of class Quiz.
     */
    @Test
    public void testCreateQuiz() {
        System.out.println("createQuiz");
        int numQuestions = 1;
        String[] questionArray = new String[numQuestions+1];
        questionArray[numQuestions] = "test";
        ArrayList<ArrayList<String>> QandAlist2d = new ArrayList<ArrayList<String>>();
        ArrayList<String> QandAlist1d = new ArrayList<>();
        QandAlist1d.add("test");
        QandAlist2d.add(QandAlist1d);
        Quiz instance = new Quiz();
        boolean expResult = true;
        boolean result = instance.createQuiz(numQuestions, questionArray, QandAlist2d);
        assertEquals(expResult, result);
    } 
}
