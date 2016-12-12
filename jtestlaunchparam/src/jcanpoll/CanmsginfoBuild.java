/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcanpoll;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;


/**
 *
 * @author deh
 */
public class CanmsginfoBuild {
 
    public CanmsginfoBuild(){
    }
          
    public void genlist_Canid(Statement stmt, ArrayList canidlist) throws SQLException{
/* The following can be pasted in the Service tab to manually run the query (for testing and amazement)
SELECT 
    CANID.*,
    PAYLOAD_TYPE.PAYLOAD_TYPE_CODE,
    PAYLOAD_TYPE.DESCRIPTION
FROM CANID
JOIN PAYLOAD_TYPE 
    ON PAYLOAD_TYPE.PAYLOAD_TYPE_NAME = CANID.CAN_MSG_FMT
ORDER BY CANID.CANID_HEX;
*/      
       String query = "select CANID.*,PAYLOAD_TYPE.PAYLOAD_TYPE_CODE,PAYLOAD_TYPE.DESCRIPTION12  FROM CANID \n "
        + "JOIN PAYLOAD_TYPE \n"
               + "ON PAYLOAD_TYPE.PAYLOAD_TYPE_NAME = CANID.CAN_MSG_FMT \n"
               + "ORDER BY CANID.CANID_HEX";
        
            ResultSet rs;
            rs = stmt.executeQuery(query);            
            int count = 0;
            
            // Extract result of database query (sorted order) and store in arraylist
            while (rs.next()) {
                // Convert sql/db info into Canmsginfo
                Canmsginfo cmi1 = new Canmsginfo ();
                cmi1 = fillCanlist(rs);
                System.out.format("%3d %s\t0x%08X %3d %s\t%s %s\n", count,
                        cmi1.can_name,
                        cmi1.can_hex,
                        cmi1.pay_type_code,
                        cmi1.can_msg_fmt,
                        cmi1.descript_canid,
                        cmi1.descript_payload);
                // Add Canmsginfo to ArrayList
                canidlist.add(cmi1);
                count += 1;
            }
            System.out.format("\n/* TOTAL COUNT = %d  */\n\n",count);
            // List array and check the count (debugging)
//            count = 0;
//            System.out.format("cmi1 size: %d\n",canidlist.size());
//            Iterator<Canmsginfo> itr = canidlist.iterator();
//            while(itr.hasNext()) {
//                Canmsginfo x = itr.next();
//                 System.out.format("ITR: %3d 0x%08X %3d %s\t%s %s\n", count,
//                        x.can_hex,
//                        x.pay_type_code,
//                        x.can_msg_fmt,
//                        x.descript_canid,
//                        x.descript_payload);
//                 count += 1;
//            }
//            System.out.format("\n/* TOTAL COUNT = %d  */\n",count);
        }
    private static Canmsginfo fillCanlist(ResultSet rs) throws SQLException{
        Canmsginfo cmi = new Canmsginfo(
                rs.getString("CANID_NAME"),
                rs.getString("CANID_HEX"),
                rs.getString("CANID_TYPE"),
                rs.getString("CAN_MSG_FMT"),
                rs.getString("DESCRIPTION"),    // CANID
                rs.getInt   ("PAYLOAD_TYPE_CODE"),
                rs.getString("DESCRIPTION12")   // PAYLOAD_TYPE
        );
        return cmi;
    }
 
        
 
        
         
    
}
