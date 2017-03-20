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
public class BookmarkTest {
    
    public BookmarkTest() {
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
     * Test of addBookmark method, of class Bookmark.
     */
    @Test
    public void testAddBookmark() {
        System.out.println("addBookmark");
        Bookmark instance = new Bookmark();
        boolean expResult = true;
        int quizID = 100;
        int studentID = 24;
        boolean result = instance.addBookmark(quizID, studentID);
        assertEquals(expResult, result);
    }

    /**
     * Test of checkBookmarkExists method, of class Bookmark.
     */
    @Test
    public void testCheckBookmarkExists() {
        System.out.println("checkBookmarkExists");
        Bookmark instance = new Bookmark();
        boolean expResult = true;
        int quizID = 100;
        int studentID = 24;
        instance.addBookmark(quizID, studentID);
        boolean result = instance.checkBookmarkExists(quizID, studentID);
        assertEquals(expResult, result);
    }

    /**
     * Test of removeBookmark method, of class Bookmark.
     */
    @Test
    public void testRemoveBookmark() {
        System.out.println("removeBookmark");
        Bookmark instance = new Bookmark();
        int quizID = 100;
        int studentID = 24;
        instance.addBookmark(quizID, studentID);
        boolean expResult = true;
        boolean result = instance.removeBookmark(quizID, studentID);
        assertEquals(expResult, result);
    }


    /**
     * Test of getBookmarkedQuizzes method, of class Bookmark.
     */
    @Test
    public void testGetBookmarkedQuizzes() {
        System.out.println("getBookmarkedQuizzes");
        int userID = 140011723;
        Bookmark instance = new Bookmark();
        ArrayList<String[]> bookmarkList = new ArrayList<String[]>();
        bookmarkList = instance.getBookmarkedQuizzes(userID);
        if(bookmarkList.size() > 0){
            assertTrue(true);
        }
        else{
            fail();
        }
    }
    
    
}
