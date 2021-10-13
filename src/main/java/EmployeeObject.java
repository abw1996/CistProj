/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author dpizo
 */
public class EmployeeObject {
    private String employeeID, firstName, lastName, employeePN, employeePW;
    //public List list = new List();
    
    public EmployeeObject() {
        employeeID = "";
        firstName = "";
        lastName = "";
        employeePN = "";
        employeePW = "";
    }
    
    public EmployeeObject(String id, String fName, String lName, String pn, String pw) {
        employeeID = id;
        firstName = fName;
        lastName = lName;
        employeePN = pn;
        employeePW = pw; 
    }
    
    public void setID(String id) {
        employeeID = id;
    }
    public String getID() {
        return employeeID;
    }
    public void setFName(String fName) {
        firstName = fName;
    }
    public String getFName() {
        return firstName;
    }
    
    public void setLName(String lName) {
        lastName = lName;
    }
    public String getLName() {
        return lastName;
    }
    
    public void setPN(String pn) {
        employeePN = pn;
    }
    public String getPN() {
        return employeePN;
    }
    
    public void setPW(String pw) {
        employeePW = pw;
    }
    public String getPW() {
        return employeePW;
    }
    
    public void display() {
        System.out.println("Employee ID = " + getID());
        System.out.println("Employee First Name = " + getFName());
        System.out.println("Employee Last Name = " + getLName());
        System.out.println("Employee PN = " + getPN());
        System.out.println("Employee Password = " + getPW());
        //list.displayList();
    }
    
    public void selectDB(String id) {
        employeeID = id;
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://" + "C://Database//SalonDB.mdb");
            Statement stmt = con.createStatement();
            String sql = "Select * from Employees where employeeID ='"+getID()+"'";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            setFName(rs.getString(2));
            setLName(rs.getString(3));
            setPN(rs.getString(4));
            setPW(rs.getString(5));
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void insertDB(String id, String fName, String lName, String pn, String pw) {
        employeeID = id;
        firstName = fName;
        lastName = lName;
        employeePN = pn;
        employeePW = pw;
        
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://" + "C://Database//SalonDB.mdb");
            Statement stmt = con.createStatement();
            String sql = "Insert into Employees values('"+getID()+"',"+
                    "'"+getFName()+"',"+
                    "'"+getLName()+"',"+
                    "'"+getPN()+"',"+
                    "'"+getPW()+"')";
            System.out.println(sql);
            int n1 = stmt.executeUpdate(sql);
            if (n1 == 1) 
                System.out.println("Insert Sucessful!");
            else
                System.out.println("Insert Fail!");
        }
        catch (Exception e1) {
            System.out.println(e1);
        }
    }
    
    public void updateDB() {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://" + "C://Database//SalonDB.mdb");
            Statement stmt = con.createStatement();
            String sql = "Update Employees set employeeID = '"+getID()+"',"+
                    " firstName = '"+getFName()+"',"+
                    " lastName = '"+getLName()+"',"+
                    " employeePN = '"+getPN()+"',"+
                    " employeePW = '"+getPW()+"',"+
                    " WHERE employeeID=' "+getID()+"'";
            System.out.println(sql);
            int n = stmt.executeUpdate(sql);
            if (n == 1)
                System.out.println("Update Succssful!");
            else
                System.out.println("Update Failed");
            con.close();
        }
        catch (Exception ex) {
            System.out.print(ex);
        }
    }
    
    public void deleteDB() {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://" + "C://Database//SalonDB.mdb");
            Statement stmt = con.createStatement();
            String sql = "Delete from Employees where employeeID='"+getID()+"'";
            System.out.println(sql);
            int n = stmt.executeUpdate(sql);
            if(n == 1)
                System.out.println("Delete Sucessful!");
            else
                System.out.println("Delete Failed");
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public static void main(String args[]) {
        EmployeeObject employee = new EmployeeObject();
        employee.selectDB("E1");
        employee.display();
        //list.addA
    }
}


