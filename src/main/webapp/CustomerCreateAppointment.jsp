<%-- 
    Document   : CustomerCreateAppointment
    Created on : Nov 10, 2021, 11:22:02 AM
    Author     : ashto
--%>

<%@page import="BusinessObjects.Appointment"%>
<%@page import="BusinessObjects.Employee"%>
<%@page import="java.util.ArrayList"%>
<%@page import="BusinessObjects.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <head>
        <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Curl Me Crazy</title>
    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <link rel="stylesheet" href="css/site.css" />
    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.bundle.min.js"></script>
    <script src="js/site.js" asp-append-version="true"></script> 
    <script>
        $(document).ready(function(){
           
           $('#Header').load("Header.html");
           $("#Footer").load("Footer.html");
        
        });
        </script>
    </head>
    </head>
   <%
        Customer customer = new Customer((String) request.getParameter("customerEmail"));
        Employee employee = new Employee((String) request.getParameter("employeeID"));
        Employee employeeGetter = new Employee();
        ArrayList<String> days = employee.getDays(employee.getEmployeeID()); 
    %>
    
    <body>
    <div id="Header"></div>
    <div class="container text-center">
        <div class="row justify-content-center">
        <div class="col-lg-6 col-sm-12 prettify-box-blue justify-content-center text-center" style="padding: 30px;">
            <h1 class="form-title">Create Appointment</h1>
        <form action="CustomerSelectTime.jsp" method="post">
            <input type="hidden" name="customerID" id="customerID" value="<%=customer.getCustomerID()%>">
            <input type="hidden" name="customerEmail" id="customerEmail" value="<%=customer.getEmail()%>">
            <input type="hidden" name="employeeID" id="employeeID" value="<%=employee.getEmployeeID()%>">
            <label class="hire-form-text" for="employeeID">Stylist - <%out.println(employee.getFirstName() + " " + employee.getLastName());%></label> <br>
            <label class="hire-form-text">Day</label> <br>
            <select class="text-input" type="text" name="day" id="day">
                <%
                for (int x = 0; x < days.size(); x++) {
                    %>
                    <option value="<%=days.get(x)%>"><%out.println(days.get(x));%></option>
                    <%
                }
                %>
            </select><br>
            <label class="hire-form-text align-self-end cursor-on-hover" for="submit">Continue</label>
            <input class="hide" name="submit" id="submit" type="submit">
            </form>
        </div>
        </div>
    </div>
    </body>
</html>
