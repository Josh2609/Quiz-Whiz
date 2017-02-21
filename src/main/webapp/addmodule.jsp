<%-- 
    Document   : addmodule
    Created on : 21-Feb-2017, 18:00:09
    Author     : joshcorps
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add a module</title>
    </head>
        <form method="POST"  action="AddModule">
                <ul>
                    <li>Module Code <input type="text" name="moduleCode" required="true"></li>
                    <li>Module Name <input type="text" name="moduleName" required="true"></li>
                </ul>
                <br/>
                <input type="submit" value="Add"> 
        </form>
</html>
