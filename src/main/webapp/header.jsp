<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" content= "text/css" href="${pageContext.request.contextPath}/style.css">
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Faster+One" rel="stylesheet">
</head>
<nav class="navbar navbar-default">
        <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>                        
                    </button>
                    <a class="navbar-brand" href="${pageContext.request.contextPath}/"><img src="https://upload.wikimedia.org/wikipedia/en/thumb/8/83/University_of_Dundee_logo.svg/1200px-University_of_Dundee_logo.svg.png" alt="Dundee University Logo" style="width: 25px; height: 30px; display:inline;">
                        <div style="display:inline; font-family: 'Faster One', cursive;  font-weight: 400;">QuizWhiz</div>
                    </a>
                </div>
                <div class="collapse navbar-collapse" id="myNavbar">
                    <ul class="nav navbar-nav">
                        <li><a href="${pageContext.request.contextPath}/">Home</a></li>
                        <li><a href="${pageContext.request.contextPath}/ViewQuiz/1">View Quizzes</a></li>

                        <li><a href="${pageContext.request.contextPath}/CreateQuiz">Create Quiz</a></li>
                        <li><a href="${pageContext.request.contextPath}/EditQuiz/1">Edit Quizzes</a></li>
                        <li><a href="${pageContext.request.contextPath}/SitQuiz/1">Sit Quiz</a></li>
                   </ul>
                   <ul class="nav navbar-nav navbar-right">

                        <!--<li><a href="${pageContext.request.contextPath}/signup.jsp"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
                        <li><a href="${pageContext.request.contextPath}/Login"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>-->
                        <li><a href="#register" data-toggle="modal" data-target="#logModal" class="modal-toggle"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
                        <li><a href="#login" data-toggle="modal" data-target="#logModal" class="modal-toggle"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
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


  <!-- Modal -->
  <div class="modal fade" id="logModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <ul class="nav nav-tabs">
            <li><a data-toggle="tab" href="#login"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
            <li><a data-toggle="tab" href="#register"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
          </ul>
          <!--<h4 class="modal-title">Login</h4>-->
        </div>
        <div class="modal-body">
        <div class="tab-content">
            <div id="login" class="tab-pane">
                <form method="POST"  action="Login">	
                    <div style="text-align:center;">
                        <input id="msg" type="text" class="inputDesign" name="matric" placeholder="Enter your matriculation number here"><br>
                            <br>
                        <input id="msg" type="password" class="inputDesign" name="password" placeholder="Enter your password here"><br>
                        <br><br><br>
                    </div>
                    <div class="span12" style="text-align:center">
                        <div class="STYLE">
                            <input type="submit" class="btn btn-success" value="Login">
                        </div>
                    </div>
                    <br>
		</form>
            </div>
            <div id="register" class="tab-pane fade">
                <form method="POST"  action="SignUp">
                    <div style="text-align:center;">
                        <input id="msg" type="text" class="inputDesign" name="matric" placeholder="Enter your matriculation number here"><br>
                            <br>
                        <input id="msg" type="password" class="inputDesign" name="password" placeholder="Enter your password here"><br>
                            <br>
                        <input id="msg" type="password" class="inputDesign" name="password" placeholder="Enter your password again"><br>
                        <br>
                    </div>    
                    <br><br>
                <div class="span12" style="text-align:center">
                    <div class="STYLE">
                        <input type="submit" class="btn btn-success" value="Sign Up">
                    </div>
                </div> 
                <br><br>
                </form>
            </div>
        </div>
        </div>
      </div>
      
    </div>
  </div>
                    
<script>       
        $(document).ready(function() {
	// get current URL path and assign 'active' class
	var pathname = window.location.pathname;
	$('.nav > li > a[href="'+pathname+'"]').parent().addClass('active');
});

$('.modal-toggle').click(function (e) {
    var tab = e.target.hash;
    $('li > a[href="' + tab + '"]').tab("show");
});
</script>