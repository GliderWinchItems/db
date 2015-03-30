/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package derbytest;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.management.remote.JMXConnectorFactory.connect;


/**
 *
 * @author deh
 */
public class Derbytest {

        private static String driverName = "org.apache.derby.jdbc.EmbeddedDriver";
        private static String clientDriverName = "org.apache.derby.jdbc.ClientDriver";
        private static String databaseConnectionName = "jdbc:derby://localhost:1527/pcc;create=true";
    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        

        Canid canid = new Canid();
        
        Connection connection = null;
        PreparedStatement pstmt = null;
        Statement stmt = null;
        Statement stmt2 = null;//        String query = "SELECT * FROM APP.CANID "
//                + "WHERE CANID_HEX = 'D1C0000C' ";
//        String query = "select * from APP.CANID "
  //      + "WHERE CANID_TYPE = 'LAUNCH' "
    //    + "ORDER BY CANID_TYPE DESC, CANID_HEX ASC";
      
        // List of CAN units and
       String queryA = 
"-- CAN loader info\n" +
"SELECT CAN_UNIT_NAME, CANID_HEX, PROG_PATH, SKIP, CAN_UNIT.DESCRIPTION \n" +
"FROM CAN_UNIT\n" +
"  JOIN CANID\n" +
"    ON CAN_UNIT.CANID_NAME = CANID.CANID_NAME\n" +
"       ORDER BY CAN_UNIT_NAME"               ;    
        
        // List of CAN units and
       String queryB = 
 "-- CMD canids by unit name and function\n" +
"SELECT FUNCTIONS.*, CANID_HEX\n" +
"FROM FUNCTIONS\n" +
"  JOIN CANID\n" +
"    ON CANID.CANID_NAME = FUNCTIONS.CANID_CMD_NAME\n" +
"    ORDER BY FUNCTION_NAME DESC\n"     ;              
    
       
       // Some header stuff
       System.out.println("# LIST FOR CAN PROGRAM LOADER");
       System.out.println("#   Lines starting with--");
       System.out.println("# U - New CAN Unit line");
       System.out.println("# M - CANIDs for function command associated with above CAN UNIT");
       System.out.println("# Headings for the types of lines--");       
       System.out.println("# U CAN_UNIT_NAME\t\t  CAN ID           PROG_PATH_NAME        SKIP            DESCRIPTION");
       System.out.println("# M    FUNCTION_NAME\t\t  CAN ID          FUNCTION_TYPE                 FUNCTION DESCRIPTION\n");

       

//Check for DB drivers
        try{
            Class.forName(clientDriverName);
            Class.forName(driverName);
        }catch(java.lang.ClassNotFoundException e) {
            System.out.println("client Driver Name, or driver Name exception\n");
            throw e;
        }
    
        //Try to connect to the specified database
        try{
            connection = DriverManager.getConnection(databaseConnectionName);
//            System.out.println("// Seems to have made the database connection!\n");
            
            stmt = connection.createStatement();         
            stmt2 = connection.createStatement();                     

            ResultSet rs = stmt.executeQuery(queryA);
            while (rs.next()) {
                // CAN UNIT, PROG_PATH
                 String canid_name = rs.getString("CAN_UNIT_NAME");
                 System.out.format("U  " + "%-24s\t",canid_name);
                 System.out.format("  %-10s",  rs.getString("CANID_HEX"));
                 System.out.format("  %-10s",  rs.getString("PROG_PATH"));
                 System.out.format("  %-12s",  rs.getString("SKIP"));
                 System.out.format("%s" + "\n",rs.getString("DESCRIPTION"));
                 
                 ResultSet rs2 = stmt2.executeQuery(queryB);
                 while (rs2.next()) {
                     String c1 = rs2.getString("CAN_UNIT_NAME");
//System.out.format("A " + "%s" + " %s\n",canid_name, c1);
                     if (c1.equals(canid_name)){
//System.out.format("A " + "%s" + " %s\n",canid_name, c1);                         
                         System.out.format("M     " + "%-24s\t", rs2.getString("FUNCTION_NAME"));
                         System.out.format("  %-10s",          rs2.getString("CANID_HEX"));
                         System.out.format("%-24s\t",        rs2.getString("FUNCTION_TYPE"));
                         System.out.format("%s\n",rs.getString("DESCRIPTION"));

                     }
                 }
            }
 
             
        }catch(SQLException e) {
            //TODO Fix error handling
            e.printStackTrace();
            throw e;
 
        }
        
        
        finally {
            if (pstmt != null) pstmt.close();
        }
    }
   
}