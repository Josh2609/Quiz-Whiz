<%-- 
    Document   : viewquizzes
    Created on : 12-Mar-2017, 16:52:21
    Author     : joshcorps
--%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="uk.ac.dundee.computing.team7.agilequiz.models.Quiz"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Quiz List!</h1>
        <%
            ArrayList<String[]> quizList = (ArrayList<String[]>) request.getAttribute("quizList");;
            Iterator<String[]> iterator = quizList.iterator();
            while (iterator.hasNext())
            {
                String[] tempArr = iterator.next();
                %>
                <p>
                <%
                for (int i = 0; i < tempArr.length; i++)
                {
                    if (i == 0)
                    {
                        %>
                        <a href="${pageContext.request.contextPath}/ViewQuiz/<%=tempArr[i]%>"/>
                        <%
                        continue;
                    }
                %>
                    &nbsp; <%=tempArr[i]%>  &nbsp;
                <%}
                %>
                </p>
                <%
            }
            
            %>
    </body>
</html>
