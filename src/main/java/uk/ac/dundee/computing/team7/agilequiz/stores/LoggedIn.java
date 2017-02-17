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
    private String matric = null;
    
      
    public void LoggedIn(){
        
    }
    public void setMatric(String matric){
        this.matric = matric;
    }
    public String getMatric(){
        return matric;
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
