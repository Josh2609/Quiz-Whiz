<%-- 
    Document   : index
    Created on : 14-Feb-2017, 22:18:16
    Author     : Josh
--%>
<%LoggedIn lg2 = (LoggedIn) session.getAttribute("LoggedIn");
   if (lg2 == null) 
   {    response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/")); }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
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
    <link rel="shortcut icon" href="https://s-media-cache-ak0.pinimg.com/originals/04/38/35/0438350175391a71727f8dac6e7be433.png">
    </head>
    
    <body>
        
    <%@ include file="header.jsp" %>
    
    <% String msg = (String)request.getAttribute("Message");
               if(msg != null) { %>
               <div class="container"><div class="alert alert-warning"><strong>Error: It's</strong> <%= msg %></div></div>
            <% } %>
            
        <div class="container">
            <div class='panel panel-default'>
                <div class='panel-heading'>
                    <h3 style='text-align:center'>Profile Page</h3>
                </div>
                <div class='panel-body'>
            <div style="text-align: left;">
                <% if(pb != null){%>
                            <h4>First Name: <%=pb.getFirstName()%></h4>
                            <h4>Surname: <%=pb.getSurname()%></h4>
                            <%if(lg.getStaff() == false){%>
                                <h4>Matriculation Number: <%=pb.getMatric()%></h4>
                            <%}else{%>
                                <h4>Staff ID: <%=pb.getMatric()%></h4>
                            <%}%>
                                <br>
                                <div class="ChangePass">
                                    <input type="submit" class="btn btn-success" value="Change Password" onclick="changePass()">
                                </div>
                                <br><br>
                                
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Module</th>
                        <th>Author</th>
                        <th>Description</th>
                    </tr>
                </thead>
                    <tbody>
                                
                        <%
                            ArrayList<String[]> bk = (ArrayList<String[]>) request.getAttribute("bookmarkList");
                            Iterator<String[]> iterator = bk.iterator();
                            while (iterator.hasNext()){
                                String[] tempArr = iterator.next();
                        %>
                        <tr class='clickable-row' data-href="${pageContext.request.contextPath}/SitQuiz/<%=tempArr[0]%>">
                        <%
                         for (int i = 1; i < tempArr.length; i++)
                        {
                        %>
                        <td>&nbsp; <%=tempArr[i]%>  &nbsp;</td>
                        <%}
                            }
                        %>
                        </tr>
                    </tbody>
                </table>
                        
                        
                        <%}else{%>               
                    SOMETHING HAs GONE WrONg!!!?!?!
                <%}%>
            </div>
                </div>
            </div>
	</div>
     </body>
 </html>

 
 <script>
jQuery(document).ready(function($) {
    $(".clickable-row").click(function() {
        window.location = $(this).data("href");
    });
});
</script>
