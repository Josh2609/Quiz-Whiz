<%-- 
    Document   : createquiz
    Created on : 14-Feb-2017, 22:30:39
    Author     : Josh Corps
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
	<%@ include file="header.jsp" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create a Quiz</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <script src="js/createQuiz.js"></script> 
        <style>
  body {
      position: relative;
  }
  ul.nav-pills {
      top: 100px;
      position: fixed;
  }
  div.col-sm-9 div {
      height: 100%;
  }  
  
  
/*  #section1{
      background: #e38d13;
  }
  #section2{
      background: #5cb85c;
  }*/
  @media screen and (max-width: 810px) {
    #section1, #section2, #section3, #section41, #section42  {
        margin-left: 150px;
        padding:20px;
    }
  }
  </style>
    </head>

   
    
    
    <body>   
<!--<body data-spy="scroll" data-target="#myScrollspy" data-offset="20">
    <div class="row">
        <nav class="col-sm-3" id="myScrollspy">
            <ul class="nav nav-pills nav-stacked">
            <div class="navBarAdd">
                <li class="active"><a href="#section1">Quiz Details</a></li>
                <li><a href="#section2">Section 2</a></li>
                <li><a href="#section3">Section 3</a></li>
            </div>
            </ul>
        </nav>
    </div>-->
      
   <div class="container" style="text-align:center">
        
<!--START OF SECTION 1      
            <div id="section1" style="text-align:center">    -->  
                <h1>Details for the Quiz</h1><br>
                    <form>
                        <div class="input-group">
                            <span class="input-group-addon">Title/Name</span>
                            <input id="msg" type="text" class="form-control" name="msg" placeholder="Enter the Title for your Quiz here">
                        </div>
                        <br>
                        <div class="input-group">
                            <span class="input-group-addon">Description</span>
                            <textarea id="msg" type="text" class="form-control" name="msg" style="height:100px;" placeholder="Enter a short description of this Quiz here"></textarea>
                        </div>
                    </form>
                <br>
                <label for="sel1">Select the Module code that this Quiz is contained within</label>
                <select class="form-control" id="sel1">
                    <option>AC007</option>
                    <option>AC420</option>
                    <option>AC360NoScope</option>
                    <option>AC12:51</option>
                </select><br>
                <label for="sel2">Select a Time a limit for your quiz</label>
                <select class="form-control">
                    <option>None</option>
                    <option>1</option>
                    <option>2</option>
                    <option>3</option>
                    <option>4</option>
                    <option>5</option>
                </select><br>
                <select class="form-control">
                    <option>None</option>
                    <option>15</option>
                    <option>30</option>
                    <option>45</option>
                </select>
                <br>
                <!--<div class="span12" style="text-align:center">
                    <input type="button" class="btn btn-primary" value="Done">
                </div><br><br>-->
            </div><br><br>
      
      <!-- Questions START HERE-->
      
            <div id=questions style="text-align:center">   
                <!--<div id="section2" style="text-align:center">-->
                    
                    <form class="questions" method="POST"  action="CreateQuiz">
                               
                    <input type="button" class="btn btn-primary" onclick="addQuestion()" value="Add Question"><br><br>
                    <input type="text" id="numQuestions" name="numQuestions" value="0" hidden>
                        <div class="STYLE">
                            <input type="submit" class="btn btn-success" value="Create">
                        </div>
                    </div> 
                    </form>       
                    <input type="button" class="btn btn-primary" onclick="addQuestion()" value="Add Question">
                    

                    <br>   
            </div>
        </div>
</body>
</html>
