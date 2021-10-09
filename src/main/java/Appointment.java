
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
public class Appointment {
    
    private String appointmentID;
    private String apptDateTime;
    private String employeeID;
    private String procedureID;
    private String customerID;
    private Connection con;

    public Appointment() {
        appointmentID = "";
        apptDateTime = "";
        employeeID = "";
        procedureID = "";
        customerID = "";
        InitializeDB();
    }

    //I added a constructor that will create the object, get database access and info in one statement
    public Appointment(String appointmentID) {
        InitializeDB();
        this.appointmentID = appointmentID;
        selectDB(appointmentID);
    }

    public void selectDB(String appointmentID) {
        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM Appointments WHERE AppointmentID = \"" + appointmentID + "\";");
            while (rs.next()){
                this.appointmentID = rs.getString(1);
                this.apptDateTime = rs.getString(2);
                this.employeeID = rs.getString(3);
                this.procedureID = rs.getString(4);
                this.customerID = rs.getString(5);
            }


        } catch (SQLException ex) {
            System.out.println("Failed to get appointment data");
        }
    }

    public void insertDB(String appointmentID, String apptDateTime, String employeeID, String procedureID, String customerID) {
        try {
            Statement s = con.createStatement();
            s.executeUpdate("INSERT INTO Procedures VALUES (\"" + appointmentID + "\", \"" + apptDateTime + "\", \""+ employeeID + "\", \"" + procedureID + "\", \"" + customerID + "\");");
        } catch (SQLException ex) {
            System.out.println("Failed to enter appointment data");
        }
    }

    public void deleteDB(String procedureID) {
        try {
            Statement s = con.createStatement();
            s.executeUpdate("DELETE FROM Appointments WHERE AppointmentID = \"" + appointmentID + "\";");
        } catch (SQLException ex) {
            System.out.println("Failed to delete procedure data");
            System.out.println(ex.toString());
        }
    }

    public void display() {
        System.out.println("Appointment id: " + this.appointmentID);
        System.out.println("DateTime: " + this.apptDateTime);
        System.out.println("EmployeeID: " + this.employeeID);
        System.out.println("Procedure ID: " + this.procedureID);
        System.out.println("Customer ID: " + this.customerID);
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
    
    private String getAppointmentID() {
        return this.appointmentID;
    }
    
    private String getAppointmentDateTime() {
        return this.apptDateTime;
    }
    private String getCustomerID() {
        return this.customerID;
    }
    private String getEmployeeID() {
        return this.employeeID;
    }
    private String getProcedureID() {
        return this.procedureID;
    }
}
