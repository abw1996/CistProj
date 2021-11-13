/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessObjects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    

/**
 *
 * @author ashto
 */
public class ScheduledDay {
    
    private String date;
    private String employeeID;
    private String startTime;
    private String endTime;
    private Connection con;
    
    public ScheduledDay() {
        InitializeDB();
    }
    
    public ScheduledDay(String date, String employeeID, String startTime, String endTime) {
        InitializeDB();
        this.date = date;
        this.employeeID = employeeID;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    
    public void insertDB(String date, String employeeID, String startTime, String endTime) {
        try {
            Statement s = con.createStatement();
            String query = "INSERT INTO Schedule(day,employeeID,startTime,endTime)VALUES(#" + date + "#,\"" + employeeID + "\",#"+ startTime + "#,#" + endTime + "#);";
            s.executeUpdate(query);
            System.out.println("Update sent");
        } catch (SQLException ex) {
            String error = ex.toString();
            System.out.println(error);
        }
    }
    
    public ArrayList<ScheduledDay> getDays() {
        ArrayList<ScheduledDay> days = new ArrayList<ScheduledDay>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");  
        LocalDateTime now = LocalDateTime.now();
        String today = dtf.format(now);
        System.out.println("");
        try {
            Statement s = con.createStatement();
            String query = "Select * FROM Schedule WHERE day >= #" + today + "# ORDER BY day ASC;";
            ResultSet rs = s.executeQuery(query);
            while (rs.next()){
                String date = rs.getString(1);
                String employeeID = rs.getString(2);
                String startTime = rs.getString(3);
                String endTime = rs.getString(4);
                ScheduledDay day = new ScheduledDay(date, employeeID, startTime, endTime);
                days.add(day);
                
            }   
        } catch (SQLException ex) {
            System.out.println("SQL error getting appointments");
            System.out.println(ex.toString());
        }
        
        
        return days;
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


    public String getDate(){
        return this.date;
    }
    
    public String getEmployeeID(){
        return this.employeeID;
    }
    
    public String getStartTime(){
        return this.startTime;
    }
    
    public String getEndTime(){
        return this.endTime;
    }
}
