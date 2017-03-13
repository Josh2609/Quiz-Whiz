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
 * @author joshcorps
 */
public class StaffTest {
    
    public StaffTest() {
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
     * Test of checkDetails method, of class Staff.
     */
    @Test
    public void testCheckDetails() {
        System.out.println("checkDetails");
	String staffNumber = "130012977";
	String password = "password";
	Staff instance = new Staff();
	boolean expResult = true;
	boolean result = instance.checkDetails(staffNumber, password);
	assertEquals(expResult, result);
    }

    /**
     * Test of createStaff method, of class Staff.
     */
    @Test
    public void testCreateStaff() {
        System.out.println("createStaff");
        String staffNumber = "staffNumber";
        String password = "password";         
        Staff instance = new Staff();
        boolean expResult = true;
        boolean result = instance.createStaff(staffNumber, password);
        assertEquals(expResult, result);
    }

    /**
     * Test of removeStaff method, of class Staff.
     */
    @Test
    public void testRemoveStaff() {
        System.out.println("removeStaff");
        String matric = "staffNumber";
        Staff instance = new Staff();
        boolean expResult = true;
        boolean result = instance.removeStaff(matric);
        assertEquals(expResult, result);
    }
    
}
