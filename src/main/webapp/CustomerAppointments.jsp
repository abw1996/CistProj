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
        Employee employeeGetter = new Employee();
        ArrayList<Employee> employees = employeeGetter.getEmployees();
    %>
    
    <body>
    <div id="Header"></div>
    <div class="container text-center">
        <div class="row justify-content-center">
        <div class="col-lg-6 col-sm-12 prettify-box-blue justify-content-center text-center" style="padding: 30px;">
            <h1 class="form-title">Create Appointment</h1>
        <form action="CustomerCreateAppointment.jsp" method="post">
            <input type="hidden" name="customerEmail" id="customerEmail" value="<%=customer.getEmail()%>">
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
            <label class="hire-form-text align-self-end cursor-on-hover" for="submit">Continue</label>
            <input class="hide" name="submit" id="submit" type="submit">
            </form>
        </div>
        </div>
            <hr>
            <h1 class="form-title">Current Appointments</h1>
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
                    String appointmentSubmit = appointment.getAppointmentID() + "Submit";
                    String appointmentDeleteSubmit = appointment.getAppointmentID() + "DeleteSubmit";
                    %>
                    <tr>
                        <th class="text-center"><%out.println(appointment.getAppointmentDateTime().substring(0, appointment.getAppointmentDateTime().length()-7));%></th>
                        <th class="text-center"><%out.println(employee.getFirstName() + " " + employee.getLastName());%></th>
                        <th class="text-center"><%out.println(procedure.getProcedureName());%></th>
                        <th class="text-center"><%out.println(customer.getFirstName() + " " + customer.getLastName());%></th>
                        <th>
                            <form action="EditAppointment.jsp" method="post">
                                <input type="hidden" name="appointmentID" id="appointmentID" value="<%=appointment.getAppointmentID()%>">
                                <input type="hidden" name="customerEmail" id="customerEmail" value="<%=customer.getEmail()%>">
                                <input type="hidden" name="isCustomer" id="isCustomer" value="TRUE">
                                <label class="hire-form-text align-self-end cursor-on-hover" for="<%=appointmentSubmit%>">Change Service</label>
                                <input class="hide" name="<%=appointmentSubmit%>" id="<%=appointmentSubmit%>" type="submit">
                            </form>
                        </th>
                        <th>
                            <form action="DeleteAppointmentServlet" method="post">
                                <input type="hidden" name="appointmentID" id="appointmentID" value="<%=appointment.getAppointmentID()%>">
                                <input type="hidden" name="customerEmail" id="customerEmail" value="<%=customer.getEmail()%>">
                                <input type="hidden" name="isCustomer" id="isCustomer" value="TRUE">
                                <label class="hire-form-text align-self-end cursor-on-hover" for="<%=appointmentDeleteSubmit%>">Cancel/Delete</label>
                                <input class="hide" name="<%=appointmentDeleteSubmit%>" id="<%=appointmentDeleteSubmit%>" type="submit">
                            </form>
                        </th>
                    </tr>
                    <%
                }
            %>
        </table>
        
        <hr>
        
        <div class="row justify-content-center align-items-center">
            <div class="col-lg-12 col-sm-12 prettify-box-blue justify-content-center text-center" style="padding: 30px; margin: 30px;">
                <h1 class="form-title">Edit Account</h1>
                <hr>
                <form class="text-center" action="EditCustomerServlet" method="post">
                    <input type="hidden" name="customerEmail" id="customerEmail" value="<%=customer.getEmail()%>">
                    <input type="hidden" name="isCustomer" id="isCustomer" value="TRUE">
                    <label class="hire-form-text">First Name</label> <br>
                    <input class="text-input" type="text" name="firstName" id="firstName" placeholder="<%=customer.getFirstName()%>" required><br>
                    <label class="hire-form-text">Last Name</label> <br>
                    <input class="text-input" type="text" name="lastName" id="lastName" placeholder="<%=customer.getLastName()%>" required><br>                   
                    <label class="hire-form-text">Email</label> <br>
                    <input class="text-input" type="text" name="email" id="email" placeholder="<%=customer.getEmail()%>" required><br>
                    <label class="hire-form-text">Phone Number</label> <br>
                    <input class="text-input" type="tel" name="phoneNumber" id="phoneNumber" placeholder="<%=customer.getPhoneNumber()%>" required><br>
                    <label class="hire-form-text">Password</label> <br>
                    <input class="text-input" type="password" name="password" id="password" placeholder="<%=customer.getPassword()%>" required><br>
                    <hr>

                    <label class="hire-form-text align-self-end cursor-on-hover" for="EditCustomerSubmit">Submit Changes</label>
                    <input class="hide" name="EditCustomerSubmit" id="EditCustomerSubmit" type="submit">

                </form>

            </div>
            
        </div>
                    <form class="text-center" action="DeleteCustomerServlet" method="post">
                        <input type="hidden" value="<%=customer.getCustomerID()%>" name="customerID" id="customerID">
                        <input type="hidden" name="isCustomer" id="isCustomer" value="TRUE">
                        <label class="hire-form-text align-self-end cursor-on-hover" for="DeleteCustomerSubmit">Delete Account</label>
                        <input class="hide" name="DeleteCustomerSubmit" id="DeleteCustomerSubmit" type="submit">
                    </form>
    </div>
</body>
<footer id="Footer"></footer>
</html>
