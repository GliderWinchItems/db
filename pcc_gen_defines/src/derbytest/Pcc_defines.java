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

        private static final String driverName = "org.apache.derby.jdbc.EmbeddedDriver";
        private static final String clientDriverName = "org.apache.derby.jdbc.ClientDriver";
        private static final String databaseConnectionName = "jdbc:derby://localhost:1527/pcc;create=true";
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
//        String query = "SELECT * FROM APP.CANID "
//                + "WHERE CANID_HEX = 'D1C0000C' ";
//        String query = "select * from APP.CANID "
  //      + "WHERE CANID_TYPE = 'LAUNCH' "
    //    + "ORDER BY CANID_TYPE DESC, CANID_HEX ASC";
      
       String query = "select * from APP.PARAM_LIST "
        + "ORDER BY FUNCTION_TYPE";        
        
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
            
            System.out.println("// Defines from database pcc");
            java.util.Date date= new java.util.Date();
            System.out.format("// %s\n",new Timestamp(date.getTime()));

            System.out.println("#ifndef  DATABASE_DEFINES");
            System.out.println("#define  DATABASE_DEFINES\n");

            
            connection = DriverManager.getConnection(databaseConnectionName);
//            System.out.println("// Seems to have made the database connection\n");
            
            stmt = connection.createStatement();
            ResultSet rs;
            
            // Generate #defines for NUMBER_TYPE table
            gendefines_Canid(stmt);

            // Generate #defines for NUMBER_TYPE table
            gendefines_Number_type(stmt);
            
            // Generate #defines for CMD_CODES table
            gendefines_Cmd_codes(stmt);
            
            // Generate #defines for PAYLOAD_TYPE table
            gendefines_Payload_type(stmt);
                     
           // Generate #defines for PARAM_TYPE table
            gendefines_Param_List(stmt);
            
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
   
        private static void gendefines_Canid(Statement stmt) throws SQLException{
            String query = "select * from CANID ";        
        
            ResultSet rs;
            rs = stmt.executeQuery(query);
            Integer count = 0;
            while (rs.next()) {count += 1;}
            System.out.format("\n#define CANID_TOTAL_COUNT %d\n",count);
            
            rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                System.out.format("#define  " + "%-24s",rs.getString("CANID_NAME"));
                System.out.format("%-10s",             rs.getString("CANID_HEX"));
                System.out.format("// " + "%-15s: ",     rs.getString("CANID_TYPE"));
                System.out.format("%s" + "\n",         rs.getString("DESCRIPTION"));
            }
        }
         private static void gendefines_Number_type(Statement stmt) throws SQLException{
             String query = "select * from APP.NUMBER_TYPE ";        
        
            ResultSet rs;
            rs = stmt.executeQuery(query);
            Integer count = 0;
            while (rs.next()) {count += 1;}
            System.out.format("\n#define NUMBER_TYPE_TOTAL_COUNT %d\n",count);
            
            rs = stmt.executeQuery(query);
            
             while (rs.next()) {
                 System.out.format("#define  " + "%-24s",rs.getString("TYPE_NAME"));
                 System.out.format("%-10s",rs.getString("TYPE_CODE"));
                 System.out.format("// " + "%-12s",rs.getString("TYPE_CT"));
                 System.out.format("%s" + "\n",rs.getString("DESCRIPTION"));
             }
         }
        private static void gendefines_Cmd_codes(Statement stmt) throws SQLException{
             String query = "select * from APP.CMD_CODES ";        
        
            ResultSet rs;
            rs = stmt.executeQuery(query);
            Integer count = 0;
            while (rs.next()) {count += 1;}
            System.out.format("\n#define CMD_CODES_TOTAL_COUNT %d\n",count);
            
            rs = stmt.executeQuery(query);
            
             while (rs.next()) {
                 System.out.format("#define  " + "%-24s",rs.getString("CMD_CODE_NAME"));
                 System.out.format("%-10s",rs.getString("CMD_CODE_NUMBER"));
                 System.out.format("// " + "%-12s\n",rs.getString("DESCRIPTION"));
             }
         }
        private static void gendefines_Payload_type(Statement stmt) throws SQLException{
            String query = "select * from PAYLOAD_TYPE ";        
        
            ResultSet rs;
            rs = stmt.executeQuery(query);
            Integer count = 0;
            while (rs.next()) {count += 1;}
            System.out.format("\n#define PAYLOAD_TYPE_TOTAL_COUNT %d\n",count);
            
            rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                System.out.format("#define  " + "%-24s",rs.getString("PAYLOAD_TYPE_NAME"));
                System.out.format("%-10s",             rs.getString("PAYLOAD_TYPE_CODE"));
                System.out.format("// " + "%-48s\n",     rs.getString("DESCRIPTION"));
            }
        }
        private static void gendefines_Param_List(Statement stmt) throws SQLException{
            String query = "select * from PARAM_LIST ";        
        
            ResultSet rs;
            rs = stmt.executeQuery(query);
            Integer count = 0;
            while (rs.next()) {count += 1;}
            System.out.format("\n#define PARAM_LIST_TOTAL_COUNT %d\n",count);
            
            rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                System.out.format("#define  " + "%-24s", rs.getString("PARAM_NAME"));
                System.out.format("%-10s",               rs.getString("PARAM_CODE"));
                System.out.format("\t// %-14s",               rs.getString("FUNCTION_TYPE"));
                System.out.format("%-48s\n",     rs.getString("DESCRIPTION"));                
            }
        }
}