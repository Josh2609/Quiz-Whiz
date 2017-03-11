/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.team7.agilequiz.models;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Josh
 */
public class UserTest {
    
    public UserTest() {
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
     * Test of checkDetails method, of class User.
     */
    @Test
    public void testCheckDetails() {
	System.out.println("checkDetails");
	String matric = "130012977";
	String password = "password";
	User instance = new User();
	boolean expResult = true;
	boolean result = instance.checkDetails(matric, password);
	assertEquals(expResult, result);
    }

    /**
     * Test of CreateUser method, of class User.
     */
    @Test
    public void testCreateUser() {
        System.out.println("CreateUser");
        User instance = new User();
        boolean expResult = true;
        boolean result = instance.CreateUser();
        assertEquals(expResult, result);
    }
    
}