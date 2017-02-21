<%-- 
    Document   : createquiz
    Created on : 14-Feb-2017, 22:30:39
    Author     : Josh Corps
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create a Quiz</title>
        <script src="js/createQuiz.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
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

    
    <!-- Start of Nav bar Here / Only placeholder nav in right now-->
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>                        
                    </button>
                    <a class="navbar-brand" href="#"><b>Qu¡zWh¡z</b></a>
                </div>
                <div class="collapse navbar-collapse" id="myNavbar">
                    <ul class="nav navbar-nav">
                        <li><a href="index.jsp">Home</a></li>
                        <li><a href="#">View Quizzes</a></li>
                        <li class="active"><a href="#">Create Quiz</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="#"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
                        <li><a href="login.jsp"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
                    </ul>
                    <!--Search bar, if wanted <form class="navbar-form navbar-left">
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="Search">
                        </div>
                        <button type="submit" class="btn btn-success">Submit</button>
                    </form> -->
                </div>
        </div>
    </nav>
    <!-- END of Nav-bar Here / Only placeholder nav in right now-->
    
    
    
<body data-spy="scroll" data-target="#myScrollspy" data-offset="20">
  <div class="row">
    <nav class="col-sm-3" id="myScrollspy">
      <ul class="nav nav-pills nav-stacked">
        <li class="active"><a href="#section1">Quiz Details</a></li>
        <li><a href="#section2">Section 2</a></li>
        <li><a href="#section3">Section 3</a></li>
        <li class="dropdown">
          <a class="dropdown-toggle" data-toggle="dropdown" href="#">Section 4 <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#section41">Section 4-1</a></li>
            <li><a href="#section42">Section 4-2</a></li>                     
          </ul>
        </li>
      </ul>
    </nav>
      
   <div class="container">
    <div class="col-sm-9">
        
<!--START OF SECTION 1-->        
        <div id="section1">    
            <h1 style="text-align:center">Details for the Quiz</h1><br>
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
            <label for="sel1">Select a Time a limit for your quiz</label>
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
        <div class="span12" style="text-align:center">
            <input type="button" class="btn btn-primary" value="Done">
        </div><br><br>
      </div><br><br>
        
        
                <div id="section2">
                    <div id=questions> 
                        <form class="questions" method="POST"  action="CreateQuiz">
                            <input type="text" id="numQuestions" name="numQuestions" value="0" hidden>
                            <div class="span12" style="text-align:center">
                                <div class="STYLE">
                                    <input type="submit" class="btn btn-success" value="Create">
                                </div>
                            </div> 
                        </form>       
                        <input type="button" class="btn btn-primary" onclick="addQuestion()" value="Add Question">
                        <br>   
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
