<%-- 
    Document   : index
    Created on : 14-Feb-2017, 22:18:16
    Author     : Josh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile</title>
        <script src="js/createQuiz.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <link rel="stylesheet" content= "text/css" href="${pageContext.request.contextPath}/style.css">
        <%ProfileBean pb = (ProfileBean) session.getAttribute("ProfileBean");%>
        <script src="js/changePass.js"></script>  
    </head>
    
    <body>
        
    <%@ include file="header.jsp" %>
            
        <div class="container">
            <div style="text-align: center;">
                <% if(pb != null){%>
                            <h4>First Name: <%=pb.getFirstName()%></h4>
                            <h4>Surname: <%=pb.getSurname()%></h4>
                            <%if(lg.getStaff() == false){%>
                                <h4>Matriculation Number: <%=pb.getMatric()%></h4>
                            <%}else{%>
                                <h4>Staff ID: <%=pb.getMatric()%></h4>
                            <%}%>
                                <br>
                            <input type="submit" class="btn btn-success" value="Change Password" onclick="changePass()">
                            <div class="ChangePass"></div>
                        <%}else{%>               
                    SOMETHING HAs GONE WrONg!!!?!?!
                <%}%>
            </div>
	</div>
     </body>
 </html>

