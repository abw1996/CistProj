
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
public class Customer {

    private String customerID;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String password;
    private Connection con;

    public Customer() {
        customerID = "";
        firstName = "";
        lastName = "";
        phoneNumber = "";
        email = "";
        password = "";
        InitializeDB();
    }

    //I added a constructor that will create the object, get database access and info in one statement
    public Customer(String customerID) {
        InitializeDB();
        this.customerID = customerID;
        selectDB(customerID);
    }

    public void selectDB(String customerID) {
        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM Customers WHERE CustomerID = \"" + customerID + "\";");
            while (rs.next()){
                this.customerID = rs.getString(1);
                this.firstName = rs.getString(2);
                this.lastName = rs.getString(3);
                this.phoneNumber = rs.getString(4);
                this.email = rs.getString(5);
                this.password = rs.getString(6);
            }


        } catch (SQLException ex) {
            System.out.println("Failed to get customer data");
        }
    }

    public void insertDB(String customerID, String firstName, String lastName, String phoneNumber, String email, String password) {
        try {
            Statement s = con.createStatement();
            s.executeUpdate("INSERT INTO Customers VALUES (\"" + customerID + "\", \"" + firstName + "\", \""+ lastName + "\", \"" + phoneNumber + "\", \"" + email + "\", \"" + password + "\");");
        } catch (SQLException ex) {
            System.out.println("Failed to insert customer data");
            System.out.println(ex.toString());
        }
    }

    public void deleteDB(String customerID) {
        try {
            Statement s = con.createStatement();
            s.executeUpdate("DELETE FROM Customers WHERE customerID = \"" + customerID + "\";");
        } catch (SQLException ex) {
            System.out.println("Failed to delete customer data");
            System.out.println(ex.toString());
        }
    }

    public void display() {
        System.out.println("customer id: " + this.customerID);
        System.out.println("customer first name: " + this.firstName);
        System.out.println("customer lastname: " + this.lastName);
        System.out.println("customer Phone Number: " + this.phoneNumber);
        System.out.println("customer email: " + this.email);
        System.out.println("customer pw: " + this.password);
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
    
    private String getCustomerID() {
        return this.customerID;
    }
    
    private String getFirstName() {
        return this.firstName;
    }
    
    private String getLastName() {
        return this.lastName;
    }
    
    private String getEmail() {
        return this.email;
    }
    
    private String getPassword() {
        return this.password;
    }
    
    private String getPhoneNumber() {
        return this.phoneNumber;
    }
}