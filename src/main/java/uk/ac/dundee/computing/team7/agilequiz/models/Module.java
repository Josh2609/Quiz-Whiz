/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.team7.agilequiz.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import uk.ac.dundee.computing.team7.agilequiz.lib.dbconnect;

/**
 *
 * @author Josh Corps
 */
public class Module {
    // code refactored from AddModule servlet
    public boolean addModule(String moduleCode, String moduleName)
    {
        int numAffectedRows = 0;
        dbconnect dbCon = new dbconnect();
	Connection con = dbCon.mysqlConnect();
	PreparedStatement stmt;
	try {
            String sql = "INSERT INTO module (Module_ID, Module_Code, Module_Name) VALUES (NULL, ?, ?)";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, moduleCode);
            stmt.setString(2, moduleName);
            numAffectedRows = stmt.executeUpdate();
        } 
        catch (SQLException e)
	{
            e.printStackTrace();
        }
        return numAffectedRows > 0;
    }
    
    public boolean removeModule(String moduleCode)
    {
        boolean success;
        int numAffectedRows = 0;
        dbconnect dbCon = new dbconnect();
	Connection con = dbCon.mysqlConnect();
	PreparedStatement stmt;
	try {
            String sql = "DELETE FROM module WHERE Module_Code=?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, moduleCode);
            numAffectedRows = stmt.executeUpdate();
        } 
        catch (SQLException e)
	{
            System.out.println("Yo, SQLException thrown");
        }
        success = numAffectedRows > 0;
        return success;
    }
    
    public ArrayList<String[]> getModules()
    {
        ArrayList<String[]> moduleList = new ArrayList<>();
        dbconnect dbCon = new dbconnect();
	Connection con = dbCon.mysqlConnect();
	PreparedStatement stmt;
	String sql = "SELECT * From Module";
        
        try {
            stmt = con.prepareStatement(sql);
	    ResultSet rs=stmt.executeQuery(); 
            while(rs.next())
            {
                String[] temp = new String[3];
                temp[0] = Integer.toString(rs.getInt("Module_ID"));
                temp[1] = rs.getString("Module_Code");
                temp[2] = rs.getString("Module_Name");
                moduleList.add(temp);
            }    
        } catch (SQLException ex) {
            Logger.getLogger(Quiz.class.getName()).log(Level.SEVERE, null, ex);
        }  
        return moduleList;
    }
}
