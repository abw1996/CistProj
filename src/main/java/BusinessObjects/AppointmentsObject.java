package BusinessObjects;
import java.sql.*;
/**
 *
 * @author dpizo
 */
/******************************************************************
* The Appointment class is used to hold Appointments
 ******************************************************************/
public class AppointmentsObject {
    
    String appointmentID, apptDateTime,employeeID, customerID, procCode;
    
    public AppointmentsObject() {
        appointmentID = "";
        apptDateTime = "";
        employeeID = "";
        customerID = "";
        procCode = "";
    }
    
    public AppointmentsObject(String id, String appt, String empID, String custID, String proc) {
        appointmentID = id;
        apptDateTime = appt;
        employeeID = empID;
        customerID = custID;
        procCode = proc;
    }
    
    public void setAppointmentID(String id) {
        appointmentID = id;
    }
    public String getAppointmentID() {
        return appointmentID;
    }
    
    public void setAppointmentDateTime(String appt) {
        apptDateTime = appt;
    }
    public String getAppointmentDateTime() {
        return apptDateTime;
    }
    
    public void setEmployeeID(String id) {
        employeeID = id;
    }
    public String getEmployeeID() {
        return employeeID;
    }
    
    public void setCustomerID(String id) {
        customerID = id;
    }
    public String getCustomerID() {
        return customerID;
    }
    
    public void setProcedureID(String code) {
        procCode = code;
    }
    public String getProcedureID() {
        return procCode;
    }
    
    public void display() {
        System.out.println("Appointment ID: " + getAppointmentID());
        System.out.println("Appointment Time: " + getAppointmentDateTime());
        System.out.println("Employee's ID: " + getEmployeeID());
        System.out.println("Customer's ID: " + getCustomerID());
        System.out.println("ProcCode : " + getProcedureID());
    }
    
    public void selectDB(String id) {
        appointmentID = id;
        try {
           Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
           Connection con = DriverManager.getConnection("jdbc:ucanaccess://" + "C://Database//SalonDB.mdb");
           Statement stmt = con.createStatement();
           String sql = "Select * from Appointments where appointmentID ='"+getAppointmentID()+"'";
           System.out.println(sql);
           ResultSet rs = stmt.executeQuery(sql);
           rs.next();
           setAppointmentDateTime(rs.getString(1));
           setEmployeeID(rs.getString(2));
           setCustomerID(rs.getString(3));
           setProcedureID(rs.getString(4));
        }
        catch (Exception e1) {
            System.out.println(e1);
        }
    }
    
    public void insertDB(String id, String dateTime, String empID, String custID, String code) {
        appointmentID = id;
        apptDateTime = dateTime;
        employeeID = empID;
        customerID = custID;
        procCode = code;
        
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://" + "C://Database//SalonDB.mdb");
            Statement stmt = con.createStatement();
            String sql = "Insert into Appointments values('"+getAppointmentID()+"',"+
                    "'"+getAppointmentDateTime()+"',"+
                    "'"+getEmployeeID()+"',"+
                    "'"+getCustomerID()+"',"+
                    "'"+getProcedureID()+"')";
            System.out.println(sql);
            int n1 = stmt.executeUpdate(sql);
            if (n1 == 1)
                System.out.println("Insert Sucessful!");
            else
                System.out.println("Insert Failed!");
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
            String sql = "Update Appointments set appointmentID = '"+getAppointmentID()+"',"+
                    " apptDateTime = '"+getAppointmentDateTime()+"',"+
                    " employeeID = '"+getEmployeeID()+"',"+
                    " customerID = '"+getCustomerID()+"',"+
                    " procCode = '"+getProcedureID()+"'"+
                    " WHERE appointmentID='"+getAppointmentID()+"'";
            System.out.println(sql);
            int n = stmt.executeUpdate(sql);
            if (n ==1)
                System.out.println("Update Successful!");
            else
                System.out.println("Update Failed!");
            con.close();
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void deleteDB() {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://" + "C://Database//SalonDB.mdb");
            Statement stmt = con.createStatement();
            String sql = "Delete from Appointments where appointmentID='"+getAppointmentID()+"'";
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
        AppointmentsObject appt = new AppointmentsObject();
        //appt.selectDB("1")
        //appt.insertDB("2", "dec5", "E444", "C2222", "C45");
        appt.selectDB("1");
        //appt.setProcedureID("oioi");
        appt.setProcedureID("Olaaaa");
        appt.updateDB();
        
    }
}