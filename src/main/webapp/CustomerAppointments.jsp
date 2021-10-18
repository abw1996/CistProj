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
                <th class="text-center">Appointment Date/Time</th>
                <th class="text-center">Stylist</th>
                <th class="text-center">Service</th>
                <th class="text-center">Customer</th>
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
                        <th class="text-center"><%out.println(appointment.getAppointmentDateTime().substring(0, appointment.getAppointmentDateTime().length()-7));%></th>
                        <th class="text-center"><%out.println(employee.getFirstName() + " " + employee.getLastName());%></th>
                        <th class="text-center"><%out.println(procedure.getProcedureName());%></th>
                        <th class="text-center"><%out.println(customer.getFirstName() + " " + customer.getLastName());%></th>
                        <th>
                            <form action="EditAppointment.jsp" metho="post">
                                <input type="hidden" name="appointmentID" id="appointmentID" value="<%=appointment.getAppointmentID()%>">
                                <label class="hire-form-text align-self-end cursor-on-hover" for="submit">Make Changes</label>
                                <input class="hide" name="submit" id="submit" type="submit">
                            </form>
                        </th>
                        <th>
                            <form action="AppointmentDeleteServlet" metho="post">
                                <input type="hidden" name="appointmentID" id="appointmentID" value="<%=appointment.getAppointmentID()%>">
                                <label class="hire-form-text align-self-end cursor-on-hover" for="submit">Cancel/Delete</label>
                                <input class="hide" name="submit" id="submit" type="submit">
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
