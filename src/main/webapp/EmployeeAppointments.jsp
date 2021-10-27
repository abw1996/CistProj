<%-- 
    Document   : EmployeeAppointments
    Created on : Oct 7, 2021, 11:36:23 PM
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
        ArrayList<Appointment> appointments = employee.getAppointments();
        ArrayList<Customer> customers = employee.getCustomers();
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
        <form action="CreateAppointmentServlet" method="post">
            <label class="hire-form-text">Customer</label><br>
            <select class="text-input" type="text" name="customerID" id="customerID" value="" required>
                <%
                    for (int x = 0; x < customers.size(); x++) {
                        %>
                        <option value="<%=customers.get(x).getCustomerID()%>"><%out.println(customers.get(x).getFirstName() + " " + customers.get(x).getLastName());%></option>
                        <%
                    }
                %>
            </select><br>
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
                <option value="P0002">Shampoo and Blow Dry</option>
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
        <hr>
        <div class="row justify-content-center align-items-center">
            <div class="col-lg-12 col-sm-12 prettify-box-blue justify-content-center text-center" style="padding: 30px; margin: 30px;">
                <h1 class="form-title">Add New Employee</h1>
                <hr>
                <form class="text-center" action="/CreateEmployeeServlet" method="post">
                    <label class="hire-form-text">First Name</label> <br>
                    <input class="text-input" type="text" name="firstName" id="firstName" required><br>
                    <label class="hire-form-text">Last Name</label> <br>
                    <input class="text-input" type="text" name="lastName" id="lastName" required><br>
                    <label class="hire-form-text">Phone Number</label> <br>
                    <input class="text-input" type="tel" name="phoneNumber" id="employeePN" required><br>
                    <label class="hire-form-text">Password</label> <br>
                    <input class="text-input" type="password" name="password" id="employeePW" required><br>
                    <hr>

                    <label class="hire-form-text align-self-end cursor-on-hover" for="submit">Create</label>
                    <input class="hide" name="submitEmployeeCreate" id="submitEmployeeCreate" type="submit">

                </form>

            </div>
            
            <div class="col-lg-12 col-sm-12 prettify-box-blue justify-content-center text-center" style="padding: 30px; margin: 30px;">
                <h1 class="form-title">Create Customer</h1>
                <hr>
                <form class="text-center" action="/CreateCustomerServlet" method="post">
                    <label class="hire-form-text">First Name</label> <br>
                    <input class="text-input" type="text" name="firstName" id="firstName" required><br>
                    <label class="hire-form-text">Last Name</label> <br>
                    <input class="text-input" type="text" name="lastName" id="lastName" required><br>
                    <label class="hire-form-text">Email</label> <br>
                    <input class="text-input" type="email" name="email" id="email" required><br>
                    <label class="hire-form-text">Phone Number</label> <br>
                    <input class="text-input" type="tel" name="phoneNumber" id="phoneNumber" required><br>
                    <label class="hire-form-text">Password</label> <br>
                    <input class="text-input" type="password" name="password" id="password" required><br>
                    <hr>

                    <label class="hire-form-text align-self-end cursor-on-hover" for="submit">Create</label>
                    <input class="hide" name="submitCreateCustomer" id="submit" type="submitCreateCustomer">

                </form>

            </div>
            
            <div class="col-lg-12 col-sm-12 prettify-box-blue justify-content-center text-center" style="padding: 30px; margin: 30px;">
                <h1 class="form-title">Delete Employee/Customer</h1>
                <hr>
                <form class="text-center" action="/DeleteCustomerServlet" method="post">
                    <label class="hire-form-text">Customer</label><br>
                    <select class="text-input" type="text" name="customerID" id="customerID" value="" required>
                        <%
                            for (int x = 0; x < customers.size(); x++) {
                                %>
                                <option value="<%=customers.get(x).getCustomerID()%>"><%out.println(customers.get(x).getFirstName() + " " + customers.get(x).getLastName());%></option>
                                <%
                            }
                        %>
                    </select>

                    <label class="hire-form-text align-self-end cursor-on-hover" for="submit">Delete</label>
                    <input class="hide" name="DeleteCustomerSubmit" id="submit" type="DeleteCustomerSubmit">

                </form>
                    
                <form class="text-center" action="/DeleteEmployeeServlet" method="post">
                    <label class="hire-form-text">Employee</label><br>
                    <select class="text-input" type="text" name="employeeID" id="employeeID" value="" required>
                        <%
                            for (int x = 0; x < employees.size(); x++) {
                                %>
                                <option value="<%=employees.get(x).getEmployeeID()%>"><%out.println(employees.get(x).getFirstName() + " " + employees.get(x).getLastName());%></option>
                                <%
                            }
                        %>
                    </select>

                    <label class="hire-form-text align-self-end cursor-on-hover" for="submit">Delete</label>
                    <input class="hide" name="DeleteEmployeeSubmit" id="submit" type="DeleteEmployeeSubmit">

                </form>

            </div>
        </div>
        
    </div>     
</body>
<footer id="Footer"></footer>
</html>
