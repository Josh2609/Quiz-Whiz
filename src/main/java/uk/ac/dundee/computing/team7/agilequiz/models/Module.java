/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.team7.agilequiz.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import uk.ac.dundee.computing.team7.agilequiz.lib.dbconnect;

/**
 *
 * @author Josh Corps
 */
public class Module {
 
    public boolean addModule(String moduleCode, String moduleName)
    {
        boolean success;
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
            System.out.println("Yo, SQLException thrown");
        }
        if (numAffectedRows > 0)
            success = true;
        else 
            success = false;
        return success;
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
        if (numAffectedRows > 0)
            success = true;
        else 
            success = false;
        return success;
    }
}
