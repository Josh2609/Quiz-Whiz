/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.team7.agilequiz.models;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author joshcorps
 */
public class ResultsTest {
    
    public ResultsTest() {
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
     * Test of getStudentsQuizResults method, of class Results.
     */
    @Test
    public void testGetStudentsQuizResults() {
        System.out.println("getStudentsQuizResults");
        ArrayList<String[]> testList = new ArrayList<>();
        Results instance = new Results();
        ArrayList<String[]> quizList = instance.getStudentsQuizResults(1,24);
        if (quizList.size() > 0)
            assertTrue(true);
        else 
            fail();
    }

    /**
     * Test of getStudentsQuizResultsList method, of class Results.
     */
    @Test
    public void testGetStaffQuizResults() {
        System.out.println("getStaffQuizResults");
        ArrayList<String[]> testList = new ArrayList<>();
        int quizID = 100;
        String sortBy = "Quiz_ID";
        int currentPage = 1;
        Results instance = new Results();
        testList = instance.getStaffQuizResults(quizID, sortBy, currentPage);
        if (testList.size() > 0)
            assertTrue(true);
        else 
            fail();
    }
    
}
