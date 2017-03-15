/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.team7.agilequiz.stores;

import java.sql.Date;

/**
 *
 * @author joshcorps
 */
public class QuizBean {
    private int quizID;
    private int quizVersion;
    private String quizName;
    private String quizDescription;
    private String quizCreator;
    private String moduleCode;
    private String moduleName;
    private boolean available;
    private Date dateAdded;
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
    
    public void setQuizName(String quizName)
    {   this.quizName = quizName;   }
    
    public String getQuizName()
    {   return quizName;   }
    
    public void setQuizDescription(String quizDescription)
    {   this.quizDescription = quizDescription;   }
    
    public String getQuizDescription()
    {   return quizDescription;   }
    
    public void setQuizCreator(String quizCreator)
    {   this.quizCreator = quizCreator;   }
    
    public String getQuizCreator()
    {   return quizCreator;   }
    
    public void setModuleCode(String moduleCode)
    {   this.moduleCode = moduleCode;   }
    
    public String getModuleCode()
    {   return moduleCode;   }
    
    public void setModuleName(String moduleName)
    {   this.moduleName = moduleName;   }
    
    public String getModuleName()
    {   return moduleName;   }
    
    public void setDateAdded(Date dateAdded)
    {   this.dateAdded = dateAdded;   }
    
    public Date getDateAdded()
    {   return dateAdded;   }
    
    public void setNumQuestions(int numQuestions)
    {   this.numQuestions = numQuestions;   }
    
    public int getNumQuestions()
    {   return numQuestions;   }
    
    
}
