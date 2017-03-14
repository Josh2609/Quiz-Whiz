/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.team7.agilequiz.stores;

/**
 *
 * @author Rory Raeper
 */
public class ProfileBean {
    private String firstName;
    private String surname;
    private String matricNo;
    
    //Constructor
    public ProfileBean(){
        firstName = null;
        surname = null;
        matricNo = null;
    }

    public String getFirstName(){ 
        return firstName;
    }
    
    public void setFirstName(String fName){
        this.firstName = fName;
    }
    
    public String getSurname(){
        return surname;
    }
    
    public void setSurname(String sName){
        this.surname = sName;
    }
    
    public String getMatric(){
        return matricNo;
    }
    
    public void setMatric(String matric){
        this.matricNo = matric;
    } 
}
