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
    <link rel="shortcut icon" href="https://s-media-cache-ak0.pinimg.com/originals/04/38/35/0438350175391a71727f8dac6e7be433.png">
        <title>Sign Up</title>
    </head>
    <body>
        <%@ include file="header.jsp" %>

        <div class="container">
            <div class="col-sm-9">
                <h1 style="text-align:center">Sign Up</h1><br>
                <h2 style="text-align:center">Please select one of the options, based on your academic status.</h2><br>

                <% String msg = (String) request.getAttribute("Message");
            if (msg != null) {%>
                <p id="flash_message"><%= msg%></p>
                <% }%>



                <div class="STYLE">
                    <div class="span12" style="text-align:center">
                        <button style ="height: 50px; width:90px" class="btn btn-success" onclick="location.href = '${pageContext.request.contextPath}/StaffSignup'" type="button">Staff</button>

                    </div>
                        <br>
                    <div class="span12" style="text-align:center">
                        <button style ="height: 50px; width:90px" class="btn btn-success" onclick="location.href = '${pageContext.request.contextPath}/StudentSignup'" type="button">Student</button>
                    </div>
                </div>

            </div>
        </div>

    </body>

</html>