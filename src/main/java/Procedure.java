
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author ashto
 */
public class Procedure {

    private String procedureID;
    private String procedureName;
    private String procedureCost;
    private Connection con;

    public Procedure() {
        procedureID = "";
        procedureName = "";
        procedureCost = "";
        InitializeDB();
    }

    //I added a constructor that will create the object, get database access and info in one statement
    public Procedure(String procedureID) {
        InitializeDB();
        this.procedureID = procedureID;
        selectDB(procedureID);
    }

    public void selectDB(String procedureID) {
        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM Procedures WHERE ProcedureID = \"" + procedureID + "\";");
            while (rs.next()){
                this.procedureID = rs.getString(1);
                this.procedureName = rs.getString(2);
                this.procedureCost = rs.getString(3);
            }


        } catch (SQLException ex) {
            System.out.println("Failed to get procedure data");
        }
    }

    public void insertDB(String procedureID, String procedureName, String procedureCost) {
        try {
            Statement s = con.createStatement();
            s.executeUpdate("INSERT INTO Procedures VALUES (\"" + procedureID + "\", \"" + procedureName + "\", "+ procedureCost + ");");
        } catch (SQLException ex) {
            System.out.println("Failed to enter Procedure data");
        }
    }

    public void deleteDB(String procedureID) {
        try {
            Statement s = con.createStatement();
            s.executeUpdate("DELETE FROM Procedures WHERE procedureID = \"" + procedureID + "\";");
        } catch (SQLException ex) {
            System.out.println("Failed to delete procedure data");
            System.out.println(ex.toString());
        }
    }

    public void display() {
        System.out.println("procedure id: " + this.procedureID);
        System.out.println("procedure name: " + this.procedureName);
        System.out.println("procedure cost: " + this.procedureCost);
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