<%-- 
    Document   : EmployeePage
    Created on : Oct 13, 2021, 2:52:25 PM
    Author     : dpizo
--%>
<%@page import="Business.EmployeeObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <form>
            <% javax.servlet.http.HttpSession httpSession = request.getSession();
            EmployeeObject employee = (EmployeeObject)(httpSession.getAttribute("employee"));
            employee.display(); %>
            
            <h1>Employee's Account Details</h1>
            <table>
                <tr>
                    <th>Employee's ID: </th>
                    <th>Employee's First Name: </th>
                    <th>Employee's Last Name: </th>
                    <th>Employee's Phone Number: </th>
                </tr>
                <tr>
                    <td><%=employee.getID()%></td>
                    <td><%=employee.getFName()%></td>
                    <td><%=employee.getLName()%></td>
                    <td><%=employee.getPN()%></td>
                </tr>
            </table>
                <h2>Change current information <input type="submit" value="Enter" name="changeinfo"</h2><br>
                <h2>View next appointment<input type="submit" value="Enter" name="existingapp"</h2>
        </form>
    </body>
</html>
