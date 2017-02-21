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
public class AnswerBean {
    private int answerID;
    private String answerText;
    private boolean correctAnswer;
    
    public void setAnswerID(int answerID)
    {   this.answerID = answerID;   }
    
    public int getAnswerID()
    {   return answerID;   }
    
    public void setAnswerText(String answerText)
    {   this.answerText = answerText;   }
    
    public String getAnswerText()
    {   return answerText;   }
    
    public void setCorrectAnswer(boolean correctAnswer)
    {   this.correctAnswer = correctAnswer;   }
    
    public boolean getCorrectAnswer()
    {   return correctAnswer;   }
    
}
