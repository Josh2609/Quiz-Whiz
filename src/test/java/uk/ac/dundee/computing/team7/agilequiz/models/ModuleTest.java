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
public class ModuleTest {
    
    public ModuleTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        Module module = new Module();
        module.addModule("7357","Test");
    }
    
    @After
    public void tearDown() {
        Module module = new Module();
        module.removeModule("7357");
    }

    /**
     * Test of addModule method, of class Module.
     */
    @Test
    public void testAddModule() {
        System.out.println("addModule");
        String moduleCode = "7357";
        String moduleName = "UnitTest";
        Module instance = new Module();
        boolean expResult = true;
        boolean result = instance.addModule(moduleCode, moduleName);
        assertEquals(expResult, result);
    }

    /**
     * Test of removeModule method, of class Module.
     */
    @Test
    public void testRemoveModule() {
        System.out.println("removeModule");
        String moduleCode = "7357";
        Module instance = new Module();
        boolean expResult = true;
        boolean result = instance.removeModule(moduleCode);
        assertEquals(expResult, result);
    }

    /**
     * Test of getModules method, of class Module.
     */
    @Test
    public void testGetModules() {
        System.out.println("getModules");
        Module module = new Module();
        String[] moduleTestInfo = new String[3];
        moduleTestInfo[0] = "1"; 
        moduleTestInfo[1] = "AC31007";
        moduleTestInfo[2] = "Agile Software Engineering";
        ArrayList<String> expResult = null;
        ArrayList<String[]> moduleList = new ArrayList<>();
        moduleList = module.getModules();
        
        String[] moduleInfo = moduleList.get(0);
        
        for (int i = 0; i < moduleInfo.length; i++)
        {
            if (moduleInfo[i].equalsIgnoreCase(moduleTestInfo[i]))
            {
            } else {
                fail();
            }
        }
        
        //assertEquals(expResult, moduleList);
    }
    
}
