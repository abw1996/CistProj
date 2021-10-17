<%-- 
    Document   : CustomerAppointments
    Created on : Oct 7, 2021, 11:35:27 PM
    Author     : ashto
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="BusinessObjects.Customer"%>
<%@page import="BusinessObjects.Appointment"%>
<%@page import="BusinessObjects.Procedure"%>
<%@page import="BusinessObjects.Employee"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
    
    <%
        Customer customer = (Customer)session.getAttribute("customer");
        ArrayList<Appointment> appointments = customer.getAppointments();
        int length = appointments.size();
    %>
    
    <body>
    <div id="Header"></div>
    <div class="container">
        <table>
            <tr>
                <th>Appointment Date/Time</th>
                <th>Employee</th>
                <th>Procedure</th>
                <th>Customer</th>
                <th>&nbsp</th>
                <th>&nbsp</th>
            </tr>
            <%
                for (int x = 0; x < length; x++) {
                    Appointment appointment = appointments.get(x);
                    Procedure procedure = new Procedure(appointment.getProcedureID());
                    Employee employee = new Employee(appointment.getEmployeeID());
                    %>
                    <tr>
                        <th><%out.println(appointment.getAppointmentDateTime());%></th>
                        <th><%out.println(employee.getFirstName() + " " + employee.getLastName());%></th>
                        <th><%out.println(procedure.getProcedureName());%></th>
                        <th><%out.println(customer.getFirstName() + " " + customer.getLastName());%></th>
                        <th>
                            <form>
                                
                            </form>
                        </th>
                        <th>
                            <form>
                                
                            </form>
                        </th>
                    </tr>
                    <%
                }
            %>
        </table>
    </div>     
</body>
<footer id="Footer"></footer>
</html>
