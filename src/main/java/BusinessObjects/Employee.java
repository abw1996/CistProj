package BusinessObjects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ashto
 */
public class Employee {

    private String employeeID;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String password;
    private String adminAccess;
    private Connection con;

    public Employee() {
        employeeID = "";
        firstName = "";
        lastName = "";
        phoneNumber = "";
        password = "";
        InitializeDB();
    }

    //I added a constructor that will create the object, get database access and info in one statement
    public Employee(String employeeID) {
        InitializeDB();
        this.employeeID = employeeID;
        selectDB(employeeID);
    }

    public void selectDB(String employeeID) {
        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM Employees WHERE EmployeeID = \"" + employeeID + "\";");
            while (rs.next()){
                this.employeeID = rs.getString(1);
                this.firstName = rs.getString(2);
                this.lastName = rs.getString(3);
                this.phoneNumber = rs.getString(4);
                this.password = rs.getString(5);
                this.adminAccess = rs.getString(6);
            }


        } catch (SQLException ex) {
            System.out.println("Failed to get employee data");
        }
    }

    public void insertDB(String employeeID, String firstName, String lastName, String phoneNumber, String password, String adminAccess) {
        try {
            Statement s = con.createStatement();
            s.executeUpdate("INSERT INTO Employees VALUES (\"" + employeeID + "\", \"" + firstName + "\", \""+ lastName + "\", \"" + phoneNumber +"\", \"" + password + "\", \"" + adminAccess + "\");");
        } catch (SQLException ex) {
            System.out.println("Failed to insert customer data");
            System.out.println(ex.toString());
        }
    }

    public void deleteDB(String employeeID) {
        try {
            Statement s = con.createStatement();
            s.executeUpdate("DELETE FROM Employees WHERE employeeID = \"" + employeeID + "\";");
        } catch (SQLException ex) {
            System.out.println("Failed to delete employee data");
            System.out.println(ex.toString());
        }
    }
    
    public void updateDB(String employeeID, String firstName, String lastName, String phoneNumber, String password, String adminAccess) {
        try {
            Statement s = con.createStatement();
            String query = "UPDATE Employees " +
                            "SET firstName = \"" + firstName + "\", lastName = \"" + lastName + "\", employeePN = \"" + phoneNumber + "\", employeePW = \"" + password + "\", adminAccess = \"" + adminAccess + "\" " +
                            "WHERE employeeID = \"" + employeeID + "\";";
            s.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println("Failed to delete employee data");
            String exString = ex.toString();
            System.out.println(exString);
        }
    }

    public void display() {
        System.out.println("employee id: " + this.employeeID);
        System.out.println("employee first name: " + this.firstName);
        System.out.println("employee lastname: " + this.lastName);
        System.out.println("employee Phone Number: " + this.phoneNumber);
        System.out.println("employee pw: " + this.password);
    }

    private void InitializeDB() {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Failed DB connection");
        }
        try {
            con = DriverManager.getConnection("jdbc:ucanaccess://" + "C://Database//SalonDB.mdb");
        } catch (SQLException ex) {
            System.out.println("Failed DB connection");
        }
    }
    
    public String getEmployeeID() {
        return this.employeeID;
    }
    
    public String getFirstName() {
        return this.firstName;
    }
    
    public String getLastName() {
        return this.lastName;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public String getPhoneNumber() {
        return this.phoneNumber;
    }
    
    public String getAdminAccess() {
        return this.adminAccess;
    }
    
    public ArrayList<Employee> getEmployees() {
        ArrayList<Employee> employees = new ArrayList<Employee>();
        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("Select employeeID FROM Employees;");
            while (rs.next()){
                String employeeID = rs.getString(1);
                Employee employee = new Employee(employeeID);
                employees.add(employee);
                
            }   
        } catch (SQLException ex) {
            System.out.println("SQL error getting employees");
            System.out.println(ex.toString());
        }
        return employees;
    }
    
    public ArrayList<Appointment> getAppointments() {
        ArrayList<Appointment> appointments = new ArrayList<Appointment>();
        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("Select appointmentID FROM Appointments WHERE employeeID = \"" + this.employeeID + "\";");
            while (rs.next()){
                String appointmentID = rs.getString(1);
                Appointment appointment = new Appointment(appointmentID);
                appointments.add(appointment);
                
            }   
        } catch (SQLException ex) {
            String exString = ex.toString();
            System.out.println(exString);
        }
        return appointments;
    }
    
    public ArrayList<String> getDays(String employeeID) {
        ArrayList<String> days = new ArrayList<String>();
        try {
            Statement s = con.createStatement();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");  
            LocalDateTime now = LocalDateTime.now();
            String today = dtf.format(now);
            String query = "Select day FROM Schedule WHERE employeeID = \"" + employeeID + "\" AND day >= #" + today + "# ORDER BY day ASC;";
            ResultSet rs = s.executeQuery(query);
            while (rs.next()){
                String day = rs.getString(1);
                day = day.substring(0, day.length()-16);
                days.add(day);
            }   
        } catch (SQLException ex) {
            String exString = ex.toString();
            System.out.println(exString);
        }
        return days;
    }
    
    public ArrayList<String> getTimes(String employeeID, String day) {
        ArrayList<String> times = new ArrayList<String>();
        ArrayList<String> allTimesForDay = new ArrayList<String>();
        ArrayList<String> allTimesTaken = new ArrayList<String>();
        String startTime = "";
        String endTime = "";
        try {
            Statement s = con.createStatement();
            String query = "Select startTime, endTime FROM Schedule WHERE employeeID = \"" + employeeID + "\" AND day = #" + day + "#;";
            ResultSet rs = s.executeQuery(query);
            while (rs.next()){
                startTime = rs.getString(1);
                endTime = rs.getString(2);
                startTime = startTime.substring(11, startTime.length()-7);
                endTime = endTime.substring(11, endTime.length()-7);
            }   
        } catch (SQLException ex) {
            String exString = ex.toString();
            System.out.println(exString);
        }
        
        try {
            Statement x = con.createStatement();
            String query = "Select apptDateTime FROM Appointments WHERE employeeID = \"" + employeeID + "\" AND apptDateTime > #" + day + " 00:00:00# AND apptDateTime < #" + day + " 23:00:00# ORDER BY apptDateTime ASC;";
            ResultSet rs = x.executeQuery(query);
            while (rs.next()){
                String apptDateTime = rs.getString(1);
                String apptTime = apptDateTime.substring(11, apptDateTime.length()-7);
                allTimesTaken.add(apptTime);
            }   
        } catch (SQLException ex) {
            String exString = ex.toString();
            System.out.println(exString);
        }
        
        int startInt = Integer.parseInt(startTime.substring(0,2));
        int endInt = Integer.parseInt(endTime.substring(0,2));
        
        for (int x = startInt; x <= endInt; x++) {
            String timeString;
            if (x < 10) {
                timeString = "0" + String.valueOf(x) + ":00:00";
            } else {
                timeString = String.valueOf(x) + ":00:00";
            }
            allTimesForDay.add(timeString);
        }
        
        
        
        for (int x = 0; x < allTimesForDay.size(); x++) {
            boolean timeTaken = false;
            for (int y = 0; y < allTimesTaken.size(); y++) {
                if (allTimesForDay.get(x).equals(allTimesTaken.get(y))) {
                    timeTaken = true;
                }
            }
            if (!timeTaken) {
                times.add(allTimesForDay.get(x));
            }
        }
        
        return times;
    }
    
    
    
    public ArrayList<Customer> getCustomers() {
        ArrayList<Customer> customers = new ArrayList<Customer>();
        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("Select email FROM Customers;");
            while (rs.next()){
                String email = rs.getString(1);
                Customer customer = new Customer(email);
                customers.add(customer);
                
            }   
        } catch (SQLException ex) {
            System.out.println("SQL error getting appointments");
            System.out.println(ex.toString());
        }
        return customers;
    }

    public ArrayList<Appointment> getAppointmentsforDate(String date) {
        ArrayList<Appointment> appointments = new ArrayList<Appointment>();
        try {
            Statement s = con.createStatement();
            String query = "SELECT appointmentID FROM Appointments WHERE (employeeID = \"" + this.employeeID + "\" AND (apptDateTime>=#" + date + " 01:00:00 AM# AND apptDateTime<=#" + date + " 11:59:00 PM#));";
            ResultSet rs = s.executeQuery(query);
            while (rs.next()){
                String appointmentID = rs.getString(1);
                Appointment appointment = new Appointment(appointmentID);
                appointments.add(appointment);
                
            }   
        } catch (SQLException ex) {
            System.out.println("SQL error getting appointments");
            String exString = ex.toString();
            System.out.println(exString);
        }
        return appointments;
    }
    
    public ArrayList<Appointment> getAppointmentsforCustomer(String customerID) {
        ArrayList<Appointment> appointments = new ArrayList<Appointment>();
        try {
            Statement s = con.createStatement();
            String query = "SELECT appointmentID FROM Appointments WHERE (employeeID = \"" + this.employeeID + "\" AND customerID = \"" + customerID + "\");";
            ResultSet rs = s.executeQuery(query);
            while (rs.next()){
                String appointmentID = rs.getString(1);
                Appointment appointment = new Appointment(appointmentID);
                appointments.add(appointment);
                
            }   
        } catch (SQLException ex) {
            System.out.println("SQL error getting appointments");
            String exString = ex.toString();
            System.out.println(exString);
        }
        return appointments;
    }
    
}