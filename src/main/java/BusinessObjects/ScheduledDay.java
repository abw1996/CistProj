/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessObjects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ashto
 */
public class ScheduledDay {
    
    private Connection con;
    
    public ScheduledDay() {
        InitializeDB();
    }
    
    public void insertDB(String date, String employeeID, String startTime, String endTime) {
        try {
            Statement s = con.createStatement();
            String query = "INSERT INTO Schedule (day, employeeID, startTime, endTime) VALUES (\"" + date + "\", \"" + employeeID + "\", \""+ startTime + "\", \"" + endTime + "\");";
            s.executeUpdate(query);
            System.out.println("Update sent");
        } catch (SQLException ex) {
            String error = ex.toString();
            System.out.println(error);
        }
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
    
}
