/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package derbytest;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 *
 * @author deh
 */
public class DbPayload {
    
    public DbPayload(){
        
    }
    public String convert(CanDisplay cd){
        String s = "";
        Canmsg2j cmsg = new Canmsg2j();
        cmsg.pb = cd.pb;

        int ptc = cd.cmi.pay_type_code; // Numeric payload type 
        switch (ptc){
            case 5: // U8_U32: 
                s  = String.format("%d: %02X: ",cd.dlc,cd.pb[6]  );
                    for (int i = 7 ; i < cd.dlc+6; i++){
                    s += String.format("%02x ",cd.pb[i]);
                }
                cmsg.in_1int_n(1);
                s += String.format("%8d",cmsg.p0);
                break;

            case 24: // Unix time U8_U32
// Display hex for debugging                
//                s  = String.format("%d: ",cd.dlc );
//                    for (int i = 6 ; i < cd.dlc+6; i++){
//                    s += String.format("%02x ",cd.pb[i]);
//                }
                cmsg.in_1int_n(1);  // Convert bytes to int
                java.util.Date date= new java.util.Date(); // Get current date/time
                SimpleDateFormat ss = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
                s += ss.format(date); // Add date time to display string
                break;
                
            default: // Type not found
                s ="\tcmi.pay_type_codeTYPE_NOT_FOUND";
                break;
        }       
        return s;
    }    
}
