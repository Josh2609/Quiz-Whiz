<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <script src="js/createQuiz.js"></script>
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
                <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
                <!-- Latest compiled and minified CSS -->
                <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
                <link rel="stylesheet" content= "text/css" href="${pageContext.request.contextPath}/style.css">
		<title>Sign Up</title>
	</head>
	<body>
	<%@ include file="header.jsp" %>
	
	<div class="container">
    <div class="col-sm-9">
	<h1 style="text-align:center">Sign Up</h1><br>
	<h2 style="text-align:center">Please select one of the options, based on your academic status.</h2><br>
        
	<% String msg = (String)request.getAttribute("Message");
               if(msg != null) { %>
            <p id="flash_message"><%= msg %></p>
            <% } %>
	 
	
	 
	 <div class="STYLE">
	    <form action="${pageContext.request.contextPath}/staffsignup.jsp">
			<div class="span12" style="text-align:center">
				<input style ="height: 50px; width:90px"type="submit" class="btn btn-success" value="Staff"> 
			</div>
		</form>
		
		<form action="${pageContext.request.contextPath}/studentsignup.jsp">
			<div class="span12" style="text-align:center">		
				<input style ="height: 50px; width:90px" type="submit" class="btn btn-success" value="Student">
			</div>
		</form>
     </div>
	 
	</div>
	</div>
	
	</body>
	
</html>