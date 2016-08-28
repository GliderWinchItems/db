/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package derbytest;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.management.remote.JMXConnectorFactory.connect;

/**
 *
 * @author deh
 */
public class Pcc_defines {

        private static String driverName = "org.apache.derby.jdbc.EmbeddedDriver";
        private static String clientDriverName = "org.apache.derby.jdbc.ClientDriver";
        private static String databaseConnectionName = "jdbc:derby://localhost:1527/pcc";
        private static Object LocalDateTime;
    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
             
        Connection connection = null;
        PreparedStatement pstmt = null;
        Statement stmt = null;

        
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
            
            System.out.println("List of CANIDs sorted by HEX ");
            java.util.Date date= new java.util.Date();
            System.out.format("// %s\n",new Timestamp(date.getTime()));
            
            connection = DriverManager.getConnection(databaseConnectionName);
//            System.out.println("// Seems to have made the database connection\n");
            
            stmt = connection.createStatement();
            ResultSet rs;
            
            Integer totalct = 0;
            
            
            // Generate #defines for READINGS_BOARD table
            genlist_Canid(stmt);         
            
 
        }
        catch(SQLException e) {
            //TODO Fix error handling
            e.printStackTrace();
            throw e;
 
        }
        
        finally {
            if (pstmt != null) pstmt.close();
        }
    }
   
        private static void genlist_Canid(Statement stmt) throws SQLException{
/*
SELECT 
    CANID.*,
    PAYLOAD_TYPE.PAYLOAD_TYPE_CODE,
    PAYLOAD_TYPE.DESCRIPTION
FROM CANID
JOIN PAYLOAD_TYPE 
    ON PAYLOAD_TYPE.PAYLOAD_TYPE_NAME = CANID.CAN_MSG_FMT
ORDER BY CANID.CANID_HEX;
*/      
       String query = "select CANID.*,PAYLOAD_TYPE.PAYLOAD_TYPE_CODE,PAYLOAD_TYPE.DESCRIPTION  FROM CANID \n "
        + "JOIN PAYLOAD_TYPE \n"
               + "ON PAYLOAD_TYPE.PAYLOAD_TYPE_NAME = CANID.CAN_MSG_FMT \n"
               + "ORDER BY CANID.CANID_HEX";
        
            ResultSet rs;
            rs = stmt.executeQuery(query);
            
            int count = 0;
            
            while (rs.next()) {
                System.out.format("%-24s",rs.getString("CANID_NAME"));
                System.out.format(" 0x%-10s",             rs.getString("CANID_HEX"));
                System.out.format("// " + "%-15s: ",     rs.getString("CANID_TYPE"));
                System.out.format("%s" + "\n",         rs.getString("DESCRIPTION"));
                count += 1;
            }
                       System.out.format("\n/* TOTAL COUNT OF #defines = %d  */\n",count);
        }
}