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

                        <li><a href="${pageContext.request.contextPath}/CreateQuiz">Create a quiz</a></li>
                        <li><a href="${pageContext.request.contextPath}/EditQuiz/1">Edit your quizzes</a></li>
                        <li><a href="${pageContext.request.contextPath}/SitQuiz/1">Sit Quiz</a></li>
                   </ul>
                   <ul class="nav navbar-nav navbar-right">

                        <li><a href="${pageContext.request.contextPath}/SignUp"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
                        <li><a href="${pageContext.request.contextPath}/Login"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
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
                    
<script>       
        $(document).ready(function() {
	// get current URL path and assign 'active' class
	var pathname = window.location.pathname;
	$('.nav > li > a[href="'+pathname+'"]').parent().addClass('active');
})
</script>