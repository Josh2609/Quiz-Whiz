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
import uk.ac.dundee.computing.team7.agilequiz.stores.ProfileBean;

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
        String fname = "fname";
	String sname = "sname";
        Staff instance = new Staff();
        boolean expResult = true;
        boolean result = instance.createStaff(staffNumber, fname, sname, password);
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

    /**
     * Test of getStaffProfile method, of class Staff.
     */
    @Test
    public void testGetStaffProfile() {
        System.out.println("getStaffProfile");
        String staffNumber = "130012977";
        Staff instance = new Staff();
        ProfileBean pb = new ProfileBean();
        pb = instance.getStaffProfile(staffNumber);
        assertTrue(pb.getFirstName().equalsIgnoreCase("Josh") && pb.getSurname().equalsIgnoreCase("Corps"));
    }

    /**
     * Test of changePassword method, of class Staff.
     */
    @Test
    public void testChangePassword() {
        System.out.println("changePassword");
        String staffNumber = "7357";
        String newPass = "pass";
        Staff instance = new Staff();
        boolean expResult = true;
        boolean result = instance.changePassword(staffNumber, newPass);
        assertEquals(expResult, result);
    }

    /**
     * Test of getStaffIDFromNumber method, of class Staff.
     */
    @Test
    public void testGetStaffIDFromNumber() {
        System.out.println("getStaffIDFromNumber");
        String sNumber = "7357";
        Staff instance = new Staff();
        int expResult = 99999;
        int result = instance.getStaffIDFromNumber(sNumber);
        assertEquals(expResult, result);
    }
    
}
