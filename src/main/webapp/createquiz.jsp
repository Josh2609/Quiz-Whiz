<%-- 
    Document   : createquiz
    Created on : 14-Feb-2017, 22:30:39
    Author     : Josh Corps
--%>
<%LoggedIn lg2 = (LoggedIn) session.getAttribute("LoggedIn");
   if (lg2 == null) 
   {    response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/")); }
%>
<%@page import="java.util.ArrayList"%>
<%@page import="uk.ac.dundee.computing.team7.agilequiz.models.Module"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create a Quiz</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js" charset="utf-8"></script>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

        <link rel="stylesheet" type= "text/css" href="style.css">

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
        
	<%@ include file="header.jsp" %>
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
            <div id="section1" style="text-align:center">   -->
                
      
      <!-- Questions START HERE-->  
                

      <form class="questions" method="POST"  action="CreateQuiz">
          <div class="panel panel-default">
              <div class="panel-body">
          <h1>Details for the Quiz</h1><br>
                        <div class="input-group">
                            <span class="input-group-addon">Title/Name</span>
                            <input type="text" class="form-control" name="quizName" placeholder="Enter the Name for your Quiz here">
                        </div>
                        <br>
                        <div class="input-group">
                            <span class="input-group-addon">Description</span>
                            <textarea type="text" class="form-control" name="quizDescription" style="height:100px;" placeholder="Enter a short description of this Quiz here"></textarea>
                        </div>
                <br>
                <label for="sel1">Select the Module code that this Quiz is contained within</label>
                
                <select class="form-control" id="moduleSelect" name="moduleSelect">
                    <%
                    Module module = new Module();
                    ArrayList<String[]> moduleList = new ArrayList<>();
                    moduleList = module.getModules();
                    for (int i = 0; i < moduleList.size(); i++)
                    {
                        String[] temp = moduleList.get(i);
                        //for (int j = 0; j < temp.length; j++)%>
                        <option value="<%=temp[0]%>"><%=temp[1]%></option>
                   <%}%>
                </select><br>
                <br>
                <p class="field switch">
                    <input type="radio" id="radio1" name="radioAv" value="Av" checked hidden/>
                    <input type="radio" id="radio2" name="radioAv" value="Un" hidden/>
                    <label for="radio1" class="cb-enable selected"><span>Available</span></label>
                    <label for="radio2" class="cb-disable"><span>Unavailable</span></label>
                </p>
              </div></div>
                <br>
                <!--<div class="span12" style="text-align:center">
                    <input type="button" class="btn btn-primary" value="Done">
                </div><br><br>-->
            <br><br>
          
          <div id="section2" style="text-align:center">
              <div id=questions style="text-align:center"> 
                  <input type="button" class="btn btn-primary" onclick="addQuestion()" value="Add Question"><br><br>
                  <input type="text" id="numQuestions" name="numQuestions" value="0" hidden>
                  <div class="STYLE">
                      <input type="submit" class="btn btn-success" value="Create">

                  </div> 
              </div>
          </div>
      </form>

                    <br>   
            
    </div>
</body>
</html>

<script type="text/javascript">
$(document).ready( function(){ 
	$(".cb-enable").click(function(){
		var parent = $(this).parents('.switch');
		$('.cb-disable',parent).removeClass('selected');
		$(this).addClass('selected');
		$('.checkbox',parent).attr('checked', true);
	});
	$(".cb-disable").click(function(){
		var parent = $(this).parents('.switch');
		$('.cb-enable',parent).removeClass('selected');
		$(this).addClass('selected');
		$('.checkbox',parent).attr('checked', false);
	});
});
</script>
