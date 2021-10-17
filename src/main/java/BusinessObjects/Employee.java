package BusinessObjects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
            }


        } catch (SQLException ex) {
            System.out.println("Failed to get employee data");
        }
    }

    public void insertDB(String employeeID, String firstName, String lastName, String phoneNumber, String password) {
        try {
            Statement s = con.createStatement();
            s.executeUpdate("INSERT INTO Employees VALUES (\"" + employeeID + "\", \"" + firstName + "\", \""+ lastName + "\", \"" + phoneNumber +"\", \"" + password + "\");");
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
    
    private String getEmployeeID() {
        return this.employeeID;
    }
    
    private String getFirstName() {
        return this.firstName;
    }
    
    private String getLastName() {
        return this.lastName;
    }
    
    private String getPassword() {
        return this.password;
    }
    
    private String getPhoneNumber() {
        return this.phoneNumber;
    }

}