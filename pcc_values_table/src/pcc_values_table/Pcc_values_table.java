/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pcc_values_table;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.Boolean.TRUE;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

/**
 *
 * @author deh
 */
public class Pcc_values_table {
        private static String driverName = "org.apache.derby.jdbc.EmbeddedDriver";
        private static String clientDriverName = "org.apache.derby.jdbc.ClientDriver";
        private static String databaseConnectionName = "jdbc:derby://localhost:1527/pcc";
        private static Object LocalDateTime;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {

        
        Connection connection = null;
        PreparedStatement pstmt = null;
        Statement stmt = null;

        Connection connection2 = null;
        PreparedStatement pstmt2 = null;
        Statement stmt2 = null; 
       
       String query = "SELECT "
        + "PARAM_VAL.FUNCTION_NAME,"
        + "PARAM_LIST.PARAM_CODE,"
        + "PARAM_VAL.PARAM_VAL,"
        + "NUMBER_TYPE.TYPE_CODE,"
        + "PARAM_LIST.PARAM_NAME, " 
        + "PARAM_VAL.DESCRIPTION11 \n"
        + "FROM PARAM_LIST \n"
            + "JOIN PARAM_VAL \n"
                + "ON PARAM_LIST.PARAM_NAME = PARAM_VAL.PARAM_NAME\n"
                    + "JOIN NUMBER_TYPE \n"
                        + "ON PARAM_LIST.TYPE_NAME = NUMBER_TYPE.TYPE_NAME \n"
        + "ORDER BY PARAM_VAL.FUNCTION_NAME, PARAM_LIST.PARAM_CODE";
       
        
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
            
            java.util.Date date= new java.util.Date();
            System.out.format("// %s\n\n",new Timestamp(date.getTime()));

            connection = DriverManager.getConnection(databaseConnectionName);
//            System.out.println("// Seems to have made the database connection\n");
            
            stmt = connection.createStatement();
            
            connection2 = DriverManager.getConnection(databaseConnectionName);
//          System.out.println("// Seems to have made the database connection2\n");
            stmt2 = connection2.createStatement();
            
//            ScriptRunner sr = new ScriptRunner(connection,TRUE,TRUE);
//           sr.runScript(new BufferedReader(new FileReader("/home/deh/svn_common/trunk/db/LOAD_PCC_DB.sql")));
                     
            Integer totalct = 0;
        
            ResultSet rs;            
            rs = stmt.executeQuery(query);
            Integer count = 0;
            while (rs.next()) {count += 1;}
//            System.out.format("\n#define PARAM_VAL_COUNT %d\t// total count\n",count);
// ========= Make param counts ===============================
            System.out.format("// =========== PARAMETER ARRAY/TABLE SIZES ============================\n");
            System.out.format("// Note: The instances of the same function types should have the same size. \n");
            System.out.format("//        If not, then some is wrong with the PARAMETERS_VAL_INSERT. \n\n");
            Integer flag = 0; 
            Integer count1 = 0;            
            String tmp = "";
            String old = "";
String tmpsave = "";
            rs = stmt.executeQuery(query);
            while (rs.next()) {               
                tmp = rs.getString("FUNCTION_NAME");
                /* Skip 'size' on first time */
                if (!(tmp .equals(old))){
                    if (flag == 0){
                        flag = 1;
                    }else{
                        System.out.format("#define %s_PARAM_SIZE %d\n",old,count1);
                        count1 = 0;
                    }              
                    old = tmp;
                }
                count1 += 1;    
            }

            System.out.format("#define %s_PARAM_SIZE %d\n\n",old,count1);
            
//            System.out.format("\n// ======================= SELECTION DEFINES ==========================\n"); 
//            System.out.format("/* The following #include is located in the directory with the app program and"
//       + "\n      selects the arrays for that particular app from the following which includes all.*/\n ");
//System.out.format("// #include \"select_function.h\"\t// A #define selects from the following two array/table groups.\n"
//       + "//\t\t\t\t   1) parameters, and 2) unit/command canid\n\n");         
// ========= Make param table ===============================            
            flag = 0; 
            count1 = 0; count = 0;  
            old = "";
            rs = stmt.executeQuery(query);
            while (rs.next()) {               
                tmp = rs.getString("FUNCTION_NAME");
tmpsave = tmp;                
                /* Skip 'size' on first if new function name. */
                if (!(tmp .equals(old))){
                    if (flag == 0){
                        flag = 1;
                    }else{
//                        System.out.format("//  %s SIZE %d\n",old,count1);                        
                        System.out.format("};\n#endif\n"); // End for this function_name
                        count = 0;
                     }
                    System.out.format("\n// =====================================================================\n");
                    System.out.format("#ifdef %s\t// Include following parameters?\n",tmp);
                    System.out.format("const uint32_t paramval[] = {\n");
                    System.out.format(" %s_PARAM_SIZE,\t/* Number of param entries that follow */\n", tmp);
                    count1 = 0;                    

                    old = tmp;
                }    
                Integer code = rs.getInt("TYPE_CODE");
                String val = rs.getString("PARAM_VAL");
                Integer x = 0; 
                String y = "";
                switch(code){
                    case 1: case 2: case 3: case 4: case 5: case 6:
                        x = Integer.parseInt(val);
                        break;
                            
                    case 11:
                        Float ftmp = Float.parseFloat(val);
                        x = Float.floatToIntBits(ftmp);
                        break;
                        
                    case 17:    // CAN ID
                        y = getCANID(stmt2, val);
                        break;
                        
                    default:
                        x = 0x45454545;
                }
                count += 1;
                if (code == 17){
                     System.out.format(" 0x%s , /* %3d ",y, count);                   
                }else{                   
                    System.out.format(" 0x%08X , /* %3d ",x, count);
                }
                System.out.format("%-18s ",rs.getString("PARAM_VAL"));
                System.out.format(" %2d ",code);
                System.out.format(" %-80s*/\n",rs.getString("DESCRIPTION11"));
            }
            System.out.format("};\n#endif\n");
// =========================================================================            
// ========== Add command CAN IDs for functions ============================ 
// =========================================================================     
  //          String query3 = "SELECT * FROM FUNCTIONS ";
            
 //           String query3 = "SELECT "
//        + "FUNCTIONS.*,"
//        + "FUNCTION_CODES.* \n"
//        + "FROM FUNCTIONS \n"
//            + "JOIN FUNCTION_CODES \n"
//                + "ON FUNCTION_CODES.FUNCTION_NAME = FUNCTIONS.FUNCTION_NAME\n"
//        + "ORDER BY FUNCTIONS.CAN_UNIT_NAME";
            
        String query3 = "SELECT FUNCTIONS.*,FUNCTION_CODES.*,CANID.CANID_HEX,FUNCTIONS_TYPE.FUNCTION_TYPE_CODE \n"
            + "FROM FUNCTIONS \n"
            + "JOIN FUNCTION_CODES \n"
                +  "ON FUNCTION_CODES.FUNCTION_NAME = FUNCTIONS.FUNCTION_NAME\n"
            + "JOIN CAN_UNIT \n"
                + "ON CAN_UNIT.CAN_UNIT_NAME = FUNCTIONS.CAN_UNIT_NAME\n"
            + "JOIN CANID \n"
                + "ON CANID.CANID_NAME = CAN_UNIT.CANID_NAME\n"
            + "JOIN FUNCTIONS_TYPE \n"
                + "ON FUNCTIONS_TYPE.FUNCTION_TYPE = FUNCTION_CODES.FUNCTION_TYPE \n"   
        + "ORDER BY FUNCTIONS.CAN_UNIT_NAME";
          
            
            rs = stmt.executeQuery(query);
            count = 0;

// ========= Make counts of sizes of command CAN ID tables ===============================
            flag = 0; 
            count1 = 0;            
            tmp = "";
            old = "";
            rs = stmt.executeQuery(query3);
            System.out.format("\n// ================= COMMAND CANID TABLES ========================\n");
            while (rs.next()) {               
                tmp = rs.getString("CAN_UNIT_NAME");
                /* Skip 'size' on first time */
                if (!(tmp .equals(old))){
                    if (flag == 0){
                        flag = 1;
                    }else{
                        System.out.format("\n#define %s_CMDID_TABLE_SIZE %d",old,count1);
                        System.out.format("\t// %s\n",rs.getString("DESCRIPTION7"));
                        count1 = 0;
                    }              
                    old = tmp;
                }
                count1 += 1;    
            }

            System.out.format("\n#define %s_CMDID_TABLE_SIZE %d\n\n",old,count1);  
        
    // ========= Make table command CANID =============================== 
    
            flag = 0; 
            count = 0;  
            old = "";
            rs = stmt.executeQuery(query3);
            while (rs.next()) {               
                tmp = rs.getString("CAN_UNIT_NAME");
                /* Skip 'size' on first if new function name. */
                if (!(tmp .equals(old))){
                    if (flag == 0){
                        flag = 1;
                    }else{                        
                        System.out.format("};\n#endif\n"); // End for this unit
                        count = 0;
                     }
                    String yid = rs.getString("CANID_HEX");
                    System.out.format("\n// =====================================================================\n");
                    System.out.format("#ifdef %s\t// a #define is used to select the following\n",tmp);
                    System.out.format("const struct FUNC_CANID func_canid[] = {\n");
                    System.out.format("{   0x%s, %s_CMDID_TABLE_SIZE },\t/* {Unit CAN ID, Number of CAN IDs that follow} */\n", yid,tmp);
                    count = 0;                    

                    old = tmp;
                }    
                String val = rs.getString("CANID_CMD_NAME");
                Integer x = 0; 
                String y = getCANID(stmt2, val);
                count += 1;
                int ftc = rs.getInt("FUNCTION_TYPE_CODE");
                String cmd_ir = rs.getString("CMD_IR");
                if ("R".equals(cmd_ir))
                    ftc += 1000;
                System.out.format("{%4d,  0x%s }, /* %3d ",ftc,y, count);
                System.out.format("%-18s ",rs.getString("CAN_UNIT_NAME"));
                System.out.format("  %s\t", val);
                System.out.format(" %-30s*/\n",rs.getString("DESCRIPTION7"));
            }
            System.out.format("};\n#endif\n");        

// ========================================   

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
    private static String getCANID(Statement stmtx, String func) throws SQLException{  
        String query2 = "SELECT * FROM CANID ";
  
        ResultSet rs2;
        String tmp2;
        rs2 = stmtx.executeQuery(query2);
            
        while (rs2.next()) {               
            tmp2 = rs2.getString("CANID_NAME");
            if ((tmp2 .equals(func))){
                return rs2.getString("CANID_HEX");
            }
        }
        return "## CAN ID NOT FOUND ##";
    }               
}
