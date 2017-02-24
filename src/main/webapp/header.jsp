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
                        <li><a href="${pageContext.request.contextPath}/">Home</a></li>
                        <li><a href="${pageContext.request.contextPath}/ViewQuizzes">View Quizzes</a></li>
						<li><a href="${pageContext.request.contextPath}/CreateQuiz">Create a quiz</a></li>
						<li><a href="${pageContext.request.contextPath}/EditQuizzes">Edit your quizzes</a></li>
                   </ul>
				   <ul class="nav navbar-nav navbar-right">
                        <li><a href="${pageContext.request.contextPath}/signup.jsp"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
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