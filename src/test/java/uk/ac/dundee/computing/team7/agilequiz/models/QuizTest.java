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
//    @Test
//    public void testGetAnswers2() {
//        System.out.println("getAnswers2");
//        Quiz instance = new Quiz();
//        instance.getQuiz(1);
//        ArrayList<AnswerBean> result = instance.getAnswers2();
//        Assert.assertNotNull(result);
//    }

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
    public void testGetQuizDetails() {
        System.out.println("getQuizDetails");
        int quizID = 100;
        Quiz instance = new Quiz();
        QuizBean result = instance.getQuizDetails(quizID);
        assertEquals(quizID, result.getQuizID());
    }

    /**
     * Test of createQuiz method, of class Quiz.
     */
    @Test
    public void testCreateQuiz() {
        System.out.println("createQuiz");
        int numQuestions = 1;
        String quizName = "*d*e*l*e*t*e*";
        String quizDescription = "testQuiz";
        int available = 0;
        String moduleID = "-1";
        int creatorID = -1;
        String[] questionArray = new String[numQuestions+1];
        questionArray[numQuestions] = "test";
        ArrayList<ArrayList<String>> QandAlist2d = new ArrayList<>();
        ArrayList<String> QandAlist1d = new ArrayList<>();
        QandAlist1d.add("test");
        QandAlist2d.add(QandAlist1d);
        Quiz instance = new Quiz();
        boolean expResult = true;
        boolean result = instance.createQuiz(quizName, quizDescription, moduleID, available, 
                creatorID, numQuestions, questionArray, QandAlist2d);
        assertEquals(expResult, result);
    } 

    /**
     * Test of getAnswers2 method, of class Quiz.
     */
    @Test
    public void testGetAnswers2() {
//        System.out.println("getAnswers2");
//        Quiz instance = new Quiz();
//        ArrayList<AnswerBean> expResult = null;
//        ArrayList<AnswerBean> result = instance.getAnswers2();
//        assertEquals(expResult, result);
//   
    }

    /**
     * Test of getNumQuestions method, of class Quiz.
     */
    @Test
    public void testGetNumQuestions() {
        System.out.println("getNumQuestions");
        Quiz instance = new Quiz();
        int expResult = 0;
        int result = instance.getNumQuestions();
        assertEquals(expResult, result);
    }

    /**
     * Test of compareAnswer method, of class Quiz.
     */
    @Test
    public void testCompareAnswer() {
        System.out.println("compareAnswer");
        String answerID = "90";
        Quiz instance = new Quiz();
        boolean expResult = true;
        boolean result = instance.compareAnswer(answerID);
        assertEquals(expResult, result);
    }

    /**
     * Test of getQuizScore method, of class Quiz.
     */
    @Test
    public void testGetQuizScore() {
        System.out.println("getQuizScore");
        Quiz instance = new Quiz();
        int expResult = 0;
        ArrayList<String> answerList = new ArrayList<>();
        answerList.add("90");
        int result = instance.getQuizScore(answerList);
        assertEquals(expResult, result);
    }

    /**
     * Test of getQuizList method, of class Quiz.
     */
    @Test
    public void testGetAvailableQuizList() {
        System.out.println("getQuizList");
        ArrayList<String[]> testList = new ArrayList<>();
        Quiz instance = new Quiz();
        ArrayList<String[]> quizList = instance.getAvailableQuizList(1);
        if (quizList.size() > 0)
            assertTrue(true);
        else 
            fail();
        //Assert.assertEquals(testList, quizList);
    }

    /**
     * Test of addCompletedAnswers method, of class Quiz.
     */
    @Test
    public void testAddCompletedAnswers() {
        System.out.println("addCompletedAnswers");
        ArrayList<Integer> correctAnswerList = new ArrayList<>();
        correctAnswerList.add(1);
        ArrayList<Integer> incorrectAnswerList = new ArrayList<>();
        incorrectAnswerList.add(1);
        Quiz instance = new Quiz();
        boolean expResult = true;
        boolean result = instance.addCompletedAnswers(correctAnswerList, incorrectAnswerList, 1);
        assertEquals(expResult, result);
    }

    /**
     * Test of AddCompletedQuiz method, of class Quiz.
     */
    @Test
    public void testAddCompletedQuiz() {
        System.out.println("AddCompletedQuiz");
        int score = 0;
        int attempt = 0;
        int quizID = 0;
        int studentID = 0;
        Quiz instance = new Quiz();
        int expResult = 0;
        int result = instance.addCompletedQuiz(score, attempt, quizID, studentID);
        if (expResult < result)
            assertTrue(true);
        else
            fail();
    }



    /**
     * Test of getStudentAnswers method, of class Quiz.
     */
    @Test
    public void testGetStudentAnswers() {
        System.out.println("getStudentAnswers");
        int completedQuizID = 53;
        Quiz instance = new Quiz();
        ArrayList<Integer> testList = new ArrayList<>();
        testList = instance.getStudentAnswers(completedQuizID);
        if (testList.size() > 0)
            assertTrue(true);
        else 
            fail();
    }

    /**
     * Test of getQuizIDFromCompletedID method, of class Quiz.
     */
    @Test
    public void testGetQuizIDFromCompletedID() {
        System.out.println("getQuizIDFromCompletedID");
        int completedQuizID = 53;
        Quiz instance = new Quiz();
        int expResult = 100;
        int result = instance.getQuizIDFromCompletedID(completedQuizID);
        assertEquals(expResult, result);
    }

    /**
     * Test of getQuizListCreatedByStaff method, of class Quiz.
     */
    @Test
    public void testGetQuizListCreatedByStaff() {
        System.out.println("getQuizListCreatedByStaff");
        int currentPage = 1;
        int staffID = 1;
        Quiz instance = new Quiz();
        ArrayList<String[]> testList = new ArrayList<>();
        testList = instance.getQuizListCreatedByStaff(currentPage, staffID);
        if (testList.size() > 0)
            assertTrue(true);
        else 
            fail();
    }

    /**
     * Test of editQuestions method, of class Quiz.
     */
    @Test
    public void testEditQuestions() {
        System.out.println("EditQuestion");
        String newQuestion = "EditedQuestion";
        String newQuestionID = "1";
        ArrayList<String[]> testArray = new ArrayList<String[]>();
        testArray.add(new String[]{newQuestionID, newQuestion});
        Quiz instance = new Quiz();
        boolean expResult = true;
        boolean result = instance.editQuestions(testArray);
        if (expResult == result)
            assertTrue(true);
        else
            fail();
    }
    
    
    
    /**
     * Test of editAnswers method, of class Quiz.
     */
    @Test
    public void testEditAnswers() {
        System.out.println("EditAnswers");
        String newAnswer = "EditedAnswer";
        String newAnswerID = "1";
        ArrayList<String[]> testArray = new ArrayList<String[]>();
        testArray.add(new String[]{newAnswerID,newAnswer});
        Quiz instance = new Quiz();
        boolean expResult = true;
        boolean result = instance.editQuestions(testArray);
        if (expResult == result)
            assertTrue(true);
        else
            fail();
    }
    
    

}
