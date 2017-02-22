/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.team7.agilequiz.lib;

import java.sql.Connection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author joshcorps
 */
public class dbconnectTest {
    
    public dbconnectTest() {
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
     * Test of mysqlConnect method, of class dbconnect.
     */
    @Test
    public void testMysqlConnect() {
        System.out.println("mysqlConnect");
        dbconnect instance = new dbconnect();
        Connection result = instance.mysqlConnect();
        Assert.assertNotNull(result);
    }
    
}
