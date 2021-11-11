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
        ArrayList<Employee> employees = employeeGetter.getEmployees();
    %>
    
    <body>
    <div id="Header"></div>
    <div class="container text-center">
        <div class="row justify-content-center">
        <div class="col-lg-6 col-sm-12 prettify-box-blue justify-content-center text-center" style="padding: 30px;">
            <h1 class="form-title">Create Appointment</h1>
        <form action="CreateAppointmentServlet" method="post">
            <input type="hidden" name="customerID" id="customerID" value="<%=customer.getCustomerID()%>">
            <input type="hidden" name="isCustomer" id="isCustomer" value="TRUE">
            <label class="hire-form-text">Date/Time</label> <br>
            <input class="text-input" type="datetime-local" name="dateTime" id="dateTime" value="" required><br>
            <label class="hire-form-text" for="employeeID">Stylist</label> <br>
            <select class="text-input" type="text" name="employeeID" id="employeeID" value="" required>
                <%
                    for (int x = 0; x < employees.size(); x++) {
                        %>
                        <option value="<%=employees.get(x).getEmployeeID()%>"><%out.println(employees.get(x).getFirstName() + " " + employees.get(x).getLastName());%></option>
                        <%
                    }
                %>
            </select>
                <br>
            <label class="hire-form-text">Service</label> <br>
            <select class="text-input" type="text" name="procedureID" id="procedureID" value="" required>
                <option value="P0001">Kids Cut</option>
                <option value="P0002">Shampoo and Blowdry</option>
                <option value="P0003">Color and Cut</option>
                <option value="P0004">Color-Retouch and Cut</option>
                <option value="P0005">Color, Highlights, and Cut</option>
                <option value="P0006">Color Correction</option>
                <option value="P0007">Perm and Cut</option>
                <option value="P0008">Extensions</option>
                <option value="P0009">Ultimate Curl</option>
                <option value="P0010">Ultimate Brow</option>
                <option value="P0011">Hydration Facial</option>
                <option value="P0012">Steam Facial</option>
                <option value="P0013">Lash Tint</option>
                <option value="P0014">Serum</option>
                <option value="P0015">Chemical Peel</option>
                <option value="P0016">Vitamin C Facial</option>
                <option value="P0017">Acne Elimination</option>
                <option value="P0018">Lip Wax</option>
                <option value="P0019">Beard Trim</option>
                <option value="P0020">Mens Shave</option>
            </select><br><hr>
            <label class="hire-form-text align-self-end cursor-on-hover" for="submit">Create</label>
            <input class="hide" name="submit" id="submit" type="submit">
            </form>
        </div>
        </div>
    </div>
    </body>
</html>
