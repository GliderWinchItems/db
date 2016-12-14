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
    
        // xxx
        //Try to connect to the specified database
        try{
            
            System.out.println("// Defines from database pcc");
            java.util.Date date= new java.util.Date();
            System.out.format("// %s\n",new Timestamp(date.getTime()));

//            System.out.println("#ifndef  DATABASE_DEFINES");
//            System.out.println("#define  DATABASE_DEFINES\n");

            System.out.format("public class PccFinal {\n");
            
            connection = DriverManager.getConnection(databaseConnectionName);
//            System.out.println("// Seems to have made the database connection\n");
            
            stmt = connection.createStatement();
            ResultSet rs;
            
            Integer totalct = 0;
            
            // Generate #defines for NUMBER_TYPE table
            totalct += gendefines_Canid(stmt);

            // Generate #defines for NUMBER_TYPE table
            totalct += gendefines_Number_type(stmt);
            
            // Generate #defines for CMD_CODES table
            totalct += gendefines_Cmd_codes(stmt);
            
            // Generate #defines for PAYLOAD_TYPE table
            totalct += gendefines_Payload_type(stmt);

            // Generate #defines for PARAM_LIST table
            totalct += gendefines_Param_List(stmt);

            // Generate #defines for READINGS_LIST table
            totalct += gendefines_Readings_List(stmt);
            
            // Generate #defines for FUNC_BIT_PARAM table
            totalct += gendefines_Func_Bit_Param(stmt);   
            
            // Generate #defines for FUNCTIONS_TYPE table
            totalct += gendefines_Functions_Type(stmt);      
            
            // Generate #defines for READINGS_BOARD table
            totalct += gendefines_Readings_Board(stmt);  

            // Generate #defines for MISC_SYS table
            totalct += gendefines_Misc_sys(stmt);      
            
            System.out.format("}\n");
                        
            System.out.format("\n/* TOTAL COUNT OF static final = %d  */\n",totalct);

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
   
        private static int gendefines_Canid(Statement stmt) throws SQLException{
            String query = "select * from CANID ";        
        
            ResultSet rs;
            rs = stmt.executeQuery(query);
            Integer count = 0;
            while (rs.next()) {
                long k = Long.parseLong(rs.getString("CANID_HEX"), 16); 
                if (((k & 0x001fff8)!= 0) && ((k & 0x4) == 0) ){
                    System.out.format("ERROR Illegal 11b CAN ID ct: %d %s %08X %s\n",count,
                        rs.getString("CANID_NAME"),
                        k,rs.getString("DESCRIPTION") );
                }
                if ((k & 0x1) != 0){
                    System.out.format("ERROR Illegal bit0 CAN ID ct: %d %s %08X %s\n",count,
                        rs.getString("CANID_NAME"),
                        k,rs.getString("DESCRIPTION") );
                }
                count += 1;
            }
            System.out.format("public final static int CANID_COUNT = %d;\n",count);
            
            rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                System.out.format("public static final int " + "%-24s",rs.getString("CANID_NAME"));
                System.out.format("= 0x%-8s;  ",        rs.getString("CANID_HEX"));
                System.out.format("// " + "%-15s: ", rs.getString("CANID_TYPE"));
                System.out.format("%s" + "\n",       rs.getString("DESCRIPTION"));
            }
            return count;
        }
         private static int gendefines_Number_type(Statement stmt) throws SQLException{
             String query = "select * from APP.NUMBER_TYPE ";        
        
            ResultSet rs;
            rs = stmt.executeQuery(query);
            Integer count = 0;
            while (rs.next()) {count += 1;}
            System.out.format("\npublic static final int NUMBER_TYPE_COUNT = %d;\n",count);
            
            rs = stmt.executeQuery(query);
            
             while (rs.next()) {
                 System.out.format("public static final int " + "%-24s",rs.getString("TYPE_NAME"));
                 System.out.format("= %d;\t",rs.getInt("TYPE_CODE"));
                 System.out.format("// " + "%-12s",rs.getString("TYPE_CT"));
                 System.out.format("%s" + "\n",rs.getString("DESCRIPTION9"));
             }
             return count;
         }
        private static int gendefines_Cmd_codes(Statement stmt) throws SQLException{
             String query = "select * from APP.CMD_CODES ";        
        
            ResultSet rs;
            rs = stmt.executeQuery(query);
            Integer count = 0;
            while (rs.next()) {count += 1;}
            System.out.format("\npublic static final int CMD_CODES_COUNT = %d;\n",count);
            
            rs = stmt.executeQuery(query);
            
             while (rs.next()) {
                 System.out.format("public static final int " + "%-24s",rs.getString("CMD_CODE_NAME"));
                 System.out.format("= %d;\t",rs.getInt("CMD_CODE_NUMBER"));
                 System.out.format("// " + "%-12s\n",rs.getString("DESCRIPTION4"));
             }
             return count;
         }
        private static int gendefines_Payload_type(Statement stmt) throws SQLException{
            String query = "select * from PAYLOAD_TYPE ";        
        
            ResultSet rs;
            rs = stmt.executeQuery(query);
            Integer count = 0;
            while (rs.next()) {count += 1;}
            System.out.format("\npublic static final int PAYLOAD_TYPE_COUNT = %d;\n",count);
            
            rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                System.out.format("public static final int " + "%-24s",rs.getString("PAYLOAD_TYPE_NAME"));
                System.out.format("= %d;\t",             rs.getInt("PAYLOAD_TYPE_CODE"));
                System.out.format("// " + "%-48s\n",     rs.getString("DESCRIPTION12"));
            }
            return count;
        }
        
       private static int gendefines_Param_List(Statement stmt) throws SQLException{
            String query = "select * from PARAM_LIST ";        
        
            ResultSet rs;
            rs = stmt.executeQuery(query);
            Integer count = 0;
            Integer count1 = 0;
            Integer flag = 0;
            while (rs.next()) {count += 1;}
            System.out.format("\npublic static final int PARAM_LIST_COUNT = %d;\t// TOTAL COUNT OF PARAMETER LIST\n\n",count);
            
            rs = stmt.executeQuery(query);
            String old = "";
            String tmp;
            
            while (rs.next()) {
                tmp = rs.getString("FUNCTION_TYPE");
                if (!(tmp .equals(old))){
                    if (flag == 0){
                        flag = 1;
                    }else{
                        System.out.format("\npublic static final int PARAM_LIST_CT_%s\t= %d;\t// Count of same FUNCTION_TYPE in preceding list\n\n",old, count1);
                        count1 = 0;
                    }
                }
                old = tmp;
                System.out.format("public static final int " + "%-24s\t",rs.getString("PARAM_NAME"));
                System.out.format("= %d;\t",                rs.getInt("PARAM_CODE"));
                System.out.format("// " + "%-48s\n",      rs.getString("DESCRIPTION10"));
                count1 += 1;
            }
            System.out.format("\npublic static final int PARAM_LIST_CT_%s\t= %d;\t// Count of same FUNCTION_TYPE in preceding list\n\n",old, count1);            
            return count;
        }    
       
       private static int gendefines_Readings_List(Statement stmt) throws SQLException{
            String query = "select * from READINGS_LIST ";        
        
            ResultSet rs;
            rs = stmt.executeQuery(query);
            Integer count = 0;
            while (rs.next()) {count += 1;}
            System.out.format("\npublic static final int READINGS_LIST_COUNT = %d;\n",count);
            
            rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                System.out.format("public static final int " + "%-24s\t",rs.getString("READINGS_NAME"));
                System.out.format("= %d;\t",                rs.getInt("READINGS_CODE"));
                System.out.format("// " + "%-48s\n",      rs.getString("DESCRIPTION16"));
            }
            return count;
        }   
       private static int gendefines_Func_Bit_Param(Statement stmt) throws SQLException{
            String query = "select * from FUNC_BIT_PARAM";        
        
            ResultSet rs;
            rs = stmt.executeQuery(query);
            Integer count = 0;
            while (rs.next()) {count += 1;}
            System.out.format("\npublic static final int FUNC_BIT_PARAM_COUNT = %d;\n",count);
            
            rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                System.out.format("public static final int " + "%-24s\t",rs.getString("FUNC_BIT_PARAM_NAME"));
                System.out.format("= %s;\t",                rs.getString("FUNC_BIT_PARAM_VAL"));
                System.out.format("// " + "%-20s",        rs.getString("FUNCTION_TYPE"));
                System.out.format("%-48s\n",              rs.getString("DESCRIPTION5"));
            }
            return count;
        } 
        private static int gendefines_CanUnit(Statement stmt) throws SQLException{
            String query = "select * from CAN_UNIT";        
        
            ResultSet rs;
            rs = stmt.executeQuery(query);
            Integer count = 0;
            while (rs.next()) {count += 1;}
            System.out.format("\npublic static final int CANUNIT_COUNT = %d;\n",count);
            
            rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                System.out.format("public static final int " + "%-24s\t",rs.getString("CAN_UNIT_NAME"));
                System.out.format("= %-10s;",                rs.getString("CAN_UNIT_CODE"));
                System.out.format("// " + "%-20s",        rs.getString("CANID_NAME"));
                System.out.format("%-48s\n",              rs.getString("DESCRIPTION3"));
            }
            return count;
        }    
        private static int gendefines_Functions_Type(Statement stmt) throws SQLException{
            String query = "select * from FUNCTIONS_TYPE";        
        
            ResultSet rs;
            rs = stmt.executeQuery(query);
            Integer count = 0;
            while (rs.next()) {count += 1;}
            System.out.format("\npublic static final int FUNCTION_TYPE_COUNT = %d;\n",count);
            
            rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                System.out.format("public static final int FUNCTION_TYPE_" + "%-24s\t",rs.getString("FUNCTION_TYPE"));
                System.out.format("= %d;\t",                rs.getInt("FUNCTION_TYPE_CODE"));
                System.out.format("// " + "%-48s\n",              rs.getString("DESCRIPTION8"));
            }
            return count;
        }  
        private static int gendefines_Readings_Board(Statement stmt) throws SQLException{
            String query = "select * from READINGS_BOARD";        
        
            ResultSet rs;
            rs = stmt.executeQuery(query);
            Integer count = 0;
            while (rs.next()) {count += 1;}
            System.out.format("\n#define READINGS_BOARD_COUNT = %d;\n",count);
            
            rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                System.out.format("public static final int " + "%-24s\t",rs.getString("READINGS_BOARDNAME"));
                System.out.format("= %d;\t",                rs.getInt("READINGS_BOARDCODE"));
                System.out.format("// " + "%-48s\n",              rs.getString("DESCRIPTION15"));
            }
            return count;
        }           
        private static int gendefines_Misc_sys(Statement stmt) throws SQLException{
            String query = "select * from MISC_SYS";        
        
            ResultSet rs;
            rs = stmt.executeQuery(query);
            Integer count = 0;
            while (rs.next()) {count += 1;}
            System.out.format("\npublic static final int MISC_SYS_COUNT = %d;\n",count);
            
            rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                System.out.format("public static final int  " + "%-24s =\t",rs.getString("MISC_SYS_NAME"));
                String s = rs.getString("MISC_SYS_FMT");    // Type of value (e.g. int, float)
                String u = rs.getString("MISC_SYS_VAL");
                switch (s)
                {
                case "TYPE_U32": // Unsigned int
  
                    Integer x = Integer.parseInt(u);
                    System.out.format("\t= %d;",x);
                    break;
                default:
                    System.out.format("\t%s // ### ERROR Value Conversion not made ####", u);
                    break;
                               
                }
                System.out.format("\t// ASCII value %-10s", rs.getInt("MISC_SYS_VAL"));
                System.out.format("%-48s\n",  rs.getString("DESCRIPTION_MISC_SYS"));
            }
            return count;
        }           
}