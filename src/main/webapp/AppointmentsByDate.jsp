<%-- 
    Document   : AppointmentsByDate
    Created on : Oct 28, 2021, 10:07:32 AM
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
        Employee employee = (Employee)session.getAttribute("employee");
        ArrayList<Appointment> appointments = (ArrayList<Appointment>) session.getAttribute("appointments");
        ArrayList<Customer> customers = employee.getCustomers();
        int length = appointments.size();
        Employee employeeGetter = new Employee();
        ArrayList<Employee> employees = employeeGetter.getEmployees();
    %>
    <body>
        <div id="Header"></div>
        <div class="container text-center">
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
                    Employee tempEmployee = new Employee(appointment.getEmployeeID());
                    Customer tempCustomer = new Customer();
                    tempCustomer.selectDB(appointment.getCustomerID());
                    String appointmentSubmit = appointment.getAppointmentID() + "Submit";
                    String appointmentDeleteSubmit = appointment.getAppointmentID() + "DeleteSubmit";
                    %>
                    <tr>
                        <th class="text-center"><%out.println(appointment.getAppointmentDateTime().substring(0, appointment.getAppointmentDateTime().length()-7));%></th>
                        <th class="text-center"><%out.println(tempEmployee.getFirstName() + " " + tempEmployee.getLastName());%></th>
                        <th class="text-center"><%out.println(procedure.getProcedureName());%></th>
                        <th class="text-center"><%out.println(tempCustomer.getFirstName() + " " + tempCustomer.getLastName());%></th>
                        <th>
                            <form action="EditAppointment.jsp" method="post">
                                <input type="hidden" name="appointmentID" id="appointmentID" value="<%=appointment.getAppointmentID()%>">
                                <label class="hire-form-text align-self-end cursor-on-hover" for="<%=appointmentSubmit%>">Make Changes</label>
                                <input class="hide" name="<%=appointmentSubmit%>" id="<%=appointmentSubmit%>" type="submit">
                            </form>
                        </th>
                        <th>
                            <form action="DeleteAppointmentServlet" method="post">
                                <input type="hidden" name="appointmentID" id="appointmentID" value="<%=appointment.getAppointmentID()%>">
                                <label class="hire-form-text align-self-end cursor-on-hover" for="<%=appointmentDeleteSubmit%>">Cancel/Delete</label>
                                <input class="hide" name="<%=appointmentDeleteSubmit%>" id="<%=appointmentDeleteSubmit%>" type="submit">
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
