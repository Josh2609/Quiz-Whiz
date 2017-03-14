/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.team7.agilequiz.stores;

/**
 *
 * @author Josh Corps
 */
public class LoggedIn {
    private boolean loggedIn = false;
    private boolean staff = false;
    private String matric = null;
    private String staffID = null;
    
      
    public void LoggedIn(){
        
    }
    public void setMatric(String matric){
        this.matric = matric;
    }
    public String getMatric(){
        return matric;
    }
    
    public void setStudent(){
        this.staff = false;
    }
    public void setStaff(){
        this.staff = true;
    }
    public boolean getStaff(){
        return staff;
    }
    
    public void setStaffID(String staffid){
        this.staffID = staffid;
    }
    public String getStaffID(){
        return staffID;
    }
    
    public void setLoggedIn(){
        loggedIn=true;
    }
    public void setLoggedOut(){
        loggedIn=false;
    }
    
    public void setLoginState(boolean loggedin){
        this.loggedIn=loggedin;
    }
    public boolean getloggedIn(){
        return loggedIn;
    }
}
