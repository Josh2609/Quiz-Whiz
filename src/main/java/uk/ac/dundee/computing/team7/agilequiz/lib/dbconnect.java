/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.team7.agilequiz.lib;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Josh Corps
 * @date 
 */
public class dbconnect 
{
    public Connection mysqlConnect()
    {
	try {  
	    Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException ex) {
	    Logger.getLogger(dbconnect.class.getName()).log(Level.SEVERE, null, ex);
	}
	Connection con = null;
	
	try {
	    con = DriverManager.getConnection(
		    "jdbc:mysql://silva.computing.dundee.ac.uk:3306/16agileteam7db","16agileteam7","9865.at7.5689"); //db,user,pass

	    return con;
	} catch (SQLException ex) {
	    Logger.getLogger(dbconnect.class.getName()).log(Level.SEVERE, null, ex);
	}
	return con;
    }
}
