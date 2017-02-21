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
public class QuestionBean {
    private int questionID;
    private String questionText;
    private String questionExplanation;
    
    public void setQuestionID(int questionID)
    {   this.questionID = questionID;   }
    
    public int getQuestionID()
    {   return questionID;   }
    
    public void setQuestionText(String questionText)
    {   this.questionText = questionText;   }
    
    public String getQuestionText()
    {   return questionText;   }
    
    public void setQuestionExplanation(String questionExplanation)
    {   this.questionExplanation = questionExplanation;   }
    
    public String getQuestionExplanation()
    {   return questionExplanation;   }
}
