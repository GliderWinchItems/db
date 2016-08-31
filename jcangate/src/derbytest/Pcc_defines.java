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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
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
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
             
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
            long l1 = 0xFFFFFF16;   // Remember sign extends
            l1 = (l1 << 32) >>> 32; // Get rid of upper bits
            Long L1 = l1;           // Needed for comparisons
            Canmsginfo cmi2 = new Canmsginfo (L1); // CAN ID w remainder nulls
            int index = Collections.binarySearch(canidlist, cmi2); // Lookup
            System.out.format("search index: %d %08X\n",index, L1); 
// ===================================================================================
            String ip;
            ip = "127.0.0.1";   // Default ip address
            //String ip = new String("10.1.1.80");
            int port = new Integer (32123); // Default port
        
            /* Deal with the arguments on the command line */
            if (args.length > 2){
                System.out.format("Only two args allowed, we saw %d\n", args.length);
                System.exit(-1);
            } 
            if (args.length == 2){
              ip = args[0];
              port = Integer.parseInt(args[1]);
            }
            Socket socket = new Socket(ip, port);
            BufferedReader in = 
            new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
            Canmsg2j can1; 
            can1 = new Canmsg2j();    // Received CAN message
            int ret;
            int count2 = 0;
            int count3 = 0;
            int count4 = 0;
            int tickct = 0;
            
            // Build array with CAN ids encountered
            ArrayList<CanDisplay> candisplay = new ArrayList<CanDisplay>();
   // ======== Endless loop ====================================================
            while (true) {
              String msg = in.readLine();         // Get a line from socket
     //         System.out.format("%s\n",msg);
              ret = can1.convert_msgtobin(msg);   // Convert ascii/hex msg to binary byte array
              if (ret != 0){ // Did the conversion pass all the checks?
                    System.out.format("Input conversion error: %d\n", ret); // No show the error code
                    continue;
                    }      
if(1 != 1){              
              if (can1.id == 0xE1800000){ 
        //            System.out.format("%08X\n",can1.id);
                    Canmsginfo cmi3 = new Canmsginfo (Long.parseLong("E1800000",16));
                    int index1 = Collections.binarySearch(canidlist, cmi3);
                    Canmsginfo cmi4 = new Canmsginfo (canidlist.get(index1));
                    System.out.format("idx: %3d %4d %08X %s\n",index1, count2++,
                            cmi4.can_hex,
                            cmi4.can_name);
              }
}
           // +++++++++++++++++++++++++++++++++++
              // Convert 'int' to 'Long' (handling sign extension problem)
              long ltmp = can1.id;  
              ltmp = (ltmp << 32) >>>32;  
              Long Ltmp = ltmp;
              
              /* Look up CAN ID in display table */
              CanDisplay cd2 = new CanDisplay(Ltmp);
              int index2 = Collections.binarySearch(candisplay, cd2);
              if (index2 < 0){  // Check if CAN ID was found
                   /* Look up database info for this new CAN ID */
                  Canmsginfo cmi5 = new Canmsginfo (Ltmp);
                  int index5 = Collections.binarySearch(canidlist, cmi5);
                  if (index5 < 0){ // When not found add a dummy to list
                      canidlist.add ((-index5-1),new Canmsginfo(Ltmp,"NOT FOUND"));
                  } 
                  /* Add new CAN ID with database info to display list */
                  Canmsginfo cmi6 = new Canmsginfo (canidlist.get(index5));
                  CanDisplay cd3 = new CanDisplay(Ltmp, cmi6);
                  candisplay.add ((-index2-1), cd3); // Add new CAN ID to list
                  index2 = Collections.binarySearch(candisplay, cd2);
                  count3 += 1;  // For test purposes
              }
              /* Add to count */
              cd2 = candisplay.get(index2);
              cd2.count += 1;
              candisplay.set(index2, cd2);
              /* Display update timing based on CAN time msg */
              if (can1.id == 0x00200000){ // Use time msgs to time display
                  count4 += 1; // 64 per second
                  if (count4 >= 64){
                    count4 = 0; // Use to count number of entries
                    Iterator<CanDisplay> itr = candisplay.iterator(); // List list
                    while(itr.hasNext()) {
                        CanDisplay x = itr.next();
                        System.out.format("CD: %3d 0x%08X %4d %s\t%s\n",count4++,
                                x.can_hex,
                                x.count,
                                x.cmi.can_name,
                                x.cmi.descript_canid);
                        x.count = 0; // Reset count for next display interval
                  }
                  tickct += 1;  // Running time tick ct
                  System.out.format("SECS: %5d NUMBER IN LISTs: %d  %d\n",
                          tickct,count4, count3);
                  count4 = 0; // Timing count reset
                }
              }
            }          
// =============================================================================
        }
        catch(SQLException e) {
            //TODO Fix error handling
            e.printStackTrace();
            throw e;
 
        }
        
        finally {
            if (pstmt != null) pstmt.close();
        }
        
//        System.out.format("DONE\n");
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