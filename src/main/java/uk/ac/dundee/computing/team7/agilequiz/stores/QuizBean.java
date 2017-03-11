/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.team7.agilequiz.stores;

/**
 *
 * @author joshcorps
 */
public class QuizBean {
    private int quizID;
    private int quizVersion;
    private String quizName;
    private int moduleID;
    private boolean available;
    int numQuestions;
    //TODO
    
    public void setQuizID(int quizID)
    {   this.quizID = quizID;   }
    
    public int getQuizID()
    {   return quizID;   }
    
    public void setQuizVersion(int quizVersion)
    {   this.quizVersion = quizVersion;   }
    
    public int getQuizVersion()
    {   return quizVersion;   }
    
    public void setNumQuestions(int numQuestions)
    {   this.numQuestions = numQuestions;   }
    
    public int getNumQuestions()
    {   return numQuestions;   }
    
    
}
