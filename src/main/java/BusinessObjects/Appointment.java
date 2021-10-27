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
            ResultSet rs = s.executeQuery("SELECT * FROM Appointments WHERE appointmentID = \"" + appointmentID + "\";");
            while (rs.next()){
                this.appointmentID = rs.getString(1);
                this.apptDateTime = rs.getString(2);
                this.employeeID = rs.getString(3);
                this.procedureID = rs.getString(4);
                this.customerID = rs.getString(5);
                System.out.println();
            }


        } catch (SQLException ex) {
            System.out.println("Failed to get appointment data");
        }
    }

    public void insertDB(String appointmentID, String apptDateTime, String employeeID, String procedureID, String customerID) {
        try {
            Statement s = con.createStatement();
            String query = "INSERT INTO Appointments VALUES (\"" + appointmentID + "\", \"" + apptDateTime + "\", \""+ employeeID + "\", \"" + procedureID + "\", \"" + customerID + "\");";
            s.executeUpdate(query);
            System.out.println("Update sent");
        } catch (SQLException ex) {
            String error = ex.toString();
            System.out.println(error);
        }
    }

    public void deleteDB(String appointmentID) {
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
    
    public String getAppointmentID() {
        return this.appointmentID;
    }
    
    public String getAppointmentDateTime() {
        return this.apptDateTime;
    }
    public String getCustomerID() {
        return this.customerID;
    }
    public String getEmployeeID() {
        return this.employeeID;
    }
    public String getProcedureID() {
        return this.procedureID;
    }
    
    public void setAppointmentID(String id) {
        appointmentID = id;
    }
    
    public void setAppointmentDateTime(String appt) {
        apptDateTime = appt;
    }
    
    public void setEmployeeID(String id) {
        employeeID = id;
    }
    
    public void setCustomerID(String id) {
        customerID = id;
    }
    
    public void setProcedureID(String code) {
        procedureID = code;
    }
    
    public String createID() {
        int highest = 0;
        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT appointmentID FROM Appointments;");
            while (rs.next()){
                int x = Integer.parseInt(rs.getString(1));
                if (x > highest) {
                    highest = x;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Failed to get appointment data");
        }
        highest += 1;
        String returnValue = String.valueOf(highest);
        int count = 6 - returnValue.length();
        for (int x = 0; x < count; x++) {
            returnValue = "0" + returnValue;
        }
        return returnValue;
    }
    
    public void updateDB(String appointmentID, String apptDateTime, String employeeID, String procedureID) {
        try {
            Statement s = con.createStatement();
            String query = "Update Appointments SET apptDateTime= \"" + apptDateTime + "\", employeeID= \"" + employeeID + "\",procedureID= \"" + procedureID + "\" WHERE appointmentID = \""+ appointmentID + "\";";
            s.executeUpdate(query);
            System.out.println("Update sent");
        } catch (SQLException ex) {
            String error = ex.toString();
            System.out.println(error);
        }
    }
    
    
}
