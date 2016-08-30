/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package derbytest;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.*;

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
            
            // Fill ArrayList with database data
            ArrayList<Canmsginfo> canidlist = new ArrayList<Canmsginfo>();
            genlist_Canid(stmt, canidlist);         
            
            // Test search
            String sid = "05C0000C";
            Canmsginfo cmi2 = new Canmsginfo (Long.parseLong(sid,16));
 
            int index = Collections.binarySearch(canidlist, cmi2);
            System.out.format("search index: %d %08X\n",index, Long.parseLong(sid,16));
            
            
//         int index = Collections.binarySearch(Canmsginfo, canidlist, 0xFFFFFFCC);
        }
        catch(SQLException e) {
            //TODO Fix error handling
            e.printStackTrace();
            throw e;
 
        }
        
        finally {
            if (pstmt != null) pstmt.close();
        }
        
        System.out.format("DONE\n");
    }
        // Fill an ArrayList with database data
        private static void genlist_Canid(Statement stmt, ArrayList canidlist) throws SQLException{

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
            
            //ArrayList<Canmsginfo> canidlist = new ArrayList<Canmsginfo>();

            while (rs.next()) {
                /*
                System.out.format("%-24s",rs.getString("CANID_NAME"));
                System.out.format(" 0x%-10s",             rs.getString("CANID_HEX"));
                System.out.format("// " + "%-15s: ",     rs.getString("CANID_TYPE"));
                System.out.format("%s" + "\n",         rs.getString("DESCRIPTION"));
                */
                count += 1;
                // Convert sql/db info into Canmsginfo
                Canmsginfo cmi1 = new Canmsginfo ();
                cmi1 = fillCanlist(rs);
                System.out.format("0x%08X %3d %s\t%s %s\n",cmi1.can_hex,cmi1.pay_type_code,cmi1.can_msg_fmt,cmi1.descript_canid,
                        cmi1.descript_payload);
                // Add Canmsginfo to ArrayList
                canidlist.add (new Canmsginfo(cmi1));
            }
            System.out.format("\n/* TOTAL COUNT = %d  */\n\n",count);
            
            count = 0;
            System.out.format("cmi1 size: %d\n",canidlist.size());
            Iterator<Canmsginfo> itr = canidlist.iterator();
            while(itr.hasNext()) {
                Canmsginfo x = itr.next();
                 System.out.format("ITR: %3d 0x%08X %3d %s\t%s %s\n", count,
                        x.can_hex,
                        x.pay_type_code,
                        x.can_msg_fmt,
                        x.descript_canid,
                        x.descript_payload);
                 count += 1;
            }
            System.out.format("\n/* TOTAL COUNT = %d  */\n",count);
        }
    private static Canmsginfo fillCanlist(ResultSet rs) throws SQLException{
        Canmsginfo cmi = new Canmsginfo(
                rs.getString("CANID_HEX"),
                rs.getInt   ("PAYLOAD_TYPE_CODE"),
                rs.getString("CAN_MSG_FMT"),
                rs.getString("CANID_NAME"),
                rs.getString("DESCRIPTION"),
                rs.getString("CANID_TYPE")
        );
        return cmi;
    }
}